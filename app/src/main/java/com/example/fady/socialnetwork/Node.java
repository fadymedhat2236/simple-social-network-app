package com.example.fady.socialnetwork;

import java.util.ArrayList;

/**
 * Created by Fady on 5/15/2018.
 */

public class Node {
    private int id;
    private int distance;
    private ArrayList<Node> friends;
    Boolean visit;

    public Node(int id)
    {
        this.id=id;
        this.distance=Integer.MAX_VALUE;
        this.friends=new ArrayList<>();
        visit=false;
    }
    public Boolean isVisited()
    {
        return visit;
    }
    public void setVisit(Boolean n)
    {
        visit=n;
    }
    public void connect(Node n)
    {
        this.friends.add(n);
    }
    public ArrayList<Node> getFriends()
    {
        return friends;
    }
    public void setDistance(int distance)
    {
        this.distance=distance;
    }
    public int getId()
    {
        return id;
    }
    public int getDistance()
    {
        return distance;
    }
    public Boolean isFriend(Node n)
    {
        for(int i=0;i<this.friends.size();i++)
        {
            if(friends.get(i).id==n.id)
            {
                return true;
            }
        }
        return false;
    }
}
