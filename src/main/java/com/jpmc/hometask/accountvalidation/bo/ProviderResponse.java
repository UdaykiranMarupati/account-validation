package com.jpmc.hometask.accountvalidation.bo;

public class ProviderResponse {

    private String provider;

    private Boolean isValid;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }
}
