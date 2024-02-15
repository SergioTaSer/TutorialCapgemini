INSERT INTO category(name) VALUES ('Eurogames');
INSERT INTO category(name) VALUES ('Ameritrash');
INSERT INTO category(name) VALUES ('Familiar');

INSERT INTO author(name, nationality) VALUES ('Alan R. Moon', 'US');
INSERT INTO author(name, nationality) VALUES ('Vital Lacerda', 'PT');
INSERT INTO author(name, nationality) VALUES ('Simone Luciani', 'IT');
INSERT INTO author(name, nationality) VALUES ('Perepau Llistosella', 'ES');
INSERT INTO author(name, nationality) VALUES ('Michael Kiesling', 'DE');
INSERT INTO author(name, nationality) VALUES ('Phil Walker-Harding', 'US');

INSERT INTO game(title, age, category_id, author_id) VALUES ('On Mars', '14', 1, 2);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Aventureros al tren', '8', 3, 1);
INSERT INTO game(title, age, category_id, author_id) VALUES ('1920: Wall Street', '12', 1, 4);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Barrage', '14', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Los viajes de Marco Polo', '12', 1, 3);
INSERT INTO game(title, age, category_id, author_id) VALUES ('Azul', '8', 3, 5);

INSERT INTO cliente(name) VALUES ('Sergio');
INSERT INTO cliente(name) VALUES ('Juan');
INSERT INTO cliente(name) VALUES ('Pepe');
INSERT INTO cliente(name) VALUES ('Antonio');
INSERT INTO cliente(name) VALUES ('Julio');

INSERT INTO prestamo(game_id, client_id, fecha_prestamo, fecha_devolucion) VALUES (1, 1, '2024-02-12', '2024-02-19');
INSERT INTO prestamo(game_id, client_id, fecha_prestamo, fecha_devolucion) VALUES (2, 2, '2024-03-01', '2024-03-10');
INSERT INTO prestamo(game_id, client_id, fecha_prestamo, fecha_devolucion) VALUES (3, 3, '2024-04-15', '2024-04-22');
INSERT INTO prestamo(game_id, client_id, fecha_prestamo, fecha_devolucion) VALUES (4, 4, '2024-05-05', '2024-05-12');
INSERT INTO prestamo(game_id, client_id, fecha_prestamo, fecha_devolucion) VALUES (5, 5, '2024-06-20', '2024-06-27');


