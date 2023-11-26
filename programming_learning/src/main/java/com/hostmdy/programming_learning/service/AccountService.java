package com.hostmdy.programming_learning.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.programming_learning.domain.Account;

public interface AccountService {
	Account saveAccount(Account account);
	
	Account createAccount(Account account);
	
	List<Account> getAllAccount();
	
	Optional<Account> getAccoutById(Long accoutId);
	
	Optional<Account> getAccoutByName(String name);
	
	Account getAccountByEmail(String email);
	
	Optional<Account> getAccountByPassword(String password);
	
	void deleteAccountById(Long accoutId);

}
