package br.uece.sigdp.exceptions;

public class CnpjInvalidoException extends IdentificadorInvalidoException {
    private static final long serialVersionUID = 1L; 
    public CnpjInvalidoException(String cnpj) {
        super("CNPJ inválido: " + cnpj);
    }
}