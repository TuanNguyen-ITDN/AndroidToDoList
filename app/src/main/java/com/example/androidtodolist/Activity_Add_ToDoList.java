package com.example.androidtodolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class Activity_Add_ToDoList extends AppCompatActivity {
    AppDatabase db;
    List<ToDo> tasks;
    String editTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__add__to_do_list);

        final Button btn_Add_Task = (Button) findViewById(R.id.btn_AddTask);
        final EditText edit_text_Task = (EditText) findViewById(R.id.edit_text_Task);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();
        editTask = edit_text_Task.getText().toString();


        btn_Add_Task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTask = edit_text_Task.getText().toString();
                Log.w("Tag", "Add Task : " + editTask);
                addTask();
                getandDisplayTask();
                finish();
            }
        });
    }

    public void addTask() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                ToDo task = new ToDo();
                task.task = editTask;
                db.toDoDao().insert(task);
                return null;
            }
        }.execute();
    }

    public void getandDisplayTask() {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                final List<ToDo> tasks = db.toDoDao().getAll();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Activity_Add_ToDoList.this, "size" + tasks.size(), Toast.LENGTH_SHORT).show();
                    }
                });
                return null;
            }
        }.execute();
    }
}
