package br.univali.cc.m3.controllers;

import java.util.ArrayList;
import java.util.List;

import br.univali.cc.m3.errors.AccountDoesntExists;
import br.univali.cc.m3.errors.AccountNumberAlreadyExists;
import br.univali.cc.m3.errors.InvalidBalanceValue;

public class Bank {
	private String name;
	private int accountNumber;
	private List<CurrentAccount> accounts;
	private int initialAccountNumber = 0;

	public Bank(String name, int accountNumber) {
		this.name = name;
		this.accountNumber = accountNumber;
		this.accounts = new ArrayList<CurrentAccount>();
	}

	public void createAccount(double initialBalance) throws AccountNumberAlreadyExists {
		int newAccountNumber = this.initialAccountNumber++;
		CurrentAccount account = this.findAccount(newAccountNumber);
		if (account != null) {
			throw new AccountNumberAlreadyExists();
		}
		this.accounts.add(new CurrentAccount(newAccountNumber + 1, initialBalance));
	}

	public void createAccount(double initialBalance, double limit) throws AccountNumberAlreadyExists {
		int newAccountNumber = this.initialAccountNumber++;
		CurrentAccount account = this.findAccount(newAccountNumber);
		if (account != null) {
			throw new AccountNumberAlreadyExists();
		}
		this.accounts.add(new CurrentAccount(newAccountNumber + 1, initialBalance, limit));
	}

	private CurrentAccount findAccount(int accountNumber) {
		for (CurrentAccount account : this.accounts) {
			if (account != null && account.getAccountNumber() == accountNumber) {
				return account;
			}
		}
		return null;
	}

	public void deposit(int accountNumber, double value) {
		CurrentAccount currentAccount = this.findAccount(accountNumber);
		if (currentAccount != null) {
			currentAccount.deposit(value);
		}
	}

	public void draw(int accountNumber, double value) {
		CurrentAccount currentAccount = this.findAccount(accountNumber);
		if (currentAccount != null) {
			try {
				currentAccount.draw(value);
			} catch (InvalidBalanceValue error) {
				System.out.println(error.getMessage());
			}
		}
	}

	public void transfer(int toAccountNumber, int fromAccountNumber, double value)
			throws InvalidBalanceValue, AccountDoesntExists {
		CurrentAccount origin = this.findAccount(toAccountNumber);
		CurrentAccount destiny = this.findAccount(fromAccountNumber);
		try {
			if (origin != null && destiny != null) {
				if (origin.draw(value)) {
					destiny.deposit(value);
				}
			} else {
				throw new AccountDoesntExists();
			}
		} catch (InvalidBalanceValue error) {
			System.out.println(error.getMessage());
		}
	}

	public String getExtract(int accountNumber) {
		CurrentAccount currentAccount = this.findAccount(accountNumber);
		if (currentAccount != null) {
			return currentAccount.getExtract();
		}
		return "No extracts";
	}

	public String getNome() {
		return this.name;
	}
}
