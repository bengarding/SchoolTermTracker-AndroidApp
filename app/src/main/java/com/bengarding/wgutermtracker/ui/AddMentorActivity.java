package com.bengarding.wgutermtracker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bengarding.wgutermtracker.R;
import com.bengarding.wgutermtracker.database.WguDatabaseRepository;
import com.bengarding.wgutermtracker.entity.Mentor;
import com.google.android.material.snackbar.Snackbar;

public class AddMentorActivity extends AppCompatActivity {
    WguDatabaseRepository dbRepo;
    private EditText name;
    private EditText phone;
    private EditText email;
    private int mentorId;
    private Mentor mentor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mentor);

        dbRepo = new WguDatabaseRepository(getApplication());
        TextView activityTitle = findViewById(R.id.txtAddMentorTitle);
        name = findViewById(R.id.editMentorName);
        phone = findViewById(R.id.editMentorPhone);
        email = findViewById(R.id.editMentorEmail);

        mentorId = getIntent().getIntExtra("mentorId", -1);
        if (mentorId != -1) {
            mentor = dbRepo.getMentor(mentorId);
            activityTitle.setText(R.string.edit_mentor);
            setTitle(R.string.edit_mentor);
            name.setText(mentor.getName());
            phone.setText(mentor.getPhone());
            email.setText(mentor.getEmail());
        }

    }

    public void save(View view) {
        if (name.getText().toString().isEmpty() || phone.getText().toString().isEmpty() || email.getText().toString().isEmpty()) {
            Snackbar snackbar = Snackbar.make(view, R.string.all_fields_required, Snackbar.LENGTH_LONG);
            snackbar.show();
            return;
        }

        if (mentorId != -1) {
            mentor.setName(name.getText().toString());
            mentor.setPhone(phone.getText().toString());
            mentor.setEmail(email.getText().toString());

            dbRepo.update(mentor);
        } else {
            Mentor mentor = new Mentor();
            mentor.setName(name.getText().toString());
            mentor.setPhone(phone.getText().toString());
            mentor.setEmail(email.getText().toString());

            dbRepo.insert(mentor);
        }
        startActivity(new Intent(this, MentorListActivity.class));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (mentorId != -1) {
            getMenuInflater().inflate(R.menu.menu_delete, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.btnDeleteMentor) {
            if (!dbRepo.getCourseListForMentor(mentorId).isEmpty()) {
                Snackbar snackbar = Snackbar.make(name, R.string.cannot_delete_mentor_with_courses, Snackbar.LENGTH_LONG);
                snackbar.show();
            } else if (dbRepo.getAllMentors().size() <= 1) {
                Snackbar snackbar = Snackbar.make(name, R.string.cannot_delete_mentor, Snackbar.LENGTH_LONG);
                snackbar.show();
            } else {
                dbRepo.delete(mentor);
                startActivity(new Intent(this, MentorListActivity.class));
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
