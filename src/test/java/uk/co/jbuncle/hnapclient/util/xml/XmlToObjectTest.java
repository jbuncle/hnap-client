/*
 * James Buncle 2017
 */
package uk.co.jbuncle.hnapclient.util.xml;

import java.util.List;
import java.util.Map;
import junit.framework.TestCase;

/**
 *
 * @author James Buncle <jbuncle@hotmail.com>
 */
public class XmlToObjectTest extends TestCase {

    /**
     * Test of fromXml method, of class XmlToObject.
     */
    public void testFromXmlSimple() throws Exception {
        System.out.println("fromXml");
        String xmlString = "<test><hello>world</hello></test>";
        Map<String, Object> result = XmlToObject.fromXml(xmlString);
        assertEquals(1, result.size());
        assertEquals("world", result.get("hello"));
    }

    /**
     * Test of fromXml method, of class XmlToObject.
     */
    public void testFromXmlList() throws Exception {
        System.out.println("fromXml");
        String xmlString = "<test><hello>world1</hello><hello>world2</hello><hello>world3</hello></test>";
        Map<String, Object> result = XmlToObject.fromXml(xmlString);
        assertEquals(1, result.size());
        assertTrue(result.get("hello") instanceof List);
        
        List list = (List) result.get("hello");
        assertEquals(3, list.size());
        assertEquals("world1", list.get(0));
        assertEquals("world2", list.get(1));
        assertEquals("world3", list.get(2));
    }

}
