/*
 * James Buncle 2017
 */
package uk.co.jbuncle.hnapclient.util.xml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author James Buncle <jbuncle@hotmail.com>
 */
public class XMLUtility {

    public static String toString(Node node) throws XMLException {
        try {
            final TransformerFactory tf = TransformerFactory.newInstance();
            final Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            final StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(node), new StreamResult(writer));
            return writer.getBuffer().toString().replaceAll("\n|\r", "");
        } catch (TransformerException ex) {
            throw new XMLException(ex);
        }
    }

    public static Document loadXML(
            final String xml
    ) throws XMLException {
        try {
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final InputSource is = new InputSource(new StringReader(xml));
            return builder.parse(is);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            throw new XMLException(ex);
        }
    }

    public static Document loadXML(final File file) throws XMLException {
        try {
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            final DocumentBuilder builder = factory.newDocumentBuilder();
            final InputSource is = new InputSource(new FileReader(file));
            return builder.parse(is);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            throw new XMLException(ex);
        }
    }

    /**
     * http://stackoverflow.com/questions/3300839/get-a-nodes-inner-xml-as-string-in-java-dom
     *
     * @param node
     * @return
     */
    public static String innerXml(final Node node) {
        final DOMImplementationLS lsImpl = (DOMImplementationLS) node
                .getOwnerDocument()
                .getImplementation()
                .getFeature("LS", "3.0");
        final LSSerializer lsSerializer = lsImpl.createLSSerializer();
        final DOMConfiguration c = lsSerializer.getDomConfig();
        c.setParameter("format-pretty-print", false);
        c.setParameter("xml-declaration", false);
        final NodeList childNodes = node.getChildNodes();
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < childNodes.getLength(); i++) {
            sb.append(lsSerializer.writeToString(childNodes.item(i)));
        }
        return sb.toString();
    }

    public static Document marshall(
            final Object object
    ) throws XMLException {

        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(object.getClass());

            Marshaller m = jaxbContext.createMarshaller();
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();

            m.marshal(object, doc);

            return doc;
        } catch (ParserConfigurationException | JAXBException ex) {
            throw new XMLException(ex);
        }
    }

    public static <T> T unmarshall(
            final Class<T> clazz,
            final String xml
    ) throws XMLException {
        try {
            final JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (T) jaxbUnmarshaller.unmarshal(new StringReader(xml));
        } catch (final JAXBException e) {
            throw new XMLException(e);
        }
    }

    public static List<Element> getChildElements(final Node element, final String name) {
        final List<Element> children = new LinkedList<>();
        getChildElements(element).stream()
                .filter((childElement) -> (childElement.getTagName().equals(name)))
                .forEachOrdered((childElement) -> {
                    children.add(childElement);
                });
        return children;
    }

    public static Collection<Element> getChildElementsUnique(final Element element) {
        final LinkedHashMap<String, Element> map = new LinkedHashMap<>();
        getChildElements(element).forEach((childElement) -> {
            map.put(childElement.getTagName(), childElement);
        });
        return map.values();
    }

    public static List<Element> getChildElements(final Node element) {
        final List<Element> childElements = new LinkedList<>();
        final NodeList childNodes = element.getChildNodes();
        for (int index = 0; index < childNodes.getLength(); index++) {
            final Node childNode = childNodes.item(index);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                final Element childElement = (Element) childNode;
                childElements.add(childElement);
            }
        }
        return childElements;
    }
}
