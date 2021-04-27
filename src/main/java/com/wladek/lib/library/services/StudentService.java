package com.wladek.lib.library.services;

import com.wladek.lib.library.models.dtos.BookAction;
import com.wladek.lib.library.models.dtos.RentResponseDTO;
import com.wladek.lib.library.models.dtos.StudentDTO;

public interface StudentService {
    StudentDTO createStudent(StudentDTO studentDTO);
    StudentDTO rentedBooks(String studentId);
    RentResponseDTO bookAction(String studentId, BookAction bookAction);
    void init();
}
