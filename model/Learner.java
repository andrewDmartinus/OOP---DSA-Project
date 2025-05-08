package com.drivinglearners.driving_learners.model;

import java.time.LocalDate;

public class Learner {
    private String id;
    private String name;
    private String email;
    private String licenseType;
    private String contact;
    private LocalDate expiryDate;

    public Learner(String id, String name, String email, String licenseType, String contact, LocalDate expiryDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.licenseType = licenseType;
        this.contact = contact;
        this.expiryDate = expiryDate != null ? expiryDate : LocalDate.now().plusYears(1); // Default to 1 year
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getLicenseType() { return licenseType; }
    public void setLicenseType(String licenseType) { this.licenseType = licenseType; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    public LocalDate getExpiryDate() { return expiryDate; }
    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    public String trackProgress() {
        return "Tracking progress for " + name;
    }
}