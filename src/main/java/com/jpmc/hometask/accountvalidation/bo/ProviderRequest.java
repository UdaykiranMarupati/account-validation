package com.jpmc.hometask.accountvalidation.bo;

public class ProviderRequest {

    private String accountNumber;

    public ProviderRequest(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
