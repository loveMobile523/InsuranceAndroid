package com.whatscover.android.model;

public class ModelAgent {
    private int imgDummy;
    private String applicationTitle;
    private String agentName;
    private String productCoverage;
    private String appointment;
    private String percent;

    public ModelAgent() {}

    public ModelAgent(int imgDummy, String applicationTitle, String agentName, String productCoverage,
                      String appointment, String percent) {
        this.imgDummy = imgDummy;
        this.applicationTitle = applicationTitle;
        this.agentName = agentName;
        this.productCoverage = productCoverage;
        this.appointment = appointment;
        this.percent = percent;
    }

    public void setImgDummy(int imgDummy) {
        this.imgDummy = imgDummy;
    }

    public int getImgDummy() {
        return imgDummy;
    }

    public void setApplicationTitle(String applicationTitle) {
        this.applicationTitle = applicationTitle;
    }

    public String getApplicationTitle() {
        return applicationTitle;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setProductCoverage(String productCoverage) {
        this.productCoverage = productCoverage;
    }

    public String getProductCoverage() {
        return productCoverage;
    }

    public void setAppointment(String appointment) {
        this.appointment = appointment;
    }

    public String getAppointment() {
        return appointment;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getPercent() {
        return percent;
    }
}
