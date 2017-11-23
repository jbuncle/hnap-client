/*
 * James Buncle 2017
 */
package uk.co.jbuncle.hnapclient;

import java.net.URL;
import uk.co.jbuncle.hnapclient.http.HttpClient;
import uk.co.jbuncle.hnapclient.interfaces.HnapClientI;
import uk.co.jbuncle.hnapclient.soap.BasicSoapClient;

/**
 *
 * @author James Buncle <jbuncle@hotmail.com>
 */
public class ClientBuilder {

    public HnapClientI createHnapClient(
            final URL url,
            final String username,
            final String password) {

        final HttpClient httpClient = new HttpClient();
        final BasicSoapClient soapClient = new BasicSoapClient(httpClient);
        return new HnapClient(soapClient, url, username, password);
    }
}
