package com.bengarding.wgutermtracker.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.bengarding.wgutermtracker.dao.AssessmentDao;
import com.bengarding.wgutermtracker.dao.CourseDao;
import com.bengarding.wgutermtracker.dao.MentorDao;
import com.bengarding.wgutermtracker.dao.TermDao;
import com.bengarding.wgutermtracker.entity.Assessment;
import com.bengarding.wgutermtracker.entity.Course;
import com.bengarding.wgutermtracker.entity.Mentor;
import com.bengarding.wgutermtracker.entity.Term;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Term.class, Course.class, Mentor.class, Assessment.class}, exportSchema = false, version = 2)
@TypeConverters(DateTypeConverter.class)
public abstract class WguDatabase extends RoomDatabase {

    public abstract TermDao termDao();
    public abstract CourseDao courseDao();
    public abstract MentorDao mentorDao();
    public abstract AssessmentDao assessmentDao();
    public static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService dbWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static volatile WguDatabase instance;

    public static synchronized WguDatabase getInstance(Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), WguDatabase.class, Constant.DATABASE_NAME)
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }

}
