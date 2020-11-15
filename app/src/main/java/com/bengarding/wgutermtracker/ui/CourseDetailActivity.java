package com.bengarding.wgutermtracker.ui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bengarding.wgutermtracker.R;
import com.bengarding.wgutermtracker.database.WguDatabaseRepository;
import com.bengarding.wgutermtracker.entity.Course;
import com.bengarding.wgutermtracker.entity.Mentor;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class CourseDetailActivity extends AppCompatActivity {
    private WguDatabaseRepository dbRepo = new WguDatabaseRepository(getApplication());
    public static int courseId;
    TextView name;
    Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        name = findViewById(R.id.txtCourseNameDetail);
        TextView start = findViewById(R.id.txtCourseStartDetail);
        TextView end = findViewById(R.id.txtCourseEndDetail);
        TextView status = findViewById(R.id.txtCourseStatusDetail);
        TextView mentorName = findViewById(R.id.txtMentorNameDetail);
        TextView mentorPhone = findViewById(R.id.txtMentorPhoneDetail);
        TextView mentorEmail = findViewById(R.id.txtMentorEmailDetail);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

        RecyclerView recyclerView = findViewById(R.id.assessmentRecyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        final CourseDetailAdapter adapter = new CourseDetailAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        courseId = getIntent().getIntExtra("courseId", -1);
        if (courseId == -1) {
            courseId = TermDetailAdapter.courseId;
        }
        course = dbRepo.getCourse(courseId);
        Mentor mentor = dbRepo.getMentor(course.getMentorId());

        adapter.setAssessmentList(dbRepo.getAssessmentList(courseId));
        setTitle(course.getName());
        name.setText(course.getName());
        start.setText(dateFormat.format(course.getStart()));
        end.setText(dateFormat.format(course.getEnd()));
        status.setText(course.getStatus());
        mentorName.setText(mentor.getName());
        mentorPhone.setText(mentor.getPhone());
        mentorEmail.setText(mentor.getEmail());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.btnEdit) {
            Intent intent = new Intent(this, AddCourseActivity.class);
            intent.putExtra("courseId", courseId);
            startActivity(intent);
            return true;
        } else if (id == R.id.btnDelete) {
            dbRepo.delete(dbRepo.getCourse(courseId));
            startActivity(new Intent(this, TermDetailActivity.class));
            return true;
        } else if (id == R.id.btnStartNotification){
            Intent intent = new Intent(this, Receiver.class);
            intent.putExtra("title", name.getText().toString());
            intent.putExtra("text", "Course is starting today");
            PendingIntent sender = PendingIntent.getBroadcast(this, Receiver.alertNumber++, intent, 0);
            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, course.getStart().getTime(), sender);
            return true;
        } else if (id == R.id.btnEndNotification){
            Intent intent = new Intent(this, Receiver.class);
            intent.putExtra("title", name.getText().toString());
            intent.putExtra("text", "Course is ending today");
            PendingIntent sender = PendingIntent.getBroadcast(this, Receiver.alertNumber++, intent, 0);
            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, course.getEnd().getTime(), sender);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addAssessment(View view) {
        if(dbRepo.getAssessmentList(courseId).size() < 5) {
            Intent intent = new Intent(this, AddAssessmentActivity.class);
            intent.putExtra("courseId", courseId);
            startActivity(intent);
        } else {
            Snackbar snackbar = Snackbar.make(view, R.string.up_to_five_assessments, Snackbar.LENGTH_LONG);
            snackbar.show();
        }
    }

    public void editNotes(View view){
        Intent intent = new Intent(this, EditNotesActivity.class);
        intent.putExtra("courseId", courseId);
        startActivity(intent);
    }
}
