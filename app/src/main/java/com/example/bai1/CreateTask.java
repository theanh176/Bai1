package com.example.bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateTask extends AppCompatActivity {

    SQLite database;
    EditText edtTitle, edtDes;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        mapping();
        event();
    }
    public void mapping(){
        edtTitle = findViewById(R.id.editTextTitle);
        edtDes = findViewById(R.id.editTextDes);
        btnSave = findViewById(R.id.buttonSave);
    }
    public void event(){
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createTask();
            }
        });
    }
    public void createTask(){
        String titleTask = edtTitle.getText().toString();
        String desTask = edtDes.getText().toString();
        if(titleTask.equals("")&&desTask.equals("")){
            Toast.makeText(CreateTask.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
        }else{
            database = new SQLite(CreateTask.this, "task.sqlite", null, 1);
            database.QueryData("CREATE TABLE IF NOT EXISTS Task(Id INTEGER PRIMARY KEY AUTOINCREMENT, Title VARCHAR(200), Description VARCHAR(200))");
            database.QueryData("INSERT INTO Task VALUES(null, '"+titleTask+"','"+desTask+"')");
            Toast.makeText(CreateTask.this, "Đã thêm.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CreateTask.this, MainActivity.class);
            startActivity(intent);
        }
    }
}