package com.example.flixster.activities;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.R;
import com.example.flixster.models.Movie;
import com.example.flixster.utils.MovieToast;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcels;

import okhttp3.Headers;

public class DetailActivity extends YouTubeBaseActivity {

    private final String YOUTUBE_API_KEY = "AIzaSyBI6vC63Io7pyuwkxlyH3FD6Mxd3m8fio4";
    private final String VIDEOS_URL = "https://api.themoviedb.org/3/movie/%d/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    private final String TAG = DetailActivity.class.getSimpleName();
    
    TextView tvTitle;
    TextView tvReleaseDate;
    RatingBar ratingBar;
    TextView tvOverview;
    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvTitle = findViewById(R.id.tvTitle);
        tvReleaseDate = findViewById(R.id.tvReleaseDate);
        ratingBar = findViewById(R.id.ratingBar);
        tvOverview = findViewById(R.id.tvOverview);
        youTubePlayerView = findViewById(R.id.player);

        Movie movie = Parcels.unwrap(getIntent().getParcelableExtra("movie"));
        tvTitle.setText(movie.getTitle());
        tvReleaseDate.setText(movie.getReleaseDate());
        tvOverview.setText(movie.getOverview());
        ratingBar.setRating((float)movie.getRating());

        AsyncHttpClient detailsClient = new AsyncHttpClient();
        detailsClient.get(String.format(VIDEOS_URL, movie.getMovieId()), new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Headers headers, JSON json) {
                        try {
                            JSONArray trailerResults = json.jsonObject.getJSONArray("results");
                            if (trailerResults.length() < 1) {
                                return;
                            }
                            String trailerVideoKey = trailerResults.getJSONObject(0).getString("key");
                            initializeYouTube(trailerVideoKey);

                        } catch (JSONException e) {
                            MovieToast.create(getApplicationContext(), "Error: Unable to load trailer", Toast.LENGTH_LONG);
                            //Toast.makeText(DetailActivity.this, "Unable to load trailer", Toast.LENGTH_LONG).show();
                            Log.e(TAG, "onSuccess: exception", e);
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                        MovieToast.create(getApplicationContext(), "Error: Unable to load trailer.\nCheck your internet connection", Toast.LENGTH_LONG);
                    }
                });
    }

    void initializeYouTube(final String trailerKey) {
        youTubePlayerView.initialize(YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG, "onInitializationSuccess");
                // do any work here to cue video, play video, etc.
                youTubePlayer.cueVideo(trailerKey);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                YouTubeInitializationResult youTubeInitializationResult) {
                Log.d(TAG, "onInitializationFailure");
                MovieToast.create(getApplicationContext(), "Error: Unable to load trailer", Toast.LENGTH_LONG);
                //Toast.makeText(DetailActivity.this, "Unable to load trailer", Toast.LENGTH_LONG).show();
            }
        });
    }



}
