package com.android.zore3x.newspaper.model.api;

import com.android.zore3x.newspaper.model.Source;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseSource {

    @SerializedName("status")
    private String mStatus;
    @SerializedName("sources")
    private List<Source> mSourceList;

    public ResponseSource(String status, List<Source> sourceList) {
        mStatus = status;
        mSourceList = sourceList;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public List<Source> getSourceList() {
        return mSourceList;
    }

    public void setSourceList(List<Source> sourceList) {
        mSourceList = sourceList;
    }
}
