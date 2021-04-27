package com.wladek.lib.library.models.dtos;

import com.wladek.lib.library.StudentAction;

import java.io.Serializable;

public class BookAction implements Serializable {
    private String bookID;
    private StudentAction studentAction;

    public BookAction(){};

    public BookAction(String bookID, StudentAction studentAction) {
        this.bookID = bookID;
        this.studentAction = studentAction;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public StudentAction getStudentAction() {
        return studentAction;
    }

    public void setStudentAction(StudentAction studentAction) {
        this.studentAction = studentAction;
    }
}
