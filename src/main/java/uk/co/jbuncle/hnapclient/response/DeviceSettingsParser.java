/*
 * James Buncle 2017
 */
package uk.co.jbuncle.hnapclient.response;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jbunc
 */
public class DeviceSettingsParser {

    public static DeviceSettingsI createFromResponse(final Map<String, Object> responseProperties)
            throws MalformedURLException {
        final DeviceSettings deviceSettings = new DeviceSettings();

        deviceSettings.setType((String) responseProperties.get("Type"));
        deviceSettings.setDeviceName((String) responseProperties.get("DeviceName"));
        deviceSettings.setVendorName((String) responseProperties.get("VendorName"));
        deviceSettings.setModelDescription((String) responseProperties.get("ModelDescription"));
        deviceSettings.setModelName((String) responseProperties.get("ModelName"));
        deviceSettings.setDeviceMacId((String) responseProperties.get("DeviceMacId"));
        deviceSettings.setFirmwareVersion((String) responseProperties.get("FirmwareVersion"));
        deviceSettings.setFirmwareRegion((String) responseProperties.get("FirmwareRegion"));
        deviceSettings.setHardwareVersion((String) responseProperties.get("HardwareVersion"));
        deviceSettings.setHNAPVersion((String) responseProperties.get("HNAPVersion"));
        deviceSettings.setPresentationUrl((String) responseProperties.get("PresentationURL"));

        final List<String> moduleTypes = new LinkedList<>();
        for (final Object object : (List) ((Map) responseProperties.get("ModuleTypes")).get("string")) {
            moduleTypes.add((String) object);
        }
        deviceSettings.setModuleTypes(moduleTypes);

        final List<URL> soapActions = new LinkedList<>();
        for (final Object object : (List) ((Map) responseProperties.get("SOAPActions")).get("string")) {
            final String urlString = (String) object;
            final URL url = new URL(urlString);
            soapActions.add(url);
        }
        deviceSettings.setSoapActions(soapActions);

        return deviceSettings;
    }
}
