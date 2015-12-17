package br.edu.ufabc.sd.Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;
import java.util.TreeSet;

import br.edu.ufabc.sd.Model.Operation;

public class OperationDAO  {

	private static final String FILE_SUFFIX = ".ser";
	private String BASE_DIR = "operations/";
	
	public OperationDAO(){
	}

	public void persist(Operation operation) throws Exception {
		if (operation == null) {
			throw new Exception("Não é permitido gravar operaçao em branco");
		}
		try (ObjectOutputStream writer = new ObjectOutputStream(
				new FileOutputStream(BASE_DIR + operation.getOperationId() + FILE_SUFFIX))) {
			writer.writeObject(operation);
		}
	}

	public Operation find(Long code) throws Exception {
		Operation operation;
		try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(BASE_DIR + code + FILE_SUFFIX))) {
			operation = (Operation) reader.readObject();
		}
		return operation;
	}

	public void remove(Long code) throws Exception {
		new File(BASE_DIR + code + FILE_SUFFIX).delete();
	}
	
	public Set<Operation> list() throws Exception {
		Set<Operation> operations = new TreeSet<>();
		for (String file : new File(BASE_DIR).list()) {
			try (ObjectInputStream reader = new ObjectInputStream(new FileInputStream(BASE_DIR + file))) {
				operations.add((Operation) reader.readObject());
			}
		}
		return operations;
	}
}
