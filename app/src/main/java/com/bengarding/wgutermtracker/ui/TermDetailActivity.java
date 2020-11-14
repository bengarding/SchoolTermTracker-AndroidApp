package com.bengarding.wgutermtracker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail);

        name = findViewById(R.id.txtNameTermDetail);
        TextView startDate = findViewById(R.id.txtCourseStartDetail);
        TextView endDate = findViewById(R.id.txtCourseEndDetail);
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
        Term term = dbRepo.getTerm(termId);
        adapter.setCourseList(dbRepo.getCourseList(termId));
        setTitle(term.getName());
        name.setText(term.getName());
        startDate.setText(dateFormat.format(term.getStart()));
        endDate.setText(dateFormat.format(term.getEnd()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_term_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.btnEditTerm) {
            Intent intent = new Intent(this, AddTermActivity.class);
            intent.putExtra("termId", termId);
            startActivity(intent);
        } else if (id == R.id.btnDeleteTerm) {
            if (!dbRepo.getCourseList(termId).isEmpty()) {
                Snackbar snackbar = Snackbar.make(name, R.string.cannot_delete_term, Snackbar.LENGTH_LONG);
                snackbar.show();
            } else {
                dbRepo.delete(dbRepo.getTerm(termId));
                startActivity(new Intent(this, TermListActivity.class));
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
