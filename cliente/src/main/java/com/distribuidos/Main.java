package com.distribuidos;

import com.distribuidos.imc.adaptadores.notificacion.AdaptadorNotificacionCliente;
import com.distribuidos.imc.adaptadores.red.AdaptadorClienteUdp;
import com.distribuidos.imc.adaptadores.red.CanalUdp;
import com.distribuidos.imc.adaptadores.red.ProtocoloUdpMapper;
import com.distribuidos.imc.aplicacion.mapper.ClienteMapper;
import com.distribuidos.imc.aplicacion.servicios.ClienteImcService;
import com.distribuidos.imc.entrypoint.gui.ClienteFrame;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import javax.swing.SwingUtilities;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Main {
    public static void main(final String[] args) {
        final AdaptadorNotificacionCliente notificador = new AdaptadorNotificacionCliente();
        final AdaptadorClienteUdp udp = new AdaptadorClienteUdp(new CanalUdp(3000), new ProtocoloUdpMapper());
        final ExecutorService executor = Executors.newSingleThreadExecutor(tarea -> {
            final Thread hilo = new Thread(tarea, "cliente-udp");
            hilo.setDaemon(true);
            return hilo;
        });
        final ClienteImcService servicio = new ClienteImcService(
                udp, notificador, new ClienteMapper(), executor);
        SwingUtilities.invokeLater(() -> {
            final ClienteFrame frame = new ClienteFrame(servicio, servicio);
            notificador.registrar(frame);
            frame.setVisible(true);
        });
    }
}
