package com.wladek.lib.library.services;

import com.wladek.lib.library.models.dtos.BookDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BooksService {
    Page<BookDTO> listBooks(int page, int size);
    List<BookDTO> search(String searchTerm);
    BookDTO createBook(BookDTO bookDTO);
    BookDTO updateBook(BookDTO bookDTO, String bookId);
    void init();
}
