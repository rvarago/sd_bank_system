package br.edu.ufabc.sd.Controller;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class BankServer {

	private static final String BANCO_BRANCO_URL = "rmi://localhost/bancoBranco";
	private static final String BANCO_AZUL_URL = "rmi://localhost/bancoAzul";
	
	private static final String CENTRAL_BANK_URL = "rmi://localhost/bancoCentral";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		try {
			LocateRegistry.createRegistry(1097);
			
			BankServerService serviceBancoBranco = new BankServerServiceImpl("bancoBranco/", CENTRAL_BANK_URL);
			BankServerService serviceBancoAzul = new BankServerServiceImpl("bancoAzul/", CENTRAL_BANK_URL);
			
			Naming.rebind(BANCO_BRANCO_URL, serviceBancoBranco);
			Naming.rebind(BANCO_AZUL_URL, serviceBancoAzul);
			System.out.println("Servidor expondo serviços em: " + BANCO_BRANCO_URL +
					" e " + BANCO_AZUL_URL);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}

}
