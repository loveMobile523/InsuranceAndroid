package com.whatscover.android.webservice.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by dorin on 15.05.2017.
 */

public class DTOProductSuggestion implements Serializable {
    @SerializedName("id")
    private Integer id;
    @SerializedName("title")
    private String title;
    @SerializedName("photo")
    private String photo;
    @SerializedName("photoContentType")
    private String photoContentType;
    @SerializedName("sumInsured")
    private Integer sumInsured;
    @SerializedName("start")
    private String start;
    @SerializedName("end")
    private String end;
    @SerializedName("active")
    private boolean active;
    @SerializedName("description")
    private String description;
    @SerializedName("overview")
    private String overview;
    @SerializedName("approved")
    private boolean approved;
    @SerializedName("premium")
    private Integer premium;
    @SerializedName("companyId")
    private Integer companyId;
    @SerializedName("companyName")
    private String companyName;
    @SerializedName("categoryId")
    private Integer categoryId;
    @SerializedName("categoryTitle")
    private String categoryTitle;
    @SerializedName("creatorId")
    private Integer creatorId;
    @SerializedName("creatorLogin")
    private String creatorLogin;
    @SerializedName("approvedById")
    private Integer approvedById;
    @SerializedName("approvedByLogin")
    private String approvedByLogin;

    public DTOProductSuggestion() {}

    public DTOProductSuggestion(String title, String photo, String photoContentType, Integer sumInsured,
                                String start, String end, boolean active, String description, String overview,
                                boolean approved, Integer premium, Integer companyId, String companyName, Integer categoryId,
                                String categoryTitle, Integer creatorId, String creatorLogin, Integer approvedById,
                                String approvedByLogin) {
        this.title = title;
        this.photo = photo;
        this.photoContentType = photoContentType;
        this.sumInsured = sumInsured;
        this.start = start;
        this.end = end;
        this.active = active;
        this.description = description;
        this.overview = overview;
        this.approved = approved;
        this.premium = premium;
        this.companyId = companyId;
        this.companyName = companyName;
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
        this.creatorId = creatorId;
        this.creatorLogin = creatorLogin;
        this.approvedById = approvedById;
        this.approvedByLogin = approvedByLogin;
    }

    public DTOProductSuggestion(Integer id, String title, String photo, String photoContentType, Integer sumInsured,
                                String start, String end, boolean active, String description, String overview,
                                boolean approved, Integer premium, Integer companyId, String companyName, Integer categoryId,
                                String categoryTitle, Integer creatorId, String creatorLogin, Integer approvedById,
                                String approvedByLogin) {
        this.id = id;
        this.title = title;
        this.photo = photo;
        this.photoContentType = photoContentType;
        this.sumInsured = sumInsured;
        this.start = start;
        this.end = end;
        this.active = active;
        this.description = description;
        this.overview = overview;
        this.approved = approved;
        this.premium = premium;
        this.companyId = companyId;
        this.companyName = companyName;
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
        this.creatorId = creatorId;
        this.creatorLogin = creatorLogin;
        this.approvedById = approvedById;
        this.approvedByLogin = approvedByLogin;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public void setSumInsured(Integer sumInsured) {
        this.sumInsured = sumInsured;
    }

    public Integer getSumInsured() {
        return sumInsured;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getStart() {
        return start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getEnd() {
        return end;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOverview() {
        return overview;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setPremium(Integer premium) {
        this.premium = premium;
    }

    public Integer getPremium() {
        return premium;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setApprovedById(Integer approvedById) {
        this.approvedById = approvedById;
    }

    public Integer getApprovedById() {
        return approvedById;
    }


    // ---------------------------------------------------------------------------------------------

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCreatorLogin(String creatorLogin) {
        this.creatorLogin = creatorLogin;
    }

    public String getCreatorLogin() {
        return creatorLogin;
    }

    public void setApprovedByLogin(String approvedByLogin) {
        this.approvedByLogin = approvedByLogin;
    }

    public String getApprovedByLogin() {
        return approvedByLogin;
    }
}
