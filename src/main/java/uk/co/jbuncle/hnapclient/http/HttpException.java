/*
 * James Buncle 2017
 */
package uk.co.jbuncle.hnapclient.http;

/**
 * @author James Buncle <jbuncle@hotmail.com>
 */
public class HttpException extends Exception {

    public HttpException() {
    }

    public HttpException(final String message) {
        super(message);
    }

    public HttpException(
            final String message, 
            final Throwable cause
    ) {
        super(message, cause);
    }

    public HttpException(final Throwable cause) {
        super(cause);
    }

    public HttpException(
            final String message,
            final Throwable cause,
            final boolean enableSuppression,
            final boolean writableStackTrace
    ) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
