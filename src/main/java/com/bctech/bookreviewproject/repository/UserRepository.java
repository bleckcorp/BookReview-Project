package com.bctech.bookreviewproject.repository;

import com.bctech.bookreviewproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Transactional
    Optional<User> findByUsername(String username);
}
