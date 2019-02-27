package com.example.parseinstagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("ashley-instagram-codepath")
                .clientKey("instagramAS123")
                .server("https://ashley-instagram-codepath.herokuapp.com/parse").build());
    }
}
