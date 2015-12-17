package br.edu.ufabc.sd.Controller;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import br.edu.ufabc.sd.Model.Operation;
import br.edu.ufabc.sd.bank.account.Account;

public interface BankServerService  extends Remote {
	
	public Account retriveAccount(long accountCode) throws RemoteException;
	
	public Collection<Operation> retrieveOperations(long accountCode, int bankId) throws RemoteException;
	
	public void withdraw(Long code, BigDecimal amount) throws RemoteException;

	public void deposit(Long code, BigDecimal amount) throws RemoteException;

	public BigDecimal viewBalance(Long code) throws RemoteException;

	public void transfer(Long accountSourceCode, Long accountSinkCode, BigDecimal amount) throws RemoteException;
	
	public String getBankURL(int bankId) throws RemoteException;
	
	public void logTransfer(Long accountSourceCode, Long accountSinkCode, int endBankId, BigDecimal amount) throws RemoteException;
	
}

