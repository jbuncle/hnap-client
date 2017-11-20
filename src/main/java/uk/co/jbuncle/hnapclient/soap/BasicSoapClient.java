/*
 * James Buncle 2017
 */
package uk.co.jbuncle.hnapclient.soap;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import uk.co.jbuncle.hnapclient.exceptions.HnapClientException;
import uk.co.jbuncle.hnapclient.http.HttpClient;
import uk.co.jbuncle.hnapclient.util.xml.XMLException;
import uk.co.jbuncle.hnapclient.util.xml.XMLUtility;

/**
 *
 * @author James Buncle <jbuncle@hotmail.com>
 */
public class BasicSoapClient {

    private final HttpClient httpClient;

    public BasicSoapClient(final HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    private String soapBody(final String payload) {
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                + "<soap:Envelope "
                + "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" "
                + "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                + "<soap:Body>"
                + payload
                + "</soap:Body></soap:Envelope>";
    }

    public String soapRequest(
            final URL url,
            final String soapAction,
            final Map<String, String> headers,
            final String body
    ) throws HnapClientException {
        try {
            final String soapBody = this.soapBody(body);
            headers.put("Content-Type", "text/xml; charset=utf-8");
            headers.put("SOAPAction", '"' + soapAction + '"');
            String result = this.httpClient.post(url.toString(), headers, soapBody);
            return getSoapBody(result);
        }
        catch (XMLException | IOException | UnsupportedOperationException ex) {
            throw new HnapClientException(ex);
        }
    }

    private String getSoapBody(final String soap) throws XMLException {
        final Document xml = XMLUtility.loadXML(soap);
        final Node body = xml.getElementsByTagName("soap:Body").item(0);
        return XMLUtility.innerXml(body);
    }

}
