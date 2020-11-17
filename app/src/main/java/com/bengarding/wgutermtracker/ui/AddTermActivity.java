package com.bengarding.wgutermtracker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bengarding.wgutermtracker.R;
import com.bengarding.wgutermtracker.database.WguDatabaseRepository;
import com.bengarding.wgutermtracker.entity.Term;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddTermActivity extends AppCompatActivity {
    WguDatabaseRepository dbRepo;
    private EditText name;
    private EditText startDate;
    private EditText endDate;
    private int termId;
    private Term term;
    private final DateFormat dateFormat =  new SimpleDateFormat("MM/dd/yy", Locale.ENGLISH);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_term);

        dbRepo = new WguDatabaseRepository(getApplication());
        TextView activityTitle = findViewById(R.id.txtAddTermTitle);
        name = findViewById(R.id.editTermName);
        startDate = findViewById(R.id.editStartTerm);
        endDate = findViewById(R.id.editEndTerm);

        termId = getIntent().getIntExtra("termId", -1);
        if(termId != -1) {
            term = dbRepo.getTerm(termId);
            activityTitle.setText(R.string.edit_term);
            setTitle(R.string.edit_term);
            name.setText(term.getName());
            startDate.setText(dateFormat.format(term.getStart()));
            endDate.setText(dateFormat.format(term.getEnd()));
        }
    }

    public void save(View view) {
        if(name.getText().toString().isEmpty() || startDate.getText().toString().isEmpty() || endDate.getText().toString().isEmpty()) {
            Snackbar snackbar = Snackbar.make(view, R.string.all_fields_required, Snackbar.LENGTH_LONG);
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
        } catch (ParseException e){
            Snackbar snackbar = Snackbar.make(view, R.string.valid_end, Snackbar.LENGTH_LONG);
            snackbar.show();
            return;
        }

        if(termId != -1) {
            term.setName(name.getText().toString());
            term.setStart(start);
            term.setEnd(end);

            dbRepo.update(term);
        } else {
            Term term = new Term();
            term.setName(name.getText().toString());
            term.setStart(start);
            term.setEnd(end);

            dbRepo.insert(term);
        }
        startActivity(new Intent(this, TermListActivity.class));
    }
}
