package com.example.flixster.utils;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.flixster.R;

public class MovieToast {

    public static void create(Context activityContext, String message, int duration) {
        if (activityContext == null) {
            return;
        }

        /**
         * Source: https://stackoverflow.com/questions/31175601/how-can-i-change-default-toast-message-color-and-background-color-in-android/31175913
         */

        Toast toast = Toast.makeText(activityContext, message, duration);
        View view = toast.getView();

        //Gets the actual oval background of the Toast then sets the colour filter
        int toastBackgroundColor = activityContext.getResources().getColor(R.color.colorAccent);
        view.getBackground().setColorFilter(toastBackgroundColor, PorterDuff.Mode.SRC_IN);

        //Gets the TextView from the Toast so it can be editted
        TextView text = view.findViewById(android.R.id.message);
        int toastTextColor = activityContext.getResources().getColor(R.color.colorMovieText);
        text.setTextColor(toastTextColor);

        toast.show();
    }
}
