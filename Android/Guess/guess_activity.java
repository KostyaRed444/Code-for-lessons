package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    TextView questionText;
    int min, max;
    int state;
    int step;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        questionText = (TextView) findViewById(R.id.question);
        Intent i = getIntent();
        String minStr = i.getStringExtra("min");
        String maxStr = i.getStringExtra("max");
        min = Integer.parseInt(minStr);
        max = Integer.parseInt(maxStr);
        step=(max-min)/2;
        state=min+step;
        questionText.setText("Угадываем число от " + minStr + " до "+maxStr +
                "\nВаше число больше " + (max-min)/2+"?");

    }
    public void onYesNoClick(View v)
    {
        if (v.getId() == R.id.yes)
        {
            step/=2;
            state=state+step;;
            questionText.setText("Ваше число больше "+Integer.toString(state)+"?");
            if (step==1) {
                questionText.setText("Ваше число: "+Integer.toString(state));
            }
        }
        if (v.getId() == R.id.no)
        {
            step/=2;
            state=state-step;
            questionText.setText("Ваше число меньше "+Integer.toString(state)+"?");
            if (step==1) {
                questionText.setText("Ваше число: "+Integer.toString(state));
            }
        }

    }



}
