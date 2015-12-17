package br.edu.ufabc.sd.Controller;

import java.math.BigDecimal;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;

import br.edu.ufabc.sd.Model.Operation;
import br.edu.ufabc.sd.bank.account.Account;

public class BankServerServiceImpl extends UnicastRemoteObject implements BankServerService {
	
	private static final long serialVersionUID = 7430682183154700054L;
	
	//Banco Branco = 1
	//Banco Azul = 2
	int bankId;
	
	AccountDAO accountDAO;
	BankManager bankManager;
	BankClientService bankClientService;
	
	CentralBankServerService centralBankServerService;
	
	public BankServerServiceImpl(String bankName, String centralBankURL) throws RemoteException{
		
		if(bankName.equals("bancoBranco/"))
			bankId = 1;
		else
			bankId = 2;
		
		accountDAO = new AccountDAOImpl(bankName);
		bankClientService = new BankClientServiceImpl();
		bankManager = new BankManager(bankClientService, accountDAO);
		
		try {
			this.centralBankServerService = (CentralBankServerService) Naming.lookup(centralBankURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Account retriveAccount(long accountCode) throws RemoteException {
		return this.bankManager.retriveAccount(accountCode);
	}

	@Override
	public void withdraw(Long code, BigDecimal amount) throws RemoteException {
		String type = "Saque";
		centralBankServerService.logOperation(code, bankId, code, bankId, amount, type);
		this.bankManager.withdraw(code, amount);
	}

	@Override
	public void deposit(Long code, BigDecimal amount) throws RemoteException {
		String type = "Depósito";
		centralBankServerService.logOperation(code, bankId, code, bankId, amount, type);
		this.bankManager.deposit(code, amount);
	}

	@Override
	public BigDecimal viewBalance(Long code) throws RemoteException {
		return this.bankManager.viewBalance(code);
	}

	@Override
	public void transfer(Long accountSourceCode, Long accountSinkCode, BigDecimal amount) throws RemoteException {
		String type = "Transferência";
		centralBankServerService.logOperation(accountSourceCode, bankId, accountSinkCode, bankId, amount, type);
		bankManager.withdraw(accountSourceCode, amount);	
		bankManager.deposit(accountSinkCode, amount);
	}
	
	

	@Override
	public Collection<Operation> retrieveOperations(long accountCode, int bankId)
			throws RemoteException {
		return centralBankServerService.getOperationsByAccountId(accountCode, bankId);
	}

	/* (non-Javadoc)
	 * @see br.edu.ufabc.sd.Controller.BankServerService#getBankURL(int)
	 */
	@Override
	public String getBankURL(int bankId) throws RemoteException {
		return centralBankServerService.getBankURL(bankId);
	}
	
	@Override
	public void logTransfer(Long accountSourceCode, Long accountSinkCode, int endBankId, BigDecimal amount) throws RemoteException {
		String type = "Transferência";
		centralBankServerService.logOperation(accountSourceCode, bankId, accountSinkCode, endBankId, amount, type);
		
	}
	

}

