package com.springboot_study.book_manager.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot_study.book_manager.models.AuthorModel;

public interface AuthorRepository extends JpaRepository<AuthorModel, UUID> {

}
