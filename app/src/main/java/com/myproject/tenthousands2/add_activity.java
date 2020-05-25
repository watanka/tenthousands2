package com.myproject.tenthousands2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class add_activity extends AppCompatActivity {

    private Button add_btn, cancel_btn;
    private EditText act_name_txt, total_time_txt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activity);

        add_btn = findViewById(R.id.add_button);
        cancel_btn = findViewById(R.id.cancel_button);
        act_name_txt = findViewById(R.id.activity_enter);
        total_time_txt = findViewById(R.id.activity_total_time);

        //엔터키 금지
        act_name_txt.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    // TODO Auto-generated method stub
                    if (keyCode == event.KEYCODE_ENTER) {
                        return true;
                    }
                    return false;
                }
                return false;
            }
        });


        출처: https://shstarkr.tistory.com/153 [아마그래머]

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("activity_name",act_name_txt.getText().toString());
                intent.putExtra("activity_total_time", (long) Integer.parseInt(total_time_txt.getText().toString())*1000/60);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });


    }
}
