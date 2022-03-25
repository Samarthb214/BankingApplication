package com.db.awmd.challenge.domain;

public class TransferResponseDomain {

	private String message="Transaction is initiated. For further processing results are visible in the Eclipse Console.";
	@Override
	public int hashCode() {
		int result = 1;

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
