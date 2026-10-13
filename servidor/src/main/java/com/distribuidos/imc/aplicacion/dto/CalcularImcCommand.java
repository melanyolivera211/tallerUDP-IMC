package com.distribuidos.imc.aplicacion.dto;

/**
 * Comando DTO para solicitar el cálculo de IMC con valores numéricos
 * primitivos.
 */
public record CalcularImcCommand(double peso, double altura) {
}
