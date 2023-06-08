package br.univali.cc.m3.views;

import br.univali.cc.m3.controllers.Bank;
import br.univali.cc.m3.errors.InvalidFormatValue;

import javax.swing.JOptionPane;

public class AtmGUI {
	private Bank bank;

	public AtmGUI(Bank bank) {
		this.bank = bank;
	}

	public String readValue(String label) {
		return JOptionPane.showInputDialog(null, label);
	}

	public String readValue(String label, String[] options) {
		return (String) JOptionPane.showInputDialog(null, null, label, JOptionPane.QUESTION_MESSAGE, null, options, null);
	}

	public void writeValue(String value) {
		JOptionPane.showMessageDialog(null, value);
	}

	public void menu() throws InvalidFormatValue, Exception {
		char option;

		do {
			String[] options = {
					"1 - Create Sample Account",
					"2 - Create Special Account",
					"3 - Deposit",
					"4 - Draw",
					"5 - Transfer",
					"6 - Extract",
					"E - Exit"
			};
			String selectedValue = this.readValue("Select an option", options);
			if (selectedValue == null) {
				option = 'E';
			} else {
				option = selectedValue.toUpperCase().charAt(0);
			}

			switch (option) {
				case '1':
					createSampleAccount();
					break;
				case '2':
					createSpecialAccount();
					break;
				case '3':
					deposit();
					break;
				case '4':
					draw();
					break;
				case '5':
					transfer();
					break;
				case '6':
					extract();
					break;
			}
		} while (option != 'E');
	}

	private void createSampleAccount() throws Exception, InvalidFormatValue {
		try {
			double initialBalance = Double.parseDouble(readValue("Enter initial balance"));
			this.bank.createAccount(initialBalance);
		} catch (InvalidFormatValue error) {
			throw new InvalidFormatValue();
		} catch (Exception error) {
			throw new Exception("Error creating sample account!");
		}
	}

	private void createSpecialAccount() throws Exception, InvalidFormatValue {
		try {
			double initialBalance = Double.parseDouble(readValue("Enter initial balance"));
			double limit = Double.parseDouble(readValue("Enter limit from account"));
			this.bank.createAccount(initialBalance, limit);
		} catch (InvalidFormatValue error) {
			throw new InvalidFormatValue();
		} catch (Exception error) {
			throw new Exception("Error creating special account!");
		}
	}

	private void deposit() throws Exception, InvalidFormatValue {
		try {
			int accountNumber = Integer.parseInt(readValue("Enter account number"));
			double value = Double.parseDouble(readValue("Informe o valor para depósito"));
			this.bank.deposit(accountNumber, value);
		} catch (InvalidFormatValue error) {
			throw new InvalidFormatValue();
		} catch (Exception error) {
			throw new Exception("Error on deposit!");
		}
	}

	private void draw() throws Exception, InvalidFormatValue {
		try {
			int accountNumber = Integer.parseInt(readValue("Enter account number"));
			double value = Double.parseDouble(readValue("Enter the withdraw value"));
			this.bank.draw(accountNumber, value);
		} catch (InvalidFormatValue error) {
			throw new InvalidFormatValue();
		} catch (Exception error) {
			throw new Exception("Error on draw!");
		}
	}

	private void transfer() throws Exception, InvalidFormatValue {
		try {
			int toAccountNumber = Integer.parseInt(readValue("Enter origin account number"));
			int fromAccountNumber = Integer.parseInt(readValue("Enter destiny account number"));
			double value = Double.parseDouble(readValue("Enter the transfer value"));
			this.bank.transfer(toAccountNumber, fromAccountNumber, value);
		} catch (InvalidFormatValue error) {
			throw new InvalidFormatValue();
		} catch (Exception error) {
			throw new Exception("Error on transfer!");
		}
	}

	private void extract() throws InvalidFormatValue {
		try {
			int accountNumber = Integer.parseInt(readValue("Enter account number"));
			this.writeValue(this.bank.getExtract(accountNumber));
		} catch (InvalidFormatValue error) {
			throw new InvalidFormatValue();
		}
	}
}
