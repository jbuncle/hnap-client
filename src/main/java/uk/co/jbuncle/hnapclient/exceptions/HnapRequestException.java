/*
 * James Buncle 2017
 */
package uk.co.jbuncle.hnapclient.exceptions;

/**
 * @author James Buncle <jbuncle@hotmail.com>
 */
public class HnapRequestException extends HnapClientException {

    private final String requestBody;

    public HnapRequestException(
            final String requestBody
    ) {
        this.requestBody = requestBody;
    }

    public HnapRequestException(
            final String message,
            final String requestBody
    ) {
        super(message);
        this.requestBody = requestBody;
    }

    public HnapRequestException(
            final String message,
            final Throwable cause,
            final String requestBody
    ) {
        super(message, cause);
        this.requestBody = requestBody;
    }

    public HnapRequestException(
            final Throwable cause,
            final String requestBody,
            final String responseBody
    ) {
        super(cause);
        this.requestBody = requestBody;
    }

    public String getRequestBody() {
        return requestBody;
    }

}
