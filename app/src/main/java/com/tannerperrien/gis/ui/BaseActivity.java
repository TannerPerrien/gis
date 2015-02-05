package com.tannerperrien.gis.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.tannerperrien.gis.GISApplication;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Base activity which sets up a per-activity object graph and performs injection.
 */
public abstract class BaseActivity extends ActionBarActivity {

    private ObjectGraph activityGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inject activity graph
        GISApplication application = GISApplication.get(this);
        activityGraph = application.getApplicationGraph().plus(getModules().toArray());
        activityGraph.inject(this);
    }

    @Override
    protected void onDestroy() {
        activityGraph = null;

        super.onDestroy();
    }

    protected List<Object> getModules() {
        return Arrays.<Object>asList(new ActivityModule(this));
    }

}
