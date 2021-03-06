package com.example.togata.flickster;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.togata.flickster.models.Config;
import com.example.togata.flickster.models.Movie;

import org.parceler.Parcels;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by togata on 6/27/18.
 */


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    ArrayList<Movie> movies;
    Config config;
    Context context;
    //private static RecyclerViewClickListener itemListener;


    public MovieAdapter(ArrayList<Movie> param){
        movies = param;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View movieView = inflater.inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.tvTitle.setText(movie.getTitle());
        holder.tvOverview.setText(movie.getOverview());

        boolean isPortrait = context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;

        if (isPortrait){
            String imageUrl = config.getImageUrl(config.getPosterSize(), movie.getPosterPath());
            //load image using glide

            GlideApp.with(context)
                    .load(imageUrl)
                    .transform(new RoundedCornersTransformation(15, 0))
                    .placeholder(R.drawable.flicks_movie_placeholder)
                    .error(R.drawable.flicks_movie_placeholder)
                    .into(holder.ivPoster);
        }

        else{
            String imageUrl = config.getImageUrl(config.getBackdropSize(), movie.getBackdropPath());
            //load image using glide

            GlideApp.with(context)
                    .load(imageUrl)
                    .transform(new RoundedCornersTransformation(15, 0))
                    .placeholder(R.drawable.flicks_backdrop_placeholder)
                    .error(R.drawable.flicks_backdrop_placeholder)
                    .into(holder.ivBackdrop);
        }



    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public Config getConfig() {
        return config;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ivPoster;
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivBackdrop;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPoster = (ImageView) itemView.findViewById(R.id.ivPoster);
            ivBackdrop = (ImageView) itemView.findViewById(R.id.ivBackdrop);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvOverview = (TextView) itemView.findViewById(R.id.tvOverview);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            // make sure the position is valid, i.e. actually exists in the view
            if (position != RecyclerView.NO_POSITION) {
                // get the movie at the position, this won't work if the class is static
                Movie movie = movies.get(position);
                // create intent for the new activity
                Intent intent = new Intent(context, ExpansionScreenActivity.class);
                // serialize the movie using parceler, use its short name as a key
                intent.putExtra(Movie.class.getSimpleName(), Parcels.wrap(movie));
                intent.putExtra("config", Parcels.wrap(config));
                //intent.putExtra("context", Parcels.wrap(context));
                // show the activity
                context.startActivity(intent);
            }


        }
    }





}
