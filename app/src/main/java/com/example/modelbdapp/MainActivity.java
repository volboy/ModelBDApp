package com.example.modelbdapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView userList;
    ArrayAdapter<User> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userList=findViewById(R.id.list);
        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User user=arrayAdapter.getItem(position);
                if (user!=null){
                    Intent intent=new Intent(getApplicationContext(), UserActivity.class);
                    intent.putExtra("id", user.getId());
                    intent.putExtra("click", 25);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        DatabaseAdapter adapter=new DatabaseAdapter(this);
        adapter.open();
        List<User> users=adapter.getUsers();
        arrayAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
        userList.setAdapter(arrayAdapter);
        adapter.close();

    }

    public void add(View view){
        Intent intent=new Intent(this, UserActivity.class);
        startActivity(intent);
    }
}
