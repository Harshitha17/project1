package com.example.harshitha.todo;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;

        import java.util.ArrayList;
        import java.util.Collections;
        import java.util.HashMap;


//class containing Methods to perform operations on DB, Like CRUD operations.
public class EntryRepo
{
    //Creating private reference of dbHelper.
    private DBHelper dbHelper;

    //Constructor.
    public EntryRepo(Context context)
    {
        dbHelper = new DBHelper(context);
    }

    //Method to insert an element in the DB.
    public int insert(Entry entry)
    {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        //Putting values in the ContentValue Object.
        values.put(Entry.KEY_TITLE,entry.title);
        values.put(Entry.KEY_DESCRIPTION,entry.description);
        values.put(Entry.KEY_DATE,entry.dueDate);
        values.put(Entry.KEY_STATUS,entry.status);

        // Inserting Row
        long student_Id = db.insert(Entry.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) student_Id;   //returning ID.
    }

    //Method to delete entry from database.
    public void delete(int entry_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Entry.TABLE, Entry.KEY_ID + "= ?", new String[] { String.valueOf(entry_Id) });
        db.close(); // Closing database connection
    }

    //Method to update the Entries of the database.
    public void update(Entry entry) {

        //Creating reference of writable db.
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //Creating object of ContentValues.
        ContentValues values = new ContentValues();

        //Putting values in the contentValues object.
        values.put(Entry.KEY_TITLE,entry.title);
        values.put(Entry.KEY_DESCRIPTION,entry.description);
        values.put(Entry.KEY_DATE,entry.dueDate);
        values.put(Entry.KEY_STATUS,entry.status);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Entry.TABLE, values, Entry.KEY_ID + "= ?", new String[] { String.valueOf(entry.entry_ID) });
        db.close(); // Closing database connection
    }


    //Method to retrive all Entries of DB.
    public ArrayList<Entry> getEntryList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        //Select query to retrive elements.
        String selectQuery =  "SELECT  " +
                Entry.KEY_ID + "," +
                Entry.KEY_TITLE + "," +
                Entry.KEY_DESCRIPTION+","+
                Entry.KEY_DATE+","+
                Entry.KEY_STATUS+
                " FROM " + Entry.TABLE;

        //Student student = new Student();
        ArrayList<Entry> entryList = new ArrayList<Entry>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        //Creating Entry objects and putting them into ArrayList.
        if (cursor.moveToFirst()) {
            do {
                Entry entry=new Entry();
                entry.entry_ID=cursor.getInt(cursor.getColumnIndex(Entry.KEY_ID));
                entry.title=cursor.getString(cursor.getColumnIndex(Entry.KEY_TITLE));
                entry.description=cursor.getString(cursor.getColumnIndex(Entry.KEY_DESCRIPTION));
                entry.dueDate=cursor.getString(cursor.getColumnIndex(Entry.KEY_DATE));
                entry.status=cursor.getInt(cursor.getColumnIndex(Entry.KEY_STATUS));
                entryList.add(entry);

            } while (cursor.moveToNext());
        }

        //Sorting the ArrayList by sort() method in Collections class.
        //We already compared the objects in the containing class only in compareTo() method.
        Collections.sort(entryList);

        //Closing the cursor and db objects.
        cursor.close();
        db.close();
        return entryList;    //returning ArrayList.

    }

    //Method to get Entry object by ID.
    public Entry getStudentById(int Id){
        //Getting reference of Readable DB.
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        //Select query.
        String selectQuery =  "SELECT  " +
                Entry.KEY_ID + "," +
                Entry.KEY_TITLE + "," +
                Entry.KEY_DESCRIPTION+","+
                Entry.KEY_DATE+","+
                Entry.KEY_STATUS+
                " FROM " + Entry.TABLE
                + " WHERE " +
                Entry.KEY_ID + "=?";// It's a good practice to use parameter ?, instead of concatenate string

        int iCount =0;
        Entry entry=new Entry();       //Creating object of Entry class.

        //Creating cursor for selectQuery.
        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        //Getting the values.
        if (cursor.moveToFirst()) {
            do {

                entry.entry_ID=cursor.getInt(cursor.getColumnIndex(Entry.KEY_ID));
                entry.title=cursor.getString(cursor.getColumnIndex(Entry.KEY_TITLE));
                entry.description=cursor.getString(cursor.getColumnIndex(Entry.KEY_DESCRIPTION));
                entry.dueDate=cursor.getString(cursor.getColumnIndex(Entry.KEY_DATE));
                entry.status=cursor.getInt(cursor.getColumnIndex(Entry.KEY_STATUS));

            } while (cursor.moveToNext());
        }

        //closing objects of db and cursor.
        cursor.close();
        db.close();
        return entry;   //returning entry.
    }

    //Method to retrive Entries whose status is 1 from DB.
    public ArrayList<Entry> getCompletedEntryList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        //Select query to retrive elements.
        String selectQuery =  "SELECT  " +
                Entry.KEY_ID + "," +
                Entry.KEY_TITLE + "," +
                Entry.KEY_DESCRIPTION+","+
                Entry.KEY_DATE+","+
                Entry.KEY_STATUS+
                " FROM " + Entry.TABLE;

        //Student student = new Student();
        ArrayList<Entry> entryList = new ArrayList<Entry>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        //Creating Entry objects and putting them into ArrayList.
        if (cursor.moveToFirst()) {
            do {
                if(cursor.getInt(cursor.getColumnIndex(Entry.KEY_STATUS))==1) {
                    Entry entry = new Entry();
                    entry.entry_ID = cursor.getInt(cursor.getColumnIndex(Entry.KEY_ID));
                    entry.title = cursor.getString(cursor.getColumnIndex(Entry.KEY_TITLE));
                    entry.description = cursor.getString(cursor.getColumnIndex(Entry.KEY_DESCRIPTION));
                    entry.dueDate = cursor.getString(cursor.getColumnIndex(Entry.KEY_DATE));
                    entry.status = cursor.getInt(cursor.getColumnIndex(Entry.KEY_STATUS));
                    entryList.add(entry);
                }

            } while (cursor.moveToNext());
        }

        //Sorting the ArrayList by sort() method in Collections class.
        //We already compared the objects in the containing class only in compareTo() method.
        Collections.sort(entryList);

        //Closing the cursor and db objects.
        cursor.close();
        db.close();
        return entryList;   //returning ArrayList.

    }


}
