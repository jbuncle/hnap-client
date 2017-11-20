/*
 * James Buncle 2017
 */
package uk.co.jbuncle.hnapclient;

import java.util.Date;
import org.apache.commons.codec.digest.HmacUtils;

/**
 *
 * @author James Buncle <jbuncle@hotmail.com>
 */
public class HnapSession {

    private final String challenge;
    private final String publicKey;
    private final String cookie;
    private final String password;

    public HnapSession(
            final String password,
            final String challenge,
            final String publicKey,
            final String cookie
    ) {
        this.challenge = challenge;
        this.publicKey = publicKey;
        this.cookie = cookie;
        this.password = password;
    }

    public String getCookie() {
        return cookie;
    }

    private String getPrivateKey() {
        return this.hexHmacMd5(this.publicKey + this.password, this.challenge).toUpperCase();
    }

    private String hexHmacMd5(final String key, final String data) {
        return HmacUtils.hmacMd5Hex(key, data);
    }

    public String getLoginPassword() {
        return this.hexHmacMd5(this.getPrivateKey(), this.challenge);
    }

    public String getAuth(final String soapAction) {
        final Date currentTime = new Date();
        final int timestamp = Math.round(currentTime.getTime() / 1000);
        final String auth = this.hexHmacMd5(this.getPrivateKey(), timestamp + soapAction);
        return auth.toUpperCase() + ' ' + timestamp;
    }
}
