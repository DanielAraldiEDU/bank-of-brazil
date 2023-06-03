package br.univali.cc.m3.controllers;

import java.util.ArrayList;
import java.util.List;

public class CurrentAccount {
	private boolean isSpecial;
	private double limit;
	private int accountNumber;
	private double balance;
	private List<Moviment> moviments = new ArrayList<Moviment>();

	public CurrentAccount(int accountNumber, double initialBalance) {
		this.isSpecial = false;
		this.limit = 0;
		this.accountNumber = accountNumber;
		this.balance = initialBalance;
		this.createMoviment("Initial Balance", 'C', initialBalance);
	}

	public CurrentAccount(int accountNumber, double initialBalance, double limit) {
		this.isSpecial = true;
		this.limit = limit;
		this.accountNumber = accountNumber;
		this.balance = initialBalance;
		this.createMoviment("Initial Balance", 'C', initialBalance);
	}

	public int getAccountNumber() {
		return this.accountNumber;
	}

	protected boolean deposit(double value) {
		if (value > 0) {
			this.balance += value;
			this.createMoviment("Deposit", 'C', value);
			return true;
		}
		return false;
	}

	protected boolean draw(double value) {
		if (this.balance + this.limit >= value) {
			this.balance -= value;
			this.createMoviment("Withdraw", 'D', value);
			return true;
		}
		return false;
	}

	private void createMoviment(String description, char type, double value) {
		moviments.add(new Moviment(description, type, value));
	}

	protected String getExtract() {
		String extract = "Bank Extract C/C " + accountNumber;
		for (Moviment moviment : this.moviments) {
			if (moviment == null) {
				break;
			}
			extract += "\n" + moviment.getMoviment();
		}
		extract += "\n final balance R$ " + this.balance;
		if (isSpecial) {
			extract += "\n limit R$ " + this.limit;
		}
		return extract;
	}

}
