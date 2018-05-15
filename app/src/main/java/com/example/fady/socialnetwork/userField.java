package com.example.fady.socialnetwork;

/**
 * Created by Fady on 5/15/2018.
 */

public class userField {
    private String name;
    private String property;
    private int rank;

    public userField()
    {

    }
    public userField(String name, String property,int rank) {
        this.name = name;
        this.property = property;
        this.rank=rank;
    }
    public String getName()
    {
        return name;
    }
    public String getProperty()
    {
        return property;
    }
    public void setRank(int rank)
    {
        this.rank=rank;
    }
    public int getRank()
    {
        return rank;
    }
}
