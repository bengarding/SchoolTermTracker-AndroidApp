package com.bengarding.wgutermtracker.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.bengarding.wgutermtracker.entity.Term;

import java.util.List;

@Dao
public interface TermDao {
    @Query("SELECT * FROM terms ORDER BY term_id")
    List<Term> getAllTerms();

    @Query("SELECT * FROM terms WHERE term_id = :termId ORDER BY term_id")
    Term getTerm(int termId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTerm(Term term);

    @Insert
    void insertAllTerms(Term... term);

    @Update
    void updateTerm(Term term);

    @Delete
    void deleteTerm(Term term);

    @Query("DELETE FROM terms")
    void nukeTermTable();
}
