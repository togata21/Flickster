package com.example.togata.flickster.models;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

/**
 * Created by togata on 6/27/18.
 */

@Parcel
public class Movie {

    String title;
    String overview;
    String posterPath;
    String backdropPath;
    String releaseDate;
    Double popularity;
    double voteAverage;

    public Movie() {}

    public Movie(JSONObject object) throws JSONException{
        title = object.getString("title");
        overview = object.getString("overview");
        posterPath = object.getString("poster_path");
        backdropPath = object.getString("backdrop_path");
        voteAverage = object.getDouble("vote_average");
        popularity = object.getDouble("popularity");
        releaseDate = object.getString("release_date");
        int month = Integer.parseInt(releaseDate.substring(5,7));
        int year = Integer.parseInt(releaseDate.substring(0,4));
        int date = Integer.parseInt(releaseDate.substring(8,releaseDate.length()));
        releaseDate = month+"/"+date+"/"+year;


    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public Double getPopularity() {
        return popularity;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }
}
