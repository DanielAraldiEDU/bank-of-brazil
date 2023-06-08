package br.univali.cc.m3.errors;

public class AccountNumberAlreadyExists extends Exception {
  public AccountNumberAlreadyExists() {
    super("Account number already exists!");
  }
}
