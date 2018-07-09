package com.android.zore3x.newspaper.model;

import com.android.zore3x.newspaper.model.api.SortBy;

public class RequestParameter {

    private String mQ = "world news";
    private String mSources;
    private String mDomains;
    private String mFrom;
    private String mTo;
    private String mLanguage;
    private SortBy mSortBy = SortBy.PUBLISHED_AT;
    private int mPage = 1;
    private int mPageSize = 5;

    public RequestParameter() {
    }

    public String getQ() {
        return mQ;
    }

    public void setQ(String q) {
        mQ = q;
    }

    public String getSources() {
        return mSources;
    }

    public void setSources(String sources) {
        mSources = sources;
    }

    public String getDomains() {
        return mDomains;
    }

    public void setDomains(String domains) {
        mDomains = domains;
    }

    public String getFrom() {
        return mFrom;
    }

    public void setFrom(String from) {
        mFrom = from;
    }

    public String getTo() {
        return mTo;
    }

    public void setTo(String to) {
        mTo = to;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public void setLanguage(String language) {
        mLanguage = language;
    }

    public SortBy getSortBy() {
        return mSortBy;
    }

    public void setSortBy(SortBy sortBy) {
        mSortBy = sortBy;
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
