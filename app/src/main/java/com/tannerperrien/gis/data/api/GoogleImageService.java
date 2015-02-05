package com.tannerperrien.gis.data.api;

import com.tannerperrien.gis.data.google.ImageSearchResponse;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

public interface GoogleImageService {

    @GET("/v1/products")
    Observable<ImageSearchResponse> queryImages(@Query("q") String query, @Query("start") int startIndex);

}
