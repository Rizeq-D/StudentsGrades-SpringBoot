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

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

}




























