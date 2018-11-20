/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.jbuncle.hnapclient.http;

import java.util.Map;

/**
 * @author James Buncle <jbuncle@hotmail.com>
 */
public interface HttpClientI {

    String get(final String url, final Map<String, String> headers) throws HttpException;

    String post(
            final String url,
            final Map<String, String> headers,
            final String body
    ) throws HttpException;

}
