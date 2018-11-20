/*
 * James Buncle 2017
 */
package uk.co.jbuncle.hnapclient.interfaces;

import java.util.Map;
import uk.co.jbuncle.hnapclient.exceptions.HnapClientException;
import uk.co.jbuncle.hnapclient.response.DeviceSettingsI;
import uk.co.jbuncle.hnapclient.session.HnapSessionI;

public interface HnapClientI {

    DeviceSettingsI discover() throws HnapClientException;

    HnapSessionI login() throws HnapClientException;

    Map<String, Object> request(
            final HnapSessionI hnapSession,
            final String method,
            final Map<String, Object> body
    ) throws HnapClientException;

}
