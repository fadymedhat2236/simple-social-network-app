package com.example.fady.socialnetwork;


import java.util.ArrayList;

public class User {
    private String name;
    private int gender;
    private int postsNumber;
    private int friendsNumber;
    private ArrayList<Post> userPosts;
    public User(String name,int gender,int friends_no,int posts_no)
    {
        this.name=name;
        this.gender=gender;
        this.postsNumber=posts_no;
        this.friendsNumber=friends_no;
        userPosts=new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getPostsNumber() {
        return postsNumber;
    }

    public void setPostsNumber(int postsNumber) {
        this.postsNumber = postsNumber;
    }

    public int getFriendsNumber() {
        return friendsNumber;
    }

    public void setFriendsNumber(int friendsNumber) {
        this.friendsNumber = friendsNumber;
    }

    public User(String name)
    {
        this.name=name;

    }

}