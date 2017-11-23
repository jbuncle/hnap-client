/*
 * James Buncle 2017
 */
package uk.co.jbuncle.hnapclient;

import java.net.URL;
import uk.co.jbuncle.hnapclient.http.HttpClient;
import uk.co.jbuncle.hnapclient.http.HttpClientI;
import uk.co.jbuncle.hnapclient.interfaces.HnapClientI;
import uk.co.jbuncle.hnapclient.session.HnapSessionBuilder;
import uk.co.jbuncle.hnapclient.session.TimestampProvider;
import uk.co.jbuncle.hnapclient.session.TimestampProviderI;
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

        final HttpClientI httpClient = new HttpClient();
        final BasicSoapClient soapClient = new BasicSoapClient(httpClient);
        final TimestampProviderI timestampProvider = new TimestampProvider();
        final HnapSessionBuilder hnapSessionBuilder = new HnapSessionBuilder(timestampProvider, password);
        return new HnapClient(soapClient, hnapSessionBuilder, url, username);
    }
}
