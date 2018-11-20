/*
 * James Buncle 2017
 */
package uk.co.jbuncle.hnapclient.session;

import java.util.Map;
import org.w3c.dom.Document;
import uk.co.jbuncle.hnapclient.exceptions.HnapClientException;
import uk.co.jbuncle.hnapclient.exceptions.HnapClientResponseXmlException;
import uk.co.jbuncle.hnapclient.util.xml.XMLException;
import uk.co.jbuncle.hnapclient.util.xml.XMLUtility;
import uk.co.jbuncle.hnapclient.util.xml.XmlToObject;

/**
 * @author James Buncle <jbuncle@hotmail.com>
 */
public class HnapSessionBuilder {

    private static final String LOGINRESPONSE_CHALLENGE = "Challenge";

    private static final String LOGINRESPONSE_PUBLICKEY = "PublicKey";

    private static final String LOGINRESPONSE_COOKIE = "Cookie";

    private final TimestampProviderI timestampProvider;

    private final String password;

    public HnapSessionBuilder(TimestampProviderI timestampProvider, String password) {
        this.timestampProvider = timestampProvider;
        this.password = password;
    }

    public HnapSessionI parseSession(final Map<String, Object> properties) {
        final String challenge = (String) properties.get(LOGINRESPONSE_CHALLENGE);
        final String publicKey = (String) properties.get(LOGINRESPONSE_PUBLICKEY);
        final String cookie = (String) properties.get(LOGINRESPONSE_COOKIE);

        return new HnapSession(this.timestampProvider, this.password, challenge,
                publicKey, cookie);
    }

}
