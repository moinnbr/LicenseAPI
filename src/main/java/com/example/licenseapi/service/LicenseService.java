package com.example.licenseapi.service;

import com.example.licenseapi.exception.CustomExceptions;
import com.example.licenseapi.model.License;
import com.example.licenseapi.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LicenseService {
    @Autowired
    private LicenseRepository licenseRepository;

    public License createLicense(License license) {
        // Check for unique license code
        licenseRepository.findByLicenseCode(license.getLicenseCode())
                .ifPresent(existing -> {
                    throw new RuntimeException("License code must be unique: " + license.getLicenseCode());
                });

        return licenseRepository.save(license);
    }

    public void activateLicense(Integer id) {
        License license = licenseRepository.findById(id)
                .orElseThrow(() -> new CustomExceptions.LicenseNotFoundException("License not found"));
        license.setActive(true);
        licenseRepository.save(license);
    }

    public void deactivateLicense(Integer id) {
        License license = licenseRepository.findById(id)
                .orElseThrow(() -> new CustomExceptions.LicenseNotFoundException("License not found"));
        license.setActive(false);
        licenseRepository.save(license);
    }

    public License getLicenseById(Integer id) {
        return licenseRepository.findById(id)
                .orElseThrow(() -> new CustomExceptions.LicenseNotFoundException("License not found"));
    }

    public void validateLicense(String licenseCode, String binNumber) {
        License license = licenseRepository.findByLicenseCode(licenseCode)
                .orElseThrow(() -> new CustomExceptions.LicenseNotFoundException("License not found"));

        if (!license.isActive()) {
            throw new CustomExceptions.LicenseInactiveException("License is not active");
        }

        if (license.getExpirationDate() != null && license.getExpirationDate().isBefore(LocalDate.now())) {
            throw new CustomExceptions.LicenseExpiredException("The license has expired");
        }

        if (!license.getBinNumber().equals(binNumber)) {
            throw new CustomExceptions.BinNumberMismatchException("Bin number mismatch");
        }

        if (license.isOneTimeUse()) {
            license.setActive(false);
            licenseRepository.save(license);
        }
    }
}
