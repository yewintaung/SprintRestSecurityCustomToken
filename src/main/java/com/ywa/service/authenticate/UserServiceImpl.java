package com.ywa.service.authenticate;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ywa.domain.Account;
import com.ywa.helper.AccountHelper;
import com.ywa.repository.AccountRepository;

@Service(value = "UserServiceImpl")
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountHelper accountHelper;
	
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, DataAccessException {
		
		if (email == null || email.trim().isEmpty()) {
			throw new UsernameNotFoundException("Email is null or empty");
		}
		
		Account user = accountRepository.findByEmail(email);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		
		return new User(user.getEmail(), user.getPassword(), user.getIsEnabled(), true, true, true, toGrantedAuthorities(accountHelper.getRoleNames(user)));
		
	}
	
	public static Collection<GrantedAuthority> toGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> result = newArrayList();

		for (String role : roles) {
			result.add(new SimpleGrantedAuthority(role));
		}

		return result;
	}

}
