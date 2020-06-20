package com.example.usermanage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this,"userData.db",null,1);
    }
    public void loginClicked(View v){
        EditText username=(EditText)findViewById(R.id.ad_username);
        EditText password=(EditText)findViewById(R.id.ad_password);
        String newname =username.getText().toString();
        String newpwd=password.getText().toString();

        if (login(newname,newpwd)) {
            Intent intent=new Intent(MainActivity.this,loginActivity.class);
            startActivity(intent);
        }
        else {
            Intent intent=new Intent(MainActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }

    public void registerClicked(View v){
        Intent intent=new Intent(MainActivity.this,registerActivity.class);
        startActivity(intent);
    }

    public boolean login(String username,String password){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "select * from userData where name=? and password=?";
        Cursor cursor = db.rawQuery(sql, new String[] {username, password});
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        return false;
    }
}
