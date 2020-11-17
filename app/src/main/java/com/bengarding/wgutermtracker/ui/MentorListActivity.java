package com.bengarding.wgutermtracker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bengarding.wgutermtracker.R;
import com.bengarding.wgutermtracker.database.WguDatabaseRepository;

public class MentorListActivity  extends AppCompatActivity {

    private WguDatabaseRepository dbRepo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_list);

        dbRepo = new WguDatabaseRepository(getApplication());
        RecyclerView recyclerView = findViewById(R.id.mentorRecyclerView);

        final MentorListAdapter adapter = new MentorListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setMentorList(dbRepo.getAllMentors());
    }

    public void addMentor(View view) {
        Intent intent = new Intent(this, AddMentorActivity.class);
        startActivity(intent);
    }
}
