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
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<CategoryModel>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryModel> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }
    
    @PostMapping
    public ResponseEntity<CategoryModel> store(@RequestBody CategoryModel category) {
        return ResponseEntity.ok(service.store(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryModel> update(@PathVariable UUID id, @RequestBody CategoryModel category) {
        return ResponseEntity.ok(service.update(id, category));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/{id}/set-book/{book_id}")
    public ResponseEntity<CategoryModel> setBook(@PathVariable UUID id, @PathVariable UUID book_id) {
        BookModel book = bookService.findById(book_id);
        return ResponseEntity.ok(service.setBook(id, book));
    }

    @PatchMapping("/{id}/unset-book/{book_id}")
    public ResponseEntity<CategoryModel> unsetBook(@PathVariable UUID id, @PathVariable UUID book_id) {
        BookModel book = bookService.findById(book_id);
        return ResponseEntity.ok(service.unsetBook(id, book));
    }

}
