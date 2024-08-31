package br.uece.sigdp.exceptions;

public class EmprestimoInvalidoException extends RuntimeException {

	private static final long serialVersionUID = 1L; 
	
	public EmprestimoInvalidoException(String message) {
        super(message);
    }
}
