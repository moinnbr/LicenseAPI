package com.example.licenseapi.controller;

import com.example.licenseapi.model.License;
import com.example.licenseapi.service.IvasIntegrationService;
import com.example.licenseapi.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/licenses")
public class LicenseController {
    @Autowired
    private IvasIntegrationService ivasIntegrationService;

    @Autowired
    private LicenseService licenseService;

    @PostMapping
    public ResponseEntity<String> createLicense(@RequestBody License license) {
        // Validate the binNumber using IVAS API
        if (!ivasIntegrationService.validateBinNumber(license.getBinNumber())) {
            return ResponseEntity.badRequest().body("BIN not found in IVAS API: Cannot create license");
        }

        // Proceed to create and save the license if the binNumber is valid
        licenseService.createLicense(license);
        return ResponseEntity.ok("License created successfully");
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<String> activateLicense(@PathVariable Integer id) {
        licenseService.activateLicense(id);
        return ResponseEntity.ok("License activated successfully");
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<String> deactivateLicense(@PathVariable Integer id) {
        licenseService.deactivateLicense(id);
        return ResponseEntity.ok("License deactivated successfully");
    }

    @GetMapping("/{id}")
    public ResponseEntity<License> getLicenseById(@PathVariable Integer id) {
        return ResponseEntity.ok(licenseService.getLicenseById(id));
    }

    @GetMapping("/validate/{licenseCode}")
    public ResponseEntity<String> validateLicense(@PathVariable String licenseCode, @RequestParam String binNumber) {
        licenseService.validateLicense(licenseCode, binNumber);
        return ResponseEntity.ok("License is valid");
    }
}
