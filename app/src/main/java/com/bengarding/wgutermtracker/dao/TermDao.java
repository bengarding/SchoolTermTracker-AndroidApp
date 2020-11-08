package com.bengarding.wgutermtracker.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.bengarding.wgutermtracker.entity.Term;

import java.util.List;

@Dao
public interface TermDao {
    @Query("SELECT * FROM terms ORDER BY id")
    List<Term> getAllTerms();

    @Query("SELECT * FROM terms WHERE id = :termId ORDER BY id")
    Term getTerm(int termId);

    @Insert
    void insertTerm(Term term);

    @Insert
    void insertAllTerms(Term... term);

    @Update
    void updateTerm(Term term);

    @Delete
    void deleteTerm(Term term);

    @Query("DELETE FROM terms")
    public void nukeTermTable();
}
