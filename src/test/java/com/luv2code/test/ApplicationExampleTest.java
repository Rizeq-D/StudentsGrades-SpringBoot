package com.luv2code.test;


import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class ApplicationExampleTest {
    private static int count = 0;
    @Value("${info.app.name}")
    private String appInfo;
    @Value("${info.app.version}")
    private String appVersion;
    @Value("${info.app.description}")
    private String appDescription;
    @Value("${info.school.name}")
    private String schoolName;
    @Autowired
    CollegeStudent student;
    @Autowired
    StudentGrades studentGrades;
    @Autowired
    ApplicationContext context;

    @BeforeEach
    public void beforeEach() {
        count = count + 1;
        System.out.println("Testing: " + appInfo + " which is " + appDescription +
                " version " + appVersion + ". Execution of test method " + count);

        student.setFirstname("Omar");
        student.setLastname("Ahmed");
        student.setEmailAddress("test@example.com");
        studentGrades.setMathGradeResults(new ArrayList<>(Arrays.asList(100.0, 80.0, 70.0, 90.50)));
        student.setStudentGrades(studentGrades);
    }
    @Test
    @DisplayName("Add grades results for student grades")
    void addGradesResultsForStudentGrades() {
        assertEquals(340.50, studentGrades.addGradeResultsForSingleClass(
                student.getStudentGrades().getMathGradeResults()));
    }
    @Test
    @DisplayName("Add grades results for student grades not equal")
    void addGradesResultsForStudentGradesAssertNotEquals() {
        assertNotEquals(0, studentGrades.addGradeResultsForSingleClass(
                student.getStudentGrades().getMathGradeResults()));
    }
    @Test
    @DisplayName("Is grade greater")
    void IsGradeGreaterStudentGrades() {
        assertTrue(studentGrades.isGradeGreater(100.0, 80.0),
                "failure - should be true");
    }
    @Test
    @DisplayName("Is grade greater is false")
    void IsGradeGreaterStudentGradesAssertFalse() {
        assertFalse(studentGrades.isGradeGreater(70.0, 90.50),
                "failure - should be false");
    }
    @Test
    @DisplayName("Check null for students grades")
    void CheckNullForStudentGrades() {
        assertNotNull(studentGrades.checkNull(student.getStudentGrades().getMathGradeResults()),
                "Object should not be null");
    }
    @Test
    @DisplayName("Create student without grade init")
    void CreateStudentWithoutGradeInit() {
        CollegeStudent student2 = context.getBean("collegeStudent", CollegeStudent.class);
        student2.setFirstname("Mohammed");
        student2.setLastname("Hamoudeh");
        student2.setEmailAddress("mo@hs.com");
        assertNotNull(student2.getFirstname());
        assertNotNull(student2.getLastname());
        assertNotNull(student2.getEmailAddress());
        assertNull(studentGrades.checkNull(student2.getStudentGrades()));
    }

}




























