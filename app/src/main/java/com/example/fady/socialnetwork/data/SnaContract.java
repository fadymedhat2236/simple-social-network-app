package com.example.fady.socialnetwork.data;

import android.net.Uri;
import android.provider.BaseColumns;

public final class SnaContract
{
    public static final class UsersEntry implements BaseColumns
    {
        public final static String TABLE_NAME="users";

        public final static String _ID =BaseColumns._ID;
        public final static String COLUMN_USER_NAME ="name";
        public final static String COLUMN_USER_GENDER ="gender";
        public final static String COLUMN_USER_NUMBER_OF_FRIENDS ="nof"; //number of friends
        public final static String COLUMN_USER_NUMBER_OF_POSTS ="nop"; //number of posts

        public static final int  GENDER_UNKNOWN =0;
        public static final int  GENDER_MALE=1;
        public static final int  GENDER_FEMALE=2;
    }
    public static final class postsEntry implements BaseColumns
    {
        public final static String TABLE_NAME="posts";
        public final static String _ID=BaseColumns._ID;
        public final static String COLUMN_POST_TEXT="postText";//text of the post
        public final static String COLUMN_POST_OWNER_ID="postOwnerID";//foreign key to the users table
    }
    public static final class postsLikes implements BaseColumns
    {
        public final static String TABLE_NAME="postsLikes";
        public final static String _ID=BaseColumns._ID;
        public final static String COLUMN_POST_ID="postID";//foreign key to the posts table
        public final static String COLUMN_POST_LIKER_ID="postLikeID";//foreign key to the users table
    }
    public static final class friendsEntry implements BaseColumns
    {
        public final static String TABLE_NAME="friends";
        public final static String _ID=BaseColumns._ID;
        public final static String COLUMN_USER="user";//foreign key to the users table
        public final static String COLUMN_USER_FRIEND="userFriend";//foreign key to the users table
    }
}


