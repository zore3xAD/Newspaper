package com.android.zore3x.newspaper.model.api;

import com.android.zore3x.newspaper.model.Source;
import com.android.zore3x.newspaper.model.api.Category;
import com.android.zore3x.newspaper.model.api.Country;
import com.android.zore3x.newspaper.model.api.SortBy;

public class TopHeadlinesQuery {

    private Country mCountry;
    private Category mCategory = Category.GENERAL;
    private String mSources;
    private String mQ = "";
    private int mPage = 1;
    private int mPageSize = 5;

    public TopHeadlinesQuery() {
    }

    public Country getCountry() {
        return mCountry;
    }

    public void setCountry(Country country) {
        mCountry = country;

        // cant mix with source param
        mSources = "";
    }

    public Category getCategory() {
        return mCategory;
    }

    public void setCategory(Category category) {
        mCategory = category;

        // cant mix with source param
        mSources = "";
    }

    public String getSources() {
        return mSources;
    }

    public void setSources(String sources) {
        mSources = sources;

        // cant mix with country and category param
        mCountry = Country.NONE;
        mCategory = Category.NONE;
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
