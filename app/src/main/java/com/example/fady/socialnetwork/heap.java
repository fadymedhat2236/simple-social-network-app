package com.example.fady.socialnetwork;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;

import com.example.fady.socialnetwork.data.SnaContract;
import com.example.fady.socialnetwork.data.SnaDbHelper;

import java.util.ArrayList;

public class heap extends AppCompatActivity
{
    private ArrayList <heapNode> heapArray;

    public void initialize(Cursor c,String s)
    {
        heapArray=new ArrayList<heapNode>();
        if(s.equals("numberOfFriends"))
        {
            while(c.moveToNext())
            {
                int nameColumnIndex=c.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_NAME);
                int nofColumnIndex=c.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_FRIENDS);
                String name=c.getString(nameColumnIndex);
                int nof=c.getInt(nofColumnIndex);
                heapArray.add(new heapNode(nof,name));
                this.siftUp(heapArray.size()-1);
            }
            c.close();
        }
        else if(s.equals("numberOfPosts"))
        {
            while(c.moveToNext())
            {
                int nameColumnIndex=c.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_NAME);
                int nopColumnIndex=c.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_POSTS);
                String name=c.getString(nameColumnIndex);
                int nop=c.getInt(nopColumnIndex);
                heapArray.add(new heapNode(nop,name));
                this.siftUp(heapArray.size()-1);
            }
            c.close();
        }
    }
    public void initialize(Cursor c,ArrayList<String> names)
    {
        heapArray=new ArrayList<heapNode>();
        int [] numberOfLikes=new int[names.size()];
        for(int i=0;i<names.size();i++)
            numberOfLikes[i]=0;
        while(c.moveToNext())
        {
            int x=c.getColumnIndex(SnaContract.postsLikes.COLUMN_POST_LIKER_ID);
            int userID=c.getInt(x);
            userID--;
            numberOfLikes[userID]++;
        }
        c.close();
        for(int i=0;i<names.size();i++)
        {
            heapArray.add(new heapNode(numberOfLikes[i],names.get(i)));
            this.siftUp(heapArray.size()-1);
        }
    }
    private int getParentIndex(int index)
    {
    return (index - 1) / 2;
    }

    private void siftUp(int index)
    {
        if(index==0)
        {
            return;
        }
        int parentIndex=getParentIndex(index);
        while(index>0 && heapArray.get(parentIndex).getNumber() < heapArray.get(index).getNumber())
        {
            int tempNumber=heapArray.get(parentIndex).getNumber();
            String tempString=heapArray.get(parentIndex).getName();
            heapNode temp=new heapNode(tempNumber,tempString);
            heapArray.set(parentIndex,heapArray.get(index));
            heapArray.set(index,temp);
            index = getParentIndex(index);
            parentIndex = getParentIndex(index);
        }
    }
    public heapNode getMax()
    {
        if(heapArray.size()==1)
        {
            heapNode temp=new heapNode(heapArray.get(0).getNumber(),heapArray.get(0).getName());
            heapArray.remove(0);
            return temp;
        }
        heapNode temp=new heapNode(heapArray.get(0).getNumber(),heapArray.get(0).getName());
        heapNode lastElement=new heapNode(heapArray.get(heapArray.size()-1).getNumber(),heapArray.get(heapArray.size()-1).getName());
        heapArray.set(0,lastElement);
        heapArray.remove(heapArray.size()-1);
        siftDown(0);
        return temp;
    }
    public void siftDown(int index)
    {
        while(true)
        {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            int maxIndex = index;
            if(leftChildIndex<heapArray.size() &&
                    heapArray.get(leftChildIndex).getNumber() > heapArray.get(maxIndex).getNumber())
            {
                maxIndex = leftChildIndex;
            }
            if(rightChildIndex<heapArray.size() &&
                    heapArray.get(rightChildIndex).getNumber() > heapArray.get(maxIndex).getNumber())
            {
                maxIndex = rightChildIndex;
            }
            if(maxIndex!=index)
            {
                int tempNumber=heapArray.get(maxIndex).getNumber();
                String tempString=heapArray.get(maxIndex).getName();
                heapNode temp=new heapNode(tempNumber,tempString);
                heapArray.set(maxIndex,heapArray.get(index));
                heapArray.set(index,temp);
                index = maxIndex;
            }
            else
            {
                break;
            }
        }
    }
    public int getCount()
    {
        return heapArray.size();
    }
}

