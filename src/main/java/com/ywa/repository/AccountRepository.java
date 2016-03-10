package com.ywa.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.ywa.domain.Account;

public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {

	Account findByEmail(String email);
}
