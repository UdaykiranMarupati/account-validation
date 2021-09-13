package com.jpmc.hometask.accountvalidation.controllers;

import com.jpmc.hometask.accountvalidation.bo.AccountResponse;
import com.jpmc.hometask.accountvalidation.bo.AccountValidationRequest;
import com.jpmc.hometask.accountvalidation.bo.AccountValidationResponse;
import com.jpmc.hometask.accountvalidation.components.YAMLConfigurations;
import com.jpmc.hometask.accountvalidation.service.ProviderValidatorService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class AccountValidationController {

    @Autowired
    private ProviderValidatorService providerValidatorService;

    @Autowired
    private YAMLConfigurations yamlConfigurations;

    @PostMapping("/accountValidation")
    public AccountResponse accountValidationService(@RequestBody AccountValidationRequest request) {
        List<AccountValidationResponse> responseList = new ArrayList<>();
        if (request.getProviders() != null) {
            for (String provider : request.getProviders()) {
                if (yamlConfigurations.getProviders().containsKey(provider)) {
                    responseList.add(new AccountValidationResponse(provider, providerValidatorService.callProvider(yamlConfigurations.getProviders().get(provider), request.getAccountNumber())));
                }
            }
        } else {
            for (Map.Entry<String, String> provider : yamlConfigurations.getProviders().entrySet()) {
                responseList.add(new AccountValidationResponse(provider.getKey(), providerValidatorService.callProvider(provider.getValue(), request.getAccountNumber())));
            }
        }
        AccountResponse response = new AccountResponse();
        response.setResult(responseList);
        return response;
    }
}
