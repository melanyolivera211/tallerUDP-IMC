package com.distribuidos.imc.aplicacion.mapper;

import com.distribuidos.imc.aplicacion.dto.CalcularImcCommand;
import com.distribuidos.imc.aplicacion.dto.ConectarCommand;
import com.distribuidos.imc.dominio.modelos.DatosImc;
import com.distribuidos.imc.dominio.vo.Altura;
import com.distribuidos.imc.dominio.vo.DestinoServidor;
import com.distribuidos.imc.dominio.vo.Peso;

import java.util.Objects;

public final class ClienteMapper {
    public DestinoServidor toDestino(final ConectarCommand comando) {
        Objects.requireNonNull(comando, "El comando de conexión es obligatorio.");
        return new DestinoServidor(comando.host(), comando.puerto());
    }

    public DatosImc toDatos(final CalcularImcCommand comando) {
        Objects.requireNonNull(comando, "El comando de cálculo es obligatorio.");
        return new DatosImc(new Peso(comando.peso()), new Altura(comando.altura()));
    }
}
