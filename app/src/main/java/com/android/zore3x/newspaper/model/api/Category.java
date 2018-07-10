package com.android.zore3x.newspaper.model.api;

public enum Category {

    BUSINESS("business"),
    ENTERTAINMENT("entertainment"),
    GENERAL("general"),
    HEALTH("health"),
    SCIENCE("science"),
    SPORTS("sports"),
    TECHNOLOGY("technology"),
    NONE("");

    private String mCategory;

    Category(String category){
        mCategory = category;
    }

    public String getCategory() {
        return mCategory;
    }

}
