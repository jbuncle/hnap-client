/*
 * James Buncle 2017
 */
package uk.co.jbuncle.hnapclient.response;

import java.net.URL;
import java.util.List;

/**
 *
 * @author jbunc
 */
public interface DeviceSettingsI {

    String getDeviceMacId();

    String getDeviceName();

    String getFirmwareRegion();

    String getFirmwareVersion();

    String getHardwareVersion();

    String getHNAPVersion();

    String getModelDescription();

    String getModelName();

    List<String> getModuleTypes();

    String getPresentationUrl();

    List<URL> getSoapActions();

    String getType();

    String getVendorName();

    boolean isCaptcha();

}
