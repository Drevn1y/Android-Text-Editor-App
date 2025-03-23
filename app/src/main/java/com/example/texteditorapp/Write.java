package com.example.texteditorapp;

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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Write extends AppCompatActivity {

    private EditText nameField, textField;
    private Button back_btn, sendResultBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_write);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        nameField = findViewById(R.id.nameField);
        textField = findViewById(R.id.textField);
        back_btn = findViewById(R.id.back_btn);
        sendResultBt = findViewById(R.id.sendResultBt);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Write.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setData(View v) {
        String name = nameField.getText().toString();
        String text = textField.getText().toString();

        try {
            FileOutputStream file = openFileOutput("user_text.txt", MODE_PRIVATE);
            // Добавьте запись текста, если она необходима
            file.write((name + "\n" + text).getBytes());
            file.close();

            nameField.setText("");
            textField.setText("");
            Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "Error please try again!", Toast.LENGTH_SHORT).show(); // Сначала показываем Toast
            throw new RuntimeException(e);
        } catch (IOException e) {
            Toast.makeText(this, "File write error!", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        }
    }
}