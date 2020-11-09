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
        db = WguDatabase.getInstance(context);
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
        if (termList == null) {
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
        sampleCourse1.setNotes("Introduction to IT examines information technology as a discipline and the " +
                "various roles and functions of the IT department as business support. Students are presented " +
                "with various IT disciplines including systems and services, network and security, scripting " +
                "and programming, data management, and business of IT, with a survey of technologies in every " +
                "area and how they relate to each other and to the business.");
        sampleCourse1.setMentorId(1);
        sampleCourse1.setTermId(termList.get(0).getTermId());

        start.clear();
        end.clear();
        start.set(2020, 8, 1);
        end.set(2020, 8, 31);
        sampleCourse2.setName("C173 - Scripting and Programming Foundations");
        sampleCourse2.setStart(start.getTime());
        sampleCourse2.setEnd(end.getTime());
        sampleCourse2.setStatus("Completed");
        sampleCourse2.setNotes("Scripting and Programming Foundations provides an introduction to programming, " +
                "covering basic elements such as variables, data types, flow control, and design concepts. The " +
                "course is language-agnostic in nature, ending in a survey of languages and introduces the distinction " +
                "between interpreted and compiled languages. There are no prerequisites for this course.");
        sampleCourse2.setMentorId(2);
        sampleCourse2.setTermId(termList.get(0).getTermId());

        start.clear();
        end.clear();
        start.set(2020, 9, 1);
        end.set(2020, 9, 30);
        sampleCourse3.setName("C779 - Web Development Foundations");
        sampleCourse3.setStart(start.getTime());
        sampleCourse3.setEnd(end.getTime());
        sampleCourse3.setStatus("Dropped");
        sampleCourse3.setNotes("This course introduces students to web design and development by presenting them with HTML5 " +
                "and Cascading Style Sheets (CSS), the foundational languages of the web, by reviewing media " +
                "strategies and by using tools and techniques commonly employed in web development.");
        sampleCourse3.setMentorId(3);
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
        sampleCourse1.setNotes("Software I builds object-oriented programming expertise and introduces powerful " +
                "new tools for Java application development. You will learn about and put into action class design, " +
                "exception handling, and other object-oriented principles and constructs to develop software that meets " +
                "business requirements. This course requires foundational knowledge of object-oriented programming and the Java language.");
        sampleCourse1.setMentorId(1);
        sampleCourse1.setTermId(termList.get(1).getTermId());

        start.clear();
        end.clear();
        start.set(2020, 11, 1);
        end.set(2020, 11, 30);
        sampleCourse2.setName("C195 - Software II");
        sampleCourse2.setStart(start.getTime());
        sampleCourse2.setEnd(end.getTime());
        sampleCourse2.setStatus("In Progress");
        sampleCourse2.setNotes("Software II - Advanced Java Concepts refines object-oriented programming expertise " +
                "and builds database and file server application development skills. You will learn about and put into " +
                "action lambda expressions, collections, input/output, advanced error handling, and the newest features " +
                "of Java 8 to develop software that meets business requirements. This course requires intermediate " +
                "expertise in object-oriented programming and the Java language.");
        sampleCourse2.setMentorId(2);
        sampleCourse2.setTermId(termList.get(1).getTermId());

        start.clear();
        end.clear();
        start.set(2020, 12, 1);
        end.set(2020, 12, 31);
        sampleCourse3.setName("C196 - Mobile Application Development");
        sampleCourse3.setStart(start.getTime());
        sampleCourse3.setEnd(end.getTime());
        sampleCourse3.setStatus("Plan to Take");
        sampleCourse3.setNotes("This course introduces students to programming for mobile devices using a " +
                "software development kit (SDK). Students with previous knowledge of programming will learn " +
                "how to install and utilize a SDK, build a basic mobile application, build a mobile application " +
                "using a graphical user interface (GUI), adapt applications to different mobile devices, save " +
                "data, execute and debug mobile applications using emulators, and deploy a mobile application.");
        sampleCourse3.setMentorId(3);
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
        sampleCourse1.setNotes("This course introduces the concepts of software engineering to students who " +
                "have completed the core courses in programming and project management. The principles build " +
                "on previously acquired concepts, switching the emphasis from programming simple routines to " +
                "engineering robust and scalable software solutions. This course does not cover programming, but " +
                "provides an overview of software engineering processes and their challenging nature, focusing on " +
                "the need for a disciplined approach to software engineering. A generic process framework provides " +
                "the groundwork for formal process models. Prescriptive process models such as the waterfall model " +
                "and agile development are included. An introduction to the elements and phases of software engineering " +
                "is included, which explores requirements for engineering, design concepts, and software quality.");
        sampleCourse1.setMentorId(1);
        sampleCourse1.setTermId(termList.get(2).getTermId());

        start.clear();
        end.clear();
        start.set(2021, 2, 1);
        end.set(2021, 2, 28);
        sampleCourse2.setName("C856 - User Experience Design");
        sampleCourse2.setStart(start.getTime());
        sampleCourse2.setEnd(end.getTime());
        sampleCourse2.setStatus("Plan to Take");
        sampleCourse2.setNotes("User Experience Design explores multiple tools and techniques used in user " +
                "experience design. Students are presented with an in-depth view of activities involved in " +
                "the design of user experience and have the opportunity to create several deliverables including " +
                "persona profiles, information architectures, and prototypes of different levels of fidelity. In " +
                "addition, the course also covers usability testing and the evaluation of quantitative and " +
                "qualitative data derived from these and other experiments.");
        sampleCourse2.setMentorId(2);
        sampleCourse2.setTermId(termList.get(2).getTermId());

        start.clear();
        end.clear();
        start.set(2021, 3, 1);
        end.set(2021, 3, 31);
        sampleCourse3.setName("C857 - Software Quality Assurance");
        sampleCourse3.setStart(start.getTime());
        sampleCourse3.setEnd(end.getTime());
        sampleCourse3.setStatus("Plan to Take");
        sampleCourse3.setNotes("Software Quality Assurance applies a QA focus to every phase of the software " +
                "development life cycle. This course investigates best practices for quality analysis, quality " +
                "planning, and testing strategies as they pertain to the everyday practice of software development. " +
                "Students will come to understand how their work fits into the bigger picture: how QA, testing, and " +
                "code-writing practices interact within specific process models; the potential impact of new code " +
                "on existing code or on other applications; the importance of usability, and the influence users have " +
                "on the ultimate success of an application. Students will explore test plans, test cases, unit tests, " +
                "integration tests, regression tests, usability tests, and test and review tools.");
        sampleCourse3.setMentorId(3);
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
            return;
        }

        List<Course> courseList = dbRepo.getCourseList(termList.get(0).getTermId());
        if (courseList == null) {
            return;
        }

        date.clear();
        date.set(2020, 7, 10);
        sampleAssessment1.setName("Quiz");
        sampleAssessment1.setCourseId(courseList.get(0).getCourseId());
        sampleAssessment1.setDate(date.getTime());
        sampleAssessment1.setType("Objective Assessment");

        date.clear();
        date.set(2020, 7, 20);
        sampleAssessment2.setName("Project");
        sampleAssessment2.setCourseId(courseList.get(0).getCourseId());
        sampleAssessment2.setDate(date.getTime());
        sampleAssessment2.setType("Performance Assessment");

        date.clear();
        date.set(2020, 7, 30);
        sampleAssessment3.setName("Final Exam");
        sampleAssessment3.setCourseId(courseList.get(0).getCourseId());
        sampleAssessment3.setDate(date.getTime());
        sampleAssessment3.setType("Objective Assessment");

        dbRepo.insert(sampleAssessment1, sampleAssessment2, sampleAssessment3);

        date.clear();
        date.set(2020, 8, 10);
        sampleAssessment1.setName("Quiz");
        sampleAssessment1.setCourseId(courseList.get(1).getCourseId());
        sampleAssessment1.setDate(date.getTime());
        sampleAssessment1.setType("Objective Assessment");

        date.clear();
        date.set(2020, 8, 20);
        sampleAssessment2.setName("Project");
        sampleAssessment2.setCourseId(courseList.get(1).getCourseId());
        sampleAssessment2.setDate(date.getTime());
        sampleAssessment2.setType("Performance Assessment");

        date.clear();
        date.set(2020, 8, 30);
        sampleAssessment3.setName("Final Exam");
        sampleAssessment3.setCourseId(courseList.get(1).getCourseId());
        sampleAssessment3.setDate(date.getTime());
        sampleAssessment3.setType("Objective Assessment");

        dbRepo.insert(sampleAssessment1, sampleAssessment2, sampleAssessment3);

        date.clear();
        date.set(2020, 9, 10);
        sampleAssessment1.setName("Quiz");
        sampleAssessment1.setCourseId(courseList.get(2).getCourseId());
        sampleAssessment1.setDate(date.getTime());
        sampleAssessment1.setType("Objective Assessment");

        date.clear();
        date.set(2020, 9, 20);
        sampleAssessment2.setName("Project");
        sampleAssessment2.setCourseId(courseList.get(2).getCourseId());
        sampleAssessment2.setDate(date.getTime());
        sampleAssessment2.setType("Performance Assessment");

        date.clear();
        date.set(2020, 9, 30);
        sampleAssessment3.setName("Final Exam");
        sampleAssessment3.setCourseId(courseList.get(2).getCourseId());
        sampleAssessment3.setDate(date.getTime());
        sampleAssessment3.setType("Objective Assessment");

        dbRepo.insert(sampleAssessment1, sampleAssessment2, sampleAssessment3);

        courseList = dbRepo.getCourseList(termList.get(1).getTermId());
        if (courseList == null) {
            return;
        }

        date.clear();
        date.set(2020, 10, 10);
        sampleAssessment1.setName("Quiz");
        sampleAssessment1.setCourseId(courseList.get(0).getCourseId());
        sampleAssessment1.setDate(date.getTime());
        sampleAssessment1.setType("Objective Assessment");

        date.clear();
        date.set(2020, 10, 20);
        sampleAssessment2.setName("Project");
        sampleAssessment2.setCourseId(courseList.get(0).getCourseId());
        sampleAssessment2.setDate(date.getTime());
        sampleAssessment2.setType("Performance Assessment");

        date.clear();
        date.set(2020, 10, 30);
        sampleAssessment3.setName("Final Exam");
        sampleAssessment3.setCourseId(courseList.get(0).getCourseId());
        sampleAssessment3.setDate(date.getTime());
        sampleAssessment3.setType("Objective Assessment");

        dbRepo.insert(sampleAssessment1, sampleAssessment2, sampleAssessment3);

        date.clear();
        date.set(2020, 11, 10);
        sampleAssessment1.setName("Quiz");
        sampleAssessment1.setCourseId(courseList.get(1).getCourseId());
        sampleAssessment1.setDate(date.getTime());
        sampleAssessment1.setType("Objective Assessment");

        date.clear();
        date.set(2020, 11, 20);
        sampleAssessment2.setName("Project");
        sampleAssessment2.setCourseId(courseList.get(1).getCourseId());
        sampleAssessment2.setDate(date.getTime());
        sampleAssessment2.setType("Performance Assessment");

        date.clear();
        date.set(2020, 11, 30);
        sampleAssessment3.setName("Final Exam");
        sampleAssessment3.setCourseId(courseList.get(1).getCourseId());
        sampleAssessment3.setDate(date.getTime());
        sampleAssessment3.setType("Objective Assessment");

        dbRepo.insert(sampleAssessment1, sampleAssessment2, sampleAssessment3);

        date.clear();
        date.set(2020, 12, 10);
        sampleAssessment1.setName("Quiz");
        sampleAssessment1.setCourseId(courseList.get(2).getCourseId());
        sampleAssessment1.setDate(date.getTime());
        sampleAssessment1.setType("Objective Assessment");

        date.clear();
        date.set(2020, 12, 20);
        sampleAssessment2.setName("Project");
        sampleAssessment2.setCourseId(courseList.get(2).getCourseId());
        sampleAssessment2.setDate(date.getTime());
        sampleAssessment2.setType("Performance Assessment");

        date.clear();
        date.set(2020, 12, 30);
        sampleAssessment3.setName("Final Exam");
        sampleAssessment3.setCourseId(courseList.get(2).getCourseId());
        sampleAssessment3.setDate(date.getTime());
        sampleAssessment3.setType("Objective Assessment");

        dbRepo.insert(sampleAssessment1, sampleAssessment2, sampleAssessment3);

        courseList = dbRepo.getCourseList(termList.get(2).getTermId());
        if (courseList == null) {
            return;
        }

        date.clear();
        date.set(2021, 1, 10);
        sampleAssessment1.setName("Quiz");
        sampleAssessment1.setCourseId(courseList.get(0).getCourseId());
        sampleAssessment1.setDate(date.getTime());
        sampleAssessment1.setType("Objective Assessment");

        date.clear();
        date.set(2021, 1, 20);
        sampleAssessment2.setName("Project");
        sampleAssessment2.setCourseId(courseList.get(0).getCourseId());
        sampleAssessment2.setDate(date.getTime());
        sampleAssessment2.setType("Performance Assessment");

        date.clear();
        date.set(2021, 1, 30);
        sampleAssessment3.setName("Final Exam");
        sampleAssessment3.setCourseId(courseList.get(0).getCourseId());
        sampleAssessment3.setDate(date.getTime());
        sampleAssessment3.setType("Objective Assessment");

        dbRepo.insert(sampleAssessment1, sampleAssessment2, sampleAssessment3);

        date.clear();
        date.set(2021, 2, 10);
        sampleAssessment1.setName("Quiz");
        sampleAssessment1.setCourseId(courseList.get(1).getCourseId());
        sampleAssessment1.setDate(date.getTime());
        sampleAssessment1.setType("Objective Assessment");

        date.clear();
        date.set(2021, 2, 20);
        sampleAssessment2.setName("Project");
        sampleAssessment2.setCourseId(courseList.get(1).getCourseId());
        sampleAssessment2.setDate(date.getTime());
        sampleAssessment2.setType("Performance Assessment");

        date.clear();
        date.set(2021, 2, 30);
        sampleAssessment3.setName("Final Exam");
        sampleAssessment3.setCourseId(courseList.get(1).getCourseId());
        sampleAssessment3.setDate(date.getTime());
        sampleAssessment3.setType("Objective Assessment");

        dbRepo.insert(sampleAssessment1, sampleAssessment2, sampleAssessment3);

        date.clear();
        date.set(2021, 3, 10);
        sampleAssessment1.setName("Quiz");
        sampleAssessment1.setCourseId(courseList.get(2).getCourseId());
        sampleAssessment1.setDate(date.getTime());
        sampleAssessment1.setType("Objective Assessment");

        date.clear();
        date.set(2021, 3, 20);
        sampleAssessment2.setName("Project");
        sampleAssessment2.setCourseId(courseList.get(2).getCourseId());
        sampleAssessment2.setDate(date.getTime());
        sampleAssessment2.setType("Performance Assessment");

        date.clear();
        date.set(2021, 3, 30);
        sampleAssessment3.setName("Final Exam");
        sampleAssessment3.setCourseId(courseList.get(2).getCourseId());
        sampleAssessment3.setDate(date.getTime());
        sampleAssessment3.setType("Objective Assessment");

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
