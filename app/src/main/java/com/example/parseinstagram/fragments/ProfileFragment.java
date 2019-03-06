package com.example.parseinstagram.fragments;

import android.util.Log;

import com.example.parseinstagram.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends PostsFragment {

    @Override
    protected void queryPosts() {
        ParseQuery<Post> postQueuery = new ParseQuery<Post>(Post.class);
        postQueuery.include(Post.KEY_USER);
        postQueuery.setLimit(20);
        postQueuery.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        postQueuery.addDescendingOrder(Post.KEY_CREATED_AT);
        postQueuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "ERROR WITH QUERY");
                    e.printStackTrace();
                    return;
                }
                mPosts.addAll(posts);
                adapter.notifyDataSetChanged();

                for (int i = 0; i < posts.size(); i++) {
                    Post post = posts.get(i);
                    Log.d(TAG, "Posts " + post.getDescription() + ", username: " + post.getUser().getUsername());
                }
            }
        });
    }
}