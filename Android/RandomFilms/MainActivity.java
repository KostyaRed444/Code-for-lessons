package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String[] films;
    TextView tv;
    List<String> usedStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        films= getResources().getStringArray(R.array.films);
        usedStrings=new ArrayList<>();
        tv=findViewById(R.id.film);
    }

    public void randomFilm(View v) {
        String randomStr=films[new Random().nextInt(films.length)];
        while (usedStrings.contains(randomStr)){
            randomStr=films[new Random().nextInt(films.length)];
            if (usedStrings.size()>=films.length) break;
        }
        if (usedStrings.size()<films.length){
            usedStrings.add(randomStr);
            tv.setText(randomStr);
        }else{
            tv.setText("End: no more films :(");
        }
    }
    public void resert(View V){
        usedStrings.clear();
    }
}