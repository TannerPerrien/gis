package com.tannerperrien.gis.data.google;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by tperrien on 2/5/15.
 */
public class ImageSearchResponse {

    private ImageSearchResponseData responseData;

    private int responseStatus;

    public ImageSearchResponseData getResponseData() {
        return responseData;
    }

    public int getResponseStatus() {
        return responseStatus;
    }
}
