package com.bengarding.wgutermtracker.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.bengarding.wgutermtracker.database.Constant;
import com.bengarding.wgutermtracker.database.DateTypeConverter;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = Constant.TABLE_ASSESSMENT,
        foreignKeys = @ForeignKey(entity = Course.class,
                parentColumns = Constant.COURSES_COL_ID,
                childColumns = Constant.ASSESSMENTS_COL_COURSE_ID,
                onDelete = CASCADE))
public class Assessment {

    @ColumnInfo(name = Constant.ASSESSMENTS_COL_ID)
    @PrimaryKey(autoGenerate = true)
    private int assessmentId;

    @ColumnInfo(name = Constant.ASSESSMENTS_COL_NAME)
    private String name;

    @ColumnInfo(name = Constant.ASSESSMENTS_COL_TYPE)
    private String type;

    @ColumnInfo(name = Constant.ASSESSMENTS_COL_END_DATE)
    @TypeConverters(DateTypeConverter.class)
    private Date endDate;

    @ColumnInfo(name = Constant.ASSESSMENTS_COL_START_DATE)
    @TypeConverters(DateTypeConverter.class)
    private Date startDate;

    @ColumnInfo(name = Constant.ASSESSMENTS_COL_COURSE_ID)
    private int courseId;

    public Assessment(int assessmentId, String name, String type, Date endDate, Date startDate, int courseId) {
        this.assessmentId = assessmentId;
        this.name = name;
        this.type = type;
        this.endDate = endDate;
        this.startDate = startDate;
        this.courseId = courseId;
    }

    @Ignore
    public Assessment() {
    }

    public int getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
