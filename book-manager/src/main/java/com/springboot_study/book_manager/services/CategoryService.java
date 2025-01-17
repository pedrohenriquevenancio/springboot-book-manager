package com.springboot_study.book_manager.services;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot_study.book_manager.models.BookModel;
import com.springboot_study.book_manager.models.CategoryModel;
import com.springboot_study.book_manager.repositories.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<CategoryModel> findAll() {
        return repository.findAll();
    }

    public CategoryModel findById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }

    public CategoryModel store(CategoryModel category) {
        return repository.save(category);
    }

     public CategoryModel update(UUID id, CategoryModel category) {
        return repository.findById(id).map(oldCategory -> {
            oldCategory.setName(category.getName());
            return repository.save(oldCategory);
        }).orElseThrow(() -> new IllegalArgumentException("Category not found"));
    }

    public void delete(UUID id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Category not found");
        }
        repository.deleteById(id);
    }

    public CategoryModel setBook(UUID id, BookModel book) {
        CategoryModel category = repository.findById(id)
        .map(oldCategory -> {
            Set<BookModel> books = oldCategory.getBooks();
            books.add(book);
            oldCategory.setBooks(books);
            return repository.save(oldCategory);
        })
        .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        return category;
    }

    public CategoryModel unsetBook(UUID id, BookModel book) {
        CategoryModel category = repository.findById(id)
        .map(oldCategory -> {
            Set<BookModel> books = oldCategory.getBooks();
            books.remove(book);
            oldCategory.setBooks(books);
            return repository.save(oldCategory);
        })
        .orElseThrow(() -> new IllegalArgumentException("Category not found"));
        return category;
    }

}
