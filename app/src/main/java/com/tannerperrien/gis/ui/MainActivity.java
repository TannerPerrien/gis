package com.tannerperrien.gis.ui;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.squareup.picasso.Picasso;
import com.tannerperrien.gis.R;
import com.tannerperrien.gis.annotations.ForActivity;
import com.tannerperrien.gis.data.api.GoogleImageService;
import com.tannerperrien.gis.data.google.ImageSearchResponse;
import com.tannerperrien.gis.data.google.ImageSearchResult;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.SerialSubscription;
import timber.log.Timber;


public class MainActivity extends BaseActivity {

    @Inject
    @ForActivity
    Context context;

    @Inject
    Picasso picasso;

    @Inject
    GoogleImageService imageService;

    @InjectView(R.id.grid)
    GridView gridView;

    private ImageAdapter imageAdapter;

    private List<ImageSearchResult> images;

    private SerialSubscription imageSubscription = new SerialSubscription();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // View Injection
        ButterKnife.inject(this);

        // Init UI
        images = new ArrayList<ImageSearchResult>();
        imageAdapter = new ImageAdapter(context, images, picasso);
        gridView.setAdapter(imageAdapter);

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        imageSubscription.unsubscribe();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction()) || true) {
//            String query = intent.getStringExtra(SearchManager.QUERY);
String query = "monkey";
            imageSubscription.set(imageService.queryImages(query, 0).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ImageSearchResponse>() {
                    @Override
                    public void onCompleted() {
                        // TODO: Hide loading overlay
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO: Hide loading overlay

                        Timber.e(e, "Could not load images");
                    }

                    @Override
                    public void onNext(ImageSearchResponse imageSearchResponse) {
                        images.clear();
                        images.addAll(imageSearchResponse.getResults());
                        imageAdapter.notifyDataSetChanged();
                    }
                })
            );
        }
    }
}
