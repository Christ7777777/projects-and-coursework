package com.example.drivewayparking.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drivewayparking.API.ApiClient;
import com.example.drivewayparking.Activity.UserRatingActivity;
import com.example.drivewayparking.Model.RatingResponse;
import com.example.drivewayparking.Model.User;
import com.example.drivewayparking.Model.UserRating;
import com.example.drivewayparking.R;

//import org.checkerframework.checker.units.qual.C;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The type User rating adapter.
 * @author: Varun Advani
 */
public class UserRatingAdapter extends RecyclerView.Adapter<UserRatingAdapter.UserRatingViewHolder> {
private final Context context;
private final List<RatingResponse> userRatingList;
private double total = 0;

    /**
     * Instantiates a new User rating adapter.
     *
     * @param context        the context
     * @param userRatingList the user rating list
     */
    public UserRatingAdapter(Context context, List<RatingResponse> userRatingList) {
        this.context = context;
        this.userRatingList = userRatingList;
    }

    @NonNull
    @Override
    public UserRatingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_ratings_view, parent, false);
        return new UserRatingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserRatingViewHolder holder, int position) {
          final RatingResponse list = userRatingList.get(position);
          holder.comments.setText(list.getComments());
          holder.accommodation_ratings.setText("" + list.getAccommodation() + "/5.0");
          holder.safety_ratings.setText("" + list.getSafety() + "/5.0");
          holder.responsiveness_ratings.setText("" + list.getResponsiveness() + "/5.0");
          total += (list.getAccommodation() + list.getResponsiveness() + list.getSafety())/3.0;

    }

    @Override
    public int getItemCount() {
        return userRatingList.size();
    }

    /**
     * Gets avg rating.
     *
     * @return the avg rating
     */
    public double getAvgRating() {return total / userRatingList.size();}

    /**
     * The type User rating view holder.
     */
    static class UserRatingViewHolder extends RecyclerView.ViewHolder{

        /**
         * The Comments.
         */
        TextView comments,
        /**
         * The Accommodation ratings.
         */
        accommodation_ratings,
        /**
         * The Safety ratings.
         */
        safety_ratings,
        /**
         * The Responsiveness ratings.
         */
        responsiveness_ratings,
        /**
         * The Delete rating.
         */
        deleteRating;
        /**
         * The User rating layout.
         */
        LinearLayout userRatingLayout;

        /**
         * Instantiates a new User rating view holder.
         *
         * @param itemView the item view
         */
        public UserRatingViewHolder(@NonNull View itemView) {
            super(itemView);
            comments = itemView.findViewById(R.id.textView_comments);
            accommodation_ratings = itemView.findViewById(R.id.textView_accommodation_rating);
            safety_ratings = itemView.findViewById(R.id.textView_safety_rating);
            responsiveness_ratings = itemView.findViewById(R.id.textView_responsiveness_rating);
            userRatingLayout = itemView.findViewById(R.id.linearLayout_userRatingList);

        }
    }
}
