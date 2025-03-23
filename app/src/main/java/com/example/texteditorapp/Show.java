package com.example.texteditorapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Show extends AppCompatActivity {

    private EditText textField;
    private Button back_btn, sendResultBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textField = findViewById(R.id.textField);
        back_btn = findViewById(R.id.back_btn);
        sendResultBt = findViewById(R.id.sendResultBt);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Show.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void showData(View v) {
        try {
            FileInputStream file = openFileInput("user_text.txt");
            InputStreamReader reader = new InputStreamReader(file);
            BufferedReader bR = new BufferedReader(reader);

            StringBuffer sB = new StringBuffer();
            String line;

            while ((line = bR.readLine()) != null) {
                sB.append(line).append("\n"); // Добавляем каждую строку в StringBuffer
            }

            bR.close(); // Закрываем поток чтения

            // Устанавливаем весь текст в nameField
            textField.setText(sB.toString());

        } catch (FileNotFoundException e) {
            Toast.makeText(this, "File not found. Please try again!", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        } catch (IOException e) {
            Toast.makeText(this, "Error reading file!", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
    }
}