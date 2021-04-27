package com.wladek.lib.library.models.entities;

import com.wladek.lib.library.models.dtos.BookDTO;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "books")
public class BookEntity extends AbstractEntity {
    private String title;
    private String author;
    private Long copies;
    private boolean available = true;
    @ManyToMany(mappedBy = "books")
    private List<StudentEntity> students;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<StudentEntity> getStudents() {
        return students;
    }

    public void setStudents(List<StudentEntity> students) {
        this.students = students;
    }

    public Long getCopies() {
        return copies;
    }

    public void setCopies(Long copies) {
        this.copies = copies;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public BookDTO toBookDTO() {
        return new BookDTO(getClientId(), this.title, this.author, this.copies, this.available);
    }
}
