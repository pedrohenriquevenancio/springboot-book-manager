package com.springboot_study.book_manager.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot_study.book_manager.models.BookModel;
import com.springboot_study.book_manager.models.CategoryModel;
import com.springboot_study.book_manager.services.BookService;
import com.springboot_study.book_manager.services.CategoryService;

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

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<BookModel>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookModel> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<BookModel> store(@RequestBody BookModel body) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.store(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookModel> update(@PathVariable UUID id, @RequestBody BookModel body) {
        return ResponseEntity.ok(service.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}/set-category/{categoryId}")
    public ResponseEntity<BookModel> setCategory(@PathVariable UUID id, @PathVariable UUID categoryId) {
        CategoryModel category = categoryService.findById(categoryId);
        return ResponseEntity.ok(service.setCategory(id, category));
    }

    @PatchMapping("/{id}/unset-category")
    public ResponseEntity<BookModel> unsetCategory(@PathVariable UUID id) {
        return ResponseEntity.ok(service.unsetCategory(id));
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
