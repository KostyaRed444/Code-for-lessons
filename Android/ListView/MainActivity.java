package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import com.google.gson.Gson;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    UserListAdapter adapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);
        InputStream stream= getResources().openRawResource(R.raw.users);
        Gson gson= new Gson();
        User[] users_arr=gson.fromJson(new InputStreamReader(stream), User[].class);
        Log.d("mytag", "users: "+ users_arr.length);
        // TODO: реализовать загрузку данных из JSON-файла
        // который загрузить в папку assets
        ArrayList<User> users=new ArrayList<>();
        Collections.addAll(users, users_arr);
//        for (int i = 0; i < 10; i++) {
//            users.add(new User("Petya", "123", Sex.MAN));
//            users.add(new User("Vasya", "234", Sex.MAN));
//            users.add(new User("Valya", "456", Sex.WOMAN));
//            users.add(new User("UFO", "@@@", Sex.UNKNOWN));
//        }

        adapter = new UserListAdapter(this, users);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);
    }
}