package com.example.togata.flickster;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.togata.flickster.models.Config;
import com.example.togata.flickster.models.Movie;

import org.parceler.Parcels;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by togata on 6/29/18.
 */

public class ExpansionScreenActivity extends AppCompatActivity {
    Movie movie;
    TextView tvTitle;
    RatingBar rbVoteAverage;
    TextView tvOverview;
    ImageView ivBackdrop;
    Config config;
    TextView tvReleaseDate;
    TextView tvPopularity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expansion_screen);
        // resolve the view objects
        tvTitle = (TextView) findViewById(R.id.tvTitle2);
        tvOverview = (TextView) findViewById(R.id.tvOverview2);
        rbVoteAverage = (RatingBar) findViewById(R.id.ratingBar);
        ivBackdrop = (ImageView) findViewById(R.id.ivBackdrop2);
        tvPopularity = (TextView) findViewById(R.id.tvPopularity) ;
        tvReleaseDate = (TextView) findViewById(R.id.tvReleaseDate) ;

        // unwrap the movie passed in via intent, using its simple name as a key
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", movie.getTitle()));

        // set the title and overview
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        //tvPopularity.setText("Popularity: "+movie.getPopularity());
        tvReleaseDate.setText("Release Date: " + movie.getReleaseDate());

        // vote average is 0..10, convert to 0..5 by dividing by 2
        float voteAverage = (float) movie.getVoteAverage();
        rbVoteAverage.setRating(voteAverage = voteAverage > 0 ? voteAverage / 2.0f : voteAverage);

        config = (Config) Parcels.unwrap(getIntent().getParcelableExtra("config"));

        //Context context = (Context) Parcels.unwrap(getIntent().getParcelableExtra("context"));

        String imageUrl = config.getImageUrl(config.getBackdropSize(), movie.getBackdropPath());
        //load image using glide

        GlideApp.with(ExpansionScreenActivity.this)
                .load(imageUrl)
                .transform(new RoundedCornersTransformation(15, 0))
                .placeholder(R.drawable.flicks_backdrop_placeholder)
                .error(R.drawable.flicks_backdrop_placeholder)
                .into(ivBackdrop);
    }

    public void back(View v){
        Intent intent = new Intent(v.getContext(), MovieListActivity.class);
        startActivityForResult(intent, 0);


    }

    public void buyTickets(View v){
        Uri uri = Uri.parse("https://www.fandango.com/"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }

}
