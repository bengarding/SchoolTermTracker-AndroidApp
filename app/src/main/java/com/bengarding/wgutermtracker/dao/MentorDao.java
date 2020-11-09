package com.bengarding.wgutermtracker.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bengarding.wgutermtracker.entity.Mentor;

import java.util.List;

@Dao
public interface MentorDao {

    @Query("SELECT * FROM mentors WHERE mentor_id = :mentorId")
    Mentor getMentor(int mentorId);

    @Query("SELECT * FROM mentors ORDER BY name")
    List<Mentor> getAllMentors();

    @Insert
    void insertMentor(Mentor mentor);

    @Insert
    void insertAllMentors(Mentor... mentor);

    @Update
    void updateMentor(Mentor mentor);

    @Delete
    void deleteMentor(Mentor mentor);

    @Query("DELETE FROM mentors")
    void nukeMentorTable();
}
