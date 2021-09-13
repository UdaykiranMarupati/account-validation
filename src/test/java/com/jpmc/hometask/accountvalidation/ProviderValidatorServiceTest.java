package com.jpmc.hometask.accountvalidation;

import com.jpmc.hometask.accountvalidation.bo.ProviderRequest;
import com.jpmc.hometask.accountvalidation.service.ProviderValidatorService;
import com.jpmc.hometask.accountvalidation.service.impl.ProviderValidatorServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class ProviderValidatorServiceTest {

    @Autowired
    private ProviderValidatorServiceImpl providerValidatorService;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private RestTemplate restTemplate;

    @Test
    public void callProviderTest() {
        ProviderRequest request = new ProviderRequest("123456789");
        String response = "{\n" +
                "    \"isValid\": true\n" +
                "}";
        Mockito.when(restTemplate.postForEntity(
                "https://provider1.com/v1/api/account/validate",
                request,
                String.class)).thenReturn(new ResponseEntity<>(response, HttpStatus.OK));

        Boolean result = providerValidatorService.callProvider("https://provider1.com/v1/api/account/validate", "123456789");
        Assert.isTrue(result, "Valid Response");
    }
}
