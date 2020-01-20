package com.example.flixster.models;

import android.nfc.Tag;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
//plain old java object = POJO
public class Movie {
    private String posterPath;
    private String backdropPath;
    private String title;
    private String overview;
    private double rating;
    private int movieId;
    private String releaseDate;

    private static final String TAG = Movie.class.getSimpleName();

    // empty constructor needed by the Parceler library
    public Movie(){
    }

    /* Each line throws exception. Silly to do try catch here because
    we don't know how to handle this function's failure. As a result,
    we should Make the caller handle the exception */
    public Movie(JSONObject jsonObject) throws JSONException {
        posterPath = jsonObject.getString("poster_path");
        backdropPath = jsonObject.getString("backdrop_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
        rating = jsonObject.getDouble("vote_average");
        movieId = jsonObject.getInt("id");
        releaseDate = jsonObject.getString("release_date");
    }

    public static List<Movie> fromJsonArray(JSONArray movieJsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < movieJsonArray.length(); ++i) {
            //movies.add(new Movie(movieJsonArray.getJSONObject(i)));
            JSONObject jsonMovie = movieJsonArray.getJSONObject(i);
            Movie movie = new Movie(jsonMovie);
            movies.add(movie);
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

    //translate 10 star rating to 5 star rating
    public double getRating() {
        return rating / 2.0;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getReleaseDate() {
        return String.format("Release Date: %s", releaseDate);
    }
}
