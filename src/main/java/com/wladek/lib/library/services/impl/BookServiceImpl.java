package com.wladek.lib.library.services.impl;

import com.wladek.lib.library.models.dtos.BookDTO;
import com.wladek.lib.library.models.entities.BookEntity;
import com.wladek.lib.library.repositories.BookRepo;
import com.wladek.lib.library.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BooksService {
    private final BookRepo bookRepo;

    @Autowired
    public BookServiceImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public Page<BookDTO> listBooks(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bookRepo.findAll(pageable).map(BookEntity::toBookDTO);
    }

    @Override
    public List<BookDTO> search(String searchTerm) {
        return bookRepo.searchBooks("%"+ searchTerm +"%").stream().map(BookEntity::toBookDTO).collect(Collectors.toList());
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
        return bookRepo.save(bookDTO.toBookEntity()).toBookDTO();
    }

    @Override
    public BookDTO updateBook(BookDTO bookDTO, String bookId) {
        BookEntity bookEntity = bookRepo.findByClientId(bookId);

        if (bookEntity == null) {
            return null;
        }

        bookEntity.setAuthor(bookDTO.getAuthor());
        bookEntity.setAvailable(bookDTO.isAvailable());
        bookEntity.setCopies(bookDTO.getCopies());
        bookEntity.setTitle(bookDTO.getTitle());
        return bookRepo.save(bookEntity).toBookDTO();
    }

    @Override
    public void init() {
        for (int i = 0; i < 10; i++) {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setAuthor("author" + i);
            bookDTO.setAvailable(i % 2 == 0);
            bookDTO.setCopies((long) (i * 2));
            bookDTO.setTitle("Title " + i);
            createBook(bookDTO);
        }
    }
}
