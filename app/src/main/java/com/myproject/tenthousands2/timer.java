package com.myproject.tenthousands2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.Locale;

public class timer extends AppCompatActivity {

//    private EditText mEditTextInput;
    private TextView mTextViewCountDown, activity_text;
    private Button mButtonSet, mButtonStartPause, mButtonReset;

    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mStartTimeInMillis,mTimeLeftInMillis, mEndTime, mTimeDone, input;


    private static final int CAMERA_REQUEST_CODE = 45;

    private int position;

    private ImageButton mButtonSmile, mButtonUnsmile, mButtonCamera;


    private String ex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_);


//        mEditTextInput = findViewById(R.id.edit_text_input);
        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mButtonSet = findViewById(R.id.button_set);
        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonReset = findViewById(R.id.button_reset);

        mButtonSmile = findViewById(R.id.smile_btn);
        mButtonUnsmile = findViewById(R.id.unsmile_btn);
        mButtonCamera = findViewById(R.id.camera_btn);

        activity_text = findViewById(R.id.activity_text);


        Intent intent = getIntent();



        position = intent.getIntExtra("position", 0);

        activity_text.setText(intent.getStringExtra("activity"));

        mButtonCamera.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
            //TODO: add camera intent
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
            }
        });

        mButtonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(timer.this, set_time.class);
                startActivityForResult(intent, 80);

//                String input = mEditTextInput.getText().toString();
//                if (input.length() == 0) {
//                    Toast.makeText(timer.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                long millisInput = Long.parseLong(input) * 60000;
//                if (millisInput == 0) {
//                    Toast.makeText(timer.this, "Please enter a positive number", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                setTime(millisInput);
//                mEditTextInput.setText("");
            }
        });
        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                    mTimeDone = mStartTimeInMillis - mTimeLeftInMillis;
                }
            }
        });
        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        /** 타이머가 끝나면 그 시간에 대한 평가 smile/sad**/
        mButtonSmile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("time", mTimeDone);
                intent.putExtra("quality", 1);
                intent.putExtra("position", position);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        mButtonUnsmile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("time", mTimeDone);
                intent.putExtra("quality", 0);
                setResult(RESULT_CANCELED, intent);
                finish();
            }
        });
//
//

    }

    private void setSmileVisible(){
        if (mTimerRunning){
            mButtonUnsmile.setVisibility(View.INVISIBLE);
            mButtonSmile.setVisibility(View.INVISIBLE);
            mButtonCamera.setVisibility(View.INVISIBLE);

        }else {
            mButtonUnsmile.setVisibility(View.VISIBLE);
            mButtonSmile.setVisibility(View.VISIBLE);
            mButtonCamera.setVisibility(View.VISIBLE);
        }
    }

    private void setTime(long milliseconds) {
        mStartTimeInMillis = milliseconds;
        resetTimer();
        closeKeyboard();
    }
    private void startTimer() {
        mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;

                updateCountDownText();
            }
            @Override
            public void onFinish() {
                mTimerRunning = false;
                setSmileVisible();
                updateWatchInterface();
            }
        }.start();
        mTimerRunning = true;
        setSmileVisible();
        updateWatchInterface();
    }
    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        setSmileVisible();
        updateWatchInterface();



    }
    private void resetTimer() {
        mTimeLeftInMillis = mStartTimeInMillis;
        updateCountDownText();
        updateWatchInterface();
    }
    private void updateCountDownText() {
        int hours = (int) (mTimeLeftInMillis / 1000) / 3600;
        int minutes = (int) ((mTimeLeftInMillis / 1000) % 3600) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted;
        if (hours > 0) {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%d:%02d:%02d", hours, minutes, seconds);
        } else {
            timeLeftFormatted = String.format(Locale.getDefault(),
                    "%02d:%02d", minutes, seconds);
        }
        mTextViewCountDown.setText(timeLeftFormatted);
    }
    private void updateWatchInterface() {
        if (mTimerRunning) {
//            mEditTextInput.setVisibility(View.INVISIBLE);
            mButtonSet.setVisibility(View.INVISIBLE);
            mButtonReset.setVisibility(View.INVISIBLE);
            mButtonStartPause.setText("Pause");
        } else {
//            mEditTextInput.setVisibility(View.VISIBLE);
            mButtonSet.setVisibility(View.VISIBLE);
            mButtonStartPause.setText("Start");
            if (mTimeLeftInMillis < 1000) {
                mButtonStartPause.setVisibility(View.INVISIBLE);
            } else {
                mButtonStartPause.setVisibility(View.VISIBLE);
            }
            if (mTimeLeftInMillis < mStartTimeInMillis) {
                mButtonReset.setVisibility(View.VISIBLE);
            } else {
                mButtonReset.setVisibility(View.INVISIBLE);
            }
        }
    }
    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putLong("startTimeInMillis", mStartTimeInMillis);
        editor.putLong("millisLeft", mTimeLeftInMillis);
        editor.putBoolean("timerRunning", mTimerRunning);
        editor.putLong("endTime", mEndTime);
        editor.apply();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        mStartTimeInMillis = prefs.getLong("startTimeInMillis", 600000);
        mTimeLeftInMillis = prefs.getLong("millisLeft", mStartTimeInMillis);
        mTimerRunning = prefs.getBoolean("timerRunning", false);
        updateCountDownText();
        updateWatchInterface();
        if (mTimerRunning) {
            mEndTime = prefs.getLong("endTime", 0);
            mTimeLeftInMillis = mEndTime - System.currentTimeMillis();
            if (mTimeLeftInMillis < 0) {
                mTimeLeftInMillis = 0;
                mTimerRunning = false;
                updateCountDownText();
                updateWatchInterface();
            } else {
                startTimer();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //TODO: receive set_time
        if (requestCode == 80){
            if (resultCode == RESULT_OK){
                input = data.getLongExtra("time", 0);

//                if (input == 0) {
//                    Toast.makeText(timer.this, "Field can't be empty", Toast.LENGTH_SHORT).show();
//                    return;
//                }

                if (input == 0) {
                    Toast.makeText(timer.this, "Please enter a positive number", Toast.LENGTH_SHORT).show();
                    return;
                }


                setTime(input);
                Toast.makeText(this, ""+input, Toast.LENGTH_LONG).show();

            }else {

                Log.i("setTimer","set timer return sth else!!" );
                return;
            }

            }
        if (requestCode == CAMERA_REQUEST_CODE){
            //CAMERA
            Bitmap bitmap = (Bitmap)data.getExtras().get("image");
            Intent intent = new Intent(this, album.class);
            intent.putExtra("image", 1);

        }
        }


}



