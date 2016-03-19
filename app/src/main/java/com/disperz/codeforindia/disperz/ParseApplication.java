package com.disperz.codeforindia.disperz;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

public class ParseApplication extends Application {
    public static final String YOUR_APPLICATION_ID = "s1z27FruCTuXoiupts6PXKsrYMYUnvm5W9QhpouM";
    public static final String YOUR_CLIENT_KEY = "HdN6B3JN6jGbGVUNJL8aFuRQhkrBBiiMXyNDImJr";

    @Override
    public void onCreate() {
        super.onCreate();

        try {
            // Add your initialization code here
            Parse.initialize(this, YOUR_APPLICATION_ID, YOUR_CLIENT_KEY);
            ParseInstallation.getCurrentInstallation().saveInBackground();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

