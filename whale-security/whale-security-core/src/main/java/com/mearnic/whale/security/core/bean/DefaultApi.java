package com.mearnic.whale.security.core.bean;

public class DefaultApi {
    private String uri;
    private String method;
    private String serviceName;

    public DefaultApi(String uri, String method, String serviceName) {
        this.uri = uri;
        this.method = method;
        this.serviceName = serviceName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
