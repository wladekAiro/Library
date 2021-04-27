package com.wladek.lib.library.controllers;

import com.wladek.lib.library.models.dtos.BookDTO;
import com.wladek.lib.library.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/books")
public class BooksController {
    private final BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<BookDTO>> getBookList(
            @RequestParam(name = "page", defaultValue = "1", required = false) int page,
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) int size) {
        Page<BookDTO> bookDTOPage = booksService.listBooks(page, size);
        return ResponseEntity.ok(bookDTOPage);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookDTO>> searchBook(@RequestParam(name = "searchTerm") String searchTerm ){
        List<BookDTO> bookDTOPage = booksService.search(searchTerm);
        return ResponseEntity.ok(bookDTOPage);
    }

    @PostMapping("/book/create")
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        BookDTO newBook = booksService.createBook(bookDTO);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @PutMapping("/book/{bookId}")
    public ResponseEntity<BookDTO> updateBook(@PathParam("bookId") String bookId,@RequestBody BookDTO bookDTO) {
        BookDTO updatedBook = booksService.updateBook(bookDTO, bookId);
        return new ResponseEntity<>(updatedBook, HttpStatus.ACCEPTED);
    }

    @GetMapping("init")
    public ResponseEntity<String> init() {
        booksService.init();
        return ResponseEntity.ok("Books initialised");
    }
}
