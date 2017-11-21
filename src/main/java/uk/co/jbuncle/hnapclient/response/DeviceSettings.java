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
class DeviceSettings implements DeviceSettingsI {

    private String type;
    private String deviceName;
    private String vendorName;
    private String modelDescription;
    private String modelName;
    private String deviceMacId;
    private String firmwareVersion;
    private String firmwareRegion;
    private String hardwareVersion;
    private String hnapVersion;
    private String presentationUrl;
    private boolean captcha;
    private List<String> moduleTypes;
    private List<URL> soapActions;

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getDeviceName() {
        return deviceName;
    }

    @Override
    public String getVendorName() {
        return vendorName;
    }

    @Override
    public String getModelDescription() {
        return modelDescription;
    }

    @Override
    public String getModelName() {
        return modelName;
    }

    @Override
    public String getDeviceMacId() {
        return deviceMacId;
    }

    @Override
    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    @Override
    public String getFirmwareRegion() {
        return firmwareRegion;
    }

    @Override
    public String getHardwareVersion() {
        return hardwareVersion;
    }

    @Override
    public String getHNAPVersion() {
        return hnapVersion;
    }

    @Override
    public String getPresentationUrl() {
        return presentationUrl;
    }

    @Override
    public boolean isCaptcha() {
        return captcha;
    }

    @Override
    public List<String> getModuleTypes() {
        return moduleTypes;
    }

    @Override
    public List<URL> getSoapActions() {
        return soapActions;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public void setModelDescription(String modelDescription) {
        this.modelDescription = modelDescription;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public void setDeviceMacId(String deviceMacId) {
        this.deviceMacId = deviceMacId;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public void setFirmwareRegion(String firmwareRegion) {
        this.firmwareRegion = firmwareRegion;
    }

    public void setHardwareVersion(String hardwareVersion) {
        this.hardwareVersion = hardwareVersion;
    }

    public void setHNAPVersion(String hnapVersion) {
        this.hnapVersion = hnapVersion;
    }

    public void setPresentationUrl(String presentationUrl) {
        this.presentationUrl = presentationUrl;
    }

    public void setCaptcha(boolean captcha) {
        this.captcha = captcha;
    }

    public void setModuleTypes(List<String> moduleTypes) {
        this.moduleTypes = moduleTypes;
    }

    public void setSoapActions(List<URL> soapActions) {
        this.soapActions = soapActions;
    }

    @Override
    public String toString() {
        return "DeviceSettings{" + "type=" + type + ", deviceName=" + deviceName + ", vendorName=" + vendorName + ", modelDescription=" + modelDescription + ", modelName=" + modelName + ", deviceMacId=" + deviceMacId + ", firmwareVersion=" + firmwareVersion + ", firmwareRegion=" + firmwareRegion + ", hardwareVersion=" + hardwareVersion + ", hnapVersion=" + hnapVersion + ", presentationUrl=" + presentationUrl + ", captcha=" + captcha + ", moduleTypes=" + moduleTypes + ", soapActions=" + soapActions + '}';
    }

}
