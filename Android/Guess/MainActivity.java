package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText minText, maxText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        minText = (EditText) findViewById(R.id.min);
        maxText = (EditText) findViewById(R.id.max);

    }

    public void onStartGameClick(View v)
    {
        String min = minText.getText().toString();
        String max = maxText.getText().toString();

        Intent i = new Intent(this, GameActivity.class);
        i.putExtra("max", max);
        i.putExtra("min", min);
        startActivity(i);
    }
}
