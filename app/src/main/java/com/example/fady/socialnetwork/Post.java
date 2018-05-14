package com.example.fady.socialnetwork;

import java.util.ArrayList;

/**
 * Created by Fady on 4/30/2018.
 */

public class Post {
    private String postText;
    private String name;
    private int numberOfLikes;
    private boolean isLiked;
    public Post()
    {
        postText="";
        name="";
        numberOfLikes=0;
        isLiked=false;
    }
    public Post(String name,String text,int numberOflikes,boolean isLiked)
    {
        postText=text;
        this.name=name;
        this.numberOfLikes=numberOflikes;
        this.isLiked=isLiked;
    }
    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
