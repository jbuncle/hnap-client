/*
 * James Buncle 2017
 */
package uk.co.jbuncle.hnapclient.util.xml;

/**
 * @author James Buncle <jbuncle@hotmail.com>
 */
public class XMLException extends Exception {

    public XMLException() {
    }

    public XMLException(String message) {
        super(message);
    }

    public XMLException(String message, Throwable cause) {
        super(message, cause);
    }

    public XMLException(Throwable cause) {
        super(cause);
    }

}
