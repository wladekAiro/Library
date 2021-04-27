package com.wladek.lib.library.models.entities;


import com.wladek.lib.library.models.dtos.StudentDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "students")
public class StudentEntity extends AbstractEntity {
    @Column(unique = true)
    private String username;
    private String fullName;
    @ManyToMany
    private List<BookEntity> books;

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

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }

    public StudentDTO studentDTO() {
        return new StudentDTO(getClientId(), this.username, this.fullName);
    }
}
