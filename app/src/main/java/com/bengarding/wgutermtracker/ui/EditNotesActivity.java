package com.bengarding.wgutermtracker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bengarding.wgutermtracker.R;
import com.bengarding.wgutermtracker.database.WguDatabaseRepository;
import com.bengarding.wgutermtracker.entity.Course;

public class EditNotesActivity extends AppCompatActivity {

    WguDatabaseRepository dbRepo;
    TextView notes;
    Course course;
    int courseId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_notes);

        dbRepo = new WguDatabaseRepository(getApplication());
        notes = findViewById(R.id.editNotesEdit);

        courseId =  getIntent().getIntExtra("courseId", -1);
        if(courseId == -1) {
            courseId = CourseDetailActivity.courseId;
        }
        course = dbRepo.getCourse(courseId);
        notes.setText(course.getNotes());
    }

    public void save(View view){
        course.setNotes(notes.getText().toString());
        dbRepo.update(course);

        startActivity(new Intent(this, CourseDetailActivity.class));
    }

    public void share(View view) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, notes.getText().toString());
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }
}
