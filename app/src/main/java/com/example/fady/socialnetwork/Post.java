package com.example.fady.socialnetwork;

import java.util.ArrayList;

/**
 * Created by Fady on 4/30/2018.
 */

public class Post {
    private String postText;
    private String name;
    private ArrayList<String> likersNames;
    public Post()
    {
        postText="";
        name="";
        likersNames=new ArrayList<String>();
    }
    public Post(String name,String text)
    {
        postText=text;
        this.name=name;
        likersNames=new ArrayList<String>();
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

    public ArrayList<String> getLikersNames() {
        return likersNames;
    }

    public void setLikersNames(ArrayList<String> likersNames) {
        this.likersNames = likersNames;
    }
    public int getPostLikesNo()
    {
        return likersNames.size();
    }
}
