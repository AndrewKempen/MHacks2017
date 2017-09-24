package com.github.wishbonea.decode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.github.akemperfi.decode.R;

public class MainActivity extends AppCompatActivity {
    final static String SOURCE_LANGUAGE_MESSAGE = "com.github.wishbonea.decode.sourceLanguage";
    final static String TARGET_LANGUAGE_MESSAGE = "com.github.wishbonea.decode.targetLanguage";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button)findViewById(R.id.translateButton);

        Spinner sourceLanguageSpinner = (Spinner)findViewById(R.id.sourceLanguage);

        Spinner targetLanguageSpinner = (Spinner)findViewById(R.id.targetLanguage);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sourceLanguage_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sourceLanguageSpinner.setAdapter(adapter);
        targetLanguageSpinner.setAdapter(adapter);
    }

    public void nextScreen(View view) {
        Spinner sourceLanguageSpinner = (Spinner)findViewById(R.id.sourceLanguage);
        Spinner targetLanguageSpinner = (Spinner)findViewById(R.id.targetLanguage);

        String languageCode;
        switch(sourceLanguageSpinner.getSelectedItem().toString()) {
            case "English":
                languageCode = "en-US";
                break;
            case "Spanish":
                languageCode = "es";
                break;
            case "French":
                languageCode = "fr-FR";
                break;
            case "Arabic":
                languageCode = "ar";
                break;
            case "Portuguese":
                languageCode = "pt-PT";
                break;
            case "Korean":
                languageCode = "ko-KR";
                break;
            case "Russian":
                languageCode = "ru-RU";
                break;
            case "German":
                languageCode = "de-DE";
                break;
            default:
                languageCode = "en-US";
                break;
        }
        Intent intent = new Intent(this, TranslateActivity.class);
        intent.putExtra(SOURCE_LANGUAGE_MESSAGE, languageCode);

        switch(targetLanguageSpinner.getSelectedItem().toString()) {
            case "English":
                languageCode = "en-US";
                break;
            case "Spanish":
                languageCode = "es";
                break;
            case "French":
                languageCode = "fr-FR";
                break;
            case "Arabic":
                languageCode = "ar";
                break;
            case "Portuguese":
                languageCode = "pt-PT";
                break;
            case "Korean":
                languageCode = "ko-KR";
                break;
            case "Russian":
                languageCode = "ru-RU";
                break;
            case "German":
                languageCode = "de-DE";
                break;
            default:
                languageCode = "en-US";
                break;
        }
        intent.putExtra(TARGET_LANGUAGE_MESSAGE, languageCode);

        startActivity(intent);
    }
}


