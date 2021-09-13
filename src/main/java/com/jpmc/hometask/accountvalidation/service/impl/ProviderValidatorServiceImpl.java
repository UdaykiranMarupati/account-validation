package com.jpmc.hometask.accountvalidation.service.impl;

import com.jpmc.hometask.accountvalidation.bo.ProviderRequest;
import com.jpmc.hometask.accountvalidation.service.ProviderValidatorService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProviderValidatorServiceImpl implements ProviderValidatorService {

    @Autowired
    private RestTemplate restTemplate;

    public Boolean callProvider(String providerURL, String accountNumber) {
        System.out.println(providerURL +" :: " + accountNumber);
        ProviderRequest request = new ProviderRequest(accountNumber);
        ResponseEntity<String> providerResponse = restTemplate.postForEntity(providerURL, request, String.class);
        if (providerResponse.hasBody()) {
            JSONObject jsonObject = new JSONObject(providerResponse.getBody());
            return jsonObject.getBoolean("isValid");
        }
        return false;
    }
}

