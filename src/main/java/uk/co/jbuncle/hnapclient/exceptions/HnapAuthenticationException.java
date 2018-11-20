/*
 * James Buncle 2017
 */
package uk.co.jbuncle.hnapclient.exceptions;

/**
 * @author James Buncle <jbuncle@hotmail.com>
 */
public class HnapAuthenticationException extends HnapClientException {

    public HnapAuthenticationException() {
        super();
    }

    public HnapAuthenticationException(String message) {
        super(message);
    }

    public HnapAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public HnapAuthenticationException(Throwable cause) {
        super(cause);
    }

}
