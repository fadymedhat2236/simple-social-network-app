package com.example.fady.socialnetwork;

/**
 * Created by Fady on 4/16/2018.
 */

public class User {
    private String name;
    private int posts_no;
    private int friends_no;

    public User(String name,int posts_no,int friends_no)
    {
        this.name=name;
        this.posts_no=posts_no;
        this.friends_no=friends_no;
    }
    public User(String name)
    {
        this.name=name;
    }
    public String getName()
    {
        return this.name;

    }
    public int getPosts_no()
    {
        return this.posts_no;
    }
    public int getFriends_no()
    {
        return this.friends_no;
    }
}
