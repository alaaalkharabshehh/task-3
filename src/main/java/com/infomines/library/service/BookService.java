package com.infomines.library.service;
import com.infomines.library.exception.BookNotFoundException;
import com.infomines.library.dto.BookRequest;
import com.infomines.library.model.Book;
import com.infomines.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAll() {
        return repository.findAll();
    }

    public Book getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public List<Book> searchByAuthor(String author) {
        return repository.findByAuthorContainingIgnoreCase(author);
    }

    public List<Book> getAvailable() {
        return repository.findByAvailableTrueOrderByYearDesc();
    }

    public Book create(BookRequest request) {
        Book book = new Book();
        book.setTitle(request.title());
        book.setAuthor(request.author());
        book.setYear(request.year());
        book.setAvailable(request.available());
        return repository.save(book);
    }

    public Book update(Long id, BookRequest request) {
        Book book = getById(id);
        book.setTitle(request.title());
        book.setAuthor(request.author());
        book.setYear(request.year());
        book.setAvailable(request.available());
        return repository.save(book);
    }

    public void delete(Long id) {
        Book book = getById(id);
        repository.delete(book);
    }
}
