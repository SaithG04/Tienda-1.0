/**
 * Author:  isai_
 * Created: 24 may. 2023
 */

-- Crear Owner

CREATE USER 'owner'@'localhost' IDENTIFIED WITH caching_sha2_password BY '';
GRANT ALL PRIVILEGES ON *.* TO 'owner'@'localhost' WITH GRANT OPTION;

-- Crear database

CREATE DATABASE 'almacen1_0' /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

-- Crear tabla usuarios

CREATE TABLE usuarios(
    id INT NOT NULL AUTO_INCREMENT,
    nombres VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    tipo_usuario VARCHAR(20) NOT NULL,
    telefono VARCHAR(20),
    correo VARCHAR(255),
    id_admin INT,
    fecha_registro TIMESTAMP,
    PRIMARY KEY (id),
    ADD CONSTRAINT fk_id_admin FOREIGN KEY (id_admin) REFERENCES usuarios(id),
    UNIQUE INDEX (username),
    UNIQUE INDEX (telefono),
    UNIQUE INDEX (correo)
);

ALTER TABLE usuarios CONVERT TO CHARACTER SET utf8 COLLATE utf8_bin;

-- Agregar primer administrador

INSERT INTO usuarios(nombres, apellidos, username, password, tipo_usuario, telefono, correo)
	VALUES ('NOMBRES', 'APELLIDOS', 'USERNAME','PASSWORD','RANGO','TELEFONO','CORREO');
    
 -- ALTER TABLE usuarios CONVERT TO CHARACTER SET utf8 COLLATE utf8_bin;
 
CREATE USER 'USER'@'SERVIDOR' IDENTIFIED BY 'PASSWORD'; -- User / Password / Servidor: '%' remote, 'localhost' local.
GRANT ALL PRIVILEGES ON * . * TO 'USER'@'SERVIDOR' WITH GRANT OPTION;

-- Crear tabla proveedores

CREATE TABLE proveedores(
	id INT NOT NULL AUTO_INCREMENT,
    razon_social VARCHAR(255) NOT NULL,
    ruc VARCHAR(11) NOT NULL,
    direccion VARCHAR(255),
    contacto VARCHAR(255),
    telefono VARCHAR(20),
    email VARCHAR(255),
    web VARCHAR(255),
    departamento VARCHAR(25) NOT NULL,
    id_user INT NOT NULL,
    fecha_registro TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX (razon_social),
    UNIQUE INDEX (ruc)
    
);
SELECT * FROM usuarios;
SELECT * from products;
SELECT p.name, p.marca, u.name as 'creador' from products p right join usuarios u on p.create_by = u.id;