/*
 * James Buncle 2017
 */
package uk.co.jbuncle.hnapclient;

import uk.co.jbuncle.hnapclient.interfaces.TimestampProviderI;
import java.util.Date;

/**
 *
 * @author James Buncle <jbuncle@hotmail.com>
 */
class TimestampProvider implements TimestampProviderI {

    @Override
    public int getTimestampSeconds() {
        final Date currentTime = new Date();
        return Math.round(currentTime.getTime() / 1000);
    }
}
