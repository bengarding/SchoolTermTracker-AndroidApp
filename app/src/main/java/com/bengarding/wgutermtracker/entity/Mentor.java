package com.bengarding.wgutermtracker.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.bengarding.wgutermtracker.database.Constant;

@Entity(tableName = Constant.TABLE_MENTOR)
public class Mentor {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constant.MENTOR_COL_ID)
    private int mentorId;

    @ColumnInfo(name = Constant.MENTOR_COL_NAME)
    private String name;

    @ColumnInfo(name = Constant.MENTOR_COL_PHONE)
    private String phone;

    @ColumnInfo(name = Constant.MENTOR_COL_EMAIL)
    private String email;

    public Mentor(int mentorId, String name, String phone, String email) {
        this.mentorId = mentorId;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    @Ignore
    public Mentor() {
    }

    public int getMentorId() {
        return mentorId;
    }

    public void setMentorId(int mentorId) {
        this.mentorId = mentorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
