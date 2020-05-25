package com.myproject.tenthousands2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

public class set_time extends AppCompatActivity {

    private Button set_btn;
    private NumberPicker np1, np2, np3;
    private long value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);

        set_btn = findViewById(R.id.set_btn);
        np1 = findViewById(R.id.numberPicker1);
        np2 = findViewById(R.id.numberPicker2);
        np3 = findViewById(R.id.numberPicker3);

        np1.setMinValue(0);
        np1.setMaxValue(12);
        np2.setMinValue(0);
        np2.setMaxValue(59);
        np3.setMinValue(0);
        np3.setMaxValue(59);

//        np1.setOnValueChangedListener(this);
//        np2.setOnValueChangedListener(this);
//        np3.setOnValueChangedListener(this);

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

        set_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();


                intent.putExtra("time", value);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }

}



