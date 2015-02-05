package com.tannerperrien.gis.data.google;

import java.util.List;

/**
 * Created by tperrien on 2/5/15.
 */
public class ImageSearchCursor {

    private List<ImageSearchCursorPage> pages;

    private int estimatedResultCount;

    private int currentPageIndex;

    public List<ImageSearchCursorPage> getPages() {
        return pages;
    }

    public int getEstimatedResultCount() {
        return estimatedResultCount;
    }

    public int getCurrentPageIndex() {
        return currentPageIndex;
    }
}
