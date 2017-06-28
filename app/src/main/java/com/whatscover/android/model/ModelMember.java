package com.whatscover.android.model;

public class ModelMember {
    private int imageId;
    private String name;
    private boolean judgeSelected;

    public ModelMember() {}

    public ModelMember(int imageId, String name, boolean judgeSelected) {
        this.imageId = imageId;
        this.name = name;
        this.judgeSelected = judgeSelected;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setJudgeSelected(boolean judgeSelected) {
        this.judgeSelected = judgeSelected;
    }

    public boolean isJudgeSelected() {
        return judgeSelected;
    }
}
