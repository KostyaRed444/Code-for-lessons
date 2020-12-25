package com.example.afinal;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    Retrofit retrofit=new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(AzureTranslationAPI.API_URL)
            .build();

    AzureTranslationAPI api=retrofit.create(AzureTranslationAPI.class);
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list);

        Call<Translation> call=api.getLanguages(); //ArrayList
        call.enqueue(new LanguagesCallback());
        ArrayAdapter<String> listLang= new ArrayAdapter<String>(this, R.layout.activity_main, (List<String>) call);
        listView.setAdapter(listLang);
   }

   //ответ от системы
    class LanguagesCallback implements Callback<Translation>{

        @Override
        public void onResponse (Call<Translation> call, Response<Translation> response){
           if (response.isSuccessful()){
                Log.d("mytag", "response: "+ response.body());
           }else{
                Log.d("mytag", "error: "+ response.code());
          }
        }

        @Override
       public  void onFailure(Call<Translation> call, Throwable t){
           Log.d("mytag", "error: "+ t.getLocalizedMessage());
       }
    }

}
