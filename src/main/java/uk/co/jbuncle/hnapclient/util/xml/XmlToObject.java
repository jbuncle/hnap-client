/*
 * James Buncle 2017
 */
package uk.co.jbuncle.hnapclient.util.xml;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author James Buncle <jbuncle@hotmail.com>
 */
public class XmlToObject {

    public static Map<String, Object> fromXml(final String xmlString)
            throws XMLException {
        final Document doc = XMLUtility.loadXML(xmlString);
        return (Map<String, Object>) fromXml(doc.getDocumentElement());
    }

    private static Object fromXml(final Element element) throws XMLException {

        final List<Element> childElements = XMLUtility.getChildElements(element);
        if (childElements.isEmpty()) {
            return element.getTextContent();
        }
        final Map<String, Object> map = new LinkedHashMap<>();
        for (final Element childElement : childElements) {
            String childElementName = childElement.getNodeName();
            addToMap(map, childElementName, fromXml(childElement));
        }
        return map;
    }

    private static void addToMap(
            final Map<String, Object> map,
            final String key,
            final Object value
    ) {
        if (map.containsKey(key)) {
            Object currentValue = map.get(key);
            if (currentValue instanceof List) {
                List list = (List) map.get(key);
                list.add(value);
            } else {
                List list = new LinkedList();
                list.add(currentValue);
                list.add(value);
                map.put(key, list);
            }

        } else {
            map.put(key, value);
        }
    }

    public static String toXml(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();

        if (map == null) {
            return "";
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            sb.append("<").append(entry.getKey()).append(">");
            if (entry.getValue() instanceof Map) {
                sb.append(toXml((Map<String, Object>) entry.getValue()));
            } else {
                sb.append(entry.getValue());
            }
            sb.append("</").append(entry.getKey()).append(">");
        }

        return sb.toString();
    }

}
