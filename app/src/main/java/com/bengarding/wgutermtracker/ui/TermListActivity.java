package com.bengarding.wgutermtracker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bengarding.wgutermtracker.R;
import com.bengarding.wgutermtracker.database.PopulateDatabase;
import com.bengarding.wgutermtracker.database.WguDatabaseRepository;

public class TermListActivity extends AppCompatActivity {

    private WguDatabaseRepository dbRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);

        dbRepo = new WguDatabaseRepository(getApplication());
        RecyclerView recyclerView = findViewById(R.id.termRecyclerView);

        final TermListAdapter adapter = new TermListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setTermList(dbRepo.getTermList());
        PopulateDatabase populateDatabase = new PopulateDatabase();
        populateDatabase.insertMentors();
    }

    public void addTerm(View view) {
        Intent intent = new Intent(this, AddTermActivity.class);
        startActivity(intent);
    }
}