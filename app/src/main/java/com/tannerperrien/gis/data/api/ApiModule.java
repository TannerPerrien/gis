package com.tannerperrien.gis.data.api;

import com.google.gson.Gson;
import com.tannerperrien.gis.BuildConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Client;
import retrofit.converter.GsonConverter;
import timber.log.Timber;

@Module(
    library = true,
    complete = false
)
public class ApiModule {

    @Provides
    @Singleton
    Endpoint provideEndpoint() {
        return Endpoints.newFixedEndpoint("https://ajax.googleapis.com/ajax/services/search/images");
    }

    @Provides
    @Singleton
    RestAdapter provideRestAdapter(Endpoint endpoint, Client client, Gson gson) {
        return new RestAdapter.Builder()
            .setEndpoint(endpoint)
            .setConverter(new GsonConverter(gson))
            .setErrorHandler(new ErrorHandler() {
                @Override
                public Throwable handleError(RetrofitError cause) {
                    Timber.e(cause, "An error occurred while processing a request");
                    return cause;
                }
            })
            .setRequestInterceptor(new GoogleImageRequestInterceptor())
            .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
            .build();
    }

    @Provides
    @Singleton
    GoogleImageService provideUberService(RestAdapter restAdapter) {
        return restAdapter.create(GoogleImageService.class);
    }

}
