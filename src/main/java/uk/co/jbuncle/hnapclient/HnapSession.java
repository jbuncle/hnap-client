/*
 * James Buncle 2017
 */
package uk.co.jbuncle.hnapclient;

import uk.co.jbuncle.hnapclient.interfaces.TimestampProviderI;
import uk.co.jbuncle.hnapclient.interfaces.HnapSessionI;
import org.apache.commons.codec.digest.HmacUtils;

/**
 *
 * @author James Buncle <jbuncle@hotmail.com>
 */
class HnapSession implements HnapSessionI {

    private final TimestampProviderI timestampProvider;
    private final String challenge;
    private final String publicKey;
    private final String cookie;
    private final String password;

    public HnapSession(
            final TimestampProviderI timestampProviderI,
            final String password,
            final String challenge,
            final String publicKey,
            final String cookie
    ) {
        this.timestampProvider = timestampProviderI;
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("password must be set");
        }
        if (challenge == null || challenge.isEmpty()) {
            throw new IllegalArgumentException("challenge must be set");
        }
        if (publicKey == null || publicKey.isEmpty()) {
            throw new IllegalArgumentException("publicKey must be set");
        }
        if (cookie == null || cookie.isEmpty()) {
            throw new IllegalArgumentException("cookie must be set");
        }
        this.challenge = challenge;
        this.publicKey = publicKey;
        this.cookie = cookie;
        this.password = password;
    }

    @Override
    public String getCookie() {
        return cookie;
    }

    private String getPrivateKey() {
        return this.hexHmacMd5(this.publicKey + this.password, this.challenge).toUpperCase();
    }

    private String hexHmacMd5(final String key, final String data) {
        return HmacUtils.hmacMd5Hex(key, data);
    }

    @Override
    public String getLoginPassword() {
        return this.hexHmacMd5(this.getPrivateKey(), this.challenge);
    }

    @Override
    public String getAuth(final String soapAction) {
        final int timestamp = this.timestampProvider.getTimestampSeconds();
        final String auth = this.hexHmacMd5(this.getPrivateKey(), timestamp + soapAction);
        return auth.toUpperCase() + ' ' + timestamp;
    }

}
