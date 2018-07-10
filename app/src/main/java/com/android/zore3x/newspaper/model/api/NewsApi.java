package com.android.zore3x.newspaper.model.api;

import com.android.zore3x.newspaper.model.Response;
import com.android.zore3x.newspaper.model.api.Endpoints.Everything;
import com.android.zore3x.newspaper.model.api.Endpoints.Sources;
import com.android.zore3x.newspaper.model.api.Endpoints.TopHeadlines;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsApi {

    public static final String BASE_URL = "https://newsapi.org/v2/";

    private TopHeadlines mTopHeadlinesService;
    private Everything mEverythingService;
    private Sources mSourcesService;

    public NewsApi(String apiKey) {

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(new OkHttpClient.Builder()
                        .addInterceptor(new HeaderInterceptor(apiKey))
                        .build())
                .build();

        mTopHeadlinesService = retrofit.create(TopHeadlines.class);
        mEverythingService = retrofit.create(Everything.class);
        mSourcesService = retrofit.create(Sources.class);
    }

    public Observable<Response> getTopHeadlines(int page, int pageSize, String country, String category, String source, String q) {
        return mTopHeadlinesService.getTopHeadlines(page, pageSize, country, category, source, q);
    }

    public Observable<Response> getEverything(int page, int pageSize, String q, String sources,
                                              String domains, String from, String to,
                                              String language, String sortBy) {
        return mEverythingService.getEverything(page, pageSize, q, sources, domains, from, to, language, sortBy);
    }

    public Observable<ResponseSource> getSources() {
        return mSourcesService.getSources();
    }
}
