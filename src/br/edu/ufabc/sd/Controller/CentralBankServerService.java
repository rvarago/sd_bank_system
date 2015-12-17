package br.edu.ufabc.sd.Controller;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;

import java.util.List;

import br.edu.ufabc.sd.Model.Operation;

public interface CentralBankServerService  extends Remote {
	
	public void logOperation(Long sourceAccountId, int sourceBankId, Long endAccountId,
			int endBankID, BigDecimal amount, String type) throws RemoteException;
	
	public Operation getOperation(Long operationId) throws RemoteException;
	
	public List<Operation> getOperationsByAccountId(Long accountId, int bankId) throws RemoteException;
	
	public String getBankURL(int bankId) throws RemoteException;
}

