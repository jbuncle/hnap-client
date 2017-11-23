/*
 * James Buncle 2017
 */
package uk.co.jbuncle.hnapclient.http;

/**
 *
 * @author James Buncle <jbuncle@hotmail.com>
 */
public class HttpException extends Exception {

    public HttpException() {
    }

    public HttpException(String message) {
        super(message);
    }

    public HttpException(String message, Throwable cause) {
        super(message, cause);
    }

    public HttpException(Throwable cause) {
        super(cause);
    }

    public HttpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    
}
