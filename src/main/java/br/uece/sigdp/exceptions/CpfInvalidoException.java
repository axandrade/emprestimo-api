package br.uece.sigdp.exceptions;

public class CpfInvalidoException extends IdentificadorInvalidoException {
	private static final long serialVersionUID = 1L; 

	public CpfInvalidoException(String cpf) {
		super("CPF inválido: " + cpf);
	}
}