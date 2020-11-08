package com.bengarding.wgutermtracker.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.bengarding.wgutermtracker.database.Constant;
import com.bengarding.wgutermtracker.database.DateTypeConverter;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = Constant.TABLE_COURSES,
        foreignKeys = {
                @ForeignKey(entity = Term.class,
                        parentColumns = Constant.TERMS_COL_ID,
                        childColumns = Constant.COURSES_COL_TERM_ID,
                        onDelete = CASCADE),
                @ForeignKey(entity = Mentor.class,
                        parentColumns = Constant.MENTOR_COL_ID,
                        childColumns = Constant.COURSES_COL_MENTOR_ID)
        })
public class Course {

    @ColumnInfo(name = Constant.COURSES_COL_ID)
    @PrimaryKey(autoGenerate = true)
    private int courseId;

    @ColumnInfo(name = Constant.COURSES_COL_TERM_ID)
    private int termId;

    @ColumnInfo(name = Constant.COURSES_COL_MENTOR_ID)
    private int mentorId;

    @ColumnInfo(name = Constant.COURSES_COL_NAME)
    private String name;

    @ColumnInfo(name = Constant.COURSES_COL_START_DATE)
    @TypeConverters(DateTypeConverter.class)
    private Date start;

    @ColumnInfo(name = Constant.COURSES_COL_END_DATE)
    @TypeConverters(DateTypeConverter.class)
    private Date end;

    @ColumnInfo(name = Constant.COURSES_COL_STATUS)
    private String status;

    @ColumnInfo(name = Constant.COURSES_COL_NOTES)
    private String notes;

    @TypeConverters(DateTypeConverter.class)
    @ColumnInfo(name = Constant.COURSES_COL_ALERT_DATE)
    private Date alertDate;

    public Course(int courseId, int termId, int mentorId, String name, Date start, Date end, String status, String notes, Date alertDate) {
        this.courseId = courseId;
        this.termId = termId;
        this.mentorId = mentorId;
        this.name = name;
        this.start = start;
        this.end = end;
        this.status = status;
        this.notes = notes;
        this.alertDate = alertDate;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(Date alertDate) {
        this.alertDate = alertDate;
    }
}
