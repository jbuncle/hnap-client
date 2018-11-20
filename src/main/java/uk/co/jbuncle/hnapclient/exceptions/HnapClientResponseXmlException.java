/*
 * James Buncle 2018
 */
package uk.co.jbuncle.hnapclient.exceptions;

import uk.co.jbuncle.hnapclient.util.xml.XMLException;

/**
 * @author James Buncle <jbuncle@hotmail.com>
 */
public class HnapClientResponseXmlException
        extends HnapClientResponseException {

    private final String body;

    public HnapClientResponseXmlException(String body, XMLException cause) {
        super(cause);
        this.body = body;
    }

    public String getBody() {
        return body;
    }

}
