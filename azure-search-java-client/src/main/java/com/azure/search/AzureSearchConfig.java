package com.azure.search;


public class AzureSearchConfig {

    private final static String DEFAULT_API_VERSION = "2014-07-31-Preview";

    private final String serviceName;
    private String apiVersion = DEFAULT_API_VERSION;
    private String adminKey;

    public AzureSearchConfig(String serviceName) {
        this.serviceName = serviceName;
    }

    public void changeApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public AzureSearchConfig addAdminKey(String adminKey) {
        this.adminKey = adminKey;
        return this;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getAdminKey() {
        return adminKey;
    }
}
