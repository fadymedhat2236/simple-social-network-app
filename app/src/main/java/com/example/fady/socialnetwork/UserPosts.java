package com.example.fady.socialnetwork;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import com.example.fady.socialnetwork.data.SnaContract;
import com.example.fady.socialnetwork.data.SnaDbHelper;

import java.util.ArrayList;

public class UserPosts extends AppCompatActivity {

    public static final int PET_LOADER = 0;

    private ArrayList<Post> posts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_posts);


        //to view the posts
        String name=getIntent().getExtras().getString("name","default");
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
                posts.add(new Post(names.get(i),text));
            }
        }




        PostAdapter postsArray=new PostAdapter(this,posts);
        ListView list=findViewById(R.id.postList);
        list.setAdapter(postsArray);
        c.close();

    }
}
