# Cliente IMC - Arquitectura Hexagonal (UDP/IP)

Cliente en Java para el cálculo del Índice de Masa Corporal (IMC) que se comunica con el servidor mediante el protocolo **UDP/IP**, estructurado con **Arquitectura Hexagonal (Puertos y Adaptadores)**.

## Arquitectura

- **Dominio (`com.distribuidos.imc.dominio`)**:
  - Modelos y Value Objects (`DatosImc`, `ResultadoImc`, `EventoCliente`, `Peso`, `Altura`, `DestinoServidor`).
  - Puertos de salida (`ClienteUdpPort`, `PuertoNotificacionCliente`).

- **Aplicación (`com.distribuidos.imc.aplicacion`)**:
  - Casos de uso / Servicio (`ClienteImcService`): Coordina la conexión, desconexión y cálculo del IMC en un pool de ejecución asíncrono.
  - Puertos de entrada (`CalcularImcInputPort`, `GestionarConexionInputPort`).
  - Comandos (`CalcularImcCommand`, `ConectarCommand`) y Mappers (`ClienteMapper`).

- **Adaptadores (`com.distribuidos.imc.adaptadores`)**:
  - Red UDP (`AdaptadorClienteUdp`, `CanalUdp`, `ProtocoloUdpMapper`): Gestión del socket DatagramSocket, serialización de paquetes UDP y timeouts.
  - Notificación (`AdaptadorNotificacionCliente`): Despacho de eventos de conexión y resultados hacia la GUI.

- **Entrypoint (`com.distribuidos.imc.entrypoint`)**:
  - Interfaz Gráfica (`ClienteFrame`): Vista Swing para ingresar servidor, puerto, peso y altura, y visualizar el estado y resultados.
  - `Main`: Composition Root para la configuración de adaptadores y GUI.

## Requisitos y Ejecución

- Java 17 o superior
- Maven 3.8+

### Compilar y Ejecutar Pruebas
```bash
mvn clean test
```

### Iniciar el Cliente
```bash
mvn compile exec:java -Dexec.mainClass="com.distribuidos.Main"
```
