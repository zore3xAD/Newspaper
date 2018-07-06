package com.android.zore3x.newspaper.model.api.Endpoints;

import com.android.zore3x.newspaper.model.api.ResponseSource;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Sources {

    @GET("sources")
    Observable<ResponseSource> getSources();

}
