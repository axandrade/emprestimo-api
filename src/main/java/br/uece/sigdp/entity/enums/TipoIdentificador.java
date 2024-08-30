package br.uece.sigdp.entity.enums;

import java.math.BigDecimal;

public enum TipoIdentificador {
    CPF(11, new BigDecimal("300.00"), new BigDecimal("10000.00")),
    CNPJ(14, new BigDecimal("1000.00"), new BigDecimal("100000.00")),
    ESTUDANTE_UNIVERSITARIO(8, new BigDecimal("100.00"), new BigDecimal("10000.00")),
    APOSENTADO(10, new BigDecimal("400.00"), new BigDecimal("25000.00"));

    private final int length;
    private final BigDecimal valorMinimoMensalParcelas;
    private final BigDecimal valorMaximoEmprestimo;

    TipoIdentificador(int length, BigDecimal valorMinimoMensalParcelas, BigDecimal valorMaximoEmprestimo) {
        this.length = length;
        this.valorMinimoMensalParcelas = valorMinimoMensalParcelas;
        this.valorMaximoEmprestimo = valorMaximoEmprestimo;
    }

    public int getLength() {
        return length;
    }

    public BigDecimal getValorMinimoMensalParcelas() {
        return valorMinimoMensalParcelas;
    }

    public BigDecimal getValorMaximoEmprestimo() {
        return valorMaximoEmprestimo;
    }

    public static TipoIdentificador fromLength(int length) {
        for (TipoIdentificador tipo : TipoIdentificador.values()) {
            if (tipo.getLength() == length) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tamanho do identificador inválido: " + length);
    }
}

