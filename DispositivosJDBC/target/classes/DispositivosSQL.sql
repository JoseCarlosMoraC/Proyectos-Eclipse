CREATE DATABASE IF NOT EXISTS DispositivosBD;
USE DispositivosBD;

CREATE TABLE dispositivos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    categoria VARCHAR(50),
    precio DOUBLE,
    stock INT
);

CREATE TABLE especificaciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(200),
    dispositivo_id INT,
    FOREIGN KEY (dispositivo_id) REFERENCES dispositivos(id)
);

CREATE TABLE valoraciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario VARCHAR(100),
    comentario VARCHAR(200),
    puntuacion INT,
    dispositivo_id INT,
    FOREIGN KEY (dispositivo_id) REFERENCES dispositivos(id)
);

-- Datos de ejemplo
INSERT INTO dispositivos (nombre, categoria, precio, stock) VALUES
('Router TP-Link', 'Red', 89.99, 10),
('Portátil Dell', 'Informática', 799.99, 5),
('Smartphone Samsung', 'Telefonía', 699.99, 8);

INSERT INTO especificaciones (descripcion, dispositivo_id) VALUES
('WiFi 6', 1),
('Cobertura 300m', 1),
('16GB RAM', 2),
('SSD 512GB', 2);

INSERT INTO valoraciones (usuario, comentario, puntuacion, dispositivo_id) VALUES
('Ana', 'Muy buen router', 5, 1),
('Luis', 'Buen precio', 4, 1),
('Marta', 'Excelente portátil', 5, 2);
