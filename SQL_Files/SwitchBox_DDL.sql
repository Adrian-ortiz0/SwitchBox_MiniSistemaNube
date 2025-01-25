
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
    tipo VARCHAR(100) NOT NULL,
    fecha_subida DATE NOT NULL,
    tamaño INT NOT NULL,
    usuario_id INT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);


CREATE TABLE IF NOT EXISTS carpetas (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    usuario_id INT,
    carpeta_padre_id INT,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (carpeta_padre_id) REFERENCES carpetas(id)
);

CREATE INDEX IF NOT EXISTS idx_carpeta_padre_id ON carpetas(carpeta_padre_id);

CREATE TABLE IF NOT EXISTS archivo_carpeta (
    id SERIAL PRIMARY KEY,
    archivo_id INT NOT NULL,
    carpeta_id INT NOT NULL,
    FOREIGN KEY (archivo_id) REFERENCES archivos(id),
    FOREIGN KEY (carpeta_id) REFERENCES carpetas(id)
);

select * from archivo_carpeta;
select * from archivos;
select * from carpetas;
select * from usuarios;

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

insert into cuentas (nombre, limite_espacio) values
('Gratuita', 9000000000),
('Premium', 12000000000)