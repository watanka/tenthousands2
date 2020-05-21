package com.myproject.tenthousands2;

import android.os.Parcel;
import android.os.Parcelable;

public class item implements Parcelable {

    private int time;
    private String text;
    private int total_time;

    public item(int time, String text, int total_time) {
        this.time = time;
        this.text = text;
        this.total_time = total_time;
    }





    protected item(Parcel in) {
        time = in.readInt();
        text = in.readString();
        total_time = in.readInt();
    }

    public static final Creator<item> CREATOR = new Creator<item>() {
        @Override
        public item createFromParcel(Parcel in) {
            return new item(in);
        }

        @Override
        public item[] newArray(int size) {
            return new item[size];
        }
    };

    public int getTotal_time() {
        return total_time;
    }

    public void setTotal_time(int total_time) {
        this.total_time = total_time;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(time);
        dest.writeString(text);
    }
}