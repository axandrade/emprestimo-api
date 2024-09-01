package br.uece.sigdp.validator;

import br.uece.sigdp.exceptions.CnpjInvalidoException;
import br.uece.sigdp.exceptions.CpfInvalidoException;
import br.uece.sigdp.exceptions.IdentificadorInvalidoException;

public class IdentificadorValidator {
	
	 public static void validarCpfCnpj(String identificador) {
	        if (identificador == null || identificador.isEmpty()) {
	            throw new IllegalArgumentException("Identificador não pode ser nulo ou vazio.");
	        }
	        identificador = identificador.replaceAll("[^\\d]", "");
	        if (identificador.length() == 11) {
	            validarCpf(identificador);
	        } else if (identificador.length() == 14) {
	            validarCnpj(identificador);
	        } else {
	            throw new IllegalArgumentException("Tamanho do identificador inválido.");
	        }
	    }

	    public static void validarCpf(String cpf) {
	        if (cpf.matches("(\\d)\\1{10}")) {
	            throw new CpfInvalidoException(cpf);
	        }

	        int[] pesos = {10, 9, 8, 7, 6, 5, 4, 3, 2};
	        int primeiroDigito = calcularDigito(cpf.substring(0, 9), pesos);
	        int segundoDigito = calcularDigito(cpf.substring(0, 9) + primeiroDigito, new int[]{11, 10, 9, 8, 7, 6, 5, 4, 3, 2});

	        if (!cpf.equals(cpf.substring(0, 9) + primeiroDigito + segundoDigito)) {
	            throw new CpfInvalidoException(cpf);
	        }
	    }

	    public static void validarCnpj(String cnpj) {
	        if (cnpj.matches("(\\d)\\1{13}")) {
	            throw new CnpjInvalidoException(cnpj);
	        }

	        int[] pesos = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
	        int primeiroDigito = calcularDigito(cnpj.substring(0, 12), pesos);
	        int segundoDigito = calcularDigito(cnpj.substring(0, 12) + primeiroDigito, new int[]{6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2});

	        if (!cnpj.equals(cnpj.substring(0, 12) + primeiroDigito + segundoDigito)) {
	            throw new CnpjInvalidoException(cnpj);
	        }
	    }

	    private static int calcularDigito(String str, int[] pesos) {
	        int soma = 0;
	        for (int i = 0; i < str.length(); i++) {
	            soma += Character.getNumericValue(str.charAt(i)) * pesos[i];
	        }
	        int resto = soma % 11;
	        return (resto < 2) ? 0 : 11 - resto;
	    }
	    
	    public static boolean validarEstudanteUniversitario(String identificador) {
	        if (identificador.length() != 8) {
	            throw new IdentificadorInvalidoException("Identificador de estudante universitário deve ter 8 caracteres.");
	        }

	        int primeiroDigito = Character.getNumericValue(identificador.charAt(0));
	        int ultimoDigito = Character.getNumericValue(identificador.charAt(7));

	        if ((primeiroDigito + ultimoDigito) != 9) {
	            throw new IdentificadorInvalidoException("A soma do primeiro e do último dígito do identificador de estudante universitário deve ser igual a 9.");
	        }

	        return true;
	    }


	    public static boolean validarAposentado(String identificador) {
	        if (identificador.length() != 10) {
	            throw new IdentificadorInvalidoException("Identificador de aposentado deve ter 10 caracteres.");
	        }

	        char ultimoDigito = identificador.charAt(9);
	        String primeirosNoveDigitos = identificador.substring(0, 9);

	        if (primeirosNoveDigitos.indexOf(ultimoDigito) != -1) {
	            throw new IdentificadorInvalidoException("Identificador de aposentado não deve repetir o último dígito nos primeiros nove dígitos.");
	        }

	        return true;
	    }


}
