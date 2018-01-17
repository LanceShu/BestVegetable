package com.example.lanceshu.bestvegetable.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.lanceshu.bestvegetable.Content;

/**
 * Created by Lance on 2018/1/17.
 */

public class MyDataBaseHelper extends SQLiteOpenHelper {

    private Context contex;

    public static final String CREATE_ORDER = "create table pOrder("
            +"id integer primary key autoincrement,"
            +"puser text,"
            +"pname text,"
            +"pprice text,"
            +"pnum text,"
            +"pimage text)";

    public MyDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.contex = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_ORDER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists pOrder");
        sqLiteDatabase.execSQL(CREATE_ORDER);
    }
}
