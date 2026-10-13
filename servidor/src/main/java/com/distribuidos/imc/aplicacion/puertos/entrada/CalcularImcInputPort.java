package com.distribuidos.imc.aplicacion.puertos.entrada;

import com.distribuidos.imc.aplicacion.dto.CalcularImcCommand;
import com.distribuidos.imc.aplicacion.dto.ResultadoImcDto;

/** Puerto de entrada (Caso de Uso) para el cálculo de IMC. */
public interface CalcularImcInputPort {
  ResultadoImcDto calcular(CalcularImcCommand comando);
}
