package com.tannerperrien.gis.data.api;

import retrofit.RequestInterceptor;

public class GoogleImageRequestInterceptor implements RequestInterceptor {
    @Override
    public void intercept(RequestFacade request) {
        request.addQueryParam("v", "1.0");
    }
}
