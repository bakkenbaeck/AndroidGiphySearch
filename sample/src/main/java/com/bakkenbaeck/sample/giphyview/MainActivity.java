package com.bakkenbaeck.sample.giphyview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.bakkenbaeck.giphysearch.GiphySearch;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private final String DEFAULT_SEARCH = "make it rain!";
    private GiphySearch giphySearch;
    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        loadImages();
    }

    private void init() {
        initGiphySearch();
        initRecyclerView();
        initInput();
    }

    private void initInput() {
        final EditText input = (EditText) findViewById(R.id.main_activity__input);
        input.addTextChangedListener(this.textListener);
        input.setText(DEFAULT_SEARCH);
    }

    private void initGiphySearch() {
        this.recyclerAdapter = new RecyclerAdapter(this);
        this.giphySearch = new GiphySearch(this.recyclerAdapter);
    }

    private void initRecyclerView() {
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_activity__recycler_view);
        recyclerView.setHasFixedSize(true);

        final RecyclerView.LayoutManager recyclerLayoutManager = new GridLayoutManager(this, RecyclerAdapter.NUM_ROWS);
        recyclerView.setLayoutManager(recyclerLayoutManager);
        recyclerView.setAdapter(this.recyclerAdapter);
    }

    private void loadImages() {
        this.giphySearch.search(DEFAULT_SEARCH);
    }

    private final TextWatcher textListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { }

        private Timer timer=new Timer();
        private final long DELAY = 500; // milliseconds

        @Override
        public void afterTextChanged(final Editable s) {
            timer.cancel();
            timer = new Timer();
            timer.schedule(
                    new TimerTask() {
                        @Override
                        public void run() {
                            giphySearch.search(s.toString());
                        }
                    },
                    DELAY
            );
        }
    };
}
