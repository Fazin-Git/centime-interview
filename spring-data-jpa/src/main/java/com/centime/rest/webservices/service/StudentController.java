package com.centime.rest.webservices.service;

import com.centime.rest.webservices.dto.MasterNames;
import com.centime.rest.webservices.dto.NestedObjectResponse;
import com.centime.rest.webservices.dto.Student;
import com.centime.rest.webservices.dto.StudentName;
import com.centime.rest.webservices.dto.StudentRepository;
import com.centime.rest.webservices.config.ApiException;
import com.centime.rest.webservices.config.LogMethodCall;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    private final String HTTP_RESPONSE_MESSAGE_404 = "Resource not found.";

    @Autowired
    StudentRepository studentRepository;

    @GetMapping(path = "/v1/student/{id}")
    @LogMethodCall
    public Student getStudent(@PathVariable int id) {
        Optional<Student> user = studentRepository.findById(id);
        if (!user.isPresent()) {
            throw new ApiException(HttpStatus.NOT_FOUND.value(), HTTP_RESPONSE_MESSAGE_404);
        }
        return user.get();
    }

    @GetMapping(path = "/v1/students")
    @LogMethodCall
    public NestedObjectResponse getAllStudents() {
        NestedObjectResponse nestedObjectResponse;
        List<Student> studentList = studentRepository.findAll();
        if (studentList.isEmpty()){
            throw new ApiException(HttpStatus.NOT_FOUND.value(), HTTP_RESPONSE_MESSAGE_404);
        }else {
            nestedObjectResponse = mapStudents(studentList);
        }
        return nestedObjectResponse;
    }

    @PostMapping(path = "/v1/students")
    @ResponseStatus(HttpStatus.CREATED)
    @LogMethodCall
    public void saveAllStudents(@RequestBody List<Student> student) {
        studentRepository.saveAll(student);
    }

    private NestedObjectResponse mapStudents(List<Student> studentList) {

        NestedObjectResponse response = new NestedObjectResponse();
        response.setMasterNames(studentList
            .stream()
            .filter(s -> s
                .getParentid()
                .equals(0))
            .map(
                parent -> {
                    MasterNames masterName = new MasterNames();
                    masterName.setName(parent.getName());
                    masterName.setSubClasses(studentList
                        .stream()
                        .filter(s -> s
                            .getParentid()
                            .equals(parent.getId()))
                        .map(currentStudent -> new StudentName(currentStudent.getName()))
                        .collect(Collectors.toList())
                    );
                    return masterName;
                })
            .collect(Collectors.toList()));
        return response;
    }
}
