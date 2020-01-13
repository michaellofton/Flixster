package com.example.flixster.models;

import android.nfc.Tag;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//plain old java object = POJO
public class Movie {
    private String posterPath;
    private String backdropPath;
    private String title;
    private String overview;

    private static final String TAG = Movie.class.getSimpleName();

    /* Each line throws exception. Silly to do try catch here because
    we don't know how to handle this function's failure. As a result,
    we should Make the caller handle the exception */
    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        backdropPath = jsonObject.getString("backdrop_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < movieJsonArray.length(); ++i) {
            //movies.add(new Movie(movieJsonArray.getJSONObject(i)));
            JSONObject jsonMovie = movieJsonArray.getJSONObject(i);
            Movie movie = new Movie(jsonMovie);
            movies.add(movie);
            Log.i(TAG, "movie size: " + movies.size());
            Log.i(TAG, "movie title: " + movie.getTitle());
        }

        return movies;
    }

    /* Append posterpath and available sizes to base URL*/
    public String getPosterPath() {
        //return posterPath;
        String width = "342";
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }
}
