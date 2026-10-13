package com.distribuidos.imc.dominio.vo;

import com.distribuidos.imc.dominio.excepciones.AlturaIncorrectaException;

public record Altura(double valor) {
    public Altura {
        if (!Double.isFinite(valor) || valor <= 0) {
            throw new AlturaIncorrectaException("La altura debe ser un número finito mayor que cero.");
        }
    }
}
