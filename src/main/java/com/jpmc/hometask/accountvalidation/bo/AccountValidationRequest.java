package com.jpmc.hometask.accountvalidation.bo;

import java.util.List;

public class AccountValidationRequest {

    private String accountNumber;

    private List<String> providers;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<String> getProviders() {
        return providers;
    }

    public void setProviders(List<String> providers) {
        this.providers = providers;
    }
}
