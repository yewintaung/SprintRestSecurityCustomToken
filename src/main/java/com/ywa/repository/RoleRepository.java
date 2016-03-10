package com.ywa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ywa.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByRoleName(String roleName);
}
