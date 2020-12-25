package com.example.afinal;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface AzureTranslationAPI {
    String API_URL = "https://api.cognitive.microsofttranslator.com";
    String key = "1db017788ad86488d30d573a7fe502c11";

    @GET("/languages?api-version=3.0&scope=translation")

    Call<Translation> getLanguages();
}
