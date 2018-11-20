/*
 * James Buncle 2017
 */
package uk.co.jbuncle.hnapclient.exceptions;

/**
 * @author James Buncle <jbuncle@hotmail.com>
 */
public class HnapRequestException extends HnapClientException {

    private final String requestBody;
    private final String responseBody;

    public HnapRequestException(
            final String requestBody,
            final String responseBody
    ) {
        this.requestBody = requestBody;
        this.responseBody = responseBody;
    }

    public HnapRequestException(
            final String message,
            final String requestBody,
            final String responseBody
    ) {
        super(message);
        this.requestBody = requestBody;
        this.responseBody = responseBody;
    }

    public HnapRequestException(
            final String message,
            final Throwable cause,
            final String requestBody,
            final String responseBody
    ) {
        super(message, cause);
        this.requestBody = requestBody;
        this.responseBody = responseBody;
    }

    public HnapRequestException(
            final Throwable cause,
            final String requestBody,
            final String responseBody
    ) {
        super(cause);
        this.requestBody = requestBody;
        this.responseBody = responseBody;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public String getResponseBody() {
        return responseBody;
    }

}
