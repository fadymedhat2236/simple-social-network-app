package com.example.fady.socialnetwork;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fady.socialnetwork.data.SnaContract;
import com.example.fady.socialnetwork.data.SnaDbHelper;

public class deleteUserActivity extends AppCompatActivity {
    private EditText userName;
    private Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_user);

        userName=findViewById(R.id.to_delete_name);
        delete=findViewById(R.id.deleteUser);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete_user(userName.getText().toString().trim().toLowerCase());
            }
        });
    }
    public void delete_user(String name)
    {
        SnaDbHelper dbHelper=new SnaDbHelper(this);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        String []selectionArgs={name};
        long id=db.delete(SnaContract.UsersEntry.TABLE_NAME,SnaContract.UsersEntry.COLUMN_USER_NAME+"=?",selectionArgs);
        if (id != 0) {

            Toast.makeText(deleteUserActivity.this,"user "+name+" was removed",Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(deleteUserActivity.this,"no user in the system has the name "+name,Toast.LENGTH_SHORT).show();
        }
    }
}
