package com.example.russianalphabetbuttons;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private final String[] russianAlphabet = {
            "А", "Б", "В", "Г", "Д", "Е", "Ё", "Ж", "З", "И", "Й",
            "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф",
            "Х", "Ц", "Ч", "Ш", "Щ", "Ъ", "Ы", "Ь", "Э", "Ю", "Я"
    };
    private final String[] russianLetters = {
            "a", "b", "v", "g", "d", "e", "yo", "zh", "z", "i", "ij",
            "k", "l", "m", "n", "o", "p", "r", "s", "t", "u", "f",
            "h", "c", "ch", "sh", "she", "tz", "ii", "mz", "ae", "yu", "ya"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout row1 = findViewById(R.id.row1);
        LinearLayout row2 = findViewById(R.id.row2);
        LinearLayout row3 = findViewById(R.id.row3);

        for (int i = 0; i < russianAlphabet.length; i++) {
            Button button = new Button(this);
            button.setText(russianAlphabet[i]);
            button.setLayoutParams(new LinearLayout.LayoutParams(
                    0,
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    1
            ));

            final String letter = russianAlphabet[i].toLowerCase();
            button.setOnClickListener(v -> playSound(letter));

            if (i < 11) {
                row1.addView(button);
            } else if (i < 22) {
                row2.addView(button);
            } else {
                row3.addView(button);
            }
        }
    }

    private void playSound(String letter) {
        int resId = getResources().getIdentifier(getLetter(letter), "raw", getPackageName());
        MediaPlayer mediaPlayer = MediaPlayer.create(this, resId);
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(MediaPlayer::release);
    }

    private String getLetter(String s){
        int j=0;
        for (int i = 0; i < russianAlphabet.length; i++) {
            if(russianAlphabet[i].equals(s.toUpperCase())) j=i;
        }
        return russianLetters[j];
    }
}