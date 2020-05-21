package com.myproject.tenthousands2;

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

public class Adapter extends RecyclerView.Adapter<Adapter.AdapterViewHolder> {

    private ArrayList<item> mItemList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
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

        public TextView mTextView;
        public ProgressBar mPgbar;
        public ImageView timer_img;

        public AdapterViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            mTextView = itemView.findViewById(R.id.textView);
            mPgbar = itemView.findViewById(R.id.progressbar);
//            mPgbar.setMax(600);

            timer_img = itemView.findViewById(R.id.click_btn_img);

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

        holder.mTextView.setText(currentItem.getText());
//        holder.mPgbar.setMax(currentItem.getTotal_time());
        // TODO: bug fix
//        holder.mPgbar.setProgress(currentItem.getTime());

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}
