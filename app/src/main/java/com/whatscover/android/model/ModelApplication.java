package com.whatscover.android.model;

public class ModelApplication {

    private int imgDummy;
    private String agentName;
    private int imgCategory;
    private String category;
    private String applicationStatus;

    public ModelApplication() {}

    public ModelApplication(int imgDummy, String agentName, int imgCategory, String category, String applicationStatus) {
        this.imgDummy = imgDummy;
        this.agentName = agentName;
        this.imgCategory = imgCategory;
        this.category = category;
        this.applicationStatus = applicationStatus;
    }

    public void setImgDummy(int imgDummy) {
        this.imgDummy = imgDummy;
    }

    public int getImgDummy() {
        return imgDummy;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setImgCategory(int imgCategory) {
        this.imgCategory = imgCategory;
    }

    public int getImgCategory() {
        return imgCategory;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }
}
