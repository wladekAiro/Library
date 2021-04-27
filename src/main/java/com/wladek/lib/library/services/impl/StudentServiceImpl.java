package com.wladek.lib.library.services.impl;

import com.wladek.lib.library.StudentAction;
import com.wladek.lib.library.models.dtos.BookAction;
import com.wladek.lib.library.models.dtos.BookDTO;
import com.wladek.lib.library.models.dtos.RentResponseDTO;
import com.wladek.lib.library.models.dtos.StudentDTO;
import com.wladek.lib.library.models.entities.BookEntity;
import com.wladek.lib.library.models.entities.StudentEntity;
import com.wladek.lib.library.repositories.BookRepo;
import com.wladek.lib.library.repositories.StudentRepo;
import com.wladek.lib.library.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepo studentRepo;
    private final BookRepo bookRepo;

    @Autowired
    public StudentServiceImpl(StudentRepo studentRepo, BookRepo bookRepo) {
        this.studentRepo = studentRepo;
        this.bookRepo = bookRepo;
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO) {
        return studentRepo.save(studentDTO.toStudentEntity()).studentDTO();
    }

    @Override
    public StudentDTO rentedBooks(String studentId) {
        StudentEntity studentEntity = studentRepo.findByClientId(studentId);
        List<BookDTO> studentBooks = studentEntity.getBooks().stream().map(BookEntity::toBookDTO).collect(Collectors.toList());
        StudentDTO studentDTO = studentEntity.studentDTO();
        studentDTO.setBooksRented(studentBooks);
        return studentDTO;
    }

    @Override
    public RentResponseDTO bookAction(String studentId, BookAction bookAction) {

        StudentEntity studentEntity = studentRepo.findByClientId(studentId);
        BookEntity bookEntity = bookRepo.findByClientId(bookAction.getBookID());

        if (studentEntity != null && bookEntity != null) {
            if (bookAction.getStudentAction().equals(StudentAction.RENT)) {
                return rentBook(studentEntity, bookEntity);
            } else {
                return returnBook(studentEntity, bookEntity);
            }
        }else {
            return new RentResponseDTO("Failed", "Make sure you have selected an existing book");
        }
    }

    private RentResponseDTO rentBook(StudentEntity studentEntity, BookEntity bookEntity) {
        if (studentEntity.getBooks().contains(bookEntity)) {
            return new RentResponseDTO("Failed","You haven't returned your rented copy of this book");
        }

        if (bookEntity.getCopies() < 1) {
            return new RentResponseDTO("Failed","No copies are available for rental");
        }

        bookEntity.setCopies(bookEntity.getCopies() - 1);
        bookRepo.save(bookEntity);

        studentEntity.getBooks().add(bookEntity);
        studentRepo.save(studentEntity);

        return new RentResponseDTO("Success", "Rental approved");
    }

    private RentResponseDTO returnBook(StudentEntity studentEntity, BookEntity bookEntity) {
        if (studentEntity.getBooks().contains(bookEntity)) {
            studentEntity.getBooks().remove(bookEntity);
            studentRepo.save(studentEntity);
            
            bookEntity.setCopies(bookEntity.getCopies() + 1);
            bookRepo.save(bookEntity);

            return new RentResponseDTO("Success","Book returned");
        }
        return new RentResponseDTO("Failed", "You haven't rented a copy of this book yet");
    }
}
