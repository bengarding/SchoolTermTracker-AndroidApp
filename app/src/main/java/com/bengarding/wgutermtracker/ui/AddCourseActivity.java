package com.bengarding.wgutermtracker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bengarding.wgutermtracker.R;
import com.bengarding.wgutermtracker.database.WguDatabaseRepository;
import com.bengarding.wgutermtracker.entity.Course;
import com.bengarding.wgutermtracker.entity.Mentor;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddCourseActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "AddCourseActivity";
    WguDatabaseRepository dbRepo;
    private final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy", Locale.US);

    private String statusSelected;
    private int mentorSelected;
    private List<Mentor> mentorList;
    private Course course;
    private int courseId;
    private int termId;

    private EditText name;
    private EditText startDate;
    private EditText endDate;
    private EditText notes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        dbRepo = new WguDatabaseRepository(getApplication());
        mentorList = dbRepo.getAllMentors();

        TextView activityTitle = findViewById(R.id.txtAddCourseTitle);
        name = findViewById(R.id.editCourseName);
        startDate = findViewById(R.id.editCourseStart);
        endDate = findViewById(R.id.editCourseEnd);
        notes = findViewById(R.id.editCourseNotes);

        List<String> mentorNames = new ArrayList<>();
        for (Mentor mentor : mentorList) {
            mentorNames.add(mentor.getName());
        }

        List<String> statusOptions = new ArrayList<>();
        statusOptions.add("In Progress");
        statusOptions.add("Completed");
        statusOptions.add("Dropped");
        statusOptions.add("Plan to Take");

        Spinner statusSpinner = findViewById(R.id.spnStatus);
        statusSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, statusOptions);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(statusAdapter);

        Spinner mentorSpinner = findViewById(R.id.spnMentor);
        mentorSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> mentorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mentorNames);
        mentorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mentorSpinner.setAdapter(mentorAdapter);

        termId = getIntent().getIntExtra("termId", -1);
        courseId = getIntent().getIntExtra("courseId", -1);
        if (courseId != -1) {
            course = dbRepo.getCourse(courseId);
            activityTitle.setText(R.string.edit_course);
            setTitle(R.string.edit_course);
            name.setText(course.getName());
            startDate.setText(dateFormat.format(course.getStart()));
            endDate.setText(dateFormat.format(course.getEnd()));
            notes.setText(course.getNotes());

            int position = 0;
            for (int i = 0; i < statusOptions.size(); i++) {
                if (statusOptions.get(i).equals(course.getStatus())) {
                    position = i;
                    break;
                }
            }
            statusSpinner.setSelection(position);

            for (int i =0; i < mentorList.size(); i++){
                if(mentorList.get(i).getMentorId() == course.getMentorId()){
                    position = i;
                    break;
                }
            }
            mentorSpinner.setSelection(position);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner statusSpinner = (Spinner) parent;

        if (statusSpinner.getId() == R.id.spnStatus) {
            statusSelected = parent.getItemAtPosition(position).toString();
        } else {
            String mentorName = parent.getItemAtPosition(position).toString();
            for (Mentor mentor : mentorList) {
                if (mentor.getName().equals(mentorName)) {
                    mentorSelected = mentor.getMentorId();
                }
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    public void save(View view) {
        if (name.getText().toString().isEmpty() || startDate.getText().toString().isEmpty() || endDate.getText().toString().isEmpty()) {
            Snackbar snackbar = Snackbar.make(view, R.string.all_fields_except_notes, Snackbar.LENGTH_LONG);
            snackbar.show();
            return;
        }

        Date start;
        Date end;

        try {
            start = dateFormat.parse(startDate.getText().toString());
        } catch (ParseException e) {
            Snackbar snackbar = Snackbar.make(view, R.string.valid_start, Snackbar.LENGTH_LONG);
            snackbar.show();
            return;
        }

        try {
            end = dateFormat.parse(endDate.getText().toString());
        } catch (ParseException e) {
            Snackbar snackbar = Snackbar.make(view, R.string.valid_end, Snackbar.LENGTH_LONG);
            snackbar.show();
            return;
        }

        if (courseId != -1) {
            course.setName(name.getText().toString());
            course.setStart(start);
            course.setEnd(end);
            course.setStatus(statusSelected);
            course.setMentorId(mentorSelected);
            course.setNotes(notes.getText().toString());

            dbRepo.update(course);
        } else {
            Course course = new Course();
            course.setTermId(termId);
            course.setName(name.getText().toString());
            course.setStart(start);
            course.setEnd(end);
            course.setStatus(statusSelected);
            course.setMentorId(mentorSelected);
            course.setNotes(notes.getText().toString());

            dbRepo.insert(course);
        }

        Intent intent = new Intent(this, TermDetailActivity.class);
        intent.putExtra("termId", termId);
        startActivity(intent);
    }
}
