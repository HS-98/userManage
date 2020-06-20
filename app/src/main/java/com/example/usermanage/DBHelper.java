package com.example.usermanage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String CREATE_USERDATA="create table userData(" +
            "id integer primary key autoincrement,"
            +"nametext,"
            +"password text)";

    private Context mContext;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory, int version){
        super(context,name,cursorFactory,version);
        mContext=context;
    }

    public  void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USERDATA);
    }

    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        //onCreate(db);
    }
}
