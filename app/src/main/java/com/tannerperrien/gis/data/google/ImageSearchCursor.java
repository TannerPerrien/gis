package com.tannerperrien.gis.data.google;

import java.util.List;

/**
 * Created by tperrien on 2/5/15.
 */
public class ImageSearchCursor {

    private List<ImageSearchCursorPage> pages;

    private String estimatedResultCount;

    private int currentPageIndex;

    public List<ImageSearchCursorPage> getPages() {
        return pages;
    }

    public String getEstimatedResultCount() {
        return estimatedResultCount;
    }

    public int getCurrentPageIndex() {
        return currentPageIndex;
    }
}
