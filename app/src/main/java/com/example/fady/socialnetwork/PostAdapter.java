package com.example.fady.socialnetwork;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class PostAdapter extends ArrayAdapter<Post>
{
    public PostAdapter(Activity context, ArrayList<Post> posts)
    {
        super(context,0,posts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Post currentPost=getItem(position);
        View listItemView=convertView;

        if(listItemView==null)
        {
            listItemView= LayoutInflater.from(getContext()).inflate(R.layout.post_item,parent,false);
        }


        TextView postOwnerName=listItemView.findViewById(R.id.postOwner);
        postOwnerName.setText(currentPost.getName());

        TextView postText=listItemView.findViewById(R.id.postText);
        postText.setText(currentPost.getPostText());

        TextView postNoOfLikes=listItemView.findViewById(R.id.postLikes);
        postNoOfLikes.setText(currentPost.getNumberOfLikes()+" Likes");

        Button likeButton=listItemView.findViewById(R.id.like_button);
        if(currentPost.isLiked())
        {
            likeButton.setVisibility(View.INVISIBLE);
        }

        return listItemView;
    }


}