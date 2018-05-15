package com.example.fady.socialnetwork;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class analysisOptions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis_options);

        LinearLayout likeSort=(LinearLayout)this.findViewById(R.id.likesSort);
        likeSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(v.getContext(),sortByLikes.class);
                startActivity(i);
            }
        });
        LinearLayout postSort=(LinearLayout)this.findViewById(R.id.postsSort);
        postSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(v.getContext(),sortByPosts.class);
                startActivity(i);
            }
        });

        LinearLayout friendSort=(LinearLayout)this.findViewById(R.id.friendsSort);
        friendSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(v.getContext(),sortByFriends.class);
                startActivity(i);
            }
        });

    }

}
