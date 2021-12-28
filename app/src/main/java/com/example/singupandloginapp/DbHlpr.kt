package com.example.singupandloginapp
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHlpr(context: Context?) : SQLiteOpenHelper(context, "detailesrg.db", null, 1) {
    var sqlitedb : SQLiteDatabase = writableDatabase

    override fun onCreate(db: SQLiteDatabase?) {
        if (db != null){
            db.execSQL("create table registers(Username text, Mobile text, Location text, Password text)")
        }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, oldVersion: Int, newVersion: Int) { }

    // save function to save the data in DB
    fun save(uname: String, mobile:String, location: String, passwrd: String): Long{
        val cv = ContentValues()
        cv.put("Username",uname)
        cv.put("Mobile",mobile)
        cv.put("Location",location)
        cv.put("Password",passwrd)
        var svdata =  sqlitedb.insert("registers",null,cv)
        return svdata
    }

    // function to show information and retrieve mobile & location by username
    @SuppressLint("Range")
    fun rtrvinfo(uname: String): String {
        var c : Cursor = sqlitedb.query("registers", null,"Username=?",
            arrayOf(uname),null,null,null)

        println(c.count)


        c.moveToFirst()

        println(c.getString(c.getColumnIndex("Mobile")))

        var uinfo = c.getString(c.getColumnIndex("Mobile"))+"\n"+ c.getString(c.getColumnIndex("Location"))

        return uinfo
    }

    //function check if username in DB will be retrieve password
    @SuppressLint("Range")
    fun validatepass(uname: String):String{
        var c : Cursor = sqlitedb.query("registers", null,"Username=?",
            arrayOf(uname),null,null,null)

        if(c.count<1){
            return "user not exist"
        }
        c.moveToFirst()

        var pas = c.getString(c.getColumnIndex("Password"))

        return pas

    }
}