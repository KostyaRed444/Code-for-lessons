package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    String[] citiesOut;

    String[] citiesIn ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        citiesOut= getResources().getStringArray(R.array.citiesOut);
        citiesIn= getResources().getStringArray(R.array.citiesIn);

        Spinner spinnerOut = (Spinner) findViewById(R.id.citiesOut);
        ArrayAdapter<String> adapterOut = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, citiesOut);
        adapterOut.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOut.setAdapter(adapterOut);

        Spinner spinnerIn = (Spinner) findViewById(R.id.citiesIn);
        ArrayAdapter<String> adapterIn = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, citiesIn);
        adapterIn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIn.setAdapter(adapterIn);
    }
}
