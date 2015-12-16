package br.edu.ufabc.sd.servers;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;

import br.edu.ufabc.sd.bank.account.Account;

public interface BankServerService  extends Remote {
	
	public Account retriveAccount(long accountCode) throws RemoteException;
	
	public void withdraw(Long code, BigDecimal amount) throws RemoteException;

	public void deposit(Long code, BigDecimal amount) throws RemoteException;

	public BigDecimal viewBalance(Long code) throws RemoteException;

	public void transfer(Long accountSourceCode, Long accountSinkCode, BigDecimal amount) throws RemoteException;
}

