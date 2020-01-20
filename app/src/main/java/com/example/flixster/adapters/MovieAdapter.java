package com.example.flixster.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.flixster.activities.DetailActivity;
import com.example.flixster.R;
import com.example.flixster.activities.MainActivity;
import com.example.flixster.models.Movie;

import org.parceler.Parcels;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private final String TAG = MovieAdapter.class.getSimpleName();
    Activity activity;
    Context context; //Where view is being inflated from
    List<Movie> movies; //data to inflate views with

    public MovieAdapter(Activity activity, Context context, List<Movie> movies) {
        this.activity = activity;
        this.context = context;
        this.movies = movies;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(movieView);
        //Returning 1 inflated ViewHolder that is holding custom xml movie with item_movie.xml
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Get the movie at the passed in position
        Movie movie = movies.get(position);

        //Bind the movie data into the ViewHolder
        holder.bind(movie);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        RelativeLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivPoster = itemView.findViewById(R.id.ivPoster);
            container = itemView.findViewById(R.id.container);
        }


        public void bind(final Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());

            RequestOptions requestOptions = new RequestOptions();
            String imageUrl;

            //if portrait, get poster path(most common, save an extra check)
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                imageUrl = movie.getPosterPath();
                requestOptions = requestOptions
                        .placeholder(R.drawable.placeholder_portrait03);
//                        .override(ivPoster.getLayoutParams().width,
//                                ivPoster.getLayoutParams().height);
//                        .transform(new FitCenter());
//                        .transform(new CenterCrop());
            }
            else { //landscape, show backdrop path
                imageUrl = movie.getBackdropPath();
                requestOptions = requestOptions
                        .placeholder(R.drawable.placeholder_land0);
            }

            /**
             * With what context are we loading a REMOTE image into WHICH image view container by
             * applying what request options?
             * Using request options to REQUEST a placeholder (local picture) to be used until the
             * image is properly downloaded from the API
             */
            Glide.with(context)
                    .load(imageUrl)
                    .apply(requestOptions)
                    .into(ivPoster);


            //1. Register click listener on the whole ro
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //2. Navigate to a new activity on tap.
                    Intent detailActivityIntent = new Intent(context, DetailActivity.class);
                    detailActivityIntent.putExtra("movie", Parcels.wrap(movie));

                    //3. Enable View Transitions for better User Experience (UX).
//                    ActivityOptionsCompat options = ActivityOptionsCompat.
//                            makeSceneTransitionAnimation(activity, (View)tvTitle, "title")
//                            .makeSceneTransitionAnimation(activity, (View)tvOverview, "overview");
                    Pair<View, String> posterPair = Pair.create((View)ivPoster, "visual");
                    Pair<View, String> titlePair = Pair.create((View)tvTitle, "title");
                    Pair<View, String> overviewPair = Pair.create((View)tvOverview, "overview");
                    ActivityOptionsCompat options = ActivityOptionsCompat.
                            makeSceneTransitionAnimation(activity, titlePair, overviewPair);
                    context.startActivity(detailActivityIntent, options.toBundle());
                }
            });


        }
    }

}
