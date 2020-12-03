package edu.temple.abrowser;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class BookmarkListObject implements Parcelable, Serializable {
    private String thePageTitle;
    private String theUrl;

    public BookmarkListObject(String theWebTitle, String url){
        this.thePageTitle = theWebTitle;
        this.theUrl = url;

    }

    public BookmarkListObject(){

    }


    public String getThePageTitle() {
        return thePageTitle;
    }

    public String getTheUrl() {
        return theUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(thePageTitle);
        dest.writeString(theUrl);
    }
    protected BookmarkListObject(Parcel in) {
        thePageTitle = in.readString();
        theUrl = in.readString();
    }

    public static final Creator<BookmarkListObject> CREATOR = new Creator<BookmarkListObject>() {
        @Override
        public BookmarkListObject createFromParcel(Parcel in) {
            return new BookmarkListObject(in);
        }

        @Override
        public BookmarkListObject[] newArray(int size) {
            return new BookmarkListObject[size];
        }
    };
}
