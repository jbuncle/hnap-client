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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 *
 * @author James Buncle <jbuncle@hotmail.com>
 */
public class HttpClient {

    public String post(final String url, final Map<String, String> headers, final String body)
            throws UnsupportedEncodingException, IOException, UnsupportedOperationException {
        final HttpPost post = new HttpPost(url);
        headers.entrySet().forEach((Map.Entry<String, String> header) -> {
            post.addHeader(header.getKey(), header.getValue());
        });
        StringEntity entity = new StringEntity(body);
        post.setEntity(entity);
        org.apache.http.client.HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(post);
        if (response.getStatusLine().getStatusCode() != 200) {
            throw new HttpResponseException(response.getStatusLine().getStatusCode(), response.getStatusLine().getReasonPhrase());
        }
        return IOUtils.toString(response.getEntity().getContent());
    }
}
