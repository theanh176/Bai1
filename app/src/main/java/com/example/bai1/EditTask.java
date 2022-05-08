package com.example.bai1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class EditTask extends AppCompatActivity {
    SQLite database;
    EditText edtTitle, edtDes;
    Button btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        mapping();

        String titleTask = getIntent().getStringExtra("title");
        String desTask = getIntent().getStringExtra("des");
        int id = getIntent().getIntExtra("id",0);
        edtTitle.setText(titleTask);
        edtDes.setText(desTask);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTaskTitle = edtTitle.getText().toString().trim();
                String newTaskDes = edtDes.getText().toString().trim();

                database = new SQLite(EditTask.this, "task.sqlite", null, 1);
                database.QueryData("CREATE TABLE IF NOT EXISTS Task(Id INTEGER PRIMARY KEY AUTOINCREMENT, Title VARCHAR(200), Description VARCHAR(200))");
                database.QueryData("UPDATE Task SET Title = '"+newTaskTitle+"', Description = '"+newTaskDes+"' WHERE Id = '"+id+"'");
                Toast.makeText(EditTask.this, "Đã cập nhật.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditTask.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    public void mapping(){
        edtTitle = findViewById(R.id.editTitle);
        edtDes = findViewById(R.id.editDes);
        btnEdit = findViewById(R.id.btnEdit);
    }
}