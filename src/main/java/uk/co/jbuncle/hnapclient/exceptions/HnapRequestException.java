/*
 * James Buncle 2017
 */
package uk.co.jbuncle.hnapclient.exceptions;

/**
 *
 * @author James Buncle <jbuncle@hotmail.com>
 */
public class HnapRequestException extends HnapClientException {

    private final String requestBody;
    private final String responseBody;

    public HnapRequestException(String requestBody, String responseBody) {
        this.requestBody = requestBody;
        this.responseBody = responseBody;
    }

    public HnapRequestException(String message, String requestBody, String responseBody) {
        super(message);
        this.requestBody = requestBody;
        this.responseBody = responseBody;
    }

    public HnapRequestException(String message, Throwable cause, String requestBody, String responseBody) {
        super(message, cause);
        this.requestBody = requestBody;
        this.responseBody = responseBody;
    }

    public HnapRequestException(Throwable cause, String requestBody, String responseBody) {
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
