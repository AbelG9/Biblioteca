-- Database: biblioteca

-- DROP DATABASE IF EXISTS biblioteca;

CREATE DATABASE biblioteca
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Peru.1252'
    LC_CTYPE = 'Spanish_Peru.1252'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;
	);

	create table if not exists usuarios(
		usuario_id serial PRIMARY KEY,
		nombre VARCHAR(255),
		apellidos VARCHAR(255) NULL,
		email VARCHAR(255) NULL,
		estado integer
	);

	CREATE TABLE if not exists articulos (
		articulo_id serial PRIMARY KEY,
		nombreArticulo VARCHAR(255),
		autor VARCHAR(255),
		editorial VARCHAR(255) NULL,
		isbn VARCHAR(255) NULL,
		isLoaned BOOLEAN,
		estado integer
	);

	create table if not exists prestamos(
		prestamo_id serial PRIMARY KEY,
		usuario integer,
		articulo integer,
		fecha_prestamo DATE NULL,
		fecha_devolucion DATE NULL,
		estado integer,

		foreign key(usuario) references usuarios(usuario_id),
		foreign key(articulo) references articulos(articulo_id)
	);

    INSERT INTO usuarios (nombre, apellidos, email, estado)
    VALUES
     ('Juan', 'Pérez García', 'juan.perez@gmail.com', 1),
     ('María', 'García López', 'maria.garcia@gmail.com', 1),
     ('Pedro', 'Rodríguez Martínez', 'pedro.rodriguez@gmail.com', 1),
     ('Ana', 'López Sánchez', 'ana.lopez@gmail.com', 1),
     ('Luis', 'Sánchez Martín', 'luis.sanchez@gmail.com', 1),
     ('Carmen', 'Gómez Fernández', 'carmen.gomez@gmail.com', 1),
     ('José', 'Fernández Pérez', 'jose.fernandez@gmail.com', 1),
     ('Daniela', 'Pérez Gómez', 'daniela.perez@gmail.com', 1),
     ('Martín', 'González Sánchez', 'martin.gonzalez@gmail.com', 1),
     ('Antonio', 'Rodríguez Fernández', 'antonio.rodriguez@gmail.com', 1);

    INSERT INTO articulos (nombreArticulo, autor, editorial, isbn, isLoaned, estado)
    VALUES
     ('El Quijote', 'Miguel de Cervantes', 'Penguin Random House', '978-84-672-3275-0', false, 1),
     ('Cien años de soledad', 'Gabriel García Márquez', 'Alfaguara', '978-84-204-8872-1', false, 1),
     ('La Metamorfosis', 'Franz Kafka', 'El Aleph Editores', '978-84-16-60216-6', false, 1),
     ('El Señor de los Anillos', 'J.R.R. Tolkien', 'Minotauro', '978-84-450-7949-8', false, 1),
     ('Harry Potter y la Piedra Filosofal', 'J.K. Rowling', 'Emecé Editores', '978-84-204-6995-0', false, 1),
     ('El Código Da Vinci', 'Dan Brown', 'Planeta', '978-84-08-05575-7', false, 1),
     ('Los Pilares de la Tierra', 'Ken Follett', 'Grijalbo', '978-84-271-2888-5', false, 1),
     ('El Juego del Ender', 'Orson Scott Card', 'RBA Molino', '978-84-271-3382-2', false, 1),
     ('El Alquimista', 'Paulo Coelho', 'Planeta', '978-84-08-05030-3', false, 1),
     ('La Brújula Dorada', 'Philip Pullman', 'Alfaguara Juvenil', '978-84-204-7206-2', false, 1);