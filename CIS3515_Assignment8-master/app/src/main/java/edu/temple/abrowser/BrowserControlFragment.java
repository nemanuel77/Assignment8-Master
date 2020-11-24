package edu.temple.abrowser;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

public class BrowserControlFragment extends Fragment {

    private BrowserControlInterface browserActivity;

    public BrowserControlFragment() {}

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof BrowserControlInterface) {
            browserActivity = (BrowserControlInterface) context;
        } else {
            throw new RuntimeException("Cannot manage windows if interface not BrowserControlInterface implemented");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View l = inflater.inflate(R.layout.fragment_browser_control, container, false);
        final ImageButton newPageButton = l.findViewById(R.id.newPageButton);
        final ImageButton newBookmarkButton = l.findViewById(R.id.newBookmarkButton);
        final ImageButton myBookmarkList = l.findViewById(R.id.myBookmarkList);

        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.equals(newPageButton))
                    browserActivity.newPage();
            }
        };
        newPageButton.setOnClickListener(ocl);

        View.OnClickListener newBookmark = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.equals(newBookmarkButton))
                    Toast.makeText(getContext(), "You clicked the new Bookmark Button", Toast.LENGTH_SHORT).show();
            }
        };
        newBookmarkButton.setOnClickListener(newBookmark);

        View.OnClickListener myBookList = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (v.equals(myBookmarkList))
                    Toast.makeText(getContext(), "You clicked the Bookmark List Button", Toast.LENGTH_SHORT).show();
                 //either create an interface method to tell the main browser to initiate a jawn or do it here

                Intent newIntent = new Intent(getActivity(), BookmarkActivity.class);
                startActivity(newIntent);

            }
        };
        myBookmarkList.setOnClickListener(myBookList);

        return l;
    }

    interface BrowserControlInterface {
        void newPage();
    }
}