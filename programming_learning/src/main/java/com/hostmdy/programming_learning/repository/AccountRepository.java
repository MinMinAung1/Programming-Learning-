package com.hostmdy.programming_learning.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.programming_learning.domain.Account;

public interface AccountRepository extends CrudRepository<Account, Long>{
	
	Optional<Account> findByName(String name);
	
    Account findByEmail(String email);
    
    Optional<Account> findByPassword(String password);
}
