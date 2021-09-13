package com.jpmc.hometask.accountvalidation.service;

import com.jpmc.hometask.accountvalidation.bo.ProviderResponse;

public interface ProviderValidatorService {

    public Boolean callProvider(String providerURL, String accountNumber);
}
