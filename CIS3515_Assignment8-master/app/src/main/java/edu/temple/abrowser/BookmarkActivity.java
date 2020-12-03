package edu.temple.abrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.FontsContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class BookmarkActivity extends AppCompatActivity {
    //D:\Android_SDK\CIS3515_Assignment8-master\CIS3515_Assignment8-master\app\src\main\java\edu\temple\abrowser
    String s = "D:\\Android_SDK\\CIS3515_Assignment8-master\\CIS3515_Assignment8-master\\app\\src\\main\\java\\edu\\temple\\abrowser\\bookmarks_txt";
    private static final String BOOKMARKS_FILE = "bookmarks.txt";
    ArrayList<BookmarkListObject> arrayList;
    ListView listView;
    Button myCloseActivityButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmark);



        FileInputStream fis = null;
        try{
            fis = getApplicationContext().openFileInput(BOOKMARKS_FILE);
            ObjectInputStream is = new ObjectInputStream(fis);
            Log.d("FFF", "Object stream is " +(is == null));
            arrayList = (ArrayList<BookmarkListObject>) is.readObject();
            Log.d("FFF","size of arrayList: " +arrayList.size());
            is.close();
            fis.close();

        }
        catch (Exception e){
            Log.d("FFF", "no file found");
            e.printStackTrace();
            arrayList = new ArrayList<>();
        }

        //Intent thisIntent = getIntent();
        /*thisIntent.getParcelableArrayListExtra("bookmarks");*/

        //arrayList = new ArrayList<>();
        //Log.d("AAA", "SIZE OF ACTIVITY LIST IS: " + arrayList.size());
        //arrayList = thisIntent.getParcelableArrayListExtra("bookmarks");
        //Log.d("AAA", "SIZE IS NOW: " + arrayList.size());

        BookmarkAdapter bookmarkAdapter = new BookmarkAdapter(this, arrayList);
        listView = (ListView) findViewById(R.id.bookmarkListView);

        listView.setAdapter(bookmarkAdapter);
        listView.setClickable(true);

        myCloseActivityButton = findViewById(R.id.btnCloseActivity);

         myCloseActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.equals(myCloseActivityButton)){
                    //Log.d("AAA", "YOU CLICKED THE CLOSE ACTIVITY BUTTON");
                    finish();
                }
            }
        });



    }
}