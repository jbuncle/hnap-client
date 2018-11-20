/*
 * James Buncle 2017
 */
package uk.co.jbuncle.hnapclient;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.w3c.dom.Document;
import uk.co.jbuncle.hnapclient.exceptions.HnapAuthenticationException;
import uk.co.jbuncle.hnapclient.exceptions.HnapClientException;
import uk.co.jbuncle.hnapclient.exceptions.HnapClientResponseXmlException;
import uk.co.jbuncle.hnapclient.exceptions.HnapRequestException;
import uk.co.jbuncle.hnapclient.interfaces.HnapClientI;
import uk.co.jbuncle.hnapclient.response.DeviceSettingsI;
import uk.co.jbuncle.hnapclient.response.DeviceSettingsParser;
import uk.co.jbuncle.hnapclient.session.HnapSessionBuilder;
import uk.co.jbuncle.hnapclient.session.HnapSessionI;
import uk.co.jbuncle.hnapclient.soap.BasicSoapClient;
import uk.co.jbuncle.hnapclient.soap.SoapException;
import uk.co.jbuncle.hnapclient.util.xml.XMLException;
import uk.co.jbuncle.hnapclient.util.xml.XMLUtility;
import uk.co.jbuncle.hnapclient.util.xml.XmlToObject;

/**
 * @author James Buncle <jbuncle@hotmail.com>
 */
class HnapClient implements HnapClientI {

    private static final String LOGINRESULT_FAILED = "failed";
    private static final String LOGINRESULT_SUCCESS = "success";
    private static final String HNAP1_XMLNS = "http://purenetworks.com/HNAP1/";
    private static final String HNAP_LOGIN_METHOD = "Login";
    private static final String LOGINRESULT = "LoginResult";
    private static final String LOGINREQUEST_ACTION = "Action";
    private static final String LOGINREQUEST_USERNAME = "Username";
    private static final String LOGINREQUEST_LOGINPASSWORD = "LoginPassword";
    private static final String LOGINREQUEST_CAPTCHA = "Captcha";
    private final BasicSoapClient soapClient;
    private final URL url;
    private final String username;
    private final HnapSessionBuilder hnapSessionBuilder;

    public HnapClient(
            final BasicSoapClient soapClient,
            final HnapSessionBuilder hnapSessionBuilder,
            final URL url,
            final String username
    ) {
        this.soapClient = soapClient;
        this.hnapSessionBuilder = hnapSessionBuilder;
        this.url = url;
        this.username = username;
    }

    @Override
    public DeviceSettingsI discover() throws HnapClientException {
        final Map<String, String> headers = new HashMap<>();
        try {
            final String response = this.soapClient.soapGet(url, headers);

            try {
                final Map<String, Object> responseProperties = XmlToObject.fromXml(response);

                return DeviceSettingsParser.createFromResponse(responseProperties);
            }
            catch (XMLException ex) {
                throw new HnapClientResponseXmlException(response, ex);
            }
        }
        catch (MalformedURLException | SoapException ex) {
            throw new HnapClientException(ex);
        }
    }

    @Override
    public HnapSessionI login() throws HnapClientException {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "text/xml; charset=utf-8");
        headers.put("SOAPAction", "\"" + HnapClient.HNAP1_XMLNS + HnapClient.HNAP_LOGIN_METHOD + "\"");

        final String loginInitProperties = XmlToObject.toXml(this.loginRequest());
        final Map<String, Object> response = this.soapRequest(url, headers, HnapClient.HNAP_LOGIN_METHOD, loginInitProperties);

        final HnapSessionI session = this.hnapSessionBuilder.parseSession(response);

        final Map<String, Object> loginProperties = this.loginParameters(session);
        final Map<String, Object> responseProperties = this.request(session, HnapClient.HNAP_LOGIN_METHOD, loginProperties);

        final String loginResult = (String) responseProperties.get(HnapClient.LOGINRESULT);

        switch (loginResult) {
            case HnapClient.LOGINRESULT_SUCCESS:
                return session;
            case HnapClient.LOGINRESULT_FAILED:
                throw new HnapAuthenticationException("Login failed");
            default:
                throw new HnapAuthenticationException("Unexpected login result " + loginResult);
        }
    }

    @Override
    public Map<String, Object> request(
            final HnapSessionI hnapSession,
            final String method,
            final Map<String, Object> body
    ) throws HnapClientException {

        final String bodyXml = XmlToObject.toXml(body);

        return this.hnapRequest(hnapSession, method, bodyXml);
    }

    /**
     * Wrap the given body string in a HNAP SOAP Action XML wrapper.
     *
     * @param action The HNAP Action
     * @param body The body to wrap
     * @return The wrapped string.
     */
    private String addMethodWrapper(
            final String action,
            final String body
    ) {
        return "<" + action + " xmlns=\"" + HnapClient.HNAP1_XMLNS + "\">"
                + body
                + "</" + action + ">";
    }

    private final Map<String, Object> hnapRequest(
            final HnapSessionI hnapSession,
            final String method,
            final String body
    ) throws HnapClientException {
        final Map<String, String> headers = new HashMap<>();
        headers.put("HNAP_AUTH", hnapSession.getAuth('"' + HnapClient.HNAP1_XMLNS + method + '"'));
        headers.put("Cookie", "uid=" + hnapSession.getCookie());
        return this.soapRequest(url, headers, method, body);
    }

    private Map<String, Object> loginRequest() {
        final Map<String, Object> properties = new LinkedHashMap<>();
        properties.put(LOGINREQUEST_ACTION, "request");
        properties.put(LOGINREQUEST_USERNAME, this.username);
        properties.put(LOGINREQUEST_LOGINPASSWORD, "");
        properties.put(LOGINREQUEST_CAPTCHA, "");
        return properties;
    }

    private Map<String, Object> loginParameters(
            final HnapSessionI hnapSession
    ) {
        final String loginPassword = hnapSession.getLoginPassword();
        final Map<String, Object> loginParams = new LinkedHashMap<>();
        loginParams.put("Action", "login");
        loginParams.put("Username", this.username);
        loginParams.put("LoginPassword", loginPassword.toUpperCase());
        loginParams.put("Captcha", "");
        return loginParams;
    }

    private Map<String, Object> soapRequest(
            final URL url,
            final Map<String, String> headers,
            final String method,
            final String requestBody
    ) throws HnapClientException {

        final String wrappedBody = this.addMethodWrapper(method, requestBody);
        try {
            // Check request is valid XML
            XMLUtility.loadXML(wrappedBody);
        }
        catch (XMLException ex) {
            throw new HnapRequestException("Request contains invalid XML", ex, requestBody);
        }

        final String responseBody = performRequest(url, method, headers, wrappedBody);
        try {
            final Document doc = XMLUtility.loadXML(responseBody);
            return XmlToObject.fromXml(doc);
        }
        catch (XMLException ex) {
            throw new HnapClientResponseXmlException(responseBody, ex);
        }
    }

    private String performRequest(
            final URL url,
            final String method,
            final Map<String, String> headers,
            final String wrappedBody
    ) throws HnapClientException {
        try {
            return this.soapClient.soapPost(url, HnapClient.HNAP1_XMLNS + method, headers, wrappedBody);
        }
        catch (SoapException ex) {
            throw new HnapClientException(ex);
        }
    }
}
