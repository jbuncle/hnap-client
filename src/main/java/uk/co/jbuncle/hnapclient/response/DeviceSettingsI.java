/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
