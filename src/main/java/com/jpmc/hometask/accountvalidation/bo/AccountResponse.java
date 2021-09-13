package com.jpmc.hometask.accountvalidation.bo;

import java.util.List;

public class AccountResponse {

    private List<AccountValidationResponse> result;

    public List<AccountValidationResponse> getResult() {
        return result;
    }

    public void setResult(List<AccountValidationResponse> result) {
        this.result = result;
    }

}
