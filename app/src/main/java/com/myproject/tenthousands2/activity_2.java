package com.myproject.tenthousands2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class activity_2 extends AppCompatActivity {

    private Button tt_save_btn, timer_save_btn;
    private TextView textView1, activity_name;
    private Spinner tt_spinner1, tt_spinner2, tt_spinner3;

    private Spinner timer_spinner1, timer_spinner2, timer_spinner3;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";

    private int position;
    private String text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        textView1 = findViewById(R.id.text1_activity2);
        tt_save_btn = findViewById(R.id.tt_save_btn);
//        timer_save_btn = findViewById(R.id.timer_save_btn);

        Intent intent_r = getIntent();
        item Item = intent_r.getParcelableExtra("item");
//        position = intent_r.getIntExtra("position", 0);

        activity_name = findViewById(R.id.activity_name);
        activity_name.setText(Item.getText().toString());


//        loadData();
//        updateData();

        tt_spinner1 = findViewById(R.id.tt_spinner1);
        Integer[] items1 = new Integer[]{0,1,2,3,4,5,6,7,8,9};
        ArrayAdapter<Integer> adapter1 = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, items1);
        tt_spinner1.setAdapter(adapter1);


        tt_spinner2 = findViewById(R.id.tt_spinner2);
        Integer[] items2 = new Integer[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59};
        ArrayAdapter<Integer> adapter2 = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, items2);
        tt_spinner2.setAdapter(adapter2);

        tt_spinner3 = findViewById(R.id.tt_spinner3);
        Integer[] items3 = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59};
        ArrayAdapter<Integer> adapter3 = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, items3);
        tt_spinner3.setAdapter(adapter3);

        tt_save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int time_value = (int) tt_spinner1.getSelectedItem() * 3600 + (int) tt_spinner2.getSelectedItem() *60 + (int) tt_spinner3.getSelectedItem();  //+(long) spinner2.getSelectedItem()*60 + (int) spinner3.getSelectedItem() ;

                Intent intent =  new Intent();
                intent.putExtra("total_time",time_value);
//                intent.putExtra("position", position);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
//
//        timer_spinner1 = findViewById(R.id.timer_spinner1);
//        Integer[] timer_items1 = new Integer[]{0,1,2,3,4,5,6,7,8,9};
//        ArrayAdapter<Integer> timer_adapter1 = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, timer_items1);
//        tt_spinner1.setAdapter(timer_adapter1);
//
//
//        timer_spinner2 = findViewById(R.id.timer_spinner2);
//        Integer[] timer_items2 = new Integer[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59};
//        ArrayAdapter<Integer> timer_adapter2 = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, timer_items2);
//        timer_spinner2.setAdapter(timer_adapter2);
//
//        timer_spinner3 = findViewById(R.id.timer_spinner3);
//        Integer[] timer_items3 = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59};
//        ArrayAdapter<Integer> timer_adapter3 = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, timer_items3);
//        timer_spinner3.setAdapter(timer_adapter3);
//
//        timer_save_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int time_value = (int) timer_spinner1.getSelectedItem() * 3600 + (int) timer_spinner2.getSelectedItem() *60 + (int) timer_spinner3.getSelectedItem();  //+(long) spinner2.getSelectedItem()*60 + (int) spinner3.getSelectedItem() ;
//
//                Intent intent = new Intent();
//                intent.putExtra("timer_time",time_value);
//                intent.putExtra("position", position);
//                setResult(RESULT_OK,intent);
//                finish();
//
//
//            }
//        });

    }

//    public void saveData(){
//        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//
//        editor.putString(TEXT, memo.getText().toString());
//        editor.apply();
//        Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show();
//
//    }
//
//    public void loadData(){
//        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
//        text = sharedPreferences.getString(TEXT, "");
//
//    }
//
//    public void updateData(){
//        textView1.setText(text);
//
//    }

}
