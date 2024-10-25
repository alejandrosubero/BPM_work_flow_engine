package com.bpm.engine.serviceImplement;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.bpm.engine.dto.EntityRespone;

import java.util.HashMap;
import java.util.Map;

@Service
public class RestTemplateService {

    private final RestTemplate restTemplate;

    private static final Logger logger = LogManager.getLogger(RestTemplateService.class);
    
    @Autowired
    public RestTemplateService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> sendPostRequest(String url, String requestBody ) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String responseBody = responseEntity.getBody();
            System.out.println("Successful: " + responseBody);
        } else {
            System.out.println("the POST faill code: " + responseEntity.getStatusCodeValue());
        }

        return responseEntity;
    }


    /***
     *
     * @param uriVariables
     * @param url "http://example.com/hotels/{hotel}/{room}"
     * @return
     */
    public HttpEntity<String> sendGetRequest(Map<String, String> uriVariables, String url ) {
        HttpEntity<String> response = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            HttpEntity<?> entity = new HttpEntity<>(headers);
            response = restTemplate.exchange(url, HttpMethod.GET,entity, String.class, uriVariables);

        }catch (Exception e){
            e.printStackTrace();
            return response;
        }

        return response;

    }
    
    
    public EntityRespone sendGetRequest2(Map<String, String> uriVariables, String url ) {
        
    	HttpEntity<EntityRespone> response = null;
      
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            HttpEntity<?> entity = new HttpEntity<>(headers);
            
            response = restTemplate.exchange(url, HttpMethod.GET,entity, EntityRespone.class, uriVariables);

        }catch (Exception e){
            e.printStackTrace();
            return response.getBody();
        }

        return response.getBody();

    }
    
    
}
