package com.distribuidos.imc.dominio.puertos.salida;

import com.distribuidos.imc.dominio.enums.EstadoConexion;
import com.distribuidos.imc.dominio.modelos.EventoCliente;
import com.distribuidos.imc.dominio.modelos.ResultadoImc;

public interface PuertoNotificacionCliente {
    void notificarEstado(EstadoConexion estado, String endpoint);

    void notificarEvento(EventoCliente evento);

    void notificarResultado(ResultadoImc resultado);

    void notificarError(String mensaje);
}
