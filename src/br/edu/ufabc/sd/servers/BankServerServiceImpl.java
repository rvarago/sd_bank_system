package br.edu.ufabc.sd.servers;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import br.edu.ufabc.sd.bank.BankClientService;
import br.edu.ufabc.sd.bank.BankClientServiceImpl;
import br.edu.ufabc.sd.bank.BankManager;
import br.edu.ufabc.sd.bank.account.Account;
import br.edu.ufabc.sd.bank.dao.AccountDAO;
import br.edu.ufabc.sd.bank.dao.AccountDAOImpl;

public class BankServerServiceImpl extends UnicastRemoteObject implements BankServerService {
	
	private static final long serialVersionUID = 7430682183154700054L;
	
	AccountDAO accountDAO;
	BankManager bankManager;
	BankClientService bankClientService;
	
	public BankServerServiceImpl(String bankName) throws RemoteException{
		accountDAO = new AccountDAOImpl(bankName);
		bankClientService = new BankClientServiceImpl();
		bankManager = new BankManager(bankClientService, accountDAO);
		
	}

	@Override
	public Account retriveAccount(long accountCode) throws RemoteException {
		return this.bankManager.retriveAccount(accountCode);
	}

	@Override
	public void withdraw(Long code, BigDecimal amount) throws RemoteException {
		this.bankManager.withdraw(code, amount);
	}

	@Override
	public void deposit(Long code, BigDecimal amount) throws RemoteException {
		this.bankManager.deposit(code, amount);
	}

	@Override
	public BigDecimal viewBalance(Long code) throws RemoteException {
		return this.bankManager.viewBalance(code);
	}

	@Override
	public void transfer(Long accountSourceCode, Long accountSinkCode, BigDecimal amount) throws RemoteException {
		System.out.println("chamarei bankManager "+accountSourceCode+" "+accountSinkCode+" "+amount);
		bankManager.transfer(accountSourceCode, accountSinkCode, amount);	
	}
	

}

