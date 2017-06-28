package com.whatscover.android.webservice.dto;

import com.google.gson.annotations.SerializedName;

/**
 * Created by dorin on 13.05.2017.
 */

public class DTOCategory {
    @SerializedName("id")
    private Integer id;
    @SerializedName("title")
    private String title;
    @SerializedName("icon")
    private String icon;
    @SerializedName("icon_content_type")
    private String iconContentType;

    public DTOCategory() {}

    public DTOCategory(String title, String icon, String iconContentType) {
        this.title = title;
        this.icon = icon;
        this.iconContentType = iconContentType;
    }

    public DTOCategory(Integer id, String title, String icon, String iconContentType) {
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.iconContentType = iconContentType;
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

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setIconContentType(String iconContentType) {
        this.iconContentType = iconContentType;
    }

    public String getIconContentType() {
        return iconContentType;
    }
}
