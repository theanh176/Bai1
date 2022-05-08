package com.example.bai1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

//    static final String AUTHORITY = "com.android.example.checkandroidid.AndroidIDProvider";
//    static final String CONTENT_PATH =  "backupdata";
//    static final String URL = "content://" + AUTHORITY + "/" + CONTENT_PATH;
//    static final Uri CONTENT_URI = Uri.parse(URL);

    SQLite database;
    ListView lvTask;
    ImageButton imgCreate;
    ArrayList<Task> taskArrayList;
    TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTask = (ListView) findViewById(R.id.lvTask);
        imgCreate = (ImageButton) findViewById(R.id.btnImgCreate);
        taskArrayList = new ArrayList<>();

        adapter = new TaskAdapter(this, R.layout.task_item, taskArrayList);
        lvTask.setAdapter(adapter);

        database = new SQLite(this, "task.sqlite", null, 1);
        database.QueryData("CREATE TABLE IF NOT EXISTS Task(Id INTEGER PRIMARY KEY AUTOINCREMENT, Title VARCHAR(200), Description VARCHAR(200))");

        GetDataTask();

        imgCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateTask.class);
                startActivity(intent);
            }
        });
    }

    private void GetDataTask(){
        Cursor dataTask = database.GetData("SELECT * FROM Task");
        taskArrayList.clear();
        while (dataTask.moveToNext()){
            String des = dataTask.getString(2);
            String title = dataTask.getString(1);
            int id = dataTask.getInt(0);
            taskArrayList.add(new Task(id, title,  des));
        }
        adapter.notifyDataSetChanged();
    }

    public void EditTask(String title, String des, int id){
        Intent intent = new Intent(MainActivity.this, EditTask.class);
        System.out.println(title);
        System.out.println(des);
        intent.putExtra("title", title);
        intent.putExtra("des", des);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void DeleteTask(String title, String des, final int id){
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(this);
        dialogDelete.setMessage("Bạn có muốn xóa công việc " +title+ " có nội dung là: "+des+" không?");
        dialogDelete.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                database.QueryData("DELETE FROM Task WHERE Id = '"+id+"'");
                Toast.makeText(MainActivity.this, "Đã xóa " +title, Toast.LENGTH_SHORT).show();
                GetDataTask();
            }
        });
        dialogDelete.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogDelete.show();
    }
}