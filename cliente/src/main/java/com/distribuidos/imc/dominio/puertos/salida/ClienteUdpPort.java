package com.distribuidos.imc.dominio.puertos.salida;

import com.distribuidos.imc.aplicacion.excepciones.ClienteRedException;
import com.distribuidos.imc.dominio.modelos.DatosImc;
import com.distribuidos.imc.dominio.modelos.ResultadoImc;
import com.distribuidos.imc.dominio.vo.DestinoServidor;

public interface ClienteUdpPort {
    void conectar(DestinoServidor destino) throws ClienteRedException;

    void desconectar() throws ClienteRedException;

    ResultadoImc solicitarCalculo(DatosImc datos) throws ClienteRedException;

    boolean estaConectado();
}
