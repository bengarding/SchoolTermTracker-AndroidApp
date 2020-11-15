package com.bengarding.wgutermtracker.ui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bengarding.wgutermtracker.R;
import com.bengarding.wgutermtracker.database.WguDatabaseRepository;
import com.bengarding.wgutermtracker.entity.Assessment;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddAssessmentActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy", Locale.US);
    private WguDatabaseRepository dbRepo;
    private String type;
    private int courseId;
    private int assessmentId;
    private Assessment assessment;

    private EditText name;
    private EditText startDate;
    private EditText endDate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assessment);

        dbRepo = new WguDatabaseRepository(getApplication());
        TextView activityTitle = findViewById(R.id.txtAddAssessmentTitle);
        name = findViewById(R.id.editAssessmentName);
        startDate = findViewById(R.id.editAssessmentStartDate);
        endDate = findViewById(R.id.editAssessmentEndDate);

        List<String> typeOptions = new ArrayList<>();
        typeOptions.add("Objective");
        typeOptions.add("Performance");

        Spinner typeSpinner = findViewById(R.id.spnType);
        typeSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, typeOptions);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);

        courseId = getIntent().getIntExtra("courseId", -1);
        assessmentId = getIntent().getIntExtra("assessmentId", -1);
        if (assessmentId != -1) {
            assessment = dbRepo.getAssessment(assessmentId);
            activityTitle.setText(R.string.edit_assessment);
            setTitle(R.string.edit_assessment);
            name.setText(assessment.getName());
            startDate.setText(dateFormat.format(assessment.getStartDate()));
            endDate.setText(dateFormat.format(assessment.getEndDate()));

            if (assessment.getType().equals("Objective")) {
                typeSpinner.setSelection(0);
            } else {
                typeSpinner.setSelection(1);
            }
        }
    }

    public void save(View view) {

        if (name.getText().toString().isEmpty() || endDate.getText().toString().isEmpty() || startDate.getText().toString().isEmpty()) {
            Snackbar snackbar = Snackbar.make(view, R.string.all_fields_required, Snackbar.LENGTH_LONG);
            snackbar.show();
            return;
        }

        Date endDateEntered;
        Date startDateEntered;

        try {
            endDateEntered = dateFormat.parse(endDate.getText().toString());
        } catch (ParseException e) {
            Snackbar snackbar = Snackbar.make(view, R.string.valid_end, Snackbar.LENGTH_LONG);
            snackbar.show();
            return;
        }

        try {
            startDateEntered = dateFormat.parse(startDate.getText().toString());
        } catch (ParseException e) {
            Snackbar snackbar = Snackbar.make(view, R.string.valid_start, Snackbar.LENGTH_LONG);
            snackbar.show();
            return;
        }

        if (assessmentId != -1) {
            assessment.setName(name.getText().toString());
            assessment.setStartDate(startDateEntered);
            assessment.setEndDate(endDateEntered);
            assessment.setType(type);

            dbRepo.update(assessment);
        } else {
            Assessment assessment = new Assessment();
            assessment.setCourseId(courseId);
            assessment.setName(name.getText().toString());
            assessment.setStartDate(startDateEntered);
            assessment.setEndDate(endDateEntered);
            assessment.setType(type);

            dbRepo.insert(assessment);
        }

        Intent intent = new Intent(this, CourseDetailActivity.class);
        intent.putExtra("courseId", courseId);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        type = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (assessmentId != -1) {
            getMenuInflater().inflate(R.menu.menu_assessment_full, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu_assessment, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.btnDeleteAssessment) {
            dbRepo.delete(assessment);
            startActivity(new Intent(this, CourseDetailActivity.class));
            return true;
        } else if (id == R.id.btnAssessmentStartNotification || id == R.id.btnAssessmentStartNotification1) {
            Intent intent = new Intent(this, Receiver.class);
            intent.putExtra("title", name.getText().toString());
            intent.putExtra("text", "Assessment starts today");
            PendingIntent sender = PendingIntent.getBroadcast(this, Receiver.alertNumber++, intent, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, assessment.getStartDate().getTime(), sender);
            return true;
        } else if (id == R.id.btnAssessmentEndNotification || id == R.id.btnAssessmentEndNotification1) {
            Intent intent = new Intent(this, Receiver.class);
            intent.putExtra("title", name.getText().toString());
            intent.putExtra("text", "Assessment is due today");
            PendingIntent sender = PendingIntent.getBroadcast(this, Receiver.alertNumber++, intent, 0);
            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, assessment.getEndDate().getTime(), sender);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
