package br.uece.sigdp;

import java.time.LocalDate;

import br.uece.sigdp.entity.Pessoa;

public class Teste {

	public static void main(String[] args) {
       
		 Pessoa pessoaCpf = new Pessoa(1L, "João Silva", "12345678901", LocalDate.of(1990, 5, 15));
	        System.out.println("Nome: " + pessoaCpf.getNome());
	        System.out.println("Identificador: " + pessoaCpf.getIdentificador());
	        System.out.println("Tipo Identificador: " + pessoaCpf.getTipoIdentificador());
	        System.out.println("Valor Mínimo Mensal Parcelas: " + pessoaCpf.getValorMinimoMensalParcelas());
	        System.out.println("Valor Máximo Empréstimo: " + pessoaCpf.getValorMaximoEmprestimo());
	        System.out.println();

	        // Teste para Pessoa Jurídica (CNPJ)
	        Pessoa pessoaCnpj = new Pessoa(2L, "Empresa X", "12345678000195", LocalDate.of(1985, 8, 20));
	        System.out.println("Nome: " + pessoaCnpj.getNome());
	        System.out.println("Identificador: " + pessoaCnpj.getIdentificador());
	        System.out.println("Tipo Identificador: " + pessoaCnpj.getTipoIdentificador());
	        System.out.println("Valor Mínimo Mensal Parcelas: " + pessoaCnpj.getValorMinimoMensalParcelas());
	        System.out.println("Valor Máximo Empréstimo: " + pessoaCnpj.getValorMaximoEmprestimo());
	        System.out.println();

	        // Teste para Estudante Universitário (EU)
	        Pessoa pessoaEstudante = new Pessoa(3L, "Ana Souza", "12345678", LocalDate.of(2000, 12, 1));
	        System.out.println("Nome: " + pessoaEstudante.getNome());
	        System.out.println("Identificador: " + pessoaEstudante.getIdentificador());
	        System.out.println("Tipo Identificador: " + pessoaEstudante.getTipoIdentificador());
	        System.out.println("Valor Mínimo Mensal Parcelas: " + pessoaEstudante.getValorMinimoMensalParcelas());
	        System.out.println("Valor Máximo Empréstimo: " + pessoaEstudante.getValorMaximoEmprestimo());
	        System.out.println();

	        // Teste para Aposentado (AP)
	        Pessoa pessoaAposentado = new Pessoa(4L, "Carlos Lima", "1234567890", LocalDate.of(1955, 4, 10));
	        System.out.println("Nome: " + pessoaAposentado.getNome());
	        System.out.println("Identificador: " + pessoaAposentado.getIdentificador());
	        System.out.println("Tipo Identificador: " + pessoaAposentado.getTipoIdentificador());
	        System.out.println("Valor Mínimo Mensal Parcelas: " + pessoaAposentado.getValorMinimoMensalParcelas());
	        System.out.println("Valor Máximo Empréstimo: " + pessoaAposentado.getValorMaximoEmprestimo());
	        System.out.println();

	}

}
