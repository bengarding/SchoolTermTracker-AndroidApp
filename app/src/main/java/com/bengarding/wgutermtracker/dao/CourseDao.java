package com.bengarding.wgutermtracker.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.bengarding.wgutermtracker.entity.Course;

import java.util.List;

@Dao
public interface CourseDao {
    @Query("SELECT * FROM courses WHERE term_id=:termId ORDER BY course_id")
    List<Course> getCourseList(int termId);

//    @Query("SELECT * FROM courses WHERE term_id=:termId AND course_id = :courseId")
//    Course getCourse(int termId, int courseId);

    @Query("SELECT * FROM courses WHERE course_id = :courseId")
    Course getCourse(int courseId);

//    @Query("INSERT INTO courses (term_id, name) VALUES(:termId, \"Course Name\"); ")
//    void addCourse(int termId);

    @Query("SELECT * FROM courses")
    List<Course> getAllCourses();

    @Insert
    void insertCourse(Course course);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAllCourses(Course... course);

    @Update
    void updateCourse(Course course);

    @Delete
    void deleteCourse(Course course);

    @Query("DELETE FROM courses")
    void nukeCourseTable();
}
