package com.example.afinal;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

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
    ArrayList<String> languages=new ArrayList<>();
    ArrayAdapter<String> listLang;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listLang=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, languages);
        listLang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


       Call<Translation> call=api.getLanguages();
       call.enqueue(new LanguagesCallback());

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
