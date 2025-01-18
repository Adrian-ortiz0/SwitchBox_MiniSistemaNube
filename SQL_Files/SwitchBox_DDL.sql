CREATE DATABASE IF NOT EXISTS switchBox;

\c switchBox;

CREATE TABLE IF NOT EXISTS cuentas (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    limite_espacio BIGINT NOT NULL
);

CREATE TABLE IF NOT EXISTS usuarios (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    espacio_usado BIGINT NOT NULL,
    cuenta_id INT,
    FOREIGN KEY (cuenta_id) REFERENCES cuentas(id)
);

CREATE TABLE IF NOT EXISTS archivos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    tipo VARCHAR(10) CHECK (tipo IN ('PDF', 'JPG', 'PNG', 'DOCX', 'PPT', 'MP3', 'AAC', 'MP4', 'ZIP', 'RAR', 'WAV', 'MOV', 'XLSX')),
    fecha_subida DATE NOT NULL,
    tamaño INT NOT NULL,
    usuario_id INT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

CREATE TABLE IF NOT EXISTS carpetas (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    tamaño INT NOT NULL DEFAULT 0,
    usuario_id INT,
    carpeta_padre_id INT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (carpeta_padre_id) REFERENCES carpetas(id)
);

CREATE TABLE IF NOT EXISTS archivo_carpeta (
    id SERIAL PRIMARY KEY,
    archivo_id INT NOT NULL,
    carpeta_id INT NOT NULL,
    FOREIGN KEY (archivo_id) REFERENCES archivos(id),
    FOREIGN KEY (carpeta_id) REFERENCES carpetas(id)
);

CREATE TABLE IF NOT EXISTS compartir_archivo (
    id SERIAL PRIMARY KEY,
    archivo_id INT NOT NULL,
    usuario_origen_id INT NOT NULL,
    usuario_destino_id INT NOT NULL,
    fecha_compartido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (archivo_id) REFERENCES archivos(id),
    FOREIGN KEY (usuario_origen_id) REFERENCES usuarios(id),
    FOREIGN KEY (usuario_destino_id) REFERENCES usuarios(id)
);

INSERT INTO cuentas (nombre, limite_espacio) 
VALUES
  ('Gratuita', 6000000000),
  ('Premium', 12000000000);
