package com.example.togata.flickster;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.togata.flickster.models.Config;
import com.example.togata.flickster.models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieListActivity extends AppCompatActivity {

    //constants

    public final static String API_BASE_URL = "https://api.themoviedb.org/3";
    public final static String API_KEY_PARAM = "api_key";
    //tag for logging this activity
    public final static String TAG = "MovieListActivitiy";


    //instances
    AsyncHttpClient client;
    ArrayList<Movie> movieList;
    RecyclerView rvMovies;
    MovieAdapter adapter;
    Config config;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        client = new AsyncHttpClient();
        movieList = new ArrayList<>();
        adapter = new MovieAdapter(movieList);
        rvMovies = (RecyclerView) findViewById(R.id.rvMovies);
        rvMovies.setLayoutManager(new LinearLayoutManager(this));
        rvMovies.setAdapter(adapter);
        getConfiguration();
    }

    public void getConfiguration(){
        String url = API_BASE_URL + "/configuration";
        RequestParams params = new RequestParams();
        params.put(API_KEY_PARAM, getString(R.string.api_key)) ;
        client.get(url, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    config = new Config(response);
                    Log.i(TAG, String.format("Loaded configuration with imageBaseUrl %s and posterSize %s", config.getImageBaseUrl(), config.getPosterSize()));
                    adapter.setConfig(config);

                } catch (JSONException e) {
                    logError("Failed parsing configuration", e, true);

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                logError("Failed getting configuration", throwable, true);
            }
        });
        getNowPlaying();

    }


    //get list of current movies
    private void getNowPlaying(){
        String url = API_BASE_URL + "/movie/now_playing";
        RequestParams params = new RequestParams();
        params.put(API_KEY_PARAM, getString(R.string.api_key));
        client.get(url, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray movies = response.getJSONArray("results");
                    int size = movies.length();
                    for (int i=0; i < size; i++){
                        Movie temp = new Movie(movies.getJSONObject(i));
                        movieList.add(temp);
                        adapter.notifyItemInserted(movieList.size()-1);
                    }
                    Log.i(TAG, String.format("Loaded %s movies", size));
                } catch (JSONException e) {
                    logError("Failed to parse now_playing movies", e, true);
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                logError("Failed to get data from now_playing endpoint", throwable, true);
            }
        });
    }

    private void logError(String message, Throwable error, boolean alertUser){
        Log.e(TAG, message, error);
        if (alertUser){
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
        }
    }

    public void changeScreen(View v, int position){
        Intent intent = new Intent(v.getContext(), ExpansionScreenActivity.class);
        String imageUrl = config.getImageUrl(config.getPosterSize(), movieList.get(position).getPosterPath());
        intent.putExtra("imageUrl", imageUrl);
        startActivityForResult(intent, 0);
    }

    public void buyTickets(View v){
        Uri uri = Uri.parse("https://www.fandango.com/"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }
}
