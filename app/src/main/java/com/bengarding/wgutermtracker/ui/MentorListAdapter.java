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
import com.bengarding.wgutermtracker.entity.Mentor;

import java.util.List;

public class MentorListAdapter extends RecyclerView.Adapter<MentorListAdapter.MentorViewHolder> {

    class MentorViewHolder extends RecyclerView.ViewHolder {
        private final TextView mentorName;
        private final TextView mentorEmail;
        private final   TextView mentorPhone;

        public MentorViewHolder(@NonNull View itemView) {
            super(itemView);
            mentorName = itemView.findViewById(R.id.txtMentorNameItem);
            mentorEmail = itemView.findViewById(R.id.txtMentorEmailItem);
            mentorPhone = itemView.findViewById(R.id.txtMentorPhoneItem);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                final Mentor current = mentorList.get(position);
                Intent intent = new Intent(context, AddMentorActivity.class);
                intent.putExtra("mentorId", current.getMentorId());
                context.startActivity(intent);
            });
        }
    }

    private final LayoutInflater inflater;
    private final Context context;
    private List<Mentor> mentorList;

    public MentorListAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MentorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.item_mentor, parent, false);

        return new MentorViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MentorViewHolder holder, int position) {
        if(mentorList != null) {
            Mentor current = mentorList.get(position);
            holder.mentorName.setText(current.getName());
            holder.mentorPhone.setText(current.getPhone());
            holder.mentorEmail.setText(current.getEmail());
        } else {
            holder.mentorName.setText("Unable to get name");
            holder.mentorPhone.setText("Unable to get phone number");
            holder.mentorEmail.setText("Unable to get email");
        }
    }

    public void setMentorList(List<Mentor> mentorList){
        this.mentorList = mentorList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mentorList != null) {
            return mentorList.size();
        } else {
            return 0;
        }
    }
}
