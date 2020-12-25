package com.example.afinal;

import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.Map;

public class Translation {

    Map<String, Language> translation;
    String languages="";
    public ArrayList getLanguages() {
        ArrayList languages = new ArrayList();
        for (String l: translation.keySet()) {
            languages.add(l);
        }
        return languages;
    }

}
