# Proyecto1_SisDistribuidos

# Guía de Instalación y Ejecución de EmailSender

¡Bienvenido a **EmailSender**! Este proyecto consiste en un sistema para enviar correos electrónicos usando **Spring Boot** en el backend y **Vue.js** en el frontend. Aquí te mostramos cómo ejecutar la aplicación en tu máquina utilizando **Docker**.

## Requisitos

Antes de comenzar, asegúrate de tener los siguientes requisitos:

- **Docker Desktop** (instalado en tu máquina). Si aún no lo tienes, puedes descargarlo [aquí](https://www.docker.com/products/docker-desktop).
- Un sistema operativo compatible con Docker (Windows, macOS o Linux).

## Paso 1: Descargar los archivos

1. Haz clic en el siguiente enlace para descargar el archivo **`emailSender.zip`** que contiene todos los archivos necesarios:  
   [Descargar emailSender.zip](https://drive.google.com/file/d/1z-V9cMx__AqlXeY7MxWlVU2c9GMZLPLs/view?usp=sharing) Tener en cuenta que el correo desde el que se consulta el archivo debe ser del dominio ucentral.edu.co

2. Extrae el contenido del archivo ZIP en una carpeta de tu elección.

Dentro de la carpeta, encontrarás los siguientes archivos:
- **`emailSender.tar`**: Las imágenes de Docker para el backend y frontend.
- **`docker-compose.yml`**: El archivo de configuración para Docker Compose.

## Paso 2: Instalar Docker Desktop

Si no tienes Docker Desktop instalado:

1. Descarga Docker Desktop desde [este enlace](https://www.docker.com/products/docker-desktop).
2. Instala Docker Desktop siguiendo las instrucciones de la página oficial.
3. Una vez instalado, abre Docker Desktop para iniciar el servicio de Docker.

## Paso 3: Cargar las imágenes de Docker

1. Copia los archivos **`emailSender.tar`** y **`docker-compose.yml`** en una carpeta en tu computadora.
   
2. Abre una terminal o línea de comandos en esa carpeta. Para ello, en la mayoría de sistemas operativos, puedes hacer clic derecho en la carpeta y seleccionar **"Abrir terminal aquí"** o **"Abrir ventana de comandos aquí"**.

3. En la terminal, ejecuta el siguiente comando para cargar las imágenes de Docker:

   ```bash
   docker load -i emailSender.tar

## Paso 4: Levantar los contenedores con Docker Compose

1. En la misma terminal, ejecuta el siguiente comando para iniciar los contenedores en segundo plano:

   ```bash
   docker-compose up -d
2. Este comando descargará los contenedores y los pondrá en marcha. Los contenedores estarán corriendo en segundo plano.

## Paso 5: Acceder a la aplicación

1. Una vez que los contenedores estén en funcionamiento, abre tu navegador y accede a la siguiente URL para ver el frontend de la aplicación:

   ```bash
   http://localhost:8081
   
2. Deberías ver la interfaz de usuario de EmailSender donde podrás interactuar con la aplicación.

