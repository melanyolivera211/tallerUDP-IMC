package com.distribuidos.imc.aplicacion.puertos.entrada;

import com.distribuidos.imc.aplicacion.dto.ProcesarPeticionUdpCommand;

/**
 * Puerto de entrada para procesar las peticiones recibidas por el listener UDP.
 */
public interface ProcesarPeticionUdpInputPort {
  void procesar(ProcesarPeticionUdpCommand comando);
}
