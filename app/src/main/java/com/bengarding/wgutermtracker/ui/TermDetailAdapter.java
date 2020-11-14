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
import com.bengarding.wgutermtracker.entity.Course;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TermDetailAdapter extends RecyclerView.Adapter<TermDetailAdapter.CourseViewHolder> {
    public static int courseId;

    class CourseViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseName;
        private final TextView courseStatus;
        private final TextView courseStart;
        private final TextView courseEnd;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            courseName = itemView.findViewById(R.id.txtAssessmentNameItem);
            courseStatus = itemView.findViewById(R.id.txtCourseStatusItem);
            courseStart = itemView.findViewById(R.id.txtCourseStartItem);
            courseEnd = itemView.findViewById(R.id.txtCourseEndItem);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                final Course current = courseList.get(position);
                Intent intent = new Intent(context, CourseDetailActivity.class);
                courseId = current.getCourseId();
                intent.putExtra("courseId", courseId);
                context.startActivity(intent);
            });
        }
    }

    private final LayoutInflater inflater;
    private final Context context;
    private List<Course> courseList;

    public TermDetailAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_course, parent, false);

        return new CourseViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull TermDetailAdapter.CourseViewHolder holder, int position) {
        if (courseList != null) {
            Course current = courseList.get(position);
            DateFormat dateFormat =  new SimpleDateFormat("MM/dd/yyyy", Locale.US);
            holder.courseName.setText(current.getName());
            holder.courseStart.setText(dateFormat.format(current.getStart()));
            holder.courseEnd.setText(dateFormat.format(current.getEnd()));
            holder.courseStatus.setText(current.getStatus());
        } else {
            holder.courseName.setText("Unable to get name");
            holder.courseStart.setText("Unable to get start date");
            holder.courseEnd.setText("Unable to get end date");
            holder.courseStatus.setText("Unable to get status");
        }
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(courseList != null) {
            return courseList.size();
        } else {
            return 0;
        }
    }
}
