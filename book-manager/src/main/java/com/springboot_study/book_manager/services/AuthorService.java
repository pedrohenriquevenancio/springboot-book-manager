package com.springboot_study.book_manager.services;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot_study.book_manager.models.AuthorModel;
import com.springboot_study.book_manager.repositories.AuthorRepository;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository repository;

}
