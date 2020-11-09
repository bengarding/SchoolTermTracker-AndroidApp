package com.bengarding.wgutermtracker.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.bengarding.wgutermtracker.database.Constant;
import com.bengarding.wgutermtracker.database.DateTypeConverter;

import java.util.Date;

@Entity(tableName = Constant.TABLE_TERMS)
public class Term {

    @ColumnInfo(name = Constant.TERMS_COL_ID)
    @PrimaryKey(autoGenerate = true)
    public int termId;

    @ColumnInfo(name = Constant.TERMS_COL_NAME)
    private String name;

    @ColumnInfo(name = Constant.TERMS_COL_START_DATE)
    @TypeConverters(DateTypeConverter.class)
    private Date start;

    @ColumnInfo(name = Constant.TERMS_COL_END_DATE)
    @TypeConverters(DateTypeConverter.class)
    private Date end;

    public Term(int termId, String name, Date start, Date end) {
        this.termId = termId;
        this.name = name;
        this.start = start;
        this.end = end;
    }

    @Ignore
    public Term() {
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
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
}
