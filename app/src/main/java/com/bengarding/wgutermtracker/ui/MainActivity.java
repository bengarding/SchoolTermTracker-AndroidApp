package com.bengarding.wgutermtracker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;

import com.bengarding.wgutermtracker.R;
import com.bengarding.wgutermtracker.database.PopulateDatabase;
import com.bengarding.wgutermtracker.database.WguDatabaseRepository;
import com.bengarding.wgutermtracker.entity.Course;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    PopulateDatabase populateDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateCounts();

        populateDatabase = new PopulateDatabase();
        ConstraintLayout currentLayout = findViewById(R.id.constraintLayout);
        ConstraintSet set = new ConstraintSet();
        Button btnEnter = new Button(this);
        btnEnter.setText(R.string.enter);
        btnEnter.setId(R.id.btnEnter);

        set.constrainHeight(btnEnter.getId(), ConstraintSet.WRAP_CONTENT);
        set.constrainWidth(btnEnter.getId(), 0);
        set.connect(btnEnter.getId(), ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP, 16);
        set.connect(btnEnter.getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 32);
        set.connect(btnEnter.getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 32);
        btnEnter.setBackgroundColor(ContextCompat.getColor(this, R.color.blue_primary));
        btnEnter.setTextColor(ContextCompat.getColor(this, R.color.white));

        currentLayout.addView(btnEnter);
        setContentView(currentLayout);
        set.applyTo(currentLayout);

        btnEnter.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TermListActivity.class)));

        populateDatabase.insertMentors();
    }

    public void populateData(View view) {
        populateDatabase.populate(MainActivity.this);
        updateCounts();
    }

    public void clearData(View view) {
        WguDatabaseRepository repository = new WguDatabaseRepository(getApplication());
        repository.nukeAllTables();
        updateCounts();
    }

    private void updateCounts() {
        int courseCount = 0;
        int inProgressCount = 0;
        int droppedCount = 0;
        int completedCount = 0;
        int planToTakeCount = 0;

        TextView courseCountView = findViewById(R.id.txtCourseCount);
        TextView inProgressCountView = findViewById(R.id.txtProgressCount);
        TextView droppedCountView = findViewById(R.id.txtDroppedCount);
        TextView completedCountView = findViewById(R.id.txtCompleteCount);
        TextView planToTakeCountView = findViewById(R.id.txtPlanCount);

        WguDatabaseRepository dbRepo = new WguDatabaseRepository(getApplication());
        List<Course> courseList = new ArrayList<>();
        courseList = dbRepo.getAllCourses();

        for (Course course : courseList) {
            courseCount++;
            switch (course.getStatus()) {
                case "In Progress":
                    inProgressCount++;
                    break;
                case "Dropped":
                    droppedCount++;
                    break;
                case "Completed":
                    completedCount++;
                    break;
                case "Plan to Take":
                    planToTakeCount++;
                    break;
                default:
                    break;
            }
        }
        courseCountView.setText(String.valueOf(courseCount));
        inProgressCountView.setText(String.valueOf(inProgressCount));
        droppedCountView.setText(String.valueOf(droppedCount));
        completedCountView.setText(String.valueOf(completedCount));
        planToTakeCountView.setText(String.valueOf(planToTakeCount));
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateCounts();
    }
}