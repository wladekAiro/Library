package com.wladek.lib.library.controllers;

import com.wladek.lib.library.models.dtos.BookAction;
import com.wladek.lib.library.models.dtos.RentResponseDTO;
import com.wladek.lib.library.models.dtos.StudentDTO;
import com.wladek.lib.library.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/students")
public class StudentsController {

    private final StudentService studentService;

    @Autowired
    public StudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<StudentDTO>> list() {
        return ResponseEntity.ok(studentService.list());
    }

    @PostMapping("/student/create")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        StudentDTO created = studentService.createStudent(studentDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{studentId}/rented")
    public ResponseEntity<StudentDTO> getRentedBooks(@PathVariable("studentId") String studentId) {
        StudentDTO studentProfile = studentService.rentedBooks(studentId);
        return ResponseEntity.ok(studentProfile);
    }

    @PutMapping("/student/{studentId}/book")
    public ResponseEntity<RentResponseDTO> bookAction(
            @PathVariable("studentId") String studentId,
            @RequestBody BookAction bookAction) {
        RentResponseDTO rentResponseDTO = studentService.bookAction(studentId, bookAction);
        return ResponseEntity.ok(rentResponseDTO);
    }

    @GetMapping("/init")
    public ResponseEntity<String> init() {
        studentService.init();
        return ResponseEntity.ok("Students initialised");
    }
}
