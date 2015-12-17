package br.edu.ufabc.sd.Model;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * @author rvarago
 */
public class Operation implements Comparable<Operation>, Serializable {

	private static final long serialVersionUID = -1369625699816793123L;

	private Long operationId;
	private Long sourceAccountId;
	private int sourceBankId;
	private Long endAccountId;
	private int endBankID;
	private BigDecimal amount;
	private String type;
	
	public Operation() {
	}
	
	public Operation(Long sourceAccountId, int sourceBankId, Long endAccountId,
			int endBankID, BigDecimal amount, String type) {
		super();
		this.operationId = System.currentTimeMillis()/1000;
		this.sourceAccountId = sourceAccountId;
		this.sourceBankId = sourceBankId;
		this.endAccountId = endAccountId;
		this.endBankID = endBankID;
		this.amount = amount;
		this.type = type;
	}
	
	public Long getSourceAccountId() {
		return sourceAccountId;
	}

	public void setSourceAccountId(Long sourceAccountId) {
		this.sourceAccountId = sourceAccountId;
	}

	public int getSourceBankId() {
		return sourceBankId;
	}

	public void setSourceBankId(int sourceBankId) {
		this.sourceBankId = sourceBankId;
	}

	public Long getEndAccountId() {
		return endAccountId;
	}

	public void setEndAccountId(Long endAccountId) {
		this.endAccountId = endAccountId;
	}

	public int getEndBankID() {
		return endBankID;
	}

	public void setEndBankID(int endBankID) {
		this.endBankID = endBankID;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Long getOperationId() {
		return operationId;
	}

	public void setOperationId(Long operationId) {
		this.operationId = operationId;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int compareTo(Operation o) {
		return this.getOperationId().compareTo(o.getOperationId());
	}
	
	

}
