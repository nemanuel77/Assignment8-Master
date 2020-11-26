package edu.temple.abrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.FontsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BookmarkActivity extends AppCompatActivity {
    ArrayList<BookmarkListObject> arrayList;
    ListView listView;
    Button myCloseActivityButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);

        Intent thisIntent = getIntent();
        /*thisIntent.getParcelableArrayListExtra("bookmarks");*/

        arrayList = new ArrayList<>();
        Log.d("AAA", "SIZE OF ACTIVITY LIST IS: " + arrayList.size());
        arrayList = thisIntent.getParcelableArrayListExtra("bookmarks");
        Log.d("AAA", "SIZE IS NOW: " + arrayList.size());

        BookmarkAdapter bookmarkAdapter = new BookmarkAdapter(this, arrayList);
        listView = (ListView) findViewById(R.id.bookmarkListView);
        listView.setAdapter(bookmarkAdapter);

        myCloseActivityButton = findViewById(R.id.btnCloseActivity);

        myCloseActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.equals(myCloseActivityButton)){
                    Log.d("AAA", "YOU CLICKED THE CLOSE ACTIVITY BUTTON");
                    finish();
                }
            }
        });




    }
}