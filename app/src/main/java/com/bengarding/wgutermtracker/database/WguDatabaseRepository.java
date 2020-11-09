package com.bengarding.wgutermtracker.database;

import android.app.Application;

import com.bengarding.wgutermtracker.dao.AssessmentDao;
import com.bengarding.wgutermtracker.dao.CourseDao;
import com.bengarding.wgutermtracker.dao.MentorDao;
import com.bengarding.wgutermtracker.dao.TermDao;
import com.bengarding.wgutermtracker.entity.Assessment;
import com.bengarding.wgutermtracker.entity.Course;
import com.bengarding.wgutermtracker.entity.Mentor;
import com.bengarding.wgutermtracker.entity.Term;

import java.util.List;

public class WguDatabaseRepository {
    private static final String TAG = "WguDatabaseRepository";
    private TermDao termDao;
    private CourseDao courseDao;
    private MentorDao mentorDao;
    private AssessmentDao assessmentDao;

    private List<Term> termList;
    private List<Course> courseList;
    private List<Assessment> assessmentList;
    private List<Mentor> mentorList;

    private Term term;
    private Course course;
    private Assessment assessment;
    private Mentor mentor;

    public WguDatabaseRepository(Application application) {
        WguDatabase db = WguDatabase.getInstance(application);
        termDao = db.termDao();
        courseDao = db.courseDao();
        mentorDao = db.mentorDao();
        assessmentDao = db.assessmentDao();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Term getTerm(int termId) {
        WguDatabase.dbWriteExecutor.execute(() -> term = termDao.getTerm(termId));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return term;
    }

    public List<Term> getTermList() {
        WguDatabase.dbWriteExecutor.execute(() -> termList = termDao.getAllTerms());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return termList;
    }

    public Course getCourse(int termId, int courseId) {
        WguDatabase.dbWriteExecutor.execute(() -> course = courseDao.getCourse(termId, courseId));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return course;
    }

    public List<Course> getAllCourses() {
        WguDatabase.dbWriteExecutor.execute(() -> courseList = courseDao.getAllCourses());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return courseList;
    }

    public List<Course> getCourseList(int termId) {
        WguDatabase.dbWriteExecutor.execute(() -> courseList = courseDao.getCourseList(termId));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return courseList;
    }

    public Assessment getAssessment(int assessmentId) {
        WguDatabase.dbWriteExecutor.execute(() -> assessment = assessmentDao.getAssessment(assessmentId));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return assessment;
    }

    public List<Assessment> getAllAssessments() {
        WguDatabase.dbWriteExecutor.execute(() -> assessmentList = assessmentDao.getAllAssessments());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return assessmentList;
    }

    public Mentor getMentor(int mentorId) {
        WguDatabase.dbWriteExecutor.execute(() -> mentor = mentorDao.getMentor(mentorId));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mentor;
    }

    public List<Mentor> getAllMentors() {
        WguDatabase.dbWriteExecutor.execute(() -> mentorList = mentorDao.getAllMentors());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mentorList;
    }

    public void insert(Term term) {
        WguDatabase.dbWriteExecutor.execute(() -> termDao.insertTerm(term));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void insert(Term... terms) {
        WguDatabase.dbWriteExecutor.execute(() -> termDao.insertAllTerms(terms));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void insert(Course course) {
        WguDatabase.dbWriteExecutor.execute(() -> courseDao.insertCourse(course));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void insert(Course... courses) {
        WguDatabase.dbWriteExecutor.execute(() -> courseDao.insertAllCourses(courses));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void insert(Assessment assessment) {
        WguDatabase.dbWriteExecutor.execute(() -> assessmentDao.insertAssessment(assessment));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void insert(Assessment... assessments) {
        WguDatabase.dbWriteExecutor.execute(() -> assessmentDao.insertAllAssessments(assessments));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void insert(Mentor mentor) {
        WguDatabase.dbWriteExecutor.execute(() -> mentorDao.insertMentor(mentor));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void insert(Mentor... mentors) {
        WguDatabase.dbWriteExecutor.execute(() -> mentorDao.insertAllMentors(mentors));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Term term) {
        WguDatabase.dbWriteExecutor.execute(() -> termDao.updateTerm(term));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Course course) {
        WguDatabase.dbWriteExecutor.execute(() -> courseDao.updateCourse(course));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Assessment assessment) {
        WguDatabase.dbWriteExecutor.execute(() -> assessmentDao.updateAssessment(assessment));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Mentor mentor) {
        WguDatabase.dbWriteExecutor.execute(() -> mentorDao.updateMentor(mentor));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Term term) {
        WguDatabase.dbWriteExecutor.execute(() -> termDao.deleteTerm(term));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Course course) {
        WguDatabase.dbWriteExecutor.execute(() -> courseDao.deleteCourse(course));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Assessment assessment) {
        WguDatabase.dbWriteExecutor.execute(() -> assessmentDao.deleteAssessment(assessment));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Mentor mentor) {
        WguDatabase.dbWriteExecutor.execute(() -> mentorDao.deleteMentor(mentor));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void nukeAllTables() {
        WguDatabase.dbWriteExecutor.execute(() -> {
            termDao.nukeTermTable();
            courseDao.nukeCourseTable();
            assessmentDao.nukeAssessmentTable();
            mentorDao.nukeMentorTable();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
