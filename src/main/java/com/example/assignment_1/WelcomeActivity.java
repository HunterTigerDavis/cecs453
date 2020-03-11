package com.example.assignment_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;

public class WelcomeActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    private final LinkedList<String> mWordList = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Intent intent = getIntent();
        String username = intent.getStringExtra("USERNAME");

        TextView usernameDisplay = findViewById(R.id.usernameDisplay);
        usernameDisplay.setText("Welcome " + username + "!");

        // Initialize data into the word list
        for (int i = 0; i < 20; i++){
            mWordList.addLast("Word " + i);
        }
        // Create the RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // Create an adapter and supply the data to be displayed
        mAdapter = new WordListAdapter(this, mWordList);
        // Connect the adapter with the recycler view
        mRecyclerView.setAdapter(mAdapter);
        // Give the recycler view a default layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Add a floating action click handler for creating new entries
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int wordListSize = mWordList.size();
                // Add a new word to the wordList
                mWordList.addLast("+ Word " + wordListSize);
                // Notify the adapter that the data has changed
                mRecyclerView.getAdapter().notifyItemInserted(wordListSize);
                // Scroll to the bottom automatically
                mRecyclerView.smoothScrollToPosition(wordListSize);
            }
        });


    }
}


