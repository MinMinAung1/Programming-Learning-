package com.hostmdy.programming_learning.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hostmdy.programming_learning.domain.Account;
import com.hostmdy.programming_learning.service.AccountService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/account")
public class AccountController {

	private AccountService accountService;

	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}

	@GetMapping("/new")
	public String createAccountForm(Model model) {
		Account account = new Account();
		model.addAttribute("account", account);
		return "account/register";
	}

	@PostMapping("/new")
	public String createAccount(HttpServletRequest request, @ModelAttribute Account account, Model model) {
		HttpSession session = request.getSession();
		account.setStatus("active");
		account.setStatus("student");
		if (accountService.getAccountByEmail(account.getEmail()) == null) {
			Account createAccount = accountService.createAccount(account);
			session.setAttribute("account", createAccount);
			return "redirect:/";
		}
		model.addAttribute("success", false);
		return "common/register";
	}

	@GetMapping("/{accountId}/update")
	public String updateAccount(@PathVariable Long accountId, Model model) {
		Optional<Account> accountOpt = accountService.getAccoutById(accountId);
		if (accountOpt.isEmpty()) {
			throw new NullPointerException("accont is not found!");
		}
		Account account = accountOpt.get();
		model.addAttribute("account", account);
		return "redirect:/";
	}

	@PostMapping("/login")
	public String login(@ModelAttribute Account account, HttpSession session, Model model) {
		String email = account.getEmail();
		Account accountOpt = accountService.getAccountByEmail(email);
		if (accountOpt != null) {
			if (account.getPassword().equals(accountOpt.getPassword())) {
				session.setAttribute("account", accountOpt);
				session.setMaxInactiveInterval(3600 * 3);
				return "redirect:/";
			}
		}
		model.addAttribute("message", "Email or Password is incorrect!");
		model.addAttribute("success", false);
		return "common/login";
	}
	
	@GetMapping({ "/allaccount" })
	public String showAllAccount(Model model) {
		model.addAttribute("accounts", accountService.getAllAccount());
		return "common/allaccount";
	}
}
