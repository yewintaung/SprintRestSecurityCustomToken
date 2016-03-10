package com.ywa.helper;

import org.springframework.stereotype.Component;

import com.ywa.domain.Role;

@Component
public class RoleHelper implements EntityHelper<Role> {

	public Role copyFrom(Role entity) {
		Role role = new Role();
		role.setId(entity.getId());
		role.setRoleName(entity.getRoleName());
		return role;
	}

	public Role copyWithoutPkFrom(Role entity) {
		Role role = new Role();
		role.setRoleName(entity.getRoleName());
		return role;
	}

	public Role updateFrom(Role fromentity, Role toEntity) {
		toEntity.setRoleName(fromentity.getRoleName());
		return null;
	}

	public Role createEntityInstance() {
		return new Role();
	}

	public Role createRandomEntity() {
		Role role = new Role();
		role.setRoleName(ValueGenerator.getUniqueString(10));
		return role;
	}

}
