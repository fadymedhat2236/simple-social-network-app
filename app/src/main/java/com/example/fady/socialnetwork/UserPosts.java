package com.example.fady.socialnetwork;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fady.socialnetwork.data.SnaContract;
import com.example.fady.socialnetwork.data.SnaDbHelper;

import java.util.ArrayList;

public class UserPosts extends AppCompatActivity {
    private String name;
    public static final int PET_LOADER = 0;

    private ArrayList<Post> posts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_posts);


        //to view the posts
        this.name=getIntent().getExtras().getString("name","default");
        this.setTitle("this is "+name+"'s posts");
        SnaDbHelper dbHelper=new SnaDbHelper(this);
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        //getting id of the user from the name
        String [] projection={
                SnaContract.UsersEntry._ID
        };
        String[] selArgs1={name};

        Cursor c=db.query(SnaContract.UsersEntry.TABLE_NAME,projection,
                SnaContract.UsersEntry.COLUMN_USER_NAME+"=?",
                selArgs1,
                null,null,null);
        c.moveToFirst();
        int idColomnIndex=c.getColumnIndex(SnaContract.UsersEntry._ID);
        int userId=c.getInt(idColomnIndex);
        c.close();

        //reading the friends table where id==userID

        String []projection2={SnaContract.friendsEntry.COLUMN_USER_FRIEND};
        String[] selArgs2={Integer.toString(userId)};
        Cursor c2=db.query(SnaContract.friendsEntry.TABLE_NAME,
                projection2,
                SnaContract.friendsEntry.COLUMN_USER+"=?",selArgs2,null,null,null);
        int columnIndex=c2.getColumnIndex(SnaContract.friendsEntry.COLUMN_USER_FRIEND);
        ArrayList<String> IDs=new ArrayList<String>();
        IDs.add(Integer.toString(userId));
        while(c2.moveToNext())
        {
            String s=Integer.toString(c2.getInt(columnIndex));
            IDs.add(s);
        }
        c2.close();

        ArrayList<String> names=new ArrayList<String>();
        String []projection2_dash={
                SnaContract.UsersEntry.COLUMN_USER_NAME};
        for(int i=0;i<IDs.size();i++)
        {
            String []selArgs2_dash={IDs.get(i)};
            Cursor c2_dash=db.query(SnaContract.UsersEntry.TABLE_NAME,
                    projection2_dash,
                    SnaContract.UsersEntry._ID+"=?",
                    selArgs2_dash,
                    null,null,null
                    );
            c2_dash.moveToFirst();
            int x=c2_dash.getColumnIndex(SnaContract.UsersEntry.COLUMN_USER_NAME);
            names.add(c2_dash.getString(x));
            c2_dash.close();

        }





        posts=new ArrayList<Post>();

        String [] projection3={
                SnaContract.postsEntry._ID,
                SnaContract.postsEntry.COLUMN_POST_OWNER_ID,
                SnaContract.postsEntry.COLUMN_POST_TEXT
        };
        for(int i=0;i<IDs.size();i++)
        {
            String [] selArgs3= {IDs.get(i)};
            Cursor c3=db.query(SnaContract.postsEntry.TABLE_NAME,
                    projection3,
                    SnaContract.postsEntry.COLUMN_POST_OWNER_ID+"=?",
                    selArgs3,
                    null,null,null);
            if(c3.getCount()==0)
                continue;
            while(c3.moveToNext())
            {

                int nameColumnIndex=c3.getColumnIndex(SnaContract.postsEntry.COLUMN_POST_TEXT);
                String text=c3.getString(nameColumnIndex);
                int postIdColumnIndex=c3.getColumnIndex(SnaContract.postsEntry._ID);
                int postId=c3.getInt(postIdColumnIndex);
                //at this point we have the ID of a certain post->we should read the database where Id=postId
                    String []projection4={SnaContract.postsLikes.COLUMN_POST_LIKER_ID};
                    String [] selArgs4={Integer.toString(postId)};
                    Cursor c4=db.query(SnaContract.postsLikes.TABLE_NAME,
                            projection4,
                            SnaContract.postsLikes.COLUMN_POST_ID+"=?",
                            selArgs4,
                            null,null,null);
                    boolean isLiked=false;
                    while(c4.moveToNext())
                    {
                        int temp=c4.getColumnIndex(SnaContract.postsLikes.COLUMN_POST_LIKER_ID);
                        int postLikerId=c4.getInt(temp);
                        if(postLikerId==userId)
                        {
                            isLiked=true;
                            break;
                        }
                    }
                posts.add(new Post(names.get(i),text,c4.getCount(),isLiked));
                c4.close();
            }
            c3.close();
        }




        PostAdapter postsArray=new PostAdapter(this,posts);
        ListView list=findViewById(R.id.postList);
        list.setAdapter(postsArray);
        c.close();

    }
    public void likePost(View v)
    {
        LinearLayout postParentRow = (LinearLayout)v.getParent();
        Button childBtn=(Button) postParentRow.getChildAt(1);
        TextView childNumber=(TextView) postParentRow.getChildAt(2);
        String number=childNumber.getText().toString();
        String []numberseq=number.split(" ");
        int number_=Integer.parseInt(numberseq[0]);
        childNumber.setText((number_+1)+" Likes");
        childBtn.setBackgroundColor(0x55000000);
        childBtn.setText("Liked");
        childBtn.setClickable(false);
        //getting the post owner name
        LinearLayout postParent=(LinearLayout)postParentRow.getParent();


        SnaDbHelper dbHelper=new SnaDbHelper(this);
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        String[] projection1= {
                        SnaContract.UsersEntry._ID
                };
        String[] selArgs1={this.name};
        Cursor c1=db.query(SnaContract.UsersEntry.TABLE_NAME,
                projection1,SnaContract.UsersEntry.COLUMN_USER_NAME+"=?",
                selArgs1,null,null,null);
        c1.moveToNext();
        int idcolomnindex=c1.getColumnIndex(SnaContract.UsersEntry._ID);
        int userId=c1.getInt(idcolomnindex);
        c1.close();
        //getting the post id
        LinearLayout postSecondChild=(LinearLayout) postParent.getChildAt(1);
        TextView postTextView=(TextView)postSecondChild.getChildAt(0);
        String postText=postTextView.getText().toString();
        String[] projection2={
                SnaContract.postsEntry._ID
        };
        String[] selArgs2={postText};
        Cursor c2=db.query(SnaContract.postsEntry.TABLE_NAME,
                projection2,SnaContract.postsEntry.COLUMN_POST_TEXT+"=?",
                selArgs2,null,null,null);
        c2.moveToNext();
        idcolomnindex=c2.getColumnIndex(SnaContract.postsEntry._ID);
        int postId=c2.getInt(idcolomnindex);
        c2.close();
        //add them to postLikes database
        db=dbHelper.getWritableDatabase();
        ContentValues newLike=new ContentValues();
        newLike.put(SnaContract.postsLikes.COLUMN_POST_ID,postId);
        newLike.put(SnaContract.postsLikes.COLUMN_POST_LIKER_ID,userId);
        db.insert(SnaContract.postsLikes.TABLE_NAME,null,newLike);
    }
    @Override
    public void onBackPressed()
    {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
