package com.example.usermanage;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class registerActivity extends Activity {
    private DBHelper dbHelper;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        dbHelper = new DBHelper(this,"userData.db",null,1);
    }
    public void regClicked(View v){
        EditText username=(EditText)findViewById(R.id.ad_username);
        EditText password=(EditText)findViewById(R.id.ad_password);
        EditText en_password=(EditText)findViewById(R.id.en_password);
        String newname =username.getText().toString();
        String newpwd=password.getText().toString();
        String newepwd=en_password.getText().toString();
        if(newepwd != newpwd){
            Toast.makeText(this,"密码不一致，注册失败",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,registerActivity.class);
            startActivity(intent);
        }
        else if (CheckIsDataAlreadyInDBorNot(newname)) {
            Toast.makeText(this,"该用户名已被注册，注册失败",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,registerActivity.class);
            startActivity(intent);
        }
        else {
            if (register(newname, newpwd)) {
                Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(registerActivity.this,MainActivity.class);
                startActivity(intent);
            }
        }
    }

    public boolean register(String username,String password){
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",username);
        values.put("password",password);
        db.insert("userData",null,values);
        db.close();
        return true;
    }
    //检验用户名是否已存在
    public boolean CheckIsDataAlreadyInDBorNot(String value){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        String Query = "Select * from userData where name =?";
        Cursor cursor = db.rawQuery(Query,new String[] { value });
        if (cursor.getCount()>0){
            cursor.close();
            return  true;
        }
        cursor.close();
        return false;
    }
}
