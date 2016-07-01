package com.bakkenbaeck.giphysearch;

import com.bakkenbaeck.giphysearch.model.GiphyData;

import java.util.List;

public interface GiphyListener {
    void onGifsLoaded(List<GiphyData> gifs);
}