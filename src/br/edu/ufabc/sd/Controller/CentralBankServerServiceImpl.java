package br.edu.ufabc.sd.Controller;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import br.edu.ufabc.sd.Model.Operation;

public class CentralBankServerServiceImpl extends UnicastRemoteObject implements CentralBankServerService {
	
	private OperationDAO operationDAO;
	private CentralBankManager centralBankManager;
	
	private final String BANCO_BRANCO_URL = "rmi://localhost/bancoBranco";
	private final String BANCO_AZUL_URL = "rmi://localhost/bancoAzul";
	
	
	protected CentralBankServerServiceImpl() throws RemoteException {
		operationDAO = new OperationDAO();
		centralBankManager = new CentralBankManager(operationDAO);
	}


	private static final long serialVersionUID = 7430682183154700054L;

	@Override
	public void logOperation(Long sourceAccountId, int sourceBankId, Long endAccountId,
			int endBankID, BigDecimal amount, String type) throws RemoteException{

		Operation operation = new Operation(sourceAccountId, sourceBankId, endAccountId, endBankID, amount, type);	
		centralBankManager.createOperation(operation);
		
	}

	@Override
	public Operation getOperation(Long operationId) throws RemoteException{
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Operation> getOperationsByAccountId(Long accountId, int bankId) throws RemoteException {
		return centralBankManager.getOperationsByAccountId(accountId, bankId);
	}

	@Override
	public String getBankURL(int bankId) throws RemoteException {
		
		//Banco Branco = 1
		//Banco Azul = 2
		if(bankId == 1)
			return BANCO_BRANCO_URL;
		else
			return BANCO_AZUL_URL;
	}
	
	

}

