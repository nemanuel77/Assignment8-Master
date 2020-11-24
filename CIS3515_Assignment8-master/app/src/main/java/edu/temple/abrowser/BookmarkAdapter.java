package edu.temple.abrowser;

import android.content.Context;
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

import java.util.ArrayList;
import java.util.List;


public class BookmarkAdapter extends BaseAdapter implements ListAdapter{
    private ArrayList<String> myInternalArrayList;
    private Context thisContext;
    /*TextView myListTextView;
    ImageButton myListImgBtn;*/

    public BookmarkAdapter(Context context, ArrayList<String> extList){
        thisContext = context;
        myInternalArrayList = extList;

    }

    @Override
    public int getCount() {
       return myInternalArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return myInternalArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

       if (convertView == null){

           ViewHolder viewHolder = new ViewHolder();
           LayoutInflater inflater = LayoutInflater.from(thisContext);
           convertView = inflater.inflate(R.layout.bookmark_list, parent,false);

           viewHolder.mylistTextView = (TextView) convertView.findViewById(R.id.listString);
           viewHolder.mylistImgBtn = (ImageButton) convertView.findViewById(R.id.btnDelFromList);

           convertView.setTag(viewHolder);

       }

       ViewHolder viewHolder = (ViewHolder) convertView.getTag();
       viewHolder.mylistTextView.setText(getItem(position).toString());

        viewHolder.mylistImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ARRAY", "SIZE IS: " +myInternalArrayList.size());
                myInternalArrayList.remove(position);
                notifyDataSetChanged();
                Log.d("ARRAY", "SIZE IS NOW: " +myInternalArrayList.size());
                Toast.makeText(thisContext, "You clicked the button at position: " +position, Toast.LENGTH_SHORT).show();
            }
        });

       return convertView;
    }

    public class ViewHolder{
        TextView mylistTextView;
        ImageButton mylistImgBtn;
    }
}
