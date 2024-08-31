package br.uece.sigdp.exceptions;

public class IdentificadorInvalidoException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	public IdentificadorInvalidoException(String mensagem) {
		super(mensagem);
	}
}
