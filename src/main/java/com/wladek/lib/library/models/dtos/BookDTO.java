package com.wladek.lib.library.models.dtos;

import com.wladek.lib.library.models.entities.BookEntity;

public class BookDTO {
    private String bookId;
    private String title;
    private String author;
    private Long copies;
    private boolean available;

    public BookDTO(){}

    public BookDTO(String bookId, String title, String author, Long copies, boolean available) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.copies = copies;
        this.available = available;
    }

    public String  getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

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

    public BookEntity toBookEntity() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setAuthor(this.author);
        bookEntity.setAvailable(this.available);
        bookEntity.setCopies(this.copies);
        bookEntity.setTitle(this.title);
        return bookEntity;
    }
}
