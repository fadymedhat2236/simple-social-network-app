package com.example.fady.socialnetwork;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fady.socialnetwork.data.SnaContract;
import com.example.fady.socialnetwork.data.SnaDbHelper;

import java.util.ArrayList;

/**
 * Created by Fady on 5/15/2018.
 */

public class Graph {
    private Node parent;
    private ArrayList<Node> network;
    public Graph(Node parent)
    {
        this.parent=parent;
        parent.setDistance(-1);
        network=new ArrayList<>();
    }
    public void addToNetwork(Node n)
    {
        this.network.add(n);
    }
    public void constructGraph(SQLiteDatabase db)
    {
        //getting parent friends
        String[] projection1={SnaContract.friendsEntry.COLUMN_USER_FRIEND};
        String[] selArgs1={Integer.toString(parent.getId())};
        Cursor c1=db.query(
                SnaContract.friendsEntry.TABLE_NAME,
                projection1,SnaContract.friendsEntry.COLUMN_USER+"=?",
                selArgs1,null,null,null
        );
        while(c1.moveToNext())
        {
            int friendColumnId=c1.getColumnIndex(SnaContract.friendsEntry.COLUMN_USER_FRIEND);
            int friendId=c1.getInt(friendColumnId);
            for(int i=0;i<network.size();i++)
            {
                if(network.get(i).getId()==friendId)
                {
                    parent.connect(network.get(i));
                    network.get(i).connect(parent);
                    //setting the parent friends distance to 0
                    network.get(i).setDistance(0);
                    break;
                }
            }
        }
        c1.close();
        //getting network friends
        for (int i=0;i<network.size();i++)
        {
            String[] projection2={SnaContract.friendsEntry.COLUMN_USER_FRIEND};
            String[] selArgs2={Integer.toString(network.get(i).getId())};
            Cursor c2=db.query(
                    SnaContract.friendsEntry.TABLE_NAME,
                    projection2,SnaContract.friendsEntry.COLUMN_USER+"=?",
                    selArgs2,null,null,null
            );
            while(c2.moveToNext())
            {
                int friendColumnId=c2.getColumnIndex(SnaContract.friendsEntry.COLUMN_USER_FRIEND);
                int friendId=c2.getInt(friendColumnId);
                for(int j=0;j<network.size();j++)
                {
                    if(network.get(i).getId()==network.get(j).getId())
                    {
                        continue;
                    }
                    if(network.get(j).getId()==friendId)
                    {
                        network.get(i).connect(network.get(j));
                        network.get(j).connect(network.get(i));
                        break;
                    }
                }
            }
            c2.close();
        }
        ArrayList<Node> queue=new ArrayList<>();
        queue.addAll(parent.getFriends());
        parent.setVisit(true);
        while(queue.size()>0)
        {
            queue.get(0).setVisit(true);
            Node current=queue.get(0);
            queue.remove(0);
            for(int i=0;i<current.getFriends().size();i++)
            {
             if(current.getFriends().get(i).visit==false)
             {
                 if(current.getFriends().get(i).getDistance()==Integer.MAX_VALUE)
                 {
                     current.getFriends().get(i).setDistance(current.getDistance()+1);
                     queue.add(current.getFriends().get(i));
                 }
             }
             else
             {
                 if(current.getFriends().get(i).getDistance()==Integer.MAX_VALUE)
                 {
                     current.setDistance(current.getDistance()+1);
                 }
                 else if(current.getFriends().get(i).getDistance()>(current.getDistance()+1))
                 {
                     current.getFriends().get(i).setDistance(current.getDistance()+1);
                 }
             }
            }
        }
    }
    public ArrayList<Node> getNear()
    {
        ArrayList<Node> suggestions=new ArrayList<>();
        int far=getFarDistance();
        for(int i=1;i<=far;i++)
        {
            for(int j=0;j<network.size();j++)
            {
                if(network.get(j).getDistance()==i)
                {
                    suggestions.add(network.get(j));

                }
            }
        }

        return suggestions;
    }
    public int getFarDistance()
    {
        int distance=0;
        for (int i=0;i<network.size();i++)
        {
            if((distance<network.get(i).getDistance())&&(network.get(i).getDistance()!=Integer.MAX_VALUE))
            {
                distance=network.get(i).getDistance();
            }
        }
        return distance;
    }
}
