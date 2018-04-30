package com.example.fady.socialnetwork;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


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
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.user_list_item,parent,false);
        }

        TextView UserName=listItemView.findViewById(R.id.name);
        UserName.setText("User Name : "+ CurrentUser.getName());


        TextView UserGender=listItemView.findViewById(R.id.gender);
        int x=CurrentUser.getGender();
        String s;
        if(x==1)
            s="male";
        else if(x==2)
            s="female";
        else
            s="unknown";

        System.out.println(x);
        System.out.println(s);

        UserGender.setText("User Gender : " + s);

        TextView UserPosts=listItemView.findViewById(R.id.posts);
        UserPosts.setText("no of posts : "+CurrentUser.getPostsNumber());

        TextView UserFriends=listItemView.findViewById(R.id.friends);
        UserFriends.setText("no of friends : "+CurrentUser.getFriendsNumber());
        return listItemView;
    }
}