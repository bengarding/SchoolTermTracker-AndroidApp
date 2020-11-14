package com.bengarding.wgutermtracker.database;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.bengarding.wgutermtracker.entity.Assessment;
import com.bengarding.wgutermtracker.entity.Course;
import com.bengarding.wgutermtracker.entity.Mentor;
import com.bengarding.wgutermtracker.entity.Term;

import java.util.Calendar;
import java.util.List;

public class PopulateDatabase extends AppCompatActivity {
    private static final String TAG = "PopulateDatabase";
    private WguDatabaseRepository dbRepo;
    WguDatabase db;

    public void populate(Context context) {
        dbRepo = new WguDatabaseRepository(getApplication());
        try {
            insertTerms();
            insertMentors();
            insertCourses();
            insertAssessments();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "populate: failed to populate database");
        }
    }

    private void insertTerms() {
        Term sampleTerm1 = new Term();
        Term sampleTerm2 = new Term();
        Term sampleTerm3 = new Term();

        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        start.clear();
        end.clear();
        start.set(2020, 7, 1);
        end.set(2020, 9, 30);
        sampleTerm1.setName("Summer 2020");
        sampleTerm1.setStart(start.getTime());
        sampleTerm1.setEnd(end.getTime());

        start.clear();
        end.clear();
        start.set(2020, 10, 1);
        end.set(2020, 12, 31);
        sampleTerm2.setName("Fall 2020");
        sampleTerm2.setStart(start.getTime());
        sampleTerm2.setEnd(end.getTime());

        start.clear();
        end.clear();
        start.set(2021, 1, 1);
        end.set(2021, 3, 31);
        sampleTerm3.setName("Winter 2021");
        sampleTerm3.setStart(start.getTime());
        sampleTerm3.setEnd(end.getTime());

        dbRepo.insert(sampleTerm1, sampleTerm2, sampleTerm3);
    }

    private void insertCourses() {
        Course sampleCourse1 = new Course();
        Course sampleCourse2 = new Course();
        Course sampleCourse3 = new Course();
        List<Term> termList = dbRepo.getTermList();
        List<Mentor> mentorList = dbRepo.getAllMentors();
        if (termList == null || mentorList == null) {
            return;
        }

        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();

        start.clear();
        end.clear();
        start.set(2020, 7, 1);
        end.set(2020, 7, 31);
        sampleCourse1.setName("C182 - Introduction to IT");
        sampleCourse1.setStart(start.getTime());
        sampleCourse1.setEnd(end.getTime());
        sampleCourse1.setStatus("Completed");
        sampleCourse1.setNotes("Sample notes");
        sampleCourse1.setMentorId(mentorList.get(0).getMentorId());
        sampleCourse1.setTermId(termList.get(0).getTermId());

        start.clear();
        end.clear();
        start.set(2020, 8, 1);
        end.set(2020, 8, 31);
        sampleCourse2.setName("C173 - Scripting and Programming Foundations");
        sampleCourse2.setStart(start.getTime());
        sampleCourse2.setEnd(end.getTime());
        sampleCourse2.setStatus("Completed");
        sampleCourse2.setNotes("Sample notes");
        sampleCourse2.setMentorId(mentorList.get(1).getMentorId());
        sampleCourse2.setTermId(termList.get(0).getTermId());

        start.clear();
        end.clear();
        start.set(2020, 9, 1);
        end.set(2020, 9, 30);
        sampleCourse3.setName("C779 - Web Development Foundations");
        sampleCourse3.setStart(start.getTime());
        sampleCourse3.setEnd(end.getTime());
        sampleCourse3.setStatus("Dropped");
        sampleCourse3.setNotes("Sample notes");
        sampleCourse3.setMentorId(mentorList.get(2).getMentorId());
        sampleCourse3.setTermId(termList.get(0).getTermId());

        dbRepo.insert(sampleCourse1, sampleCourse2, sampleCourse3);

        start.clear();
        end.clear();
        start.set(2020, 10, 1);
        end.set(2020, 10, 31);
        sampleCourse1.setName("C482 - Software I");
        sampleCourse1.setStart(start.getTime());
        sampleCourse1.setEnd(end.getTime());
        sampleCourse1.setStatus("Completed");
        sampleCourse1.setNotes("Sample notes");
        sampleCourse1.setMentorId(mentorList.get(0).getMentorId());
        sampleCourse1.setTermId(termList.get(1).getTermId());

        start.clear();
        end.clear();
        start.set(2020, 11, 1);
        end.set(2020, 11, 30);
        sampleCourse2.setName("C195 - Software II");
        sampleCourse2.setStart(start.getTime());
        sampleCourse2.setEnd(end.getTime());
        sampleCourse2.setStatus("In Progress");
        sampleCourse2.setNotes("Sample notes");
        sampleCourse2.setMentorId(mentorList.get(1).getMentorId());
        sampleCourse2.setTermId(termList.get(1).getTermId());

        start.clear();
        end.clear();
        start.set(2020, 12, 1);
        end.set(2020, 12, 31);
        sampleCourse3.setName("C196 - Mobile Application Development");
        sampleCourse3.setStart(start.getTime());
        sampleCourse3.setEnd(end.getTime());
        sampleCourse3.setStatus("Plan to Take");
        sampleCourse3.setNotes("Sample notes");
        sampleCourse3.setMentorId(mentorList.get(2).getMentorId());
        sampleCourse3.setTermId(termList.get(1).getTermId());

        dbRepo.insert(sampleCourse1, sampleCourse2, sampleCourse3);

        start.clear();
        end.clear();
        start.set(2021, 1, 1);
        end.set(2021, 1, 31);
        sampleCourse1.setName("C188 - Software Engineering");
        sampleCourse1.setStart(start.getTime());
        sampleCourse1.setEnd(end.getTime());
        sampleCourse1.setStatus("Plan to Take");
        sampleCourse1.setNotes("Sample notes");
        sampleCourse1.setMentorId(mentorList.get(0).getMentorId());
        sampleCourse1.setTermId(termList.get(2).getTermId());

        start.clear();
        end.clear();
        start.set(2021, 2, 1);
        end.set(2021, 2, 28);
        sampleCourse2.setName("C856 - User Experience Design");
        sampleCourse2.setStart(start.getTime());
        sampleCourse2.setEnd(end.getTime());
        sampleCourse2.setStatus("Plan to Take");
        sampleCourse2.setNotes("Sample notes");
        sampleCourse2.setMentorId(mentorList.get(1).getMentorId());
        sampleCourse2.setTermId(termList.get(2).getTermId());

        start.clear();
        end.clear();
        start.set(2021, 3, 1);
        end.set(2021, 3, 31);
        sampleCourse3.setName("C857 - Software Quality Assurance");
        sampleCourse3.setStart(start.getTime());
        sampleCourse3.setEnd(end.getTime());
        sampleCourse3.setStatus("Plan to Take");
        sampleCourse3.setNotes("Sample notes");
        sampleCourse3.setMentorId(mentorList.get(2).getMentorId());
        sampleCourse3.setTermId(termList.get(2).getTermId());

        dbRepo.insert(sampleCourse1, sampleCourse2, sampleCourse3);
    }

    private void insertAssessments() {
        Assessment sampleAssessment1 = new Assessment();
        Assessment sampleAssessment2 = new Assessment();
        Assessment sampleAssessment3 = new Assessment();

        Calendar date = Calendar.getInstance();

        List<Term> termList = dbRepo.getTermList();
        if (termList == null) {
            Log.d(TAG, "insertAssessments: termList returned null");
            return;
        }

        List<Course> courseList = dbRepo.getCourseList(termList.get(0).getTermId());
        if (courseList == null) {
            Log.d(TAG, "insertAssessments: courseList returned null");
            return;
        }

        date.clear();
        date.set(2020, 7, 10);
        sampleAssessment1.setName("Quiz");
        sampleAssessment1.setCourseId(courseList.get(0).getCourseId());
        sampleAssessment1.setDate(date.getTime());
        sampleAssessment1.setType("Objective");

        date.clear();
        date.set(2020, 7, 20);
        sampleAssessment2.setName("Project");
        sampleAssessment2.setCourseId(courseList.get(0).getCourseId());
        sampleAssessment2.setDate(date.getTime());
        sampleAssessment2.setType("Performance");

        date.clear();
        date.set(2020, 7, 30);
        sampleAssessment3.setName("Final Exam");
        sampleAssessment3.setCourseId(courseList.get(0).getCourseId());
        sampleAssessment3.setDate(date.getTime());
        sampleAssessment3.setType("Objective");

        dbRepo.insert(sampleAssessment1, sampleAssessment2, sampleAssessment3);

        date.clear();
        date.set(2020, 8, 10);
        sampleAssessment1.setName("Quiz");
        sampleAssessment1.setCourseId(courseList.get(1).getCourseId());
        sampleAssessment1.setDate(date.getTime());
        sampleAssessment1.setType("Objective");

        date.clear();
        date.set(2020, 8, 20);
        sampleAssessment2.setName("Project");
        sampleAssessment2.setCourseId(courseList.get(1).getCourseId());
        sampleAssessment2.setDate(date.getTime());
        sampleAssessment2.setType("Performance");

        date.clear();
        date.set(2020, 8, 30);
        sampleAssessment3.setName("Final Exam");
        sampleAssessment3.setCourseId(courseList.get(1).getCourseId());
        sampleAssessment3.setDate(date.getTime());
        sampleAssessment3.setType("Objective");

        dbRepo.insert(sampleAssessment1, sampleAssessment2, sampleAssessment3);

        date.clear();
        date.set(2020, 9, 10);
        sampleAssessment1.setName("Quiz");
        sampleAssessment1.setCourseId(courseList.get(2).getCourseId());
        sampleAssessment1.setDate(date.getTime());
        sampleAssessment1.setType("Objective");

        date.clear();
        date.set(2020, 9, 20);
        sampleAssessment2.setName("Project");
        sampleAssessment2.setCourseId(courseList.get(2).getCourseId());
        sampleAssessment2.setDate(date.getTime());
        sampleAssessment2.setType("Performance");

        date.clear();
        date.set(2020, 9, 30);
        sampleAssessment3.setName("Final Exam");
        sampleAssessment3.setCourseId(courseList.get(2).getCourseId());
        sampleAssessment3.setDate(date.getTime());
        sampleAssessment3.setType("Objective");

        dbRepo.insert(sampleAssessment1, sampleAssessment2, sampleAssessment3);

        courseList = dbRepo.getCourseList(termList.get(1).getTermId());
        if (courseList == null) {
            Log.d(TAG, "insertAssessments: courseList returned null");
            return;
        }

        date.clear();
        date.set(2020, 10, 10);
        sampleAssessment1.setName("Quiz");
        sampleAssessment1.setCourseId(courseList.get(0).getCourseId());
        sampleAssessment1.setDate(date.getTime());
        sampleAssessment1.setType("Objective");

        date.clear();
        date.set(2020, 10, 20);
        sampleAssessment2.setName("Project");
        sampleAssessment2.setCourseId(courseList.get(0).getCourseId());
        sampleAssessment2.setDate(date.getTime());
        sampleAssessment2.setType("Performance");

        date.clear();
        date.set(2020, 10, 30);
        sampleAssessment3.setName("Final Exam");
        sampleAssessment3.setCourseId(courseList.get(0).getCourseId());
        sampleAssessment3.setDate(date.getTime());
        sampleAssessment3.setType("Objective");

        dbRepo.insert(sampleAssessment1, sampleAssessment2, sampleAssessment3);

        date.clear();
        date.set(2020, 11, 10);
        sampleAssessment1.setName("Quiz");
        sampleAssessment1.setCourseId(courseList.get(1).getCourseId());
        sampleAssessment1.setDate(date.getTime());
        sampleAssessment1.setType("Objective");

        date.clear();
        date.set(2020, 11, 20);
        sampleAssessment2.setName("Project");
        sampleAssessment2.setCourseId(courseList.get(1).getCourseId());
        sampleAssessment2.setDate(date.getTime());
        sampleAssessment2.setType("Performance");

        date.clear();
        date.set(2020, 11, 30);
        sampleAssessment3.setName("Final Exam");
        sampleAssessment3.setCourseId(courseList.get(1).getCourseId());
        sampleAssessment3.setDate(date.getTime());
        sampleAssessment3.setType("Objective");

        dbRepo.insert(sampleAssessment1, sampleAssessment2, sampleAssessment3);

        date.clear();
        date.set(2020, 12, 10);
        sampleAssessment1.setName("Quiz");
        sampleAssessment1.setCourseId(courseList.get(2).getCourseId());
        sampleAssessment1.setDate(date.getTime());
        sampleAssessment1.setType("Objective");

        date.clear();
        date.set(2020, 12, 20);
        sampleAssessment2.setName("Project");
        sampleAssessment2.setCourseId(courseList.get(2).getCourseId());
        sampleAssessment2.setDate(date.getTime());
        sampleAssessment2.setType("Performance");

        date.clear();
        date.set(2020, 12, 30);
        sampleAssessment3.setName("Final Exam");
        sampleAssessment3.setCourseId(courseList.get(2).getCourseId());
        sampleAssessment3.setDate(date.getTime());
        sampleAssessment3.setType("Objective");

        dbRepo.insert(sampleAssessment1, sampleAssessment2, sampleAssessment3);

        courseList = dbRepo.getCourseList(termList.get(2).getTermId());
        if (courseList == null) {
            Log.d(TAG, "insertAssessments: courseList returned null");
            return;
        }

        date.clear();
        date.set(2021, 1, 10);
        sampleAssessment1.setName("Quiz");
        sampleAssessment1.setCourseId(courseList.get(0).getCourseId());
        sampleAssessment1.setDate(date.getTime());
        sampleAssessment1.setType("Objective");

        date.clear();
        date.set(2021, 1, 20);
        sampleAssessment2.setName("Project");
        sampleAssessment2.setCourseId(courseList.get(0).getCourseId());
        sampleAssessment2.setDate(date.getTime());
        sampleAssessment2.setType("Performance");

        date.clear();
        date.set(2021, 1, 30);
        sampleAssessment3.setName("Final Exam");
        sampleAssessment3.setCourseId(courseList.get(0).getCourseId());
        sampleAssessment3.setDate(date.getTime());
        sampleAssessment3.setType("Objective");

        dbRepo.insert(sampleAssessment1, sampleAssessment2, sampleAssessment3);

        date.clear();
        date.set(2021, 2, 10);
        sampleAssessment1.setName("Quiz");
        sampleAssessment1.setCourseId(courseList.get(1).getCourseId());
        sampleAssessment1.setDate(date.getTime());
        sampleAssessment1.setType("Objective");

        date.clear();
        date.set(2021, 2, 20);
        sampleAssessment2.setName("Project");
        sampleAssessment2.setCourseId(courseList.get(1).getCourseId());
        sampleAssessment2.setDate(date.getTime());
        sampleAssessment2.setType("Performance");

        date.clear();
        date.set(2021, 2, 30);
        sampleAssessment3.setName("Final Exam");
        sampleAssessment3.setCourseId(courseList.get(1).getCourseId());
        sampleAssessment3.setDate(date.getTime());
        sampleAssessment3.setType("Objective");

        dbRepo.insert(sampleAssessment1, sampleAssessment2, sampleAssessment3);

        date.clear();
        date.set(2021, 3, 10);
        sampleAssessment1.setName("Quiz");
        sampleAssessment1.setCourseId(courseList.get(2).getCourseId());
        sampleAssessment1.setDate(date.getTime());
        sampleAssessment1.setType("Objective");

        date.clear();
        date.set(2021, 3, 20);
        sampleAssessment2.setName("Project");
        sampleAssessment2.setCourseId(courseList.get(2).getCourseId());
        sampleAssessment2.setDate(date.getTime());
        sampleAssessment2.setType("Performance");

        date.clear();
        date.set(2021, 3, 30);
        sampleAssessment3.setName("Final Exam");
        sampleAssessment3.setCourseId(courseList.get(2).getCourseId());
        sampleAssessment3.setDate(date.getTime());
        sampleAssessment3.setType("Objective");

        dbRepo.insert(sampleAssessment1, sampleAssessment2, sampleAssessment3);
    }

    private void insertMentors() {
        Mentor sampleMentor1 = new Mentor();
        Mentor sampleMentor2 = new Mentor();
        Mentor sampleMentor3 = new Mentor();

        sampleMentor1.setName("John Hammond");
        sampleMentor1.setPhone("122-456-7895");
        sampleMentor1.setEmail("john@ingen.com");

        sampleMentor2.setName("Ian Malcolm");
        sampleMentor2.setPhone("485-985-5415");
        sampleMentor2.setEmail("ian@dinos.com");

        sampleMentor3.setName("Ellie Sattler");
        sampleMentor3.setPhone("541-541-9541");
        sampleMentor3.setEmail("ellie@fossils.com");

        dbRepo.insert(sampleMentor1, sampleMentor2, sampleMentor3);
    }
}
