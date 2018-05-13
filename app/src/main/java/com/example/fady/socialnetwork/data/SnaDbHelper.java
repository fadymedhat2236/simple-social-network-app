package com.example.fady.socialnetwork.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class SnaDbHelper extends SQLiteOpenHelper
{
    public static final String LOG_TAG = SnaDbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "users.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;


    public SnaDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_USERS_TABLE =  "CREATE TABLE " + SnaContract.UsersEntry.TABLE_NAME + " ("
                + SnaContract.UsersEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SnaContract.UsersEntry.COLUMN_USER_NAME + " TEXT NOT NULL, "
                + SnaContract.UsersEntry.COLUMN_USER_GENDER + " INTEGER NOT NULL, "
                + SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_FRIENDS + " INTEGER NOT NULL DEFAULT 0, "
                + SnaContract.UsersEntry.COLUMN_USER_NUMBER_OF_POSTS + " INTEGER NOT NULL DEFAULT 0);";

        String SQL_CREATE_POSTS_TABLE =  "CREATE TABLE " + SnaContract.postsEntry.TABLE_NAME + " ("
                + SnaContract.postsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SnaContract.postsEntry.COLUMN_POST_TEXT + " TEXT NOT NULL, "
                + SnaContract.postsEntry.COLUMN_POST_OWNER_ID + " INTEGER, "
                + "FOREIGN KEY("+SnaContract.postsEntry.COLUMN_POST_OWNER_ID+")"
                + " REFERENCES " +SnaContract.UsersEntry.TABLE_NAME+"("+SnaContract.UsersEntry._ID+")" +");";


        String SQL_CREATE_POSTS_LIKERS_TABLE =  "CREATE TABLE " + SnaContract.postsLikes.TABLE_NAME + " ("
                + SnaContract.postsLikes._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SnaContract.postsLikes.COLUMN_POST_ID + " INTEGER, "
                + SnaContract.postsLikes.COLUMN_POST_LIKER_ID + " INTEGER, "
                + "FOREIGN KEY("+SnaContract.postsLikes.COLUMN_POST_ID+")"
                + " REFERENCES " +SnaContract.postsEntry.TABLE_NAME+"("+SnaContract.postsEntry._ID+"), "
                + "FOREIGN KEY("+SnaContract.postsLikes.COLUMN_POST_LIKER_ID+")"
                + " REFERENCES " +SnaContract.postsEntry.TABLE_NAME+"("+SnaContract.UsersEntry._ID+")"
                +");";

        String SQL_CREATE_FRIENDS_TABLE =  "CREATE TABLE " + SnaContract.friendsEntry.TABLE_NAME + " ("
                + SnaContract.friendsEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SnaContract.friendsEntry.COLUMN_USER + " INTEGER, "
                + SnaContract.friendsEntry.COLUMN_USER_FRIEND + " INTEGER, "
                + "FOREIGN KEY("+SnaContract.friendsEntry.COLUMN_USER+")"
                + " REFERENCES " +SnaContract.UsersEntry.TABLE_NAME+"("+SnaContract.UsersEntry._ID+"), "
                + "FOREIGN KEY("+SnaContract.friendsEntry.COLUMN_USER_FRIEND+")"
                + " REFERENCES " +SnaContract.UsersEntry.TABLE_NAME+"("+SnaContract.UsersEntry._ID+")"
                +");";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_USERS_TABLE);
        db.execSQL(SQL_CREATE_POSTS_TABLE);
        db.execSQL(SQL_CREATE_POSTS_LIKERS_TABLE);
        db.execSQL(SQL_CREATE_FRIENDS_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.

    }
}
