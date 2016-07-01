package com.bakkenbaeck.giphysearch;

import android.util.Log;

import com.bakkenbaeck.giphysearch.model.GiphyResponse;
import com.bakkenbaeck.giphysearch.services.GiphyInterface;
import com.bakkenbaeck.giphysearch.services.GiphyService;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

public class GiphySearch {

    private final GiphyListener adapter;
    private final GiphyInterface giphyInterface;

    public GiphySearch(final GiphyListener adapter) {
        this.adapter = adapter;
        final GiphyService giphyService = new GiphyService();
        final GiphyInterface giphyInterface = giphyService.getApi();
        this.giphyInterface = giphyInterface;
    }

    public void search(final String query) {
        final Observable<GiphyResponse> call = giphyInterface.search(query);
        call.observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<GiphyResponse>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e("GiphyService", "onError. " + e);
            }

            @Override
            public void onNext(final GiphyResponse response) {
                adapter.onGifsLoaded(response.data);
            }
        });
    }
}
