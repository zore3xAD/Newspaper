package com.android.zore3x.newspaper.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    @SerializedName("status")
    private String mStatus;
    @SerializedName("totalResults")
    private int mTotalResults;
    @SerializedName("articles")
    private List<Article> mArticleList;

    public Response(String status, int totalResults, List<Article> articleList) {
        mStatus = status;
        mTotalResults = totalResults;
        mArticleList = articleList;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public int getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(int totalResults) {
        mTotalResults = totalResults;
    }

    public List<Article> getArticleList() {
        return mArticleList;
    }

    public void setArticleList(List<Article> articleList) {
        mArticleList = articleList;
    }
}
