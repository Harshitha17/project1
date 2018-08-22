package com.example.harshitha.todo;

        import android.content.Context;
        import android.content.Entity;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.os.DropBoxManager;

        import java.security.KeyStore;
        import java.util.Map;

public class DBHelper extends SQLiteOpenHelper
{

    //Creating final variables.
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "todo.application";

    public DBHelper(Context context){

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_STUDENT = "CREATE TABLE " + Entry.TABLE + "("
                + Entry.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Entry.KEY_TITLE + " TEXT, "
                +Entry.KEY_DESCRIPTION+" TEXT, "
                + Entry.KEY_DATE +" TEXT, "
                + Entry.KEY_STATUS + " INTEGER )";

        //Executing query by db.
        db.execSQL(CREATE_TABLE_STUDENT);


    }

    @Override
    //Method to upgrade the database, But  we left it empty because we don't need to upgrade the database
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
