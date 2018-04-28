package com.example.fady.socialnetwork;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Fady on 4/16/2018.
 */

public class UserAdapter extends ArrayAdapter<User> {
    public UserAdapter(Activity context, ArrayList<User> users)
    {
        super(context,0,users);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        User CurrentUser=getItem(position);
        View listItemView=convertView;

        if(listItemView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.activity_user,parent,false);
        }

        TextView UserName=listItemView.findViewById(R.id.username);
        UserName.setText("User Name : "+CurrentUser.getName());
        TextView UserPosts=listItemView.findViewById(R.id.UserPosts);
        UserPosts.setText("no of posts : "+CurrentUser.getPosts_no());
        TextView UserFriends=listItemView.findViewById(R.id.UserFriends);
        UserFriends.setText("no of friends : "+CurrentUser.getFriends_no());
        return listItemView;
    }
}
