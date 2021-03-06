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
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class activity_2 extends AppCompatActivity {

    private Button tt_set_btn;
    private NumberPicker np1, np2, np3;
    private int position;
    private long value;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);


        tt_set_btn = findViewById(R.id.tt_set_btn);

        Intent intent_r = getIntent();
        position = intent_r.getIntExtra("position", 0);

        np1 = findViewById(R.id.tt_numberPicker1);
        np2 = findViewById(R.id.tt_numberPicker2);
        np3 = findViewById(R.id.tt_numberPicker3);

        np1.setMinValue(0);
        np1.setMaxValue(12);
        np2.setMinValue(0);
        np2.setMaxValue(59);
        np3.setMinValue(0);
        np3.setMaxValue(59);


        np1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                value += newVal * 1000 * 60 * 60;
            }
        });

        np2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                value += newVal * 1000 * 60;
            }
        });

        np3.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                value += newVal * 1000;
            }
        });

        tt_set_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                intent.putExtra("position",position);
                intent.putExtra("total_time", value);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

}
