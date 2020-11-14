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
import java.util.concurrent.ExecutorService;

public class WguDatabaseRepository {
    private static final String TAG = "WguDatabaseRepository";
    private TermDao termDao;
    private CourseDao courseDao;
    private MentorDao mentorDao;
    private AssessmentDao assessmentDao;
    ExecutorService executor = WguDatabase.dbWriteExecutor;

    public WguDatabaseRepository(Application application) {
        WguDatabase db = WguDatabase.getInstance(application);
        termDao = db.termDao();
        courseDao = db.courseDao();
        mentorDao = db.mentorDao();
        assessmentDao = db.assessmentDao();
    }

    public Term getTerm(int termId) {
        return termDao.getTerm(termId);
    }

    public List<Term> getTermList() {
        return termDao.getAllTerms();
    }

    public Course getCourse(int courseId) {
        return courseDao.getCourse(courseId);
    }

    public List<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }

    public List<Course> getCourseList(int termId) {
        return courseDao.getCourseList(termId);
    }

    public Assessment getAssessment(int assessmentId) {
        return assessmentDao.getAssessment(assessmentId);
    }

    public List<Assessment> getAssessmentList(int courseId) {
        return assessmentDao.getAssessmentList(courseId);
    }

    public Mentor getMentor(int mentorId) {
        return mentorDao.getMentor(mentorId);
    }

    public List<Mentor> getAllMentors() {
        return mentorDao.getAllMentors();
    }

    public void insert(Term term) {
        executor.execute(() -> termDao.insertTerm(term));
    }

    public void insert(Term... terms) {
        termDao.insertAllTerms(terms);
    }

    public void insert(Course course) {
        executor.execute(() -> courseDao.insertCourse(course));
    }

    public void insert(Course... courses) {
        courseDao.insertAllCourses(courses);
    }

    public void insert(Assessment assessment) {
        executor.execute(() -> assessmentDao.insertAssessment(assessment));
    }

    public void insert(Assessment... assessments) {
        assessmentDao.insertAllAssessments(assessments);
    }

    public void insert(Mentor mentor) {
        executor.execute(() -> mentorDao.insertMentor(mentor));
    }

    public void insert(Mentor... mentors) {
        mentorDao.insertAllMentors(mentors);
    }

    public void update(Term term) {
        executor.execute(() -> termDao.updateTerm(term));
    }

    public void update(Course course) {
        executor.execute(() -> courseDao.updateCourse(course));
    }

    public void update(Assessment assessment) {
        executor.execute(() -> assessmentDao.updateAssessment(assessment));
    }

    public void update(Mentor mentor) {
        executor.execute(() -> mentorDao.updateMentor(mentor));
    }

    public void delete(Term term) {
        executor.execute(() -> termDao.deleteTerm(term));
    }

    public void delete(Course course) {
        executor.execute(() -> courseDao.deleteCourse(course));
    }

    public void delete(Assessment assessment) {
        executor.execute(() -> assessmentDao.deleteAssessment(assessment));
    }

    public void delete(Mentor mentor) {
        executor.execute(() -> mentorDao.deleteMentor(mentor));
    }

    public void nukeAllTables() {
            termDao.nukeTermTable();
            courseDao.nukeCourseTable();
            assessmentDao.nukeAssessmentTable();
            mentorDao.nukeMentorTable();
    }
}
