package com.wladek.lib.library.models.dtos;

import com.wladek.lib.library.models.entities.StudentEntity;

import java.io.Serializable;
import java.util.List;

public class StudentDTO implements Serializable {
    private String studentId;
    private String username;
    private String fullName;

    private List<BookDTO> booksRented;

    public StudentDTO(){}

    public StudentDTO(String studentId, String username, String fullName) {
        this.studentId = studentId;
        this.username = username;
        this.fullName = fullName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public List<BookDTO> getBooksRented() {
        return booksRented;
    }

    public void setBooksRented(List<BookDTO> booksRented) {
        this.booksRented = booksRented;
    }

    public StudentEntity toStudentEntity() {
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setFullName(this.fullName);
        studentEntity.setUsername(this.username);
        return studentEntity;
    }
}
