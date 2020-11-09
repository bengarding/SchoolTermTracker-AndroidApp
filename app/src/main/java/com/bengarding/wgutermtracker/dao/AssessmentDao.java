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

    @Query("SELECT * FROM assessments ORDER BY assessment_id")
    List<Assessment> getAllAssessments();

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
