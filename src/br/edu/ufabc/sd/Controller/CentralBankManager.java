package br.edu.ufabc.sd.Controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.edu.ufabc.sd.Model.Operation;


public class CentralBankManager {

	private OperationDAO operationDAO;

	public CentralBankManager(OperationDAO operationDAO) {
		this.operationDAO = operationDAO;
	}

	public Long createOperation(Operation operation) {
		
		try {
			this.operationDAO.persist(operation);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return operation.getOperationId();
	}
	
	public List<Operation> getOperationsByAccountId(Long accountId, int bankId){
		try {
			Collection<Operation> operations = this.operationDAO.list();
			List<Operation> results = new ArrayList<Operation>();

			
			for(int i = 0; i < operations.size(); i++){
				Operation op = (Operation) operations.toArray()[i];
				
				if((op.getSourceAccountId().equals(accountId) && op.getSourceBankId() == bankId))
					results.add(op);
			}

			return results;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

/*
	public Account retriveAccount(Long code) {
		try {
			return this.operationDAO.find(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Collection<Account> getAccounts() {
		try {
			return this.operationDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/
}
