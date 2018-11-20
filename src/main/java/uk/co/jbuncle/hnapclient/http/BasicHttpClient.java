/*
 * James Buncle 2017
 */
package uk.co.jbuncle.hnapclient.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import org.apache.commons.io.IOUtils;

/**
 * @author James Buncle <jbuncle@hotmail.com>
 */
public class BasicHttpClient implements HttpClientI {

    @Override
    public String get(
            final String url,
            final Map<String, String> headers
    ) throws HttpException {
        try {
            String charset = "UTF-8";

            URLConnection urlConnection = new URL(url).openConnection();
            urlConnection.setUseCaches(false);
            urlConnection.setDoOutput(true); // Triggers POST.

            for (Map.Entry<String, String> entry : headers.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                urlConnection.setRequestProperty(key, value);
            }
            urlConnection.setRequestProperty("accept-charset", charset);

            try (InputStream result = urlConnection.getInputStream()) {
                return IOUtils.toString(result);
            }
        }
        catch (MalformedURLException ex) {
            throw new HttpException(ex);
        }
        catch (IOException ex) {
            throw new HttpException(ex);
        }
    }

    @Override
    public String post(
            final String url,
            final Map<String, String> headers,
            final String body
    ) throws HttpException {
        try {

            final HttpURLConnection conn = (HttpURLConnection) new URL(url)
                    .openConnection();

            conn.setRequestMethod("POST");
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                conn.setRequestProperty(key, value);
            }
            conn.setRequestProperty("Content-Length", String.valueOf(body.length()));
            conn.setDoOutput(true);

            try (OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream())) {
                wr.write(body);
                wr.flush();
            }

            try (BufferedReader rd = new BufferedReader(
                    new java.io.InputStreamReader(conn.getInputStream()))) {
                // Read the response
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }
                return sb.toString();
            }
        }
        catch (MalformedURLException ex) {
            throw new HttpException(ex);
        }
        catch (IOException ex) {
            throw new HttpException(ex);
        }
    }

}
