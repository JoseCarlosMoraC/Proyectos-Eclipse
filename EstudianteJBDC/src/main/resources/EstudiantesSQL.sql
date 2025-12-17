CREATE DATABASE IF NOT EXISTS EstudiantesDB;
USE EstudiantesDB;

-- Tabla Estudiantes
CREATE TABLE IF NOT EXISTS estudiantes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    fechaNacimiento DATE
);

-- Tabla Direcciones
CREATE TABLE IF NOT EXISTS direcciones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id INT NOT NULL,
    calle VARCHAR(200),
    ciudad VARCHAR(100),
    codigoPostal VARCHAR(20),
    FOREIGN KEY (estudiante_id) REFERENCES estudiantes(id)
);

-- Tabla Scores
CREATE TABLE IF NOT EXISTS scores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    estudiante_id INT NOT NULL,
    tipo VARCHAR(50),
    puntuacion INT,
    FOREIGN KEY (estudiante_id) REFERENCES estudiantes(id)
);

-- Insertar datos de ejemplo
INSERT INTO estudiantes (nombre, email, fechaNacimiento) VALUES
('Ana', 'ana@mail.com', '2000-01-15'),
('Luis', 'luis@mail.com', '1999-05-10'),
('Marta', 'marta@mail.com', '2001-08-22');

INSERT INTO direcciones (estudiante_id, calle, ciudad, codigoPostal) VALUES
(1, 'Calle Falsa 123', 'Sevilla', '41001'),
(2, 'Avenida Real 45', 'Madrid', '28013'),
(3, 'Plaza Central 9', 'Sevilla', '41002');

INSERT INTO scores (estudiante_id, tipo, puntuacion) VALUES
(1, 'examen', 8),
(1, 'practica', 7),
(2, 'examen', 5),
(2, 'practica', 6),
(3, 'examen', 9),
(3, 'practica', 10);
