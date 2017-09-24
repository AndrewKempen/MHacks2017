package com.github.wishbonea.decode;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.akemperfi.decode.R;

import java.util.ArrayList;

public class TranslateActivity extends AppCompatActivity implements View.OnClickListener {
    private SpeechRecognizer sr;
    private static final String TAG = "Translation";
    private String sourceLanguageMessage;
    private String targetLanguageMessage;

    private TextView sourceLanguageText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        System.out.println("Create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);
        Intent intent = getIntent();
        sourceLanguageMessage = intent.getStringExtra(MainActivity.SOURCE_LANGUAGE_MESSAGE);
        targetLanguageMessage = intent.getStringExtra(MainActivity.TARGET_LANGUAGE_MESSAGE);
        ImageButton speakButton = (ImageButton) findViewById(R.id.recordButton);
        speakButton.setOnClickListener(this);
        sr = SpeechRecognizer.createSpeechRecognizer(this);
        sr.setRecognitionListener(new listener());
        sourceLanguageText = (TextView)findViewById(R.id.sourceLanguageText);
        sourceLanguageText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
    }

    class listener implements RecognitionListener {
        public void onReadyForSpeech(Bundle params) {
            Log.d(TAG, "onReadyForSpeech");
        }

        public void onBeginningOfSpeech() {
            Log.d(TAG, "onBeginningOfSpeech");
        }

        public void onRmsChanged(float rmsdB) {
            Log.d(TAG, "onRmsChanged");
        }

        public void onBufferReceived(byte[] buffer) {
            Log.d(TAG, "onBufferReceived");
        }

        public void onEndOfSpeech() {
            Log.d(TAG, "onEndofSpeech");
        }

        public void onError(int error) {
            if(error == 7) {
                sourceLanguageText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.errorRed));
                sourceLanguageText.setText("No speech input!");
            }
            Log.d(TAG, "error " + error);
        }

        public void onResults(Bundle results) {
            String str = "";
            Log.d(TAG, "onResults " + results);
            ArrayList data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            for (int i = 0; i < data.size(); i++) {
                Log.d(TAG, "result " + data.get(i));
                str += data.get(i);
            }
            System.out.println("results: " + String.valueOf(data.size()));
            System.out.println("Output: " + data.get(data.size() - 1));
            sourceLanguageText.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.black));
            sourceLanguageText.setText(data.get(data.size() - 1).toString());
        }

        public void onPartialResults(Bundle partialResults) {
            Log.d(TAG, "onPartialResults");
        }

        public void onEvent(int eventType, Bundle params) {
            Log.d(TAG, "onEvent " + eventType);
        }
    }

    public void onClick(View v) {
        System.out.println("Button clicked");
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("NOT GIVEN RECORDING PERMISSIONS!");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 0);
        }
        if (v.getId() == R.id.recordButton) {
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, "voice.recognition.test");
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, sourceLanguageMessage);

            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
            sr.startListening(intent);
            sourceLanguageText.setTextColor(ContextCompat.getColor(this, R.color.black));
            sourceLanguageText.setText("Listening...");
        }


        String inputText = "<Blabla>What we want</Blabla>";
        String outputText = "";
        for(int i = 0; i < inputText.length(); i++) {
            if(inputText.charAt(i) == '>') {
                i++;
                for(;i < inputText.length(); i++) {
                    if(inputText.charAt(i) != '<') {
                        outputText += inputText.charAt(i);
                    } else {
                        break;
                    }
                }
                break;
            }
        }

    }
}