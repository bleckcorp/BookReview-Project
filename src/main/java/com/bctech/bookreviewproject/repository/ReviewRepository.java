package com.bctech.bookreviewproject.repository;

import com.bctech.bookreviewproject.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}