package com.jaspreetflourmill.server.model;

import javax.persistence.*;

@Entity
@Table(name="license")
public class License {

    public enum LicenseKey{
        LICENSE_KEY
    }

    @Id
    @Column(name = "key_id")
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "license_key")
    private LicenseKey licenseKey;

    public License() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LicenseKey getLicenseKey() {
        return licenseKey;
    }

    public void setLicenseKey(LicenseKey licenseKey) {
        this.licenseKey = licenseKey;
    }
}
