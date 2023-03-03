package com.bctech.bookreviewproject.repository;

import com.bctech.bookreviewproject.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
