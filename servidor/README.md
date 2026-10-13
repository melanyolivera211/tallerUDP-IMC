# Servidor IMC - Arquitectura Hexagonal (UDP/IP)

Servidor en Java para el cálculo del Índice de Masa Corporal (IMC) basado en el protocolo **UDP/IP** y diseñado siguiendo los principios de la **Arquitectura Hexagonal (Puertos y Adaptadores)** y **SOLID**.

## Arquitectura

El proyecto está estructurado en capas concéntricas con inversión de dependencias:

- **Dominio (`com.distribuidos.imc.dominio`)**:
  - Modelos y Value Objects (`Calculo`, `Resultado`, `Peso`, `Altura`, `Destinatario`, `PuertoRed`).
  - Lógica de cálculo y clasificación del IMC.
  - Puertos de salida (`ControladorServidorRedPort`, `PuertoNotificacionEvento`, `PuertoSalidaRed`).
  - Sin dependencias de frameworks ni librerías externas.

- **Aplicación (`com.distribuidos.imc.aplicacion`)**:
  - Casos de uso / Servicios: `ProcesarPeticionUdpService`, `GestionarServidorService`, `CalcularImcService`.
  - Puertos de entrada (interfaces de entrada para los casos de uso).
  - Comandos y DTOs para el transporte interno de datos.
  - Mappers para transformar entre modelos y comandos.

- **Adaptadores (`com.distribuidos.imc.adaptadores`)**:
  - Adaptador de Red UDP: `CanalUdp`, `AdaptadorSalidaUdp`, `AdaptadorControlServidorRed`.
  - Mapeador de protocolo de red: `UdpNetworkMapper`.
  - Adaptador de Notificación de eventos: `AdaptadorNotificacionEvento`.

- **Entrypoints (`com.distribuidos.imc.entrypoint`)**:
  - Receptor UDP (`ReceptorPeticionesUdp`): Hilo en segundo plano que escucha peticiones en el socket UDP.
  - Interfaz Gráfica (`ServidorFrame`): Vista Swing para control del servicio y monitoreo en tiempo real.
  - `Main`: Composition Root encargado de instanciar e inyectar las dependencias.

## Protocolo de Comunicación UDP

El servidor atiende los siguientes comandos en formato texto:

1. **Conexión**:
   - Petición: `CONECTAR`
   - Respuesta: `CONECTADO_OK;<mensaje>`
2. **Cálculo de IMC**:
   - Petición: `CALCULAR;<peso>;<altura>` (ej. `CALCULAR;70.5;1.75`)
   - Respuesta: `OK_CALCULO;<valorImc>;<clasificacion>;<recomendaciones>`
3. **Desconexión**:
   - Petición: `DESCONECTAR`
   - Respuesta: `DESCONECTADO_OK;<mensaje>`
4. **Error**:
   - Respuesta: `ERROR;<mensaje_error>`

## Requisitos y Ejecución

- Java 17 o superior
- Maven 3.8+

### Compilar y Ejecutar Pruebas
```bash
mvn clean test
```

### Iniciar el Servidor
```bash
mvn compile exec:java -Dexec.mainClass="com.distribuidos.Main"
```
