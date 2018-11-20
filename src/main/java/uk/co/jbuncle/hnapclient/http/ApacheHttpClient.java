/*
 * James Buncle 2017
 */
package uk.co.jbuncle.hnapclient.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * @author James Buncle <jbuncle@hotmail.com>
 */
public class ApacheHttpClient implements HttpClientI {

    @Override
    public String post(
            final String url,
            final Map<String, String> headers,
            final String body
    ) throws HttpException {
        try {
            final HttpPost post = new HttpPost(url);

            final StringEntity entity = new StringEntity(body);
            post.setEntity(entity);

            return performRequest(headers, post);
        }
        catch (IOException | UnsupportedOperationException ex) {
            throw new HttpException(ex);
        }
    }

    @Override
    public String get(
            final String url,
            final Map<String, String> headers
    ) throws HttpException {
        final HttpGet get = new HttpGet(url);

        return performRequest(headers, get);
    }

    private String performRequest(
            final Map<String, String> headers,
            final HttpRequestBase httpRequestBase
    ) throws HttpException {
        try {
            addHeaders(headers, httpRequestBase);
            final HttpResponse httpResponse = getResponse(httpRequestBase);
            checkResponseCode(httpResponse);
            return getResponseBody(httpResponse);
        }
        catch (UnsupportedOperationException | IOException ex) {
            throw new HttpException(ex);
        }
    }

    private static String getResponseBody(
            final HttpResponse response
    ) throws UnsupportedOperationException, IOException {
        return IOUtils.toString(response.getEntity().getContent());
    }

    private void checkResponseCode(
            final HttpResponse httpResponse
    ) throws HttpResponseException {
        if (httpResponse.getStatusLine().getStatusCode() != 200) {
            throw new HttpResponseException(httpResponse.getStatusLine().getStatusCode(),
                    httpResponse.getStatusLine().getReasonPhrase());
        }
    }

    private HttpResponse getResponse(
            final HttpRequestBase httpRequestBase
    ) throws IOException {
        org.apache.http.client.HttpClient client = new DefaultHttpClient();
        final HttpResponse response = client.execute(httpRequestBase);
        return response;
    }

    private void addHeaders(
            final Map<String, String> headers,
            final HttpRequestBase httpRequestBase
    ) {
        headers.entrySet().forEach((Map.Entry<String, String> header) -> {
            httpRequestBase.addHeader(header.getKey(), header.getValue());
        });
    }

}
