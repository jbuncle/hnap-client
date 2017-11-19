/*
 * James Buncle 2017
 */
package uk.co.jbuncle.hnapclient.exceptions;

/**
 *
 * @author James Buncle <jbuncle@hotmail.com>
 */
public class HnapClientException extends Exception {

    public HnapClientException() {
    }

    public HnapClientException(String message) {
        super(message);
    }

    public HnapClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public HnapClientException(Throwable cause) {
        super(cause);
    }

}
