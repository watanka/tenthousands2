package com.myproject.tenthousands2;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.media.TimedText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Adapter extends RecyclerView.Adapter<Adapter.AdapterViewHolder> {

    private ArrayList<item> mItemList;
    private OnItemClickListener mListener;

    private int load_time_done, load_total_time;
    private String load_activity, load_percentage;


    public interface OnItemClickListener{
        void onItemClick(int position);
        void settingClick(int position);
        void timerClick(int position);

    }

//    public void updateAdapter (ArrayList<item> items){
//        mItemList = items;
//
//    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class AdapterViewHolder extends RecyclerView.ViewHolder{

        public TextView mTextView, mSubTextView;
        public ProgressBar mPgbar;
        public ImageView timer_img;
        public ImageView setting_img;
        public TextView pgnum;



        public AdapterViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.textView);
            mPgbar = itemView.findViewById(R.id.progressbar);
            mPgbar.getProgressDrawable().setColorFilter(
                    Color.parseColor("#cc99ff"), android.graphics.PorterDuff.Mode.SRC_IN
            );
            pgnum = itemView.findViewById(R.id.pg_num);
            mSubTextView = itemView.findViewById(R.id.sub_txt);

            timer_img = itemView.findViewById(R.id.click_btn_img);
            setting_img = itemView.findViewById(R.id.setting_img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            setting_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.settingClick(position);

                        }
                    }
                }
            });

            timer_img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.timerClick(position);

                        }
                    }
                }
            });

        }
    }

    public Adapter(ArrayList<item> item) {
        mItemList = item;
    }


    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        AdapterViewHolder avh = new AdapterViewHolder(v, mListener);
        return avh;

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        item currentItem = mItemList.get(position);

        String activity = currentItem.getText();
        int time_done = (int) Math.round(currentItem.getTime());
        int total_time = (int) Math.round(currentItem.getTotal_time());
        String percentage = String.format("%.2f", (double)currentItem.getTime()/currentItem.getTotal_time()*100) +"%";
        holder.mTextView.setText(activity);
        //TODO: progress bar 게이지 int 변환시 문제가 생기는 것일 수도.
        holder.mPgbar.setMax(total_time);
        holder.mPgbar.setProgress(time_done);
        holder.pgnum.setText(percentage);
        if (currentItem.getTime() != 0) {
            holder.mSubTextView.setText(String.format("%d 시간 %d 분 달성 중 !!", (int) Math.round(currentItem.getTime() / 1000 / 3600), (int) Math.round((currentItem.getTime() % 3600000) / 60000)));
        }

//        saveData(activity, time_done, total_time, percentage);
//        loadData();
//        if (load_activity != null){
//            holder.mPgbar.setMax(load_total_time);
//            holder.mPgbar.setProgress(load_time_done);
//            holder.pgnum.setText(load_percentage);
//        }


    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

//    public void saveData(String activity, int time_done, int total_time, String percentage){
//        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs",MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString("percentage", percentage);
//        editor.putString("activity", activity);
//        editor.putInt("time_done", time_done);
//        editor.putInt("total_time", total_time);
//
//        editor.apply();
//    }
//
//    public void loadData(){
//        SharedPreferences sharedPreferences = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
//        load_activity = sharedPreferences.getString("activity","");
//        load_time_done = sharedPreferences.getInt("time_done", 0);
//        load_total_time = sharedPreferences.getInt("total_time", 0);
//        load_percentage = sharedPreferences.getString("percentage","");
//    }
//
//

//    public void updateViews() {
//
//    }

}
