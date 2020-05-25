package com.myproject.tenthousands2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class album extends AppCompatActivity {
    private TextView textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);

        textView2 = (TextView)findViewById(R.id.textView2);

        Intent getintent = getIntent();
        int img = getintent.getIntExtra("image",0);
        textView2.setText(""+img);

    }
}
