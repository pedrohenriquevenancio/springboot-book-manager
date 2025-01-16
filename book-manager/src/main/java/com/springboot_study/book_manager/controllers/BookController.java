package com.springboot_study.book_manager.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot_study.book_manager.models.BookModel;
import com.springboot_study.book_manager.services.BookService;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping
    public ResponseEntity<List<BookModel>> findAll() {
        List<BookModel> books = service.findAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookModel> findById(@PathVariable UUID id) {
        BookModel book = service.findById(id);
        return ResponseEntity.ok(book);
    }

    @PostMapping
    public ResponseEntity<BookModel> store(@RequestBody BookModel body) {
        BookModel book = service.store(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookModel> update(@PathVariable UUID id, @RequestBody BookModel body) {
        BookModel book = service.update(id, body);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}/set-category/{categoryId}")
    public ResponseEntity<BookModel> setCategory(@PathVariable UUID id, @PathVariable UUID categoryId) {
        BookModel book = service.setCategory(id, categoryId);
        return ResponseEntity.ok(book);
    }

    @PatchMapping("/{id}/set-authors")
    public ResponseEntity<BookModel> setAuthors(@PathVariable UUID id, @RequestBody List<UUID> authors) {
        for (UUID authorId : authors) {
            service.setAuthor(id, authorId);
        }
        BookModel book = service.findById(id);
        return ResponseEntity.ok(book);
    }

    @PatchMapping("/{id}/set-publisher/{publisherId}")
    public ResponseEntity<BookModel> setPublisher(@PathVariable UUID id, @PathVariable UUID publisherId) {
        BookModel book = service.setPublisher(id, publisherId);
        return ResponseEntity.ok(book);
    }

    @PatchMapping("/{id}/set-review")
    public ResponseEntity<BookModel> setReview(@PathVariable UUID id, @RequestBody String review) {
        BookModel book = service.setReview(id, review);
        return ResponseEntity.ok(book);
    }

}
