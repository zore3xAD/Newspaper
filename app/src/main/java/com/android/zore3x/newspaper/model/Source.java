package com.android.zore3x.newspaper.model;

import com.google.gson.annotations.SerializedName;

public class Source {

    @SerializedName("id")
    private String mId;
    @SerializedName("name")
    private String mName;

    public Source(String id, String name) {
        mId = id;
        mName = name;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    @Override
    public String toString() {
        return mName;
    }
}
