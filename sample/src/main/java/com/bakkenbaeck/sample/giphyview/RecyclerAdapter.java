package com.bakkenbaeck.sample.giphyview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bakkenbaeck.giphysearch.GiphyListener;
import com.bakkenbaeck.giphysearch.model.GiphyData;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.util.ArrayList;
import java.util.List;

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements GiphyListener {

    static final int NUM_ROWS = 2;
    private final Context context;
    private List<GiphyData> gifs;

    @Override
    public void onGifsLoaded(List<GiphyData> gifs) {
        this.gifs = gifs;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView single_item__gif;

        ViewHolder(View v) {
            super(v);
            single_item__gif = (ImageView) v.findViewById(R.id.single_item__gif);
        }
    }

    RecyclerAdapter(final Context context) {
        this.context = context;
        this.gifs = new ArrayList<>(0);
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent,
                                                         final int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_image, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,
                                 final int position) {
        final GiphyData giphyData = this.gifs.get(position);
        final String url = giphyData.images.fixed_width.url;
        final ImageView imageView = holder.single_item__gif;
        final GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageView);
        Glide.with(context)
                .load(url)
                .into(imageViewTarget);
    }

    @Override
    public int getItemCount() {
        final int overflow = this.gifs.size() % NUM_ROWS;
        return this.gifs.size() - overflow;
    }

}