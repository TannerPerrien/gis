package com.tannerperrien.gis;

import android.app.Application;
import android.content.Context;

import dagger.ObjectGraph;
import timber.log.Timber;

public class GISApplication extends Application {

    private ObjectGraph mApplicationGraph;

    public static GISApplication get(Context context) {
        return (GISApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // DI
        mApplicationGraph = ObjectGraph.create(Modules.list(this));

        // Timber Logging initialization
        Timber.plant(new Timber.DebugTree());
    }

    public ObjectGraph getApplicationGraph() {
        return mApplicationGraph;
    }

}
