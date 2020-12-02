package com.example.notes;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView TotalTask;
    private Button AddTask;
    private ListView Tasks;
    private EditText Task, Data;
    private SQLiteDatabase DB;

    private List<Item> Items=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TotalTask =findViewById(R.id.TotalCount);
        AddTask=findViewById(R.id.addButton);
        Tasks=findViewById(R.id.Tasks);
        Task=findViewById(R.id.TaskMessage);
        Data=findViewById(R.id.Data);

        final DBHelper dbHelper=new DBHelper(this);
        DB=dbHelper.getWritableDatabase();

        getItems();
        final SimpleAdapter simpleAdapter = new SimpleAdapter(Items);
        Tasks.setAdapter(simpleAdapter);
        AddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Task.getText().toString().isEmpty() || Data.getText().toString().isEmpty())
                    return;
                ContentValues contentValues = new ContentValues();
                contentValues.put(DBHelper.COLUMN_NAME, Task.getText().toString());
                contentValues.put(DBHelper.DATA, Data.getText().toString());
                DB.insert(DBHelper.TABLE, null, contentValues);
                getItems();
                simpleAdapter.notifyDataSetChanged();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void getItems() {
        Items.clear();
        Cursor cursor = DB.query(
                DBHelper.TABLE,
                new String[]{DBHelper.COLUMN_NAME, DBHelper.DATA},
                null,
                null,
                null,
                null,
                null);
        while (cursor.moveToNext()) {
            String currentName = cursor.getString(0);
            String currentData = cursor.getString(1);
            Items.add(new Item(currentName, currentData));
        }
        cursor.close();

        TotalTask.setText("Всего заметок: " + String.valueOf(Items.size()));
    }
}
