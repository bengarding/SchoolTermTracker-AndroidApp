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
import com.bengarding.wgutermtracker.entity.Term;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class TermDetailActivity extends AppCompatActivity {

    private final WguDatabaseRepository dbRepo = new WguDatabaseRepository(getApplication());
    private int termId;
    TextView name;
    TextView startDate;
    TextView endDate;
    Term term;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail);

        name = findViewById(R.id.txtNameTermDetail);
        startDate = findViewById(R.id.txtCourseStartDetail);
        endDate = findViewById(R.id.txtCourseEndDetail);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

        RecyclerView recyclerView = findViewById(R.id.assessmentRecyclerView);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        final TermDetailAdapter adapter = new TermDetailAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        termId = getIntent().getIntExtra("termId", -1);
        if (termId == -1) {
            termId = TermListAdapter.termId;
        }
        term = dbRepo.getTerm(termId);
        adapter.setCourseList(dbRepo.getCourseList(termId));
        setTitle(term.getName());
        name.setText(term.getName());
        startDate.setText(dateFormat.format(term.getStart()));
        endDate.setText(dateFormat.format(term.getEnd()));
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
            Intent intent = new Intent(this, AddTermActivity.class);
            intent.putExtra("termId", termId);
            startActivity(intent);
            return true;
        } else if (id == R.id.btnDelete) {
            if (!dbRepo.getCourseList(termId).isEmpty()) {
                Snackbar snackbar = Snackbar.make(name, R.string.cannot_delete_term, Snackbar.LENGTH_LONG);
                snackbar.show();
            } else {
                dbRepo.delete(dbRepo.getTerm(termId));
                startActivity(new Intent(this, TermListActivity.class));
            }
            return true;
        } else if (id == R.id.btnStartNotification){
            Intent intent = new Intent(this, Receiver.class);
            intent.putExtra("title", name.getText().toString());
            intent.putExtra("text", "Term is starting today");
            PendingIntent sender = PendingIntent.getBroadcast(this, Receiver.alertNumber++, intent, 0);
            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, term.getStart().getTime(), sender);
            return true;
        } else if (id == R.id.btnEndNotification){
            Intent intent = new Intent(this, Receiver.class);
            intent.putExtra("title", name.getText().toString());
            intent.putExtra("text", "Term is ending today");
            PendingIntent sender = PendingIntent.getBroadcast(this, Receiver.alertNumber++, intent, 0);
            AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, term.getEnd().getTime(), sender);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void addCourse(View view) {
        Intent intent = new Intent(this, AddCourseActivity.class);
        intent.putExtra("termId", termId);
        startActivity(intent);
    }
}
