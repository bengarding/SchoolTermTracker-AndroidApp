package com.bengarding.wgutermtracker.ui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bengarding.wgutermtracker.R;
import com.bengarding.wgutermtracker.database.WguDatabaseRepository;
import com.bengarding.wgutermtracker.entity.Course;
import com.bengarding.wgutermtracker.entity.Mentor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class CourseDetailActivity extends AppCompatActivity {
    WguDatabaseRepository dbRepo = new WguDatabaseRepository(getApplication());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        TextView name = findViewById(R.id.txtCourseNameDetail);
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

        int courseId = getIntent().getIntExtra("courseId", -1);
        if (courseId == -1) {
            courseId = TermDetailAdapter.courseId;
        }
        Course course = dbRepo.getCourse(courseId);
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
}
