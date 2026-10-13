package com.distribuidos.imc.adaptadores.notificacion;

import com.distribuidos.imc.dominio.enums.EstadoConexion;
import com.distribuidos.imc.dominio.modelos.EventoCliente;
import com.distribuidos.imc.dominio.modelos.ResultadoImc;

public interface ObservadorCliente {
    void onEstado(EstadoConexion estado, String endpoint);

    void onEvento(EventoCliente evento);

    void onResultado(ResultadoImc resultado);

    void onError(String mensaje);
}
