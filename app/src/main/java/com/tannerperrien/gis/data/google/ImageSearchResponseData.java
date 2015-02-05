package com.tannerperrien.gis.data.google;

import java.util.List;

/**
 * Created by tperrien on 2/5/15.
 */
public class ImageSearchResponseData {

    private List<ImageSearchResult> results;

    private ImageSearchCursor cursor;

    public List<ImageSearchResult> getResults() {
        return results;
    }

    public ImageSearchCursor getCursor() {
        return cursor;
    }
}
