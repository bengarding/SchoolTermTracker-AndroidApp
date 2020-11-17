package com.bengarding.wgutermtracker.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bengarding.wgutermtracker.R;
import com.bengarding.wgutermtracker.entity.Term;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TermListAdapter extends RecyclerView.Adapter<TermListAdapter.TermViewHolder> {
    public static int termId;

    class TermViewHolder extends RecyclerView.ViewHolder {
        private final TextView termName;
        private final TextView termStart;
        private final TextView termEnd;

        public TermViewHolder(@NonNull View itemView) {
            super(itemView);
            termName = itemView.findViewById(R.id.txtAssessmentNameItem);
            termStart = itemView.findViewById(R.id.txtMentorPhoneItem);
            termEnd = itemView.findViewById(R.id.txtAssessmentEndDateItem);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                final Term  current = termList.get(position);
                Intent intent = new Intent(context, TermDetailActivity.class);
                termId = current.getTermId();
                intent.putExtra("termId", termId);
                context.startActivity(intent);
            });
        }
    }

    private final LayoutInflater inflater;
    private final Context context;
    private List<Term> termList;

    public TermListAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_term, parent, false);

        return new TermViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TermViewHolder holder, int position) {
        if (termList != null) {
            Term current = termList.get(position);
            DateFormat dateFormat =  new SimpleDateFormat("MM/dd/yyyy", Locale.US);
            holder.termName.setText(current.getName());
            holder.termStart.setText(dateFormat.format(current.getStart()));
            holder.termEnd.setText(dateFormat.format(current.getEnd()));
        } else {
            holder.termName.setText("Unable to get name");
            holder.termStart.setText("Unable to get start date");
            holder.termEnd.setText("Unable to get end date");
        }
    }

    public void setTermList(List<Term> termList) {
        this.termList = termList;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        if (termList != null) {
            return termList.size();
        } else {
            return 0;
        }
    }

}
