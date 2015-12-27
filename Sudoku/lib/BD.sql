DROP DATABASE IF EXISTS Xmalboniga002_SUDOKU_BD;
CREATE DATABASE Xmalboniga002_SUDOKU_BD;

USE Xmalboniga002_SUDOKU_BD;

CREATE TABLE USUARIO(
	NOMBRE VARCHAR(20) NOT NULL,
	CONTR VARCHAR(20) NOT NULL,
	PREG VARCHAR(50),
	RESP VARCHAR(50),
	PRIMARY KEY(NOMBRE)
);

CREATE TABLE ADMIN(
	NOMBRE VARCHAR(20) NOT NULL,
	PRIMARY KEY(NOMBRE),
	FOREIGN KEY(NOMBRE) REFERENCES USUARIO(NOMBRE)
	ON DELETE CASCADE
	ON UPDATE CASCADE
);

CREATE TABLE JUGADOR(
	NOMBRE VARCHAR(20) NOT NULL,
	PTO_TOTAL INT,
	PRIMARY KEY(NOMBRE),
	FOREIGN KEY(NOMBRE) REFERENCES USUARIO(NOMBRE)
	ON DELETE CASCADE
	ON UPDATE CASCADE
);

CREATE TABLE SUDOKU(
	ID_S INT NOT NULL AUTO_INCREMENT,
	DIFICULTAD INT,
	M_INIC VARCHAR(82),
	M_SOL VARCHAR(82),
	ACTIVO CHAR,
	PRIMARY KEY(ID_S)
);

CREATE TABLE LOGRO(
	ID_L VARCHAR(5) NOT NULL,
	DESCRIPCION VARCHAR(100),
	ID_SUDOKU INT,
	PRIMARY KEY(ID_L),
	FOREIGN KEY(ID_SUDOKU) REFERENCES SUDOKU(ID_S)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

CREATE TABLE LOGRO_PTOX(
	ID_L VARCHAR(5) NOT NULL,
	PTO INT,
	PRIMARY KEY(ID_L),
	FOREIGN KEY(ID_L) REFERENCES LOGRO(ID_L)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

CREATE TABLE LOGRO_PTOXY(
	ID_L VARCHAR(5) NOT NULL,
	PTO INT,
	NUM_JUG INT,
	PRIMARY KEY(ID_L),
	FOREIGN KEY(ID_L) REFERENCES LOGRO(ID_L)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

CREATE TABLE LOGRO_RES(
	ID_L VARCHAR(5) NOT NULL,
	NUM_JUG INT,
	PRIMARY KEY(ID_L),
	FOREIGN KEY(ID_L) REFERENCES LOGRO(ID_L)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

CREATE TABLE TIENE(
	NOMBRE_JUG VARCHAR(20) NOT NULL,
	ID_LOGRO VARCHAR(5) NOT NULL,
	PRIMARY KEY(NOMBRE_JUG, ID_LOGRO),
	FOREIGN KEY(NOMBRE_JUG) REFERENCES JUGADOR(NOMBRE)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	FOREIGN KEY(ID_LOGRO) REFERENCES LOGRO(ID_L)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

CREATE TABLE JUGADO(
	NOMBRE_JUG VARCHAR(20) NOT NULL,
	ID_SUDOKU INT NOT NULL,
	FECHA TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	COMPLETADO CHAR,
	PTO INT(11),
	SEGUNDOS INT(11),
	PRIMARY KEY(NOMBRE_JUG, ID_SUDOKU),
	FOREIGN KEY(NOMBRE_JUG) REFERENCES JUGADOR(NOMBRE)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	FOREIGN KEY(ID_SUDOKU) REFERENCES SUDOKU(ID_S)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

CREATE TABLE RETO(
	ID_R INT NOT NULL AUTO_INCREMENT,
	NOMBRE_RETADOR VARCHAR(20) NOT NULL,
	NOMBRE_RETADO VARCHAR(20) NOT NULL,
	ID_SUDOKU INT NOT NULL,
	ESTADO CHAR, -- 'A' ACEPTADO, 'R' RECHAZADO, 'P' PENDIENTE 
	FECHA TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(ID_R),
	FOREIGN KEY(NOMBRE_RETADOR) REFERENCES JUGADOR(NOMBRE)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	FOREIGN KEY(NOMBRE_RETADO) REFERENCES JUGADOR(NOMBRE)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	FOREIGN KEY(ID_SUDOKU) REFERENCES SUDOKU(ID_S)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

CREATE TABLE PARTIDA(
	NOMBRE_JUG VARCHAR(20) NOT NULL,
	ID_SUDOKU INT NOT NULL,
	MATRIZ_TABLERO VARCHAR(82),
	NUM_AYUDAS INT,
	NUM_COMPR INT,
	TIEMPO INT,
	RETO INT,
	PRIMARY KEY(NOMBRE_JUG),
	FOREIGN KEY(RETO) REFERENCES RETO(ID_R)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	FOREIGN KEY(NOMBRE_JUG) REFERENCES JUGADOR(NOMBRE)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	FOREIGN KEY(ID_SUDOKU) REFERENCES SUDOKU(ID_S)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);
