package com.tannerperrien.gis.data.google;

import java.util.List;

/**
 * Created by tperrien on 2/5/15.
 */
public class ImageSearchResponse {

    private List<ImageSearchResult> results;

    private ImageSearchCursor cursor;

    private int responseStatus;

    public List<ImageSearchResult> getResults() {
        return results;
    }

    public ImageSearchCursor getCursor() {
        return cursor;
    }

    public int getResponseStatus() {
        return responseStatus;
    }
}
