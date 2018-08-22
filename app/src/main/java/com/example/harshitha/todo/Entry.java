package com.example.harshitha.todo;



//Entry class to store the information by implementing Comparable interface to compare object at time of sorting.
public class Entry implements Comparable<Entry>
{

    // Labels table name
    public static final String TABLE = "Entry";

    // Labels Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_TITLE = "title";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_DATE = "date";
    public static final String KEY_STATUS = "status";

    // property help us to keep data
    public int entry_ID;
    public String title;
    public String description;
    public String dueDate;
    public int status;


    @Override
    //Method to compare current object and another object of similar kind.
    public int compareTo(Entry o)
    {
        //Spliting the Strings into String arrays.
        String[] currentObj = this.dueDate.split("/");
        String[] passedObj = o.dueDate.split("/");

        //Seperating the day,month and year from String Array.
        int currentDay=Integer.parseInt(currentObj[0]);
        int currentMonth=Integer.parseInt(currentObj[1]);
        int currentYear=Integer.parseInt(currentObj[2]);

        //Seperating the day,month and year from String Array.
        int passedDay=Integer.parseInt(passedObj[0]);
        int passedMonth=Integer.parseInt(passedObj[1]);
        int passedYear=Integer.parseInt(passedObj[2]);

        //Comparing parameters and returning values according to it.
        if(currentYear < passedYear)
            return -1;
        else if(currentYear > passedYear)
            return 1;
        else if(currentYear == passedYear && currentMonth < passedMonth)
            return -1;
        else if(currentYear == passedYear && currentMonth > passedMonth)
            return 1;
        else if(currentYear == passedYear && currentMonth == passedMonth && currentDay < passedDay)
            return -1;
        else if(currentYear == passedYear && currentMonth == passedMonth && currentDay > passedDay)
            return 1;

        //return 0 if both are same.
        return 0;
    }
}

