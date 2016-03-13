package com.hello.sqlitedemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.hello.sqlitedemo.database.MyOpenHelper;

/**
 * Created by iwan on 16/3/14.
 */
public class TestCase extends AndroidTestCase {
    public void test(){
        MyOpenHelper oh = new MyOpenHelper(getContext(),"people.db",null,1);
        SQLiteDatabase db = oh.getWritableDatabase();

//        普通方式实现增删改查
//        insert(db);
//        delete(db);
//        update(db);
//        select(db);

//        API方式实现增删改查

//        insertApi(db);
//        deleteApi(db);
//        updateApi(db);
        queryApi(db);


    }

    private void queryApi(SQLiteDatabase db) {
        Cursor cursor = db.query("person",null,null,null,null,null,null,null);

        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String phone = cursor.getString(cursor.getColumnIndex("phone"));
            String salary = cursor.getString(cursor.getColumnIndex("salary"));

            System.out.println(name+":"+phone+":"+salary);
        }
    }

    private void updateApi(SQLiteDatabase db) {

        ContentValues values = new ContentValues();
        values.put("salary", 800000);

        int i = db.update("person",values,"name = ?",new String[]{"yanwanfu"});  //返回int

        System.out.println(i);

    }

    private void deleteApi(SQLiteDatabase db) {
//        int i = db.delete("person","name=? and _id=?",new String[]{"闫万福","4"});
//        System.out.println(i);  返回插入条数

        db.delete("person","name=? and _id=?",new String[]{"闫万福","4"});
    }

    private void insertApi(SQLiteDatabase db) {

        ContentValues values = new ContentValues();
        values.put("name","闫万福");
        values.put("phone","1350000");
        values.put("salary",18000);
        db.insert("person",null,values); //返回long
    }

    private void select(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("select name,salary from person",null);

        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String salary = cursor.getString(cursor.getColumnIndex("salary"));
            System.out.println(name+":"+salary);
        }
    }

    private void update(SQLiteDatabase db) {
        db.execSQL("update person set phone = ? where name = ?",
                new Object[]{1360000,"xingzhou"});
    }

    private void delete(SQLiteDatabase db) {
        db.execSQL("delete from person where name = ?",
                new Object[]{"xiaoyang"});
        db.close();
    }

    private void insert(SQLiteDatabase db) {
        db.execSQL("insert into person (name,salary,phone)values(?,?,?)",
                new Object[]{"xingzhou","800000",1890000});
        db.close();
    }


}
