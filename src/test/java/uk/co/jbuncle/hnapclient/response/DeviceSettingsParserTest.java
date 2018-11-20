/*
 * James Buncle 2017
 */
package uk.co.jbuncle.hnapclient.response;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import junit.framework.TestCase;
import org.apache.commons.io.IOUtils;
import uk.co.jbuncle.hnapclient.util.xml.XmlToObject;

/**
 * @author James Buncle <jbuncle@hotmail.com>
 */
public class DeviceSettingsParserTest extends TestCase {

	/** Test of createFromResponse method, of class DeviceSettingsParser. */
	public void testCreateFromResponse() throws Exception {
		System.out.println("createFromResponse");

		final String xml = IOUtils.toString(
				DeviceSettingsParserTest.class.getClassLoader().getResourceAsStream(
						"uk/co/jbuncle/hnapclient/response/DeviceSettings.xml"));

		final Map<String, Object> responseProperties = XmlToObject.fromXml(xml);

		final DeviceSettingsI result = DeviceSettingsParser
				.createFromResponse(responseProperties);
		assertEquals("ConnectedHomeClient", result.getType());

		final List<String> expectedModuleTypes = new LinkedList<>();
		expectedModuleTypes.add("Gateway");
		expectedModuleTypes.add("Motion Sensor");
		assertEquals(expectedModuleTypes, result.getModuleTypes());

		final List<URL> expectedSOAPActions = new LinkedList<>();
		expectedSOAPActions.add(new URL("http://purenetworks.com/HNAP1/Reboot"));
		expectedSOAPActions
				.add(new URL("http://purenetworks.com/HNAP1/SetFactoryDefault"));
		assertEquals(expectedSOAPActions, result.getSoapActions());
	}

}
