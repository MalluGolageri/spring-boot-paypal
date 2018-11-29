package com.paypal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.paypal.api.payments.HyperSchema;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Links implements Serializable {

    private String href;
    private String rel;
    private HyperSchema targetSchema;
    private String method;
    private String enctype;
    private HyperSchema schema;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public HyperSchema getTargetSchema() {
        return targetSchema;
    }

    public void setTargetSchema(HyperSchema targetSchema) {
        this.targetSchema = targetSchema;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getEnctype() {
        return enctype;
    }

    public void setEnctype(String enctype) {
        this.enctype = enctype;
    }

    public HyperSchema getSchema() {
        return schema;
    }

    public void setSchema(HyperSchema schema) {
        this.schema = schema;
    }
}
