package br.univali.cc.m3.errors;

public class AccountDoesntExists extends Exception {
  public AccountDoesntExists() {
    super("Account doesn't exists!");
  }
}
