package com.example.fady.socialnetwork;

/**
 * Created by Fady on 4/30/2018.
 */

public class Post {
    private String postText;
    private int ownerId;
    public Post(int id,String text)
    {
        postText=text;
        ownerId=id;
    }
}
