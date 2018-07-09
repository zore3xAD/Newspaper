package com.android.zore3x.newspaper.model;

import com.android.zore3x.newspaper.model.api.SortBy;

public class TopHeadlinesQuery {

    private String mCountry;
    private String mCategory;
    private String mSources;
    private String mQ;
    private int mPage = 1;
    private int mPageSize = 5;

    public TopHeadlinesQuery() {
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;

        // cant mix with source param
        mSources = null;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;

        // cant mix with source param
        mSources = null;
    }

    public String getSources() {
        return mSources;
    }

    public void setSources(String sources) {
        mSources = sources;

        // cant mix with country and category param
        mCountry = null;
        mCategory = null;
    }

    public String getQ() {
        return mQ;
    }

    public void setQ(String q) {
        mQ = q;
    }

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        mPage = page;
    }

    public int getPageSize() {
        return mPageSize;
    }

    public void setPageSize(int pageSize) {
        mPageSize = pageSize;
    }
}
