package com.example.licenseapi.repository;

import com.example.licenseapi.model.License;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LicenseRepository extends JpaRepository<License, Integer> {
    Optional<License> findByLicenseCode(String licenseCode);
}
