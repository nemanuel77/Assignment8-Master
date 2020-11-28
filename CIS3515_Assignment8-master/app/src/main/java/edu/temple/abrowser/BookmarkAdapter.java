package edu.temple.abrowser;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowAnimationFrameStats;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;



import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class BookmarkAdapter extends BaseAdapter implements ListAdapter{
    private ArrayList<BookmarkListObject> myInternalArrayList;
    BookmarkListObject internalObject;
    private Context thisContext;
    ViewHolder viewHolder;

    FileInputStream theStream;
    BufferedReader theReader;
    File txtfile;

    SharedPreferences savedBookmarks;
    /*TextView myListTextView;
    ImageButton myListImgBtn;*/

    public BookmarkAdapter(Context context, ArrayList<BookmarkListObject> extList){
        thisContext = context;
        myInternalArrayList = extList;

    }

    @Override
    public int getCount() {
       return myInternalArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        //internalObject = new BookmarkListObject();
        internalObject = myInternalArrayList.get(position);

        //return myInternalArrayList.get(position);
        Log.d("ZZZ", internalObject.getThePageTitle());
        return internalObject.getThePageTitle();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

       if (convertView == null){

           viewHolder = new ViewHolder();
           LayoutInflater inflater = LayoutInflater.from(thisContext);
           convertView = inflater.inflate(R.layout.bookmark_list, parent,false);

           viewHolder.mylistTextView = (TextView) convertView.findViewById(R.id.listString);
           viewHolder.mylistImgBtn = (ImageButton) convertView.findViewById(R.id.btnDelFromList);

           convertView.setTag(viewHolder);

       }

       final ViewHolder viewHolder = (ViewHolder) convertView.getTag();
       //this is currently displaying the arraylist index position in memory
        // figure out how to show position's object's webtitle
       viewHolder.mylistTextView.setText(getItem(position).toString());

        /*savedBookmarks = thisContext.getSharedPreferences("bookmarks", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = savedBookmarks.edit();
        edit.putString("title", internalObject.getThePageTitle());
        edit.putString("url", internalObject.getTheUrl());
        edit.apply();*/


        viewHolder.mylistImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ARRAY", "SIZE IS: " +myInternalArrayList.size());

                AlertDialog.Builder dialog = new AlertDialog.Builder(thisContext);
                dialog.setMessage("Delete this Bookmark?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                txtfile = new File(thisContext.getFilesDir(), "webpages.txt");
                                try{
                                    deleteBookmark(position, txtfile);
                                }
                                catch(IOException e){
                                    e.printStackTrace();
                                }

                                myInternalArrayList.remove(position);
                                notifyDataSetChanged();

                                Log.d("ARRAY", "SIZE IS NOW: " +myInternalArrayList.size());

                                dialog.dismiss();

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                AlertDialog theAlert = dialog.create();
                theAlert.setTitle("Confirmation");
                theAlert.show();


                Toast.makeText(thisContext, "You clicked the button at position: " +position, Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.mylistTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                internalObject = myInternalArrayList.get(position);
                //Toast.makeText(thisContext, "URL: " +internalObject.getTheUrl(), Toast.LENGTH_SHORT).show();
                startTheActivity(internalObject.getTheUrl());



            }
        });


       return convertView;
    }

    public class ViewHolder{
        TextView mylistTextView;
        ImageButton mylistImgBtn;
    }

    public void startTheActivity(String theUrl){
        Intent myIntent = new Intent();
        myIntent.putExtra("theUrl", theUrl);
        ((Activity) thisContext).setResult(Activity.RESULT_OK, myIntent);
        ((Activity) thisContext).finish();
    }

    public void deleteBookmark(int pos, File file) throws IOException{
        theStream = new FileInputStream(file);
        theReader = new BufferedReader(new InputStreamReader(theStream));
        FileWriter theWriter = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(theWriter);

        String current;
        int count = 0;

        while ((current = theReader.readLine()) != null){
            count++;
            continue;

        }
        theWriter.close();
        theReader.close();
    }
}
