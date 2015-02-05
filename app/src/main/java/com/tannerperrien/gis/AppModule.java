package com.tannerperrien.gis;

import android.app.Application;
import android.content.Context;

import com.tannerperrien.gis.annotations.ForApplication;
import com.tannerperrien.gis.data.DataModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
    library = true,
    includes = {
        DataModule.class
    }
)
public class AppModule {

    private final Application mApplication;

    public AppModule(Application application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    @ForApplication
    Context provideApplicationContext() {
        return mApplication;
    }

}
