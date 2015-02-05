package com.tannerperrien.gis.data;

import android.content.Context;
import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;
import com.tannerperrien.gis.BuildConfig;
import com.tannerperrien.gis.annotations.ForApplication;
import com.tannerperrien.gis.data.api.ApiModule;

import java.io.File;
import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.client.Client;
import retrofit.client.OkClient;
import timber.log.Timber;

@Module(
    library = true,
    complete = false,
    includes = {
        ApiModule.class
    })
public class DataModule {

    public static final int DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(@ForApplication Context context) {
        return createOkHttpClient(context);
    }

    @Provides
    @Singleton
    Client provideOkClient(OkHttpClient client) {
        return new OkClient(client);
    }

    @Provides
    @Singleton
    Picasso providePicasso(@ForApplication Context context, OkHttpClient client) {
        return new Picasso.Builder(context)
            .downloader(new OkHttpDownloader(client))
            .listener(new Picasso.Listener() {
                @Override
                public void onImageLoadFailed(Picasso picasso, Uri uri, Exception e) {
                    Timber.d(e, "Failed to load image: %s", uri);
                }
            })
            .build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder builder = new GsonBuilder();

        if (BuildConfig.DEBUG) {
            builder.setPrettyPrinting();
        }

        return builder.create();

    }

    static OkHttpClient createOkHttpClient(Context context) {
        OkHttpClient client = new OkHttpClient();
        File cacheDir = new File(context.getCacheDir(), "http_cache");
        try {
            Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
            client.setCache(cache);
        } catch (IOException e) {
            Timber.e(e, "Could not create http cache");
        }
        return client;
    }

}
