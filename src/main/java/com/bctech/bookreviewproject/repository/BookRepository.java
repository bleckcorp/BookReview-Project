package com.bctech.bookreviewproject.repository;

import com.bctech.bookreviewproject.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}