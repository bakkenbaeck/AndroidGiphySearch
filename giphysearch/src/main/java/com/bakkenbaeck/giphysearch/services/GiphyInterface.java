package com.bakkenbaeck.giphysearch.services;


import com.bakkenbaeck.giphysearch.model.GiphyResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface GiphyInterface {

    @GET("/v1/gifs/search?api_key=dc6zaTOxFJmzC")
    Observable<GiphyResponse> search(
            @Query("q") String searchTerm);
}
