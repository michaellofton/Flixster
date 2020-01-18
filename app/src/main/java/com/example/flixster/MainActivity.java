package com.example.flixster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.adapters.MovieAdapter;
import com.example.flixster.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    private final String NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
    private final String TAG = MainActivity.class.getSimpleName();
    private List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movies = new ArrayList<>();

        //Obtain recycler view from XML to use with Java logic.
        RecyclerView rvMovies = findViewById(R.id.rvMovies);

        //Create the Adapter
        final MovieAdapter movieAdapter = new MovieAdapter(this, movies);

        //Set the adapter on the recycler view
        rvMovies.setAdapter(movieAdapter);

        //Set a Layout Manager on the recycler view (To determine how to layout views on screen)
        rvMovies.setLayoutManager(new LinearLayoutManager(this));

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(NOW_PLAYING_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess: Success");
                JSONObject jsonObject = json.jsonObject;
                try {
                    //Put in try/catch because key="results" may not exist in received json response.
                    //Using key "results." Look at HTTP response to determine what keys/values to expect.
                    JSONArray jsonArrayResults = jsonObject.getJSONArray("results");
                    Log.i(TAG, "onSuccess: jsonArray: " + jsonArrayResults);
                    //movies = Movie.fromJsonArray(jsonArrayResults);
                    //Don't create new reference, modify existing one so adapter points to right data.
                    movies.addAll(Movie.fromJsonArray(jsonArrayResults));
                    //if we more data, update adapter so it can show new data
                    movieAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    Log.e(TAG, "onSuccess: Encountered JSONException: ", e);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "onFailure: Failure. Oh no.");
                Toast.makeText(MainActivity.this, "Please connect to the internet", Toast.LENGTH_LONG).show();
            }
        });
    }
}
