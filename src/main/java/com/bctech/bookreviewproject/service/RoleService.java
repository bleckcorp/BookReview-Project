package com.bctech.bookreviewproject.service;

import com.bctech.bookreviewproject.entity.Role;

public interface RoleService {
    Role save(Role role);
    Role findByName(String name);
}
