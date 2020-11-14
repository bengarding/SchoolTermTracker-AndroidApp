package com.bengarding.wgutermtracker.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bengarding.wgutermtracker.entity.Assessment;

import java.util.List;

@Dao
public interface AssessmentDao {

    @Query("SELECT * FROM assessments WHERE assessment_id = :assessmentId")
    Assessment getAssessment(int assessmentId);

    @Query("SELECT * FROM assessments WHERE course_id=:courseId ORDER BY assessment_id")
    List<Assessment> getAssessmentList(int courseId);

    @Insert
    void insertAssessment(Assessment assessment);

    @Insert
    void insertAllAssessments(Assessment... assessment);

    @Update
    void updateAssessment(Assessment assessment);

    @Delete
    void deleteAssessment(Assessment assessment);

    @Query("DELETE FROM assessments")
    void nukeAssessmentTable();
}
