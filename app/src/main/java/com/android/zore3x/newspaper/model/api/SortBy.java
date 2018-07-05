package com.android.zore3x.newspaper.model.api;

public enum SortBy {

    RELEVANCY("relevancy"),
    POPULARITY("popularity"),
    PUBLISHED_AT("publishedAt");

    private String mSortBy;

    SortBy(String sortBy){
        mSortBy = sortBy;
    }

    public String getSortBy() {
        return mSortBy;
    }
}
