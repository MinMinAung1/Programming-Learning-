package com.hostmdy.programming_learning.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.programming_learning.domain.Account;
import com.hostmdy.programming_learning.repository.AccountRepository;
import com.hostmdy.programming_learning.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;

	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}
	
	@Override
	public Account saveAccount(Account account) {
		// TODO Auto-generated method stub
		return accountRepository.save(account);
	}
	
	@Override
	public Account createAccount(Account account) {
		// TODO Auto-generated method stub
		return saveAccount(account);
	}

	@Override
	public List<Account> getAllAccount() {
		// TODO Auto-generated method stub
		return (List<Account>) accountRepository.findAll();
	}

	@Override
	public Optional<Account> getAccoutById(Long accoutId) {
		// TODO Auto-generated method stub
		return accountRepository.findById(accoutId);
	}

	@Override
	public Optional<Account> getAccoutByName(String name) {
		// TODO Auto-generated method stub
		return accountRepository.findByName(name);
	}

	@Override
	public void deleteAccountById(Long accoutId) {
		// TODO Auto-generated method stub
		accountRepository.deleteById(accoutId);

	}

	@Override
	public Account getAccountByEmail(String email) {
		// TODO Auto-generated method stub
		return accountRepository.findByEmail(email);
	}

	@Override
	public Optional<Account> getAccountByPassword(String password) {
		// TODO Auto-generated method stub
		return accountRepository.findByPassword(password);
	}
}
