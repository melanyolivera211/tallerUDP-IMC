package com.distribuidos.imc.dominio.puertos.salida;

import com.distribuidos.imc.aplicacion.excepciones.ServidorRedException;
import com.distribuidos.imc.dominio.vo.PuertoRed;

/**
 * Puerto de salida para el control de la infraestructura de red del servidor.
 * Recibe exclusivamente objetos de dominio.
 */
public interface ControladorServidorRedPort {
    void iniciar(PuertoRed puerto) throws ServidorRedException;

    void detener();

    boolean isActivo();

    PuertoRed getPuertoActual();
}
