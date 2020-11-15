package com.bengarding.wgutermtracker.database;

public class Constant {

    public static final String DATABASE_NAME = "wgu_database.db";

    public static final String TABLE_TERMS = "terms";
    public static final String TERMS_COL_ID = "term_id";
    public static final String TERMS_COL_NAME = "name";
    public static final String TERMS_COL_START_DATE = "start_date";
    public static final String TERMS_COL_END_DATE = "end_date";

    public static final String TABLE_COURSES = "courses";
    public static final String COURSES_COL_ID = "course_id";
    public static final String COURSES_COL_TERM_ID = "term_id";
    public static final String COURSES_COL_MENTOR_ID = "mentor_id";
    public static final String COURSES_COL_NAME = "name";
    public static final String COURSES_COL_START_DATE = "start_date";
    public static final String COURSES_COL_END_DATE = "end_date";
    public static final String COURSES_COL_STATUS = "status";
    public static final String COURSES_COL_NOTES = "notes";
    public static final String COURSES_COL_ALERT_DATE = "alert_date";

    public static final String TABLE_ASSESSMENT = "assessments";
    public static final String ASSESSMENTS_COL_ID = "assessment_id";
    public static final String ASSESSMENTS_COL_NAME = "name";
    public static final String ASSESSMENTS_COL_TYPE = "type";
    public static final String ASSESSMENTS_COL_START_DATE = "start_date";
    public static final String ASSESSMENTS_COL_END_DATE = "end_date";
    public static final String ASSESSMENTS_COL_COURSE_ID = "course_id";

    public static final String TABLE_MENTOR = "mentors";
    public static final String MENTOR_COL_ID = "mentor_id";
    public static final String MENTOR_COL_NAME = "name";
    public static final String MENTOR_COL_PHONE = "phone";
    public static final String MENTOR_COL_EMAIL = "email";
}
