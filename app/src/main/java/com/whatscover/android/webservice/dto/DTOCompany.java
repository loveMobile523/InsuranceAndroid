package com.whatscover.android.webservice.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dorin on 14.05.2017.
 */

public class DTOCompany {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("logo")
    private String logo;
    @SerializedName("logo_content_type")
    private String logoContentType;
    @SerializedName("address")
    private String address;

    public DTOCompany() {}

    public DTOCompany(String name, String description, String logo, String logoContentType, String address) {
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.logoContentType = logoContentType;
        this.address = address;
    }

    public DTOCompany(Integer id, String name, String description, String logo, String logoContentType, String address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.logo = logo;
        this.logoContentType = logoContentType;
        this.address = address;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
    }

    public String getLogoContentType() {
        return logoContentType;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
}
