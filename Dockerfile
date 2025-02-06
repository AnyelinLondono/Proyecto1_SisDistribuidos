# Imagen base con Node.js 18
FROM node:18

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar los archivos del frontend al contenedor
COPY . .

# Instalar dependencias y construir la aplicación
RUN npm install && npm run build

# Instalar el servidor para servir archivos estáticos
RUN npm install -g serve

# Exponer el puerto del frontend
EXPOSE 8081

# Ejecutar la aplicación en el puerto 8081
CMD ["serve", "-s", "dist", "-l", "8081"]
