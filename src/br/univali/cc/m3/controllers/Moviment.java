package br.univali.cc.m3.controllers;

public class Moviment {
	private String description;
	private char type;
	private double value;

	public Moviment(String description, char type, double value) {
		this.description = description;
		this.type = type;
		this.value = value;
	}

	public String getMoviment() {
		return "(" + this.type + ") " + this.description + " R$" + this.value;
	}
}
