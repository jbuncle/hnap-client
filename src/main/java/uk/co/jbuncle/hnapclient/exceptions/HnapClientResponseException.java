/*
 * James Buncle 2017
 */
package uk.co.jbuncle.hnapclient.exceptions;

/**
 * Exception representing an error with a HNAP Response.
 *
 * @author James Buncle <jbuncle@hotmail.com>
 */
public class HnapClientResponseException
        extends HnapClientException {

    public HnapClientResponseException() {
    }

    public HnapClientResponseException(String message) {
        super(message);
    }

    public HnapClientResponseException(String message, Throwable cause) {
        super(message, cause);
    }

    public HnapClientResponseException(Throwable cause) {
        super(cause);
    }

}
