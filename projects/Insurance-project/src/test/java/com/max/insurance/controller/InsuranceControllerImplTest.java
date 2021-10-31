package com.max.insurance.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.max.insurance.entity.ClaimDetails;
import com.max.insurance.services.ClaimService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class InsuranceControllerImplTest {
    @Autowired
    MockMvc mockMvc;



    @MockBean
    private ClaimService service;

    private static ObjectMapper mapper = new ObjectMapper();



    @Test
    void getClaimDetails() throws Exception {
        Mockito.when(service.getClaimDetails(Mockito.anyLong())).thenReturn(createClaimObj());
        mockMvc.perform(get("/claim/2")).andExpect(status().isOk())
                .andExpect(jsonPath("$.claimId", Matchers.equalTo(2)))
                .andExpect(jsonPath("$.policyNum", Matchers.equalTo("P4884")));


    }

    @Test
    void getClaimDetailsForNoRecordFound() throws Exception {
        Mockito.when(service.getClaimDetails(Mockito.anyLong())).thenReturn(null);
        MvcResult mvcResult = mockMvc.perform(get("/claim/2")).andExpect(status().isOk())
                .andReturn();
        assertEquals(mvcResult.getResponse().getContentAsString(), "No Record Found");


    }



    @Test
    void createClaim() throws Exception {
        ClaimDetails claimObj = createClaimObj();
        String json = mapper.writeValueAsString(claimObj);
        Mockito.when(service.createClaim(Mockito.any(ClaimDetails.class))).thenReturn(createClaimObj());
        mockMvc.perform(post("/claim/").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
                .andExpect(jsonPath("$.claimId", Matchers.equalTo(2)))
                .andExpect(jsonPath("$.policyNum", Matchers.equalTo("P4884")));
    }

    private ClaimDetails createClaimObj() {
        return new ClaimDetails(new Long(2), "P4884", 20000.00);
    }
}