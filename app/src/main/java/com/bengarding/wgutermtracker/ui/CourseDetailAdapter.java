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
import com.bengarding.wgutermtracker.entity.Assessment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class CourseDetailAdapter extends RecyclerView.Adapter<CourseDetailAdapter.AssessmentViewHolder> {

    class AssessmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView assessmentName;
        private final TextView assessmentType;
        private final TextView assessmentStartDate;
        private final TextView assessmentEndDate;

        public AssessmentViewHolder(@NonNull View itemView) {
            super(itemView);
            assessmentName = itemView.findViewById(R.id.txtAssessmentNameItem);
            assessmentType = itemView.findViewById(R.id.txtAssessmentTypeItem);
            assessmentStartDate = itemView.findViewById(R.id.txtAssessmentStartDateItem);
            assessmentEndDate = itemView.findViewById(R.id.txtAssessmentEndDateItem);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                final Assessment current = assessmentList.get(position);
                Intent intent = new Intent(context, AddAssessmentActivity.class);
                intent.putExtra("assessmentId", current.getAssessmentId());
                intent.putExtra("courseId", current.getCourseId());
                context.startActivity(intent);
            });
        }
    }

    private final LayoutInflater inflater;
    private final Context context;
    private List<Assessment> assessmentList;

    public CourseDetailAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_assessment, parent, false);
        return new AssessmentViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AssessmentViewHolder holder, int position) {
        if (assessmentList != null) {
            Assessment current = assessmentList.get(position);
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
            holder.assessmentName.setText(current.getName());
            holder.assessmentType.setText(current.getType());
            holder.assessmentStartDate.setText(dateFormat.format(current.getStartDate()));
            holder.assessmentEndDate.setText(dateFormat.format(current.getEndDate()));
        } else {
            holder.assessmentName.setText("Unable to get name");
            holder.assessmentType.setText("Unable to get type");
            holder.assessmentStartDate.setText("Unable to get stat date");
            holder.assessmentEndDate.setText("Unable to get end date");
        }
    }

    public void setAssessmentList(List<Assessment> assessmentList) {
        this.assessmentList = assessmentList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (assessmentList != null) {
            return assessmentList.size();
        } else {
            return 0;
        }
    }
}
