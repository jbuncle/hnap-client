/*
 * James Buncle 2017
 */
package uk.co.jbuncle.hnapclient.soap;

import java.net.URL;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import uk.co.jbuncle.hnapclient.http.HttpClientI;
import uk.co.jbuncle.hnapclient.http.HttpException;
import uk.co.jbuncle.hnapclient.util.xml.XMLException;
import uk.co.jbuncle.hnapclient.util.xml.XMLUtility;

/**
 * @author James Buncle <jbuncle@hotmail.com>
 */
public class BasicSoapClient {

    private final HttpClientI httpClient;

    public BasicSoapClient(final HttpClientI httpClient) {
        this.httpClient = httpClient;
    }

    private String soapBody(final String payload) {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<soap:Envelope "
                + "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
                + "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + "<soap:Body>" + payload + "</soap:Body></soap:Envelope>";
    }

    public String soapPost(
            final URL url,
            final String soapAction,
            final Map<String, String> headers,
            final String body
    ) throws SoapException {
        final String soapBody = this.soapBody(body);
        headers.put("Content-Type", "text/xml; charset=utf-8");
        headers.put("SOAPAction", '"' + soapAction + '"');

        final String urlString = url.toString();

        final String result;
        try {
            result = this.httpClient.post(urlString, headers, soapBody);
        }
        catch (HttpException ex) {
            throw new SoapException("Request to '" + urlString + "' failed", ex);
        }
        
        if (result.isEmpty()) {
            throw new SoapException("Recieved empty response body from '" + urlString + "'");
        }

        try {
            return getSoapBody(result);
        }
        catch (XMLException ex) {
            throw new SoapException("Response body from '" + urlString + "' is not valid XML'" + result + "'", ex);
        }
    }

    public String soapGet(final URL url, final Map<String, String> headers)
            throws SoapException {
        try {
            headers.put("Content-Type", "text/xml; charset=utf-8");
            String result = this.httpClient.get(url.toString(), headers);
            return getSoapBody(result);
        }
        catch (XMLException | HttpException ex) {
            throw new SoapException(ex);
        }
    }

    private String getSoapBody(final String soap) throws XMLException {
        final Document xml = XMLUtility.loadXML(soap);
        final Node body = xml.getElementsByTagName("soap:Body").item(0);
        return XMLUtility.innerXml(body);
    }

}
