package com.paypal.response;


import com.paypal.api.payments.DefinitionsLinkdescription;
import com.paypal.api.payments.ErrorDetails;

import java.io.Serializable;
import java.util.List;

public class Error implements Serializable {

    private String name;
    private String message;
    private List<ErrorDetails> details;
    private String informationLink;
    private String debugId;
    private List<DefinitionsLinkdescription> links;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ErrorDetails> getDetails() {
        return details;
    }

    public void setDetails(List<ErrorDetails> details) {
        this.details = details;
    }

    public String getInformationLink() {
        return informationLink;
    }

    public void setInformationLink(String informationLink) {
        this.informationLink = informationLink;
    }

    public String getDebugId() {
        return debugId;
    }

    public void setDebugId(String debugId) {
        this.debugId = debugId;
    }

    public List<DefinitionsLinkdescription> getLinks() {
        return links;
    }

    public void setLinks(List<DefinitionsLinkdescription> links) {
        this.links = links;
    }
}
