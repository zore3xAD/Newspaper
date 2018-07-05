package com.android.zore3x.newspaper.model.api.Endpoints;

import com.android.zore3x.newspaper.model.Response;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Everything {

    @GET("everything")
    Observable<Response> getEverything(@Query("page") int page, @Query("pageSize") int pageSize,
                                       @Query("q") String q, @Query("sources") String sources,
                                       @Query("domains") String domains, @Query("from") String from,
                                       @Query("to") String to, @Query("language") String language,
                                       @Query("sortBy") String sortBy);
}
