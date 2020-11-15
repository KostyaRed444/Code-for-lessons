package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    String[] citiesOut = {"Иркутск", "Ангарск"};

    String[] citiesIn = {"Москва", "Киото", "Шанхай", "Рио-де-Жанейро", "Нью-Йорк", "Лондон"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinnerOut = (Spinner) findViewById(R.id.citiesOut);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapterOut = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, citiesOut);
        // Определяем разметку для использования при выборе элемента
        adapterOut.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinnerOut.setAdapter(adapterOut);

        Spinner spinnerIn = (Spinner) findViewById(R.id.citiesIn);
        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> adapterIn = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, citiesIn);
        // Определяем разметку для использования при выборе элемента
        adapterIn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinnerIn.setAdapter(adapterIn);
    }
}