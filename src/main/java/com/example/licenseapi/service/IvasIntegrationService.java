package com.example.licenseapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IvasIntegrationService {
    @Autowired
    private RestTemplate restTemplate;

    public boolean validateBinNumber(String binNumber) {
        String ivasApiUrl = "http://localhost:8081/api/ivas/validate/" + binNumber; // Adjust port and URL as needed
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(ivasApiUrl, String.class);
            return response.getStatusCode().is2xxSuccessful(); // Returns true if status is 200 OK
        } catch (Exception e) {
            return false; // Returns false if an error occurs (e.g., 404 Not Found)
        }
    }
}
