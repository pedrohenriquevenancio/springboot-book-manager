package com.springboot_study.book_manager.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot_study.book_manager.models.BookModel;

public interface BookRepository extends JpaRepository<BookModel, UUID> {

}
