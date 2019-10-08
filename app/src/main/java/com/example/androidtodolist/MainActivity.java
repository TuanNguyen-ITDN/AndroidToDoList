package com.example.androidtodolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerviewUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerviewUser = findViewById(R.id.recyclerview_id);
        recyclerviewUser.setLayoutManager(new LinearLayoutManager((this)));

        final ToDoAdapter todoAdapter = new ToDoAdapter();
        recyclerviewUser.setAdapter(todoAdapter);

        final Button btn_Add = (Button) findViewById(R.id.btn_ADD);
        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity_Add_ToDoList.class);
                startActivity(intent);
            }
        });
    }
}
