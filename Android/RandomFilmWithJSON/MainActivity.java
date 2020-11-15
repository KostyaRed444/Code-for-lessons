package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

//строки со свойствами
class Movie{
    String Title;
    String Genre;
    String Year;
    String Rated;
    String Runtime;
}

//запись строк в массив
class Movies{
    ArrayList<Movie> movies;
}

public class MainActivity extends AppCompatActivity {

    TextView title;
    TextView genre;
    TextView year;
    TextView rated;
    TextView runtime;

    ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies= getMovies();
        title=findViewById(R.id.titleMov);
        genre=findViewById(R.id.genre);
        year=findViewById(R.id.yearMov);
        rated=findViewById(R.id.ratedMov);
        runtime=findViewById(R.id.runtimeMov);
    }

    public ArrayList<Movie> getMovies() {
        InputStream stream=null;
        try {
            stream = getAssets().open("movies.json");
        } catch (IOException e){
            e.printStackTrace();
        }
        InputStreamReader reader = new InputStreamReader(stream);
        Gson gson = new Gson();
        Movies m = gson.fromJson(reader, Movies.class);
        return m.movies;
    }

    public void randomFilm(View v) {
        if (movies.size()!=0){
            int randomId=(int) (Math.random()*movies.size());
            genre.setText(movies.get(randomId).Genre);
            title.setText(movies.get(randomId).Title);
            year.setText(movies.get(randomId).Year);
            rated.setText(movies.get(randomId).Rated);
            runtime.setText(movies.get(randomId).Runtime);

            movies.remove(randomId);
        }else{
            genre.setText("End: no more films :(");
            title.setText(" ");
            year.setText(" ");
            rated.setText(" ");
            runtime.setText(" ");
        }
    }
    public void resert(View V){
        movies.clear();
    }
}