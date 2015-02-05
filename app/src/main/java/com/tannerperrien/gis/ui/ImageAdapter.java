package com.tannerperrien.gis.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tannerperrien.gis.R;
import com.tannerperrien.gis.data.google.ImageSearchResult;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by tperrien on 2/5/15.
 */
public class ImageAdapter extends BaseAdapter {

    private LayoutInflater layoutInflater;

    private List<ImageSearchResult> images;

    private Picasso picasso;

    public ImageAdapter(Context context, List<ImageSearchResult> images, Picasso picasso) {
        layoutInflater = LayoutInflater.from(context);
        this.images = images;
        this.picasso = picasso;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.griditem_image, parent, false);
        }

        ImageSearchResult result = images.get(position);

        ImageView image = ButterKnife.findById(convertView, R.id.image);
        picasso.load(result.getUrl()).centerCrop().fit().into(image);

        return convertView;
    }
}
