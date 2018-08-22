package com.example.harshitha.todo;


        import android.content.Intent;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.Toast;

        import java.util.ArrayList;

//Class created by extending the AppCompatActivity class.
public class CompletedEntries extends AppCompatActivity
{
    //Creaing References of Elements used in the Layout.
    ImageView addBtn,completeBtn;
    ListView listView;
    Toolbar toolbar;

    @Override
    //onCreate method.
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.completed_list);   //Setting Content view.

        //Setting toolbar with its ID and settinng it as Support ActionBar.
        toolbar=(Toolbar)findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        //Setting references with their IDs.
        listView=(ListView)findViewById(R.id.list2);
        addBtn=(ImageView) findViewById(R.id.add_tool_btn2);
        completeBtn=(ImageView)findViewById(R.id.complete_tool_btn2);

        //on clicking PLUS button in toolbar.
        addBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(CompletedEntries.this,"Please Press back button and go in previous activity",Toast.LENGTH_SHORT).show();
            }
        });

        //on clicking LIKE button in toolbar.
        completeBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(CompletedEntries.this,"You are watching Completed Items.",Toast.LENGTH_SHORT).show();
            }
        });

        //Creaing object of EntryRepo.
        EntryRepo entryRepo = new EntryRepo(this);

        //Creating ArrayList of Completed Entries.
        final ArrayList<Entry> completedEntries = entryRepo.getCompletedEntryList();

        //If there are no completed entries, then we will finish the activity.
        if(completedEntries.isEmpty())
        {
            Toast.makeText(CompletedEntries.this,"There are no completed Items",Toast.LENGTH_SHORT).show();

            finish();
        }

        //listing Entries by Setting adapter of CustomAdapter to listView object.
        CustomAdapter adapter = new CustomAdapter(CompletedEntries.this,completedEntries);
        listView.setAdapter(adapter);

        //on long clicking the items of listview.
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                EntryRepo newRepo = new EntryRepo(CompletedEntries.this);

                //Deleting the long clicked Entry.
                newRepo.delete(completedEntries.get(position).entry_ID);

                Toast.makeText(CompletedEntries.this,"Item Deleted",Toast.LENGTH_SHORT).show();

                //Creating object of EntryRepo and listing Entries by Setting adapter of CustomAdapter to listView object.
                ArrayList<Entry> newCompletedEntries = newRepo.getCompletedEntryList();

                CustomAdapter newAdapter = new CustomAdapter(CompletedEntries.this,newCompletedEntries);

                listView.setAdapter(newAdapter);

                //If there are no completed entries, then we will finish the activity.
                if(newCompletedEntries.isEmpty())
                {
                    Toast.makeText(CompletedEntries.this,"There are no completed Items",Toast.LENGTH_SHORT).show();

                    Intent changeActivity_1=new Intent(CompletedEntries.this,MainActivity.class);
                    startActivity(changeActivity_1);

                    finish();
                }

                Intent changeActivity_2=new Intent(CompletedEntries.this,MainActivity.class);
                startActivity(changeActivity_2);

                return true;   //returning true.
            }
        });

        //on clicking list item.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Displaying Toast.
                Toast.makeText(CompletedEntries.this,"Please, Long Click to delete the Item.",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
