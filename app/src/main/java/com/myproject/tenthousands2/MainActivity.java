package com.myproject.tenthousands2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    private  ArrayList<item> mList;

    private RecyclerView mRecycleView;
    private Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button click_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createList();
        buildRecyclerView();


        click_btn = findViewById(R.id.button_insert);
        click_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertItem(mList.size());
            }
        });
    }

    public void insertItem(int position){
        mList.add(position, new item(999999, "temp", 1000000));
        mAdapter.notifyItemInserted(position);
    }
    public void removeItem(int position){
        mList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    public void updatePgbar_total(int position, long total_time){
        mList.get(position).setTotal_time(total_time);
        mAdapter.notifyItemChanged(position);
    }

    public void updatePgbar_current(int position, long timer_time){

        mList.get(position).setTime(timer_time+ mList.get(position).getTime());
        mAdapter.notifyItemChanged(position);

        if (mList.get(position).getTime() >= mList.get(position).getTotal_time()){
            removeItem(position);
        }

    }

//    public void updatePgnum_total(int position, double total_time){
//
//    }
//
//    public void updatePgnum_current(int position, double timer_time){
//        mList.get(position).setTimer
//
//    }


    private void createList() {
        mList = new ArrayList<>();
        mList.add(new item( 60*1000*60*60, "Piano", 100*1000*60*60));
        mList.add(new item(40*1000*60*60, "swimming", 100*1000*60*60));

    }


    private void buildRecyclerView() {
        mRecycleView  = findViewById(R.id.recyclerView);
        mRecycleView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new Adapter(mList);

        mRecycleView.setLayoutManager(mLayoutManager);
        mRecycleView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent1 = new Intent(MainActivity.this, activity_2.class);
                intent1.putExtra("item", mList.get(position));
//                intent.putExtra("position", position);
                startActivityForResult(intent1, 100);
            }

            public void timerClick(int position){
                Intent intent2 = new Intent(MainActivity.this, timer.class);
//              parcelable 형태
//                intent.putExtra("activity", mList.get(position));
                intent2.putExtra("activity", mList.get(position).getText());
                intent2.putExtra("position", position);
                startActivityForResult(intent2, 10);

            }

        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10){
            if (resultCode==RESULT_OK){
                int time = (int) (data.getLongExtra("time", 0) / 1000);

                int hr = (int) Math.round(time / 3600);
                int min = (int) Math.round((time % 3600) / 60) ;
                int sec = (int) Math.round((time % 60));
                //TODO: 시/분/초 단위로 토스트 메세지 나오도록.
//                if (min == 0) {
//                    Toast.makeText(MainActivity.this, String.format("% 초", sec), Toast.LENGTH_SHORT).show();
//                } else if (hr == 0){
//                    Toast.makeText(MainActivity.this, String.format("%d 분 % 초", min, sec), Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(MainActivity.this, String.format("%d 시 %d 분 % 초", hr, min, sec), Toast.LENGTH_SHORT).show();
//                }


                updatePgbar_current(data.getIntExtra("position", 0), data.getLongExtra("time", 0));
            } else{
                Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        }
        /**설정메뉴**/
        if (requestCode == 100){
            if (resultCode==RESULT_OK){

                Toast.makeText(MainActivity.this, "timer done", Toast.LENGTH_SHORT).show();
                updatePgbar_total(data.getIntExtra("position",0),data.getLongExtra("total_time", 0));

//                Toast.makeText(MainActivity.this, ""+data.getIntExtra("total_time",0), Toast.LENGTH_SHORT).show();
            }
        }

    }
}
