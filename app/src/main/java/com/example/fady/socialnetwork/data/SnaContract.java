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
}


