package com.myproject.tenthousands2;

import android.os.Parcel;
import android.os.Parcelable;

public class item implements Parcelable {

    private long time;
    private String text;
    private long total_time;

    public item(long time, String text, long total_time) {
        this.time = time;
        this.text = text;
        this.total_time = total_time;
    }





    protected item(Parcel in) {
        time = in.readLong();
        text = in.readString();
        total_time = in.readLong();
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

    public long getTotal_time() {
        return total_time;
    }

    public void setTotal_time(long total_time) {
        this.total_time = total_time;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
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
        dest.writeLong(time);
        dest.writeString(text);
        dest.writeLong(total_time);
    }
}