package com.example.russianalphabetbuttons;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.TextViewCompat;

public class MainActivity extends AppCompatActivity {

    private final String[] russianAlphabet = {
            "А", "Б", "В", "Г", "Д", "Е", "Ё", "Ж", "З", "И", "Й",
            "К", "Л", "М", "Н", "О", "П", "Р", "С", "Т", "У", "Ф",
            "Х", "Ц", "Ч", "Ш", "Щ", "Ъ", "Ы", "Ь", "Э", "Ю", "Я"
    };
    private final String[] soundFiles = {
            "a", "b", "v", "g", "d", "e", "yo", "zh", "z", "i", "ij",
            "k", "l", "m", "n", "o", "p", "r", "s", "t", "u", "f",
            "h", "c", "ch", "sh", "she", "tz", "ii", "mz", "ae", "yu", "ya"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout mainLayout = findViewById(R.id.main_layout);

        // Настройка параметров для кнопок
        int buttonHeight = getResources().getDisplayMetrics().heightPixels / 4;
        int textSize = buttonHeight / 5; // Адаптивный размер текста

        // Создаем 3 ряда
        for (int row = 0; row < 3; row++) {
            LinearLayout rowLayout = new LinearLayout(this);
            rowLayout.setOrientation(LinearLayout.HORIZONTAL);
            rowLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    0,
                    1
            ));

            // Добавляем кнопки в ряд
            int buttonsInRow = (row < 2) ? 11 : 11; // 11 кнопок в первых двух рядах, 11 в последнем
            for (int i = row * 11; i < (row + 1) * 11 && i < russianAlphabet.length; i++) {
                Button button = createButton(russianAlphabet[i], soundFiles[i], textSize);
                rowLayout.addView(button);
            }

            mainLayout.addView(rowLayout);
        }
    }

    private Button createButton(String letter, String soundFile, int textSize) {
        Button button = new Button(this);

        // Параметры кнопки
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1
        );
        params.setMargins(4, 4, 4, 4); // Небольшие отступы

        button.setLayoutParams(params);
        button.setText(letter);
        button.setTextSize(textSize);
        button.setAllCaps(false); // Чтобы буквы не превращались в заглавные

        // Обработчик нажатия
        button.setOnClickListener(v -> playSound(soundFile));

        return button;
    }

    private void playSound(String soundFile) {
        try {
            int resId = getResources().getIdentifier(soundFile, "raw", getPackageName());
            MediaPlayer mediaPlayer = MediaPlayer.create(this, resId);
            mediaPlayer.setOnCompletionListener(MediaPlayer::release);
            mediaPlayer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}