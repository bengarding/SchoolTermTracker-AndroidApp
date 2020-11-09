package com.bengarding.wgutermtracker.ui;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bengarding.wgutermtracker.R;
import com.bengarding.wgutermtracker.database.PopulateDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PopulateDatabase populateDatabase = new PopulateDatabase();
        populateDatabase.populate(MainActivity.this);
    }

    public void populate(View view) {

    }
}