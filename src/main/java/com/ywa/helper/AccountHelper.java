package com.ywa.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

import com.ywa.domain.Account;
import com.ywa.domain.Role;

@Component
public class AccountHelper implements EntityHelper<Account> {
	
	private static final String PASSWORD_UI = "**********";

	public Account copyFrom(Account entity) {
		Account copy = new Account();
		copy.setId(entity.getId());
		copy.setFirstName(entity.getFirstName());
		copy.setLastName(entity.getLastName());
		copy.setEmail(entity.getEmail());
		copy.setPassword(PASSWORD_UI);
		copy.setIsEnabled(entity.getIsEnabled());
		return copy;
	}

	public Account copyWithoutPkFrom(Account entity) {
		Account copy = new Account();
		copy.setFirstName(entity.getFirstName());
		copy.setLastName(entity.getLastName());
		copy.setEmail(entity.getEmail());
		copy.setPassword(PASSWORD_UI);
		copy.setIsEnabled(entity.getIsEnabled());
		return copy;
	}

	public Account updateFrom(Account fromEntity, Account toEntity) {
		toEntity.setFirstName(fromEntity.getFirstName());
		toEntity.setLastName(fromEntity.getLastName());
		if (!fromEntity.getPassword().equals(PASSWORD_UI)) {
			toEntity.setPassword(DigestUtils.md5Hex(fromEntity.getPassword()));
		}
		toEntity.setIsEnabled(fromEntity.getIsEnabled());
		return toEntity;
	}

	public Account createEntityInstance() {
		return new Account();
	}

	public Account createRandomEntity() {
		Account account = new Account();
		account.setFirstName(ValueGenerator.getMaxString(100));
		account.setLastName(ValueGenerator.getMaxString(100));
		account.setEmail(ValueGenerator.getUniqueEmail());
		account.setPassword(PASSWORD_UI);
		account.setIsEnabled(true);
		return account;
	}
	
	public List<String> getRoleNames(final Account account) {
		List<String> roleNames = new ArrayList<String>();

		for (Role role : account.getRoles()) {
			roleNames.add(role.getRoleName());
		}
		return roleNames;
	}

}
