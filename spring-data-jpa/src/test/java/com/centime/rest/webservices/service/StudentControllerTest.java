package com.centime.rest.webservices.service;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.centime.rest.webservices.dto.NestedObjectResponse;
import com.centime.rest.webservices.dto.Student;
import com.centime.rest.webservices.dto.StudentRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.web.server.LocalServerPort;


public class StudentControllerTest {

    @Mock
    StudentRepository studentRepository;

    @LocalServerPort
    private int port;

    @InjectMocks
    StudentController studentController;

   /* @Mock
    StudentRepository studentRepository;*/

    @Test
    public void getStudentTest(){
        MockitoAnnotations.initMocks(this);
        doReturn(Optional.of(new Student())).when(studentRepository).findById(any());
        Student student  = studentController.getStudent(1);
        assertNotNull(student);
        verify(studentRepository,times(1)).findById(any());

    }

    @Test
    public void getStudentTestInvalid(){
        MockitoAnnotations.initMocks(this);
        doThrow(RuntimeException.class).when(studentRepository).findById(any());
        RuntimeException exception = assertThrows(RuntimeException.class,
            () -> {
                Student student  = studentController.getStudent(1);
            });
        Assertions.assertAll("Invalid user id",
            () -> assertTrue(exception instanceof RuntimeException)
        );
        verify(studentRepository,times(1)).findById(any());
    }

    @Test
    public void getStudentsTest(){
        List<Student> expectedStudentList = new ArrayList<>();
        Student student = new Student();
        student.setId(1);
        student.setName("Mage");
        student.setColor("grey");
        student.setParentid(0);
        expectedStudentList.add(student);

        MockitoAnnotations.initMocks(this);
        doReturn(expectedStudentList).when(studentRepository).findAll();
        NestedObjectResponse nestedObjectResponse = studentController.getAllStudents();
        assertNotNull(nestedObjectResponse);

        assertEquals(expectedStudentList.size(),nestedObjectResponse.getMasterNames().size());
        verify(studentRepository,times(1)).findAll();
    }

    @Test
    public void createUserTest(){
        MockitoAnnotations.initMocks(this);
        doReturn(new ArrayList<Student>()).when(studentRepository).saveAll(any());
        studentController.saveAllStudents(new ArrayList<Student>());
        verify(studentRepository,times(1)).saveAll(any());

    }

}
