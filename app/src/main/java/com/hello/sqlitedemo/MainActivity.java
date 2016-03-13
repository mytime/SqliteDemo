package com.hello.sqlitedemo;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hello.sqlitedemo.database.MyDB;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyDB db = new MyDB(this);
        SQLiteDatabase dbWrite = db.getWritableDatabase();

    }
}
