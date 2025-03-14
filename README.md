# VoizAlarm
Proyecto UX de Alarma Móvil - Curso: MISW4302 - UX-mejoramiento de la experiencia de usuario.

VoizAlarm es una aplicación de Android que permite a los usuarios crear alarmas median el uso de voz (core de la apliación), ademas caracteristicas de gestión de alarmas. Adicionalmente se pretende que los mensajes de voz sean un motivador para los usuarios.

## Estudiante

| Nombre                   | Correo                         |
| ------------------------ | ------------------------------ |
| Wilder López             | w.lopezm@uniandes.edu.co       |
| Sergio Mena Zamora       | s.menaz@uniandes.edu.co        |


## Tabla de Contenidos
- [Introducción](#introducción)
  - [Prerrequisitos](#prerrequisitos)
  - [Configuración del Proyecto](#configuración-del-proyecto)
  - [Clonación del Repositorio](#clonación-del-repositorio)
- [Estructura del Proyecto](#estructura-del-proyecto)
  - [Descripción de los Módulos](#descripción-de-los-módulos)
- [Uso](#uso)
  - [Ejecutar la Aplicación en un Emulador](#ejecutar-la-aplicación-en-un-emulador)
  - [Ejecutar la Aplicación en un Dispositivo Físico](#ejecutar-la-aplicación-en-un-dispositivo-físico)
- [Generación del APK](#generación-del-apk)

---

## Introducción

### Prerrequisitos

- **Android Studio Arctic Fox o posterior**: Se recomienda usar la última versión estable.
- **Java 11+**: El proyecto requiere Java 11 o superior.
- **SDK de Android**: Asegúrate de que el SDK de Android esté instalado.
- **Dispositivo con Android 7.0 (API 24) o superior**: La aplicación es compatible con Android Nougat (7.0, API 24) en adelante.
- **Se recomienda Android 10+ (API 29) para una mejor experiencia.**
- **Emulador de Android (opcional)**: Se recomienda crear un emulador con Android 10 (API 29) o superior para asegurar compatibilidad.


### Configuración del Proyecto

1. **Descargar e instalar Android Studio**: Si aún no está instalado, puede descargarlo desde [aquí](https://developer.android.com/studio).
2. **Instalar el Kit de Desarrollo de Java (JDK)**: Asegúrese de que Java 11 o superior esté instalado. Puedes verificar ejecutando:

   ```bash
   java -version

### URL del repositorio

https://github.com/wilderlopezm2024/voizAlarm-movil


## Estructura del proyecto

El proyecto sigue una arquitectura MVVM que divide responsabilidades en diferentes capas para facilitar el mantenimiento y las pruebas.

### Descripción de los Módulos

- MainActivity: Contiene el BottomNavigationView y gestiona la navegación entre los fragmentos principales (Home, Dashboard, Notifications).
- Fragments:
  -   EncendidoFragment: Muestra el dialogo con la alarma activada
  -   CrearAlarmaFragment: Muestra la creación de la alarma con el uso de la grabación y transcripción de voz. Uso de SpeechRecognizer.
- La app incluye el uso de animaciones sencillas


## Uso

### Ejecutar la Aplicación en un Emulador

1. Abrir el Administrador de AVD: En Android Studio, ve a Tools > AVD Manager.
2. Crear un Dispositivo Virtual: Selecciona un modelo de dispositivo (por ejemplo, Medium Phone API 35).
3. Seleccionar Imagen del Sistema: Elige una imagen del sistema (se recomienda Android 10 o superior).
4. Ejecutar el Emulador: Una vez configurado, inicia el emulador y presiona Run en Android Studio para instalar y ejecutar la aplicación.
   
### Ejecutar la Aplicación en un Dispositivo Físico

1. Habilitar Depuración USB: Ve a Ajustes > Opciones de desarrollador > Depuración USB en tu dispositivo.
2. Conectar el Dispositivo: Conecta el dispositivo a tu computadora.
3. Ejecutar la Aplicación: En Android Studio, selecciona tu dispositivo en el menú desplegable y presiona Run (parte superior derecha en Android Studio).

## Generación del APK

1. Construir el APK: En Android Studio, ve a Build > Build Bundle(s) / APK(s) > Build APK(s).
2. Después de que se complete la construcción, encuentra el archivo APK en:
  - Versión de depuración: app/build/outputs/apk/debug/app-debug.apk
  - Versión de lanzamiento: app/build/outputs/apk/release/app-release.apk (requiere firma).
3. Instalar APK: Transfiere el APK a tu dispositivo Android e instálalo directamente.


