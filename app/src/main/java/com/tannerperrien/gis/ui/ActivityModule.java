package com.tannerperrien.gis.ui;

import android.content.Context;

import com.tannerperrien.gis.AppModule;
import com.tannerperrien.gis.annotations.ForActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
    addsTo = AppModule.class,
    injects = {
        MainActivity.class
    }
)
public class ActivityModule {

    private final BaseActivity mActivity;

    public ActivityModule(BaseActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @Singleton
    @ForActivity
    Context provideActivityContext() {
        return mActivity;
    }

}
