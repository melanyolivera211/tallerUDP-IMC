package com.distribuidos.imc.dominio.puertos.salida;

import com.distribuidos.imc.dominio.enums.EstadoServidor;
import com.distribuidos.imc.dominio.modelos.EventoServidor;

/**
 * Puerto de salida para la emisión de eventos y cambios de estado del servidor
 * hacia observadores
 * externos (como interfaces gráficas o sistemas de logging). Recibe únicamente
 * objetos del dominio.
 */
public interface PuertoNotificacionEvento {
  void notificarEvento(EventoServidor evento);

  void notificarCambioEstado(EstadoServidor nuevoEstado, int puerto);
}
