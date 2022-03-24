INSERT INTO motos (marca, modelo, potencia, precio) VALUES ('Honda','CBR5F',500,12000);
INSERT INTO motos (marca, modelo, potencia, precio) VALUES ('KTM','Duke',125,6000);
INSERT INTO motos (marca, modelo, potencia, precio) VALUES ('Vespa','GTS',125,5000);
INSERT INTO motos (marca, modelo, potencia, precio) VALUES ('Suzuki','PRL',250,8800);
INSERT INTO motos (marca, modelo, potencia, precio) VALUES ('BMW','R',1200,35000);
INSERT INTO motos (marca, modelo, potencia, precio) VALUES ('Yamaha','Y12',750,15600);
INSERT INTO motos (marca, modelo, potencia, precio) VALUES ('Aprilia','APT',1200,45670);

INSERT INTO clientes (nombre, apellido, email, telefono, create_at, moto_id) VALUES('Jairo','Gonzalez','jairo@email.com',66666666,'2022-03-01',1);
INSERT INTO clientes (nombre, apellido, email, telefono, create_at, moto_id) VALUES('Daniel','Alvarez','daniel@email.com',55555555,'2022-03-02',2);
INSERT INTO clientes (nombre, apellido, email, telefono, create_at, moto_id) VALUES('Manuel','Arroyo','manuel@email.com',44444444,'2022-03-03',3);
INSERT INTO clientes (nombre, apellido, email, telefono, create_at, moto_id) VALUES('Jesus','Perez','jesus@email.com',33333333,'2022-03-02',6);
INSERT INTO clientes (nombre, apellido, email, telefono, create_at, moto_id) VALUES('Eva','Deva','eva@email.com',77777777,'2022-03-01',5);
INSERT INTO clientes (nombre, apellido, email, telefono, create_at, moto_id) VALUES('Maria','Rodriguez','maria@email.com',22222222,'2022-03-03',7);
INSERT INTO clientes (nombre, apellido, email, telefono, create_at, moto_id) VALUES('Rocio','Bernardo','rocio@email.com',88888888,'2022-03-01',3);
INSERT INTO clientes (nombre, apellido, email, telefono, create_at, moto_id) VALUES('Sara','Gonzalez','sara@email.com',11111111,'2022-03-01',1);
INSERT INTO clientes (nombre, apellido, email, telefono, create_at, moto_id) VALUES('Alex','Diez','alex@email.com',99999999,'2022-03-01',4);
INSERT INTO clientes (nombre, apellido, email, telefono, create_at, moto_id) VALUES('Irene','Castro','irene@email.com',10111213,'2022-03-01',6);

INSERT INTO usuarios (username,password,enabled) VALUES ('jairo', '$2a$10$pO.nraD5PPkeIg/TiaiI3.ydA081vVNgvFkNZlpFGKYHm0PJ22XTu', 1);
INSERT INTO usuarios (username,password,enabled) VALUES ('admin', '$2a$10$h3kT1Jm2jajKnDzQF0y3I.5D6NVgN7yBsSgEmUY4lOz1MwCT3HHCi', 1);

INSERT INTO roles (nombre) VALUES ('ROLE_USER');
INSERT INTO roles (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (1,1);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2,2);
INSERT INTO usuarios_roles (usuario_id, role_id) VALUES (2,1);






