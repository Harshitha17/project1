package com.example.harshitha.todo;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import java.util.ArrayList;

//creating class by extending BaseAdapter to locate data into ListView.
public class CustomAdapter extends BaseAdapter
{

    ArrayList<Entry> arrayList;    //ArrayList of Entry objects which will be stored in ListView.
    Context context;    //Context of Current Activity.

    //Constructor.
    public CustomAdapter(Context context,ArrayList<Entry> list)
    {
        arrayList=list;
        this.context=context;
    }

    @Override
    //Method to get number of elements in the ArrayList.
    public int getCount() {
        return arrayList.size();
    }

    @Override
    //Method to get particular element from the ArrayList.
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    //Method to get ID of particular elements in the ArrayList.
    public long getItemId(int position) {
        return (long)position;
    }

    @Override
    //getView method to create the layout and setting Values in the ArrayList in the layout.
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //Setting View to the Layout file.
        convertView= LayoutInflater.from(context).inflate(R.layout.list_raw,null);

        //Setting TextViews and ImageView with their IDs in the layout file.
        TextView headDate=(TextView)convertView.findViewById(R.id.head_date);
        TextView taleDate=(TextView)convertView.findViewById(R.id.tale_date);
        TextView titleTV=(TextView)convertView.findViewById(R.id.title_tv);
        TextView descriptionTV=(TextView)convertView.findViewById(R.id.decription_tv);
        ImageView completionStatus=(ImageView)convertView.findViewById(R.id.completion_status);

        //Setting text to the TextViews. and image to ImageView.
        headDate.setText(arrayList.get(position).dueDate);
        taleDate.setText(arrayList.get(position).dueDate);
        titleTV.setText(arrayList.get(position).title);
        descriptionTV.setText(arrayList.get(position).description);

        //Setting image based on status of object in ImageView.
        if(arrayList.get(position).status==1)
            completionStatus.setImageResource(R.drawable.complete);
        else
            completionStatus.setImageResource(R.drawable.incomplete);

        return convertView;    //returning View.
    }
}

