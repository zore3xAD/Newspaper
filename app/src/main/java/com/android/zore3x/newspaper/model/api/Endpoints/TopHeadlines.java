package com.android.zore3x.newspaper.model.api.Endpoints;

import com.android.zore3x.newspaper.model.Response;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TopHeadlines {

    @GET("top-headlines")
    Observable<Response> getTopHeadlines(@Query("page") int page, @Query("pageSize") int pageSize,
                                         @Query("country") String country, @Query("category") String category,
                                         @Query("sources") String sources, @Query("q") String q);
}
