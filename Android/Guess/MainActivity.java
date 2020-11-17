package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText minInputLayout;
    EditText  maxInputLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        minInputLayout=(EditText) findViewById(R.id.min_num);
        maxInputLayout=(EditText) findViewById(R.id.max_num);
    }

    public void next_act(View view) {
        Intent intent = new Intent(MainActivity.this, guess_activity.class);
        int min=Integer.parseInt(minInputLayout.getText().toString());
        int max=Integer.parseInt(maxInputLayout.getText().toString());
        intent.putExtra("min", min);
        intent.putExtra("max", max);
        startActivity(intent);
    }
}