package com.jpmc.hometask.accountvalidation;

import com.jpmc.hometask.accountvalidation.service.ProviderValidatorService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountValidationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProviderValidatorService providerValidatorService;

    @Test
    public void accountValidationHTTPStatusTest() throws Exception {

        when(providerValidatorService.callProvider("https://provider1.com/v1/api/account/validate", "1234567789")).thenReturn(true);

        JSONObject request = new JSONObject();
        request.put("accountNumber", "1234567789");
        request.put("city", "chicago");
        List<String> list = new ArrayList<>();
        list.add("provider1");
        JSONArray ja = new JSONArray(list);
        request.put("providers", ja);
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/accountValidation")
                        .content(new JSONObject(request.toString()).toString())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void singleProviderTest() throws Exception {

        when(providerValidatorService.callProvider("https://provider1.com/v1/api/account/validate", "1234567789")).thenReturn(true);

        JSONObject request = new JSONObject();
        request.put("accountNumber", "1234567789");
        request.put("city", "chicago");
        List<String> list = new ArrayList<>();
        list.add("provider1");
        JSONArray ja = new JSONArray(list);
        request.put("providers", ja);
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/accountValidation")
                        .content(new JSONObject(request.toString()).toString())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$.result", is(notNullValue()))))
                .andExpect(jsonPath("$.result", hasSize(1)))
                .andExpect(jsonPath("$.result[0].provider").value("provider1"))
                .andExpect(jsonPath("$.result[0].isValid").value(true))
                .andReturn();
    }

    @Test
    public void multipleProvidersTest() throws Exception {

        when(providerValidatorService.callProvider("https://provider1.com/v1/api/account/validate", "1234567789")).thenReturn(true);
        when(providerValidatorService.callProvider("https://provider2.com/v1/api/account/validate", "1234567789")).thenReturn(false);

        JSONObject request = new JSONObject();
        request.put("accountNumber", "1234567789");
        request.put("city", "chicago");
        List<String> list = new ArrayList<>();
        list.add("provider1");
        list.add("provider2");
        JSONArray ja = new JSONArray(list);
        request.put("providers", ja);
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/accountValidation")
                        .content(new JSONObject(request.toString()).toString())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$.result", is(notNullValue()))))
                .andExpect(jsonPath("$.result", hasSize(2)))
                .andExpect(jsonPath("$.result[0].provider").value("provider1"))
                .andExpect(jsonPath("$.result[0].isValid").value(true))
                .andExpect(jsonPath("$.result[1].provider").value("provider2"))
                .andExpect(jsonPath("$.result[1].isValid").value(false))
                .andReturn();
    }

    @Test
    public void defaultProvidersTest() throws Exception {

        when(providerValidatorService.callProvider("https://provider1.com/v1/api/account/validate", "1234567789")).thenReturn(true);
        when(providerValidatorService.callProvider("https://provider2.com/v1/api/account/validate", "1234567789")).thenReturn(false);

        JSONObject request = new JSONObject();
        request.put("accountNumber", "1234567789");
        request.put("city", "chicago");
        this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/accountValidation")
                        .content(new JSONObject(request.toString()).toString())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect((jsonPath("$.result", is(notNullValue()))))
                .andExpect(jsonPath("$.result", hasSize(2)))
                .andExpect(jsonPath("$.result[0].provider").value("provider1"))
                .andExpect(jsonPath("$.result[0].isValid").value(true))
                .andExpect(jsonPath("$.result[1].provider").value("provider2"))
                .andExpect(jsonPath("$.result[1].isValid").value(false))
                .andReturn();
    }

}
