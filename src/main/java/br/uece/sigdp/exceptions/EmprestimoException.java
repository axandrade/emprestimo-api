package br.uece.sigdp.exceptions;

public class EmprestimoException extends Exception {

	private static final long serialVersionUID = 1L;

	public EmprestimoException(String message) {
		super(message);
	}

	public EmprestimoException(String message, Throwable cause) {
		super(message, cause);
	}
}
