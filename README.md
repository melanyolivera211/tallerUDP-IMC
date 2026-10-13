# Taller UDP - Cálculo de IMC con Arquitectura Hexagonal

Proyecto para la asignatura de **Sistemas Distribuidos** que implementa una solución Cliente/Servidor bajo el protocolo **UDP/IP** para el cálculo del Índice de Masa Corporal (IMC), aplicando los principios de la **Arquitectura Hexagonal (Ports and Adapters)**.

## Estructura del Proyecto

```text
tallerUDP-IMC/
├── cliente/                  # Aplicación cliente Swing y socket UDP
│   ├── pom.xml
│   └── src/
│       ├── main/java/com/distribuidos/...
│       └── test/java/com/distribuidos/...
└── servidor/                 # Aplicación servidor con listener UDP y GUI
    ├── pom.xml
    └── src/
        ├── main/java/com/distribuidos/...
        └── test/java/com/distribuidos/...
```

## Arquitectura Hexagonal (Puertos y Adaptadores)

Ambos proyectos dividen sus responsabilidades en cuatro capas principales:
1. **Dominio**: Reglas de negocio puras, modelos y definición de puertos de salida (interfaces). Sin acoplamiento a frameworks o detalles técnicos.
2. **Aplicación**: Casos de uso (servicios de aplicación) y puertos de entrada (interfaces). Orquestan el flujo de datos.
3. **Adaptadores**: Implementación de los puertos de salida para la infraestructura tecnológica (sockets UDP `DatagramSocket` / `DatagramPacket`, notificaciones).
4. **Entrypoints / Infraestructura**: Puntos de entrada al sistema (listeners UDP de red, interfaces gráficas de usuario con Swing y clase `Main` como *Composition Root*).

## Protocolo de Comunicación UDP

La comunicación se realiza mediante datagramas con tramas de texto separadas por punto y coma (`;`):

- **Conexión**:
  - `Cliente -> Servidor`: `CONECTAR`
  - `Servidor -> Cliente`: `CONECTADO_OK;<mensaje>`
- **Cálculo de IMC**:
  - `Cliente -> Servidor`: `CALCULAR;<peso>;<altura>`
  - `Servidor -> Cliente`: `OK_CALCULO;<imc>;<clasificacion>;<recomendaciones>`
- **Desconexión**:
  - `Cliente -> Servidor`: `DESCONECTAR`
  - `Servidor -> Cliente`: `DESCONECTADO_OK;<mensaje>`

## Cómo Ejecutar

### 1. Iniciar el Servidor
```bash
cd servidor
mvn compile exec:java -Dexec.mainClass="com.distribuidos.Main"
```
En la ventana del servidor, configure el puerto (ej. `9090`) y haga clic en **Iniciar Servidor**.

### 2. Iniciar el Cliente
```bash
cd cliente
mvn compile exec:java -Dexec.mainClass="com.distribuidos.Main"
```
En la ventana del cliente:
1. Ingrese la IP (`localhost` o `127.0.0.1`) y el puerto configurado en el servidor (`9090`).
2. Haga clic en **Conectar**.
3. Ingrese el peso (kg) y la altura (m).
4. Haga clic en **Calcular** para obtener el resultado en tiempo real.
