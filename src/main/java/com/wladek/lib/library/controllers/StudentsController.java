package com.wladek.lib.library.controllers;

import com.wladek.lib.library.models.dtos.BookAction;
import com.wladek.lib.library.models.dtos.RentResponseDTO;
import com.wladek.lib.library.models.dtos.StudentDTO;
import com.wladek.lib.library.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/students")
public class StudentsController {

    private final StudentService studentService;

    @Autowired
    public StudentsController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{studentId/rented")
    public ResponseEntity<?> getRentedBooks(@PathVariable("studentId") String studentId) {
        StudentDTO studentProfile = studentService.rentedBooks(studentId);
        return ResponseEntity.ok(studentProfile);
    }

    @PutMapping("/student/{studentId}/book")
    public ResponseEntity<?> bookAction(
            @PathVariable("studentId") String studentId,
            @RequestBody BookAction bookAction) {
        RentResponseDTO rentResponseDTO = studentService.bookAction(studentId, bookAction);
        return ResponseEntity.ok(rentResponseDTO);
    }
}
