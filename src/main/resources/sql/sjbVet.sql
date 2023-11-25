DROP DATABASE IF EXISTS veterinariasjbdb;

CREATE DATABASE `veterinariaSJBdb` 
/*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

/*Tablas*/

use `veterinariaSJBdb`  ;

CREATE TABLE veterinariasjbdb.usuarios (
	id_usuario INT auto_increment NOT NULL,
	nombre_usuario varchar(100) NOT NULL,
	usuario varchar(100) NOT NULL,
	clave varchar(100) NOT NULL,
	CONSTRAINT usuarios_pk PRIMARY KEY (id_usuario)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE veterinariaSJBdb.clientes (
	id_cliente INT auto_increment NOT NULL,
	nombres_cliente varchar(100) NOT NULL,
	telefono_cliente varchar(20) NOT NULL,
	email_cliente varchar(100) NOT NULL,
	direcccion_cliente varchar(100) NULL,
	estado_cliente BIT(1) NOT NULL,
	CONSTRAINT clientes_pk PRIMARY KEY (id_cliente),
	CONSTRAINT clientes_un UNIQUE KEY (telefono_cliente,email_cliente)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE veterinariaSJBdb.mascotas (
	id_mascota INT auto_increment NOT NULL,
	nombre_mascota varchar(100) NOT NULL,
	especie varchar(40) NOT NULL,
	raza varchar(60) NOT NULL,
	edad INT NOT NULL,
	peso DECIMAL(5,2) NOT NULL,
	estado_mascota BIT(1) NOT NULL,
	clientes_id INT NOT NULL,
	CONSTRAINT mascotas_pk PRIMARY KEY (id_mascota),
	CONSTRAINT mascotas_check CHECK (edad>0 AND peso>0.0),
	CONSTRAINT mascotas_FK FOREIGN KEY (clientes_id) REFERENCES veterinariaSJBdb.clientes(id_cliente)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE veterinariasjbdb.servicios_tipo (
	id_servicio INT auto_increment NOT NULL,
	nombre_servicio varchar(100) NULL,
	CONSTRAINT servicios_tipo_pk PRIMARY KEY (id_servicio)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `citas` (
  `id_cita` int NOT NULL AUTO_INCREMENT,
  `fecha_cita` date NOT NULL,
  `hora_cita` time NOT NULL,
  `descripcion_cita` varchar(200),
  `mascotas_id` int NOT NULL,
  `clientes_id` int NOT NULL,
  `servicios_tipo` int NOT NULL,
 `estado_cita` BIT(1) NOT NULL,
  PRIMARY KEY (`id_cita`),
  KEY `citas_FK` (`mascotas_id`),
  KEY `citas_FK_1` (`clientes_id`),
  KEY `citas_FK_2` (`servicios_tipo`),
  CONSTRAINT `citas_FK` FOREIGN KEY (`mascotas_id`) REFERENCES `mascotas` (`id_mascota`),
  CONSTRAINT `citas_FK_1` FOREIGN KEY (`clientes_id`) REFERENCES `clientes` (`id_cliente`),
  CONSTRAINT `citas_FK_2` FOREIGN KEY (`servicios_tipo`) REFERENCES `servicios_tipo` (`id_servicio`),
  CONSTRAINT `citas_check` CHECK ((`fecha_cita` > _utf8mb4'2020-01-01'))
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE veterinariasjbdb.cargos (
	id_cargo INT auto_increment NOT NULL,
	nombre_cargo varchar(20) NOT NULL,
	CONSTRAINT cargos_pk PRIMARY KEY (id_cargo)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE veterinariasjbdb.turnos (
	id_turno INT auto_increment NOT NULL,
	nombre_turno varchar(20) NOT NULL,
	CONSTRAINT turnos_pk PRIMARY KEY (id_turno)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE veterinariasjbdb.trabajadores (
	id_trabajador INT auto_increment NOT NULL,
	nombre_trabajador varchar(100) NOT NULL,
	cargos_id INT NOT NULL,
	turnos_id INT NOT NULL,
	CONSTRAINT trabajadores_pk PRIMARY KEY (id_trabajador),
	CONSTRAINT trabajadores_FK FOREIGN KEY (cargos_id) REFERENCES veterinariasjbdb.cargos(id_cargo),
	CONSTRAINT trabajadores_FK_1 FOREIGN KEY (turnos_id) REFERENCES veterinariasjbdb.turnos(id_turno)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;

/*iNVENTARIO*/

CREATE TABLE veterinariasjbdb.productos (
	id_producto INT auto_increment NOT NULL,
	nombre_producto varchar(100) NOT NULL,
	descripcion_producto varchar(255),
	precio_producto DECIMAL(5,2) NOT NULL,
	stock INT NOT NULL,
	estado_producto BIT(1) NOT NULL,
	CONSTRAINT productos_pk PRIMARY KEY (id_producto),
	CONSTRAINT productos_check CHECK (precio_producto>0.0 AND stock>=0)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_0900_ai_ci;



/*Insert*/
INSERT INTO veterinariasjbdb.usuarios
(id_usuario, nombre_usuario, usuario, clave)
VALUES(1, 'Admin', 'admin', 'admin');


INSERT INTO veterinariasjbdb.clientes
(id_cliente, nombres_cliente, telefono_cliente, email_cliente, direcccion_cliente, estado_cliente)
VALUES(1, 'María Rodríguez', '987654321', 'maria.rodriguez@email.com', 'Calle Ficticia, Nº 123, Ciudad Los Palacios',1);
INSERT INTO veterinariasjbdb.clientes
(id_cliente, nombres_cliente, telefono_cliente, email_cliente, direcccion_cliente, estado_cliente)
VALUES(2, ' Juan Pérez', '987654322', 'juan.perez@example.com', 'Avenida Ficticia, Nº 456, Ciudad Real',1);
INSERT INTO veterinariasjbdb.clientes
(id_cliente, nombres_cliente, telefono_cliente, email_cliente, direcccion_cliente, estado_cliente)
VALUES(3, ' Xavier Salazar', '987456478', 'xavi123@gmail.com', 'Jiron los laureles, Nº 123, Ciudad Verde',1);
INSERT INTO veterinariasjbdb.clientes
(id_cliente, nombres_cliente, telefono_cliente, email_cliente, direcccion_cliente, estado_cliente)
VALUES(4, 'Valentina Rojas', '956258147', 'vale.r12@gmail.com', 'Calle los ruisenores 123, Plaza Roja',1);

INSERT INTO veterinariasjbdb.mascotas
(id_mascota, nombre_mascota, especie, raza, edad, peso, estado_mascota, clientes_id)
VALUES(1, 'Luna', 'Perro', 'Labrado Retriever', 36, 25.00,1, 2);
INSERT INTO veterinariasjbdb.mascotas
(id_mascota, nombre_mascota, especie, raza, edad, peso, estado_mascota, clientes_id)
VALUES(2, 'Simba', 'Gato', 'Naranja', 24, 7.00,1, 4);
INSERT INTO veterinariasjbdb.mascotas
(id_mascota, nombre_mascota, especie, raza, edad, peso, estado_mascota, clientes_id)
VALUES(3, 'Rocky', 'Perro', 'Bulldog', 48, 12.20,1, 2);
INSERT INTO veterinariasjbdb.mascotas
(id_mascota, nombre_mascota, especie, raza, edad, peso, estado_mascota, clientes_id)
VALUES(4, 'Pelusa', 'Gato', 'Persa', 60, 6.50,1, 3);
INSERT INTO veterinariasjbdb.mascotas
(id_mascota, nombre_mascota, especie, raza, edad, peso, estado_mascota, clientes_id)
VALUES(5, 'Max', 'Perro', 'Golden retriever', 24, 30.00,1, 2);
INSERT INTO veterinariasjbdb.mascotas
(id_mascota, nombre_mascota, especie, raza, edad, peso, estado_mascota, clientes_id)
VALUES(6, 'Bella', 'Perro', 'Chihuahaha', 12, 3.00,1, 1);
INSERT INTO veterinariasjbdb.mascotas
(id_mascota, nombre_mascota, especie, raza, edad, peso, estado_mascota, clientes_id)
VALUES(7, 'Simon', 'Conejo', 'Holandes', 60, 1.50,1, 3);
INSERT INTO veterinariasjbdb.mascotas
(id_mascota, nombre_mascota, especie, raza, edad, peso, estado_mascota, clientes_id)
VALUES(8, 'Thor', 'Perro', 'Pastor aleman', 50, 35.00,1, 2);
INSERT INTO veterinariasjbdb.mascotas
(id_mascota, nombre_mascota, especie, raza, edad, peso, estado_mascota, clientes_id)
VALUES(9, 'Lucas', 'Gato', 'Bombay', 49, 7.50,1, 4);


INSERT INTO veterinariasjbdb.servicios_tipo
(id_servicio, nombre_servicio)
VALUES(1, 'Consulta');
INSERT INTO veterinariasjbdb.servicios_tipo
(id_servicio, nombre_servicio)
VALUES(2, 'Servicio de aseo');


INSERT INTO veterinariasjbdb.citas
(id_cita, fecha_cita, hora_cita, descripcion_cita, mascotas_id, clientes_id, servicios_tipo, estado_cita)
VALUES(1, '2023-07-11','10:00', 'Cita para consulta para un perrito', 1, 2, 1, 0)
;
INSERT INTO veterinariasjbdb.citas
(id_cita, fecha_cita, hora_cita, descripcion_cita, mascotas_id, clientes_id, servicios_tipo, estado_cita)
VALUES(2, '2023-11-11','11:30', 'Cita para servicio de aseo para gatito', 2, 4, 2, 0);

INSERT INTO veterinariasjbdb.citas
(id_cita, fecha_cita, hora_cita, descripcion_cita, mascotas_id, clientes_id, servicios_tipo, estado_cita)
VALUES(3, '2023-12-20','13:30', 'consulta por fiebre de un gatito', 4, 3, 1, 1);


INSERT INTO veterinariasjbdb.cargos
(id_cargo, nombre_cargo)
VALUES(1, 'Veterinario');
INSERT INTO veterinariasjbdb.cargos
(id_cargo, nombre_cargo)
VALUES(2, 'Recepcionista');
INSERT INTO veterinariasjbdb.cargos
(id_cargo, nombre_cargo)
VALUES(3, 'Ayudante');

INSERT INTO veterinariasjbdb.turnos
(id_turno, nombre_turno)
VALUES(1, 'Dia');
INSERT INTO veterinariasjbdb.turnos
(id_turno, nombre_turno)
VALUES(2, 'Noche');

INSERT INTO veterinariasjbdb.trabajadores
(id_trabajador, nombre_trabajador, cargos_id, turnos_id)
VALUES(1, 'Sofía García Perez', 1, 1);
INSERT INTO veterinariasjbdb.trabajadores
(id_trabajador, nombre_trabajador, cargos_id, turnos_id)
VALUES(2, 'Martín López Perez', 1, 2);
INSERT INTO veterinariasjbdb.trabajadores
(id_trabajador, nombre_trabajador, cargos_id, turnos_id)
VALUES(3, 'Valentina Rodríguez Simoni', 2, 1);


INSERT INTO veterinariasjbdb.productos
(id_producto, nombre_producto, descripcion_producto, precio_producto, stock, estado_producto)
VALUES(1, 'Collar para perros', 'Collar ajustable para perros de todas las razas y tamaños. ', 45.0, 24, 1);
INSERT INTO veterinariasjbdb.productos
(id_producto, nombre_producto, descripcion_producto, precio_producto, stock, estado_producto)
VALUES(NULL, 'Rascador de Gatos', 'Rascador de sisal resistente con plataforma superior. Ideal para que los gatos se entretengan y afilen sus garras.', 65.0, 13, 1);
INSERT INTO veterinariasjbdb.productos
(id_producto, nombre_producto, descripcion_producto, precio_producto, stock, estado_producto)
VALUES(NULL, 'Comedero Automático', 'Dispensador de alimentos automático con temporizador. Ideal para mantener la rutina de alimentación de tu mascota cuando estás fuera.', 40.0, 10, 1);
INSERT INTO veterinariasjbdb.productos
(id_producto, nombre_producto, descripcion_producto, precio_producto, stock, estado_producto)
VALUES(NULL, ' Pelotas de Juguete', 'Set de tres pelotas de goma duradera para perros. Perfectas para juegos de lanzar y recoger.', 12.0, 23, 1);
INSERT INTO veterinariasjbdb.productos
(id_producto, nombre_producto, descripcion_producto, precio_producto, stock, estado_producto)
VALUES(NULL, 'Transportadora Portátil para Gatos y Perros Pequeños', 'Bolsa de transporte con malla transpirable y correa ajustable. Ideal para viajes cortos o visitas al veterinario.', 18.0, 23, 1);
INSERT INTO veterinariasjbdb.productos
(id_producto, nombre_producto, descripcion_producto, precio_producto, stock, estado_producto)
VALUES(NULL, 'Shampoo ', 'Shampoo suave y sin fragancia para perros y gatos con piel sensible. Ayuda a mantener el pelaje limpio y saludable.', 9.0, 17, 1);
INSERT INTO veterinariasjbdb.productos
(id_producto, nombre_producto, descripcion_producto, precio_producto, stock, estado_producto)
VALUES(NULL, ' Cama', 'Cama acolchada con bordes elevados para ofrecer comodidad y calidez a perros y gatos. Lavable a máquina.', 16.0, 18, 1);
INSERT INTO veterinariasjbdb.productos
(id_producto, nombre_producto, descripcion_producto, precio_producto, stock, estado_producto)
VALUES(NULL, 'Arnés Acolchado para Perros', 'Arnés ajustable y acolchado para perros. Proporciona mayor comodidad y control al pasear.', 25.0, 17, 1);
INSERT INTO veterinariasjbdb.productos
(id_producto, nombre_producto, descripcion_producto, precio_producto, stock, estado_producto)
VALUES(NULL, 'Caja de Arena ', 'Caja de arena automática con sistema de autolimpieza. Ideal para mantener la higiene sin esfuerzo.', 85.0, 11, 1);

#Fin