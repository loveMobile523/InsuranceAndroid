package com.whatscover.android.model;

import java.util.List;

public class ModelSuggestion {
    private String title;
    private List<ModelRecycler> modelRecyclers;

    public ModelSuggestion() {}

    public ModelSuggestion(String title, List<ModelRecycler> modelRecyclers) {
        this.title = title;
        this.modelRecyclers = modelRecyclers;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setModelRecyclers(List<ModelRecycler> modelRecyclers) {
        this.modelRecyclers = modelRecyclers;
    }

    public List<ModelRecycler> getModelRecyclers() {
        return modelRecyclers;
    }
}
