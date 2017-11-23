/*
 * James Buncle 2017
 */
package uk.co.jbuncle.hnapclient.soap;

/**
 *
 * @author James Buncle <jbuncle@hotmail.com>
 */
public class SoapException extends Exception {

    public SoapException() {
    }

    public SoapException(String message) {
        super(message);
    }

    public SoapException(String message, Throwable cause) {
        super(message, cause);
    }

    public SoapException(Throwable cause) {
        super(cause);
    }

    public SoapException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
