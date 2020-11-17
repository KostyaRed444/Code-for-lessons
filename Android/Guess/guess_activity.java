package com.example.myapplication;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class guess_activity extends AppCompatActivity {
    TextView tv;
    TextView result;
    int min;
    int max;
    int state;
    int step;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_activity);
        Intent intent = getIntent();
        tv=findViewById(R.id.textView7);
        result=findViewById(R.id.result);
        min = Integer.parseInt(intent.getStringExtra("min"));
        max = Integer.parseInt(intent.getStringExtra("max"));
        step=(max-min)/2;
        state=min+step;
        tv.setText(Integer.toString(state));
    }

    public void yes(View view) {
        step/=2;
        state=state+step;;
        tv.setText(Integer.toString(state));
        if (step==1) {
            result.setText(Integer.toString(state));
            result.setVisibility(View.VISIBLE);
        }
    }

    public void no(View view) {
        step/=2;
        state=state-step;
        tv.setText(Integer.toString(state));
        if (step==1) {
            result.setText(Integer.toString(state));
            result.setVisibility(View.VISIBLE);
        }
    }
}