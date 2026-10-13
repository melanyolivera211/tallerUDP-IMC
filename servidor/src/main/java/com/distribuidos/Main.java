package com.distribuidos;

import com.distribuidos.imc.adaptadores.red.AdaptadorControlServidorRed;
import com.distribuidos.imc.adaptadores.red.AdaptadorNotificacionEvento;
import com.distribuidos.imc.adaptadores.red.AdaptadorSalidaUdp;
import com.distribuidos.imc.adaptadores.red.CanalUdp;
import com.distribuidos.imc.adaptadores.red.mapper.UdpNetworkMapper;
import com.distribuidos.imc.aplicacion.mapper.CalculoMapper;
import com.distribuidos.imc.aplicacion.mapper.PeticionMapper;
import com.distribuidos.imc.aplicacion.puertos.entrada.GestionarServidorInputPort;
import com.distribuidos.imc.aplicacion.puertos.entrada.ProcesarPeticionUdpInputPort;
import com.distribuidos.imc.aplicacion.servicios.GestionarServidorService;
import com.distribuidos.imc.aplicacion.servicios.ProcesarPeticionUdpService;
import com.distribuidos.imc.dominio.puertos.salida.ControladorServidorRedPort;
import com.distribuidos.imc.dominio.puertos.salida.PuertoSalidaRed;
import com.distribuidos.imc.entrypoint.gui.ServidorFrame;
import com.distribuidos.imc.entrypoint.udp.ReceptorPeticionesUdp;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Punto de entrada del programa (Composition Root). Responsabilidad única:
 * Ensamblar las
 * dependencias según los principios SOLID y la Arquitectura Hexagonal, y lanzar
 * la interfaz de
 * usuario.
 */
public class Main {

  private static final System.Logger LOG = System.getLogger(Main.class.getName());

  private Main() {
    // Evita instanciación – clase de arranque estática
  }

  public static void main(final String[] args) {
    aplicarLookAndFeel();

    // 1. Infraestructura de red compartida
    final CanalUdp canalUdp = new CanalUdp();
    final AdaptadorNotificacionEvento notificador = new AdaptadorNotificacionEvento();
    final UdpNetworkMapper redMapper = new UdpNetworkMapper();
    final PuertoSalidaRed puertoSalidaRed = new AdaptadorSalidaUdp(canalUdp, redMapper, notificador);

    // 2. Mappers de aplicación
    final PeticionMapper peticionMapper = new PeticionMapper();
    final CalculoMapper calculoMapper = new CalculoMapper();

    // 3. Casos de uso (servicios de aplicación)
    final ProcesarPeticionUdpInputPort procesarPeticionPort = new ProcesarPeticionUdpService(puertoSalidaRed,
        notificador, peticionMapper, calculoMapper);

    // 4. Entrypoints
    final ReceptorPeticionesUdp receptorUdp = new ReceptorPeticionesUdp(canalUdp, procesarPeticionPort, notificador);

    final ControladorServidorRedPort controladorRed = new AdaptadorControlServidorRed(canalUdp, receptorUdp::iniciar,
        receptorUdp::detener);

    final GestionarServidorInputPort gestionarServidorPort = new GestionarServidorService(controladorRed, notificador);

    // 5. Lanzar GUI en el Event Dispatch Thread de Swing
    SwingUtilities.invokeLater(
        () -> {
          final ServidorFrame frame = new ServidorFrame(gestionarServidorPort);
          notificador.registrarObservador(frame);
          frame.setVisible(true);
        });
  }

  private static void aplicarLookAndFeel() {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (final ClassNotFoundException
        | InstantiationException
        | IllegalAccessException
        | UnsupportedLookAndFeelException excepcion) {
      LOG.log(System.Logger.Level.DEBUG,
          "No fue posible aplicar la apariencia del sistema; se usará la predeterminada.", excepcion);
    }
  }
}
