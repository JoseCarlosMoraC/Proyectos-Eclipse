USE coachx;

INSERT INTO equipo (nombre, categoria, nombre_entrenador, activo) VALUES
    ('FC Barcelona B',      'Segunda Division',   'Rafa Marquez',   1),
    ('Real Madrid Juvenil', 'Division de Honor',  'Alvaro Arbeloa', 1),
    ('Sevilla FC Filial',   'Segunda Division B', 'Pedro Morilla',  1);

INSERT INTO jugador (nombre, apellidos, fecha_nacimiento, dorsal, posicion, activo, equipo_id) VALUES
    ('Carlos',   'Perez Lopez',    '2001-03-15', 1,  'Portero',        1, 1),
    ('Miguel',   'Garcia Ruiz',    '2000-07-22', 4,  'Defensa',        1, 1),
    ('Adrian',   'Sanchez Torres', '2002-11-08', 8,  'Centrocampista', 1, 1),
    ('Pablo',    'Fernandez Gil',  '2001-05-30', 9,  'Delantero',      1, 1),
    ('Sergio',   'Martinez Vega',  '2000-01-19', 11, 'Delantero',      1, 1),
    ('Javier',   'Lopez Mora',     '2003-09-04', 1,  'Portero',        1, 2),
    ('Alvaro',   'Diaz Castro',    '2002-04-17', 5,  'Defensa',        1, 2),
    ('Ivan',     'Romero Blanco',  '2001-12-25', 6,  'Centrocampista', 1, 2),
    ('Ruben',    'Navarro Leal',   '2003-06-11', 10, 'Delantero',      1, 2),
    ('Antonio',  'Jimenez Reyes',  '2000-08-03', 7,  'Delantero',      0, 3),
    ('Fernando', 'Moreno Prado',   '2002-02-28', 3,  'Defensa',        1, 3),
    ('Daniel',   'Herrera Santos', '2001-10-14', 8,  'Centrocampista', 1, 3);

INSERT INTO partido (fecha, lugar, goles_a_favor, goles_en_contra, resultado, equipo_id) VALUES
    ('2025-09-14 17:00:00', 'Estadio Johan Cruyff',       2, 0, 'VICTORIA', 1),
    ('2025-09-28 19:00:00', 'Estadio Alfredo Di Stefano', 1, 1, 'EMPATE',   1),
    ('2025-10-12 18:00:00', 'Estadio Johan Cruyff',       0, 2, 'DERROTA',  1),
    ('2025-09-21 17:00:00', 'Estadio Alfredo Di Stefano', 3, 1, 'VICTORIA', 2),
    ('2025-10-05 16:00:00', 'Estadio Municipal',          1, 2, 'DERROTA',  2),
    ('2025-10-19 18:30:00', 'Estadio Alfredo Di Stefano', 2, 2, 'EMPATE',   2),
    ('2025-09-20 19:00:00', 'Estadio Sanchez-Pizjuan',    1, 0, 'VICTORIA', 3),
    ('2025-10-04 17:00:00', 'Estadio Municipal Norte',    0, 0, 'EMPATE',   3),
    ('2025-10-18 16:00:00', 'Estadio Sanchez-Pizjuan',    2, 3, 'DERROTA',  3);