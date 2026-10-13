package com.distribuidos.imc.aplicacion.puertos.entrada;

import com.distribuidos.imc.aplicacion.dto.CalcularImcCommand;
import com.distribuidos.imc.dominio.modelos.ResultadoImc;

import java.util.concurrent.CompletableFuture;

public interface CalcularImcInputPort {
    CompletableFuture<ResultadoImc> calcular(CalcularImcCommand comando);
}
