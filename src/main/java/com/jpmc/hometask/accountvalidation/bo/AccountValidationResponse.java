package com.jpmc.hometask.accountvalidation.bo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;

public class AccountValidationResponse {

    private String provider;

    private Boolean isValid;

    public AccountValidationResponse(String provider, Boolean isValid) {
        this.provider = provider;
        this.isValid = isValid;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
    @JsonProperty("isValid")
    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }
}
