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
        mList.add(position, new item(30, "temp", 100));
        mAdapter.notifyItemChanged(position);
    }
    public void removeItem(int position){
        mList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    public void updatePgbar_total(int position, int total_time){
        mList.get(position).setTotal_time(total_time);
        mAdapter.notifyItemChanged(position);
    }

    public void updatePgbar_current(int position, int timer_time){
        mList.get(position).setTime(timer_time);
        mAdapter.notifyItemChanged(position);
    }

    private void createList() {
        mList = new ArrayList<>();
        mList.add(new item( 60, "Piano", 100));
        mList.add(new item(40, "swimming", 10));

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
                Intent intent = new Intent(MainActivity.this, activity_2.class);
                intent.putExtra("item", mList.get(position));
//                intent.putExtra("position", position);
                startActivityForResult(intent, 100);
            }

            public void timerClick(int position){
                Intent intent = new Intent(MainActivity.this, timer.class);
//              parcelable 형태
//                intent.putExtra("activity", mList.get(position));
//                intent.putExtra("activity", mList.get(position).getText());
//                intent.putExtra("time", mList.get(position).getTime());
//                intent.putExtra("position", position);
                startActivityForResult(intent, 1);
            }

        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){
            if (resultCode==RESULT_OK){
                Toast.makeText(MainActivity.this, "success"+data.getIntExtra("quality",0)+", "+ data.getIntExtra("time",0) +" 시간 축적", Toast.LENGTH_SHORT).show();
                updatePgbar_current(data.getIntExtra("position", 0), data.getIntExtra("time", 0));
            } else{
                Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }
        }
        /**설정메뉴**/
        if (requestCode == 100){
            if (resultCode==RESULT_OK){
                // TODO: total_time을 progress bar max로 설정.

                updatePgbar_total(data.getIntExtra("position",0),data.getIntExtra("total_time", 0));

//                Toast.makeText(MainActivity.this, ""+data.getIntExtra("total_time",0), Toast.LENGTH_SHORT).show();
            }
        }

    }
}
