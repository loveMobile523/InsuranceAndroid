package com.whatscover.android.model;

public class ModelRecycler {
    private int type;
    private String name;
    private String value;
    private boolean judgeSelected;

    public ModelRecycler() {}

    public ModelRecycler(int type, String name, String value, boolean judgeSelected) {
        this.type = type;
        this.name = name;
        this.value = value;
        this.judgeSelected = judgeSelected;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setJudgeSelected(boolean judgeSelected) {
        this.judgeSelected = judgeSelected;
    }

    public boolean isJudgeSelected() {
        return judgeSelected;
    }
}
