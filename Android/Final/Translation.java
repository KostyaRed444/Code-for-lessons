package com.example.afinal;

import java.util.Map;

public class Translation {

    Map<String, Language> translation;
    public String toString() {
        String  languages = "";
        for (String l: translation.keySet()) {
            languages += l + ":";
        }
        return languages;
    }

}
