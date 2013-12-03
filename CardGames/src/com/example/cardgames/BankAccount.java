package com.example.cardgames;

public class BankAccount {
	private  int money;
	public static BankAccount account;
	
	private BankAccount(){
		money = 1000;
	}
	public static BankAccount  getInstanceOf(){
		if(account==null){
			account = new BankAccount();
		}
		return account;
	}
	public int getBalanace(){
		return money;
	}
	public void withdraw(int mon){
		money = money-mon;
	}
	public void deposit(int mon){
		money = money+mon;
	}
}
