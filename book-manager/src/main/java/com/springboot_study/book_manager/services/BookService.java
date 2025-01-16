package com.springboot_study.book_manager.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot_study.book_manager.models.BookModel;
import com.springboot_study.book_manager.repositories.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public List<BookModel> findAll() {
        return repository.findAll();
    }

    public BookModel findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));
    }

    public BookModel store(BookModel book) {
        return repository.save(book);
    }

    public BookModel update(UUID id, BookModel book) {
        return repository.findById(id).map(oldBook -> {
            oldBook.setTitle(book.getTitle());
            oldBook.setCategory(book.getCategory());
            oldBook.setAuthors(book.getAuthors());
            return repository.save(oldBook);
        }).orElseThrow(() -> new IllegalArgumentException("Book not found"));
    }

    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Book not found");
        }
        repository.deleteById(id);
    }

    public BookModel setCategory(UUID id, UUID categoryId) {
        BookModel book = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        return book;
    }

    public BookModel setAuthor(UUID id, UUID authorId) {
        BookModel book = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        return book;
    }

    public BookModel setPublisher(UUID id, UUID publisherId) {
        BookModel book = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        return book;
    }

    public BookModel setReview(UUID id, String review) {
        BookModel book = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        return book;
    }

}
