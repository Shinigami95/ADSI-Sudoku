-- phpMyAdmin SQL Dump
-- version 3.4.11.1deb2+deb7u2
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 09-01-2016 a las 13:02:15
-- Versión del servidor: 5.5.46
-- Versión de PHP: 5.4.45-0+deb7u2

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `Xmalboniga002_SUDOKU_BD`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ADMIN`
--

CREATE TABLE IF NOT EXISTS `ADMIN` (
  `NOMBRE` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`NOMBRE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `ADMIN`
--

INSERT INTO `ADMIN` (`NOMBRE`) VALUES
('admin');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `JUGADO`
--

CREATE TABLE IF NOT EXISTS `JUGADO` (
  `NOMBRE_JUG` varchar(20) COLLATE utf8_bin NOT NULL,
  `ID_SUDOKU` int(11) NOT NULL,
  `FECHA` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `COMPLETADO` char(1) COLLATE utf8_bin DEFAULT NULL,
  `PTO` int(11) DEFAULT NULL,
  `SEGUNDOS` int(11) DEFAULT NULL,
  PRIMARY KEY (`NOMBRE_JUG`,`ID_SUDOKU`),
  KEY `ID_SUDOKU` (`ID_SUDOKU`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `JUGADO`
--

INSERT INTO `JUGADO` (`NOMBRE_JUG`, `ID_SUDOKU`, `FECHA`, `COMPLETADO`, `PTO`, `SEGUNDOS`) VALUES
('AAA', 2, '2016-01-07 09:57:37', 'S', 12496, 19),
('Brook', 8, '2015-12-28 10:52:05', 'S', 1500, 105),
('Brook', 9, '2015-12-28 10:52:05', 'S', 1500, 100),
('Chopper', 4, '2015-12-28 10:52:54', 'N', 0, 0),
('Franky', 7, '2015-12-28 10:54:23', 'S', 2000, 50),
('Franky', 9, '2015-12-28 10:54:23', 'S', 2600, 52),
('Jorge', 2, '2015-12-27 18:47:32', 'S', 800, 400),
('Jorge', 3, '2016-01-03 18:35:49', 'N', 0, 0),
('Jorge', 4, '2015-12-27 18:47:36', 'S', 300, 350),
('Jorge', 5, '2015-12-27 19:59:12', 'N', 0, 0),
('Jorge', 6, '2016-01-04 17:37:56', 'N', 0, 0),
('Jorge', 7, '2016-01-06 16:53:37', 'N', 0, 0),
('Jorge', 8, '2015-12-27 18:47:42', 'S', 4000, 500),
('Jorge', 18, '2016-01-06 19:55:21', 'S', 9822, 11),
('Luffy', 2, '2015-12-28 10:56:01', 'S', 2000, 100),
('Luffy', 3, '2015-12-28 10:56:01', 'S', 2000, 200),
('Luffy', 6, '2015-12-28 10:56:01', 'S', 2000, 350),
('Luffy', 7, '2015-12-28 10:56:01', 'S', 2000, 300),
('Nami', 4, '2015-12-28 10:56:54', 'S', 1600, 100),
('Robin', 2, '2015-12-28 10:59:32', 'S', 500, 100),
('Robin', 3, '2015-12-28 10:59:32', 'S', 1000, 50),
('Robin', 4, '2015-12-28 10:59:32', 'S', 1000, 250),
('Robin', 5, '2015-12-28 10:59:32', 'S', 1000, 240),
('Robin', 6, '2015-12-28 10:59:32', 'S', 1000, 260),
('Robin', 7, '2015-12-28 10:59:32', 'S', 1000, 300),
('Robin', 8, '2015-12-28 10:59:32', 'S', 1000, 350),
('Robin', 9, '2015-12-28 10:59:32', 'S', 1000, 420),
('Sanji', 4, '2015-12-28 11:01:00', 'N', 0, 0),
('Sanji', 7, '2015-12-28 11:01:00', 'S', 2000, 250),
('Sanji', 8, '2015-12-28 11:01:00', 'S', 2600, 120),
('Usopp', 4, '2015-12-28 11:02:33', 'N', 0, 0),
('Usopp', 5, '2015-12-28 11:02:33', 'S', 900, 450),
('Usopp', 6, '2015-12-28 11:02:33', 'N', 0, 0),
('Zoro', 8, '2015-12-28 11:03:40', 'S', 2000, 100),
('Zoro', 9, '2015-12-28 11:03:40', 'S', 2000, 100);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `JUGADOR`
--

CREATE TABLE IF NOT EXISTS `JUGADOR` (
  `NOMBRE` varchar(20) COLLATE utf8_bin NOT NULL,
  `PTO_TOTAL` int(11) DEFAULT NULL,
  PRIMARY KEY (`NOMBRE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `JUGADOR`
--

INSERT INTO `JUGADOR` (`NOMBRE`, `PTO_TOTAL`) VALUES
('AAA', 25055),
('Brook', 3000),
('Chopper', 50),
('Franky', 5600),
('Jaja', 0),
('Jorge', 15922),
('LALA', 0),
('Luffy', 10000),
('NANA', 0),
('Nami', 1600),
('POPO', 0),
('PRUEB', 2),
('PRUEB4', 1),
('Robin', 8000),
('Ruben', 0),
('Sanji', 4600),
('Usopp', 1600),
('Zoro', 4000),
('ef', 0),
('juan', 0),
('mikel', 0),
('prueba1', 4),
('ruben', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `LOGRO`
--

CREATE TABLE IF NOT EXISTS `LOGRO` (
  `ID_L` varchar(5) COLLATE utf8_bin NOT NULL,
  `DESCRIPCION` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `ID_SUDOKU` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_L`),
  KEY `ID_SUDOKU` (`ID_SUDOKU`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `LOGRO`
--

INSERT INTO `LOGRO` (`ID_L`, `DESCRIPCION`, `ID_SUDOKU`) VALUES
('R3001', 'Ser de los 5 primeros que terminen el sudoku 3', 3),
('R4001', 'Ser de los 10 primeros que terminen el sudoku 4', 4),
('RPRUE', 'prueba', 2),
('X18F', 'Ganar 500 pto en el sudoku 18', 18),
('X3029', 'Conseguir 5000 puntos en el sudoku 2', 2),
('Y5001', 'Ser de los 10 primeros en conseguir 2000 puntos en el sudoku 5', 5),
('Y6001', 'Ser de los 20 primeros en conseguir 1000 puntos en el sudoku 6', 6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `LOGRO_PTOX`
--

CREATE TABLE IF NOT EXISTS `LOGRO_PTOX` (
  `ID_L` varchar(5) COLLATE utf8_bin NOT NULL,
  `PTO` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_L`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `LOGRO_PTOX`
--

INSERT INTO `LOGRO_PTOX` (`ID_L`, `PTO`) VALUES
('X18F', 500),
('X3029', 5000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `LOGRO_PTOXY`
--

CREATE TABLE IF NOT EXISTS `LOGRO_PTOXY` (
  `ID_L` varchar(5) COLLATE utf8_bin NOT NULL,
  `PTO` int(11) DEFAULT NULL,
  `NUM_JUG` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_L`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `LOGRO_PTOXY`
--

INSERT INTO `LOGRO_PTOXY` (`ID_L`, `PTO`, `NUM_JUG`) VALUES
('Y5001', 2000, 10),
('Y6001', 1000, 20);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `LOGRO_RES`
--

CREATE TABLE IF NOT EXISTS `LOGRO_RES` (
  `ID_L` varchar(5) COLLATE utf8_bin NOT NULL,
  `NUM_JUG` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_L`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `LOGRO_RES`
--

INSERT INTO `LOGRO_RES` (`ID_L`, `NUM_JUG`) VALUES
('R3001', 5),
('R4001', 9),
('RPRUE', 100);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PARTIDA`
--

CREATE TABLE IF NOT EXISTS `PARTIDA` (
  `NOMBRE_JUG` varchar(20) COLLATE utf8_bin NOT NULL,
  `ID_SUDOKU` int(11) NOT NULL,
  `MATRIZ_TABLERO` varchar(82) COLLATE utf8_bin DEFAULT NULL,
  `NUM_AYUDAS` int(11) DEFAULT NULL,
  `NUM_COMPR` int(11) DEFAULT NULL,
  `TIEMPO` int(11) DEFAULT NULL,
  `RETO` int(11) DEFAULT NULL,
  PRIMARY KEY (`NOMBRE_JUG`),
  KEY `RETO` (`RETO`),
  KEY `ID_SUDOKU` (`ID_SUDOKU`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `PARTIDA`
--

INSERT INTO `PARTIDA` (`NOMBRE_JUG`, `ID_SUDOKU`, `MATRIZ_TABLERO`, `NUM_AYUDAS`, `NUM_COMPR`, `TIEMPO`, `RETO`) VALUES
('AAA', 3, '000028500000600007086005340869230000512007000400500002028060000005040270004000639', 81, 5, 30, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RETO`
--

CREATE TABLE IF NOT EXISTS `RETO` (
  `ID_R` int(11) NOT NULL AUTO_INCREMENT,
  `NOMBRE_RETADOR` varchar(20) COLLATE utf8_bin NOT NULL,
  `NOMBRE_RETADO` varchar(20) COLLATE utf8_bin NOT NULL,
  `ID_SUDOKU` int(11) NOT NULL,
  `ESTADO` char(1) COLLATE utf8_bin DEFAULT NULL,
  `FECHA` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID_R`),
  KEY `NOMBRE_RETADOR` (`NOMBRE_RETADOR`),
  KEY `NOMBRE_RETADO` (`NOMBRE_RETADO`),
  KEY `ID_SUDOKU` (`ID_SUDOKU`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=17 ;

--
-- Volcado de datos para la tabla `RETO`
--

INSERT INTO `RETO` (`ID_R`, `NOMBRE_RETADOR`, `NOMBRE_RETADO`, `ID_SUDOKU`, `ESTADO`, `FECHA`) VALUES
(1, 'Robin', 'Jorge', 9, 'R', '2016-01-02 20:17:14'),
(3, 'Jorge', 'Brook', 2, 'R', '2016-01-02 20:21:04'),
(4, 'Jorge', 'Brook', 8, 'T', '2016-01-02 20:21:04'),
(11, 'Jorge', 'Brook', 18, 'P', '2016-01-06 19:56:12'),
(12, 'Jorge', 'Chopper', 18, 'P', '2016-01-06 19:56:14'),
(13, 'Jorge', 'Franky', 18, 'P', '2016-01-06 19:56:16'),
(14, 'Franky', 'Brook', 7, 'P', '2016-01-06 22:25:04'),
(15, 'Luffy', 'Brook', 7, 'P', '2016-01-06 22:48:43'),
(16, 'Robin', 'Brook', 7, 'P', '2016-01-06 22:48:43');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `SUDOKU`
--

CREATE TABLE IF NOT EXISTS `SUDOKU` (
  `ID_S` int(11) NOT NULL AUTO_INCREMENT,
  `DIFICULTAD` int(11) DEFAULT NULL,
  `M_INIC` varchar(82) COLLATE utf8_bin DEFAULT NULL,
  `M_SOL` varchar(82) COLLATE utf8_bin DEFAULT NULL,
  `ACTIVO` char(1) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID_S`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=20 ;

--
-- Volcado de datos para la tabla `SUDOKU`
--

INSERT INTO `SUDOKU` (`ID_S`, `DIFICULTAD`, `M_INIC`, `M_SOL`, `ACTIVO`) VALUES
(2, 1, '002800070609074023408003050005080030000000740027030085000360000000517408000208090', '312895674659174823478623159945781236836952741127436985784369512293517468561248397', 'S'),
(3, 1, '000028500000600007086005340869230000512007000400500002028060000005040270004000639', '741328596953614827286975341869231754512487963437596182328769415695143278174852639', 'S'),
(4, 2, '000000084500042600004000020040063700000001003630957200050009006320800109009500800', '792615384583742691164398527948263715275481963631957248857129436326874159419536872', 'S'),
(5, 2, '008900720009010040014002500000006000040000000603540902005093200006020070000675004', '368954721529718643714362589951286437247139856683547912175493268496821375832675194', 'S'),
(6, 2, '000307000083050000900000324007862030040900000352000006000093860500780040060040003', '416327598283459617975618324197862435648935172352174986724593861531786249869241753', 'S'),
(7, 3, '070090052000000000309080000000709400050030900008650037082000570040020608600100304', '874396152561247893329581746236719485457832961918654237182463579743925618695178324', 'S'),
(8, 3, '050001000000006070309040065008914000000067829097000004004100000070000691035000200', '756391482241856973389742165528914736413567829697283514964128357872435691135679248', 'S'),
(9, 3, '000800740509000000080007096004600850010500900800920004005700009090040085000005602', '362859741579416238481237596924671853613584927857923164235768419796142385148395672', 'S'),
(18, 1, '530070000600195000098000060800060003400803001700020006060000280000419005000080079', '534678912672195348198342567859761423426853791713924856961537284287419635345286179', 'S'),
(19, 1, '006002304904750082008006005003000040200400830407500000000600008700020453000370069', '576812394914753682328946715693287541251469837487531926135694278769128453842375169', 'S');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `TIENE`
--

CREATE TABLE IF NOT EXISTS `TIENE` (
  `NOMBRE_JUG` varchar(20) COLLATE utf8_bin NOT NULL,
  `ID_LOGRO` varchar(5) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`NOMBRE_JUG`,`ID_LOGRO`),
  KEY `ID_LOGRO` (`ID_LOGRO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `TIENE`
--

INSERT INTO `TIENE` (`NOMBRE_JUG`, `ID_LOGRO`) VALUES
('mikel', 'R4001'),
('Jorge', 'X18F'),
('AAA', 'X3029');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USUARIO`
--

CREATE TABLE IF NOT EXISTS `USUARIO` (
  `NOMBRE` varchar(20) COLLATE utf8_bin NOT NULL,
  `CONTR` varchar(20) COLLATE utf8_bin NOT NULL,
  `PREG` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `RESP` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`NOMBRE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `USUARIO`
--

INSERT INTO `USUARIO` (`NOMBRE`, `CONTR`, `PREG`, `RESP`) VALUES
('AAA', 'a', 'a', 'a'),
('Brook', 'one_piece', '¿Mi capitan es?', 'Luffy'),
('Chopper', 'one_piece', '¿Mi capitan es?', 'Luffy'),
('Franky', 'one_piece', '¿Mi capitan es?', 'Luffy'),
('Jaja', 'jaja', '0123456789012345678901234567890123456789', '0123456789'),
('Jorge', '123456', '¿Nombre del perro más guay que existe?', 'Snoopy'),
('LALA', 'JAJAJA', 'LALA', 'LALA'),
('Luffy', 'one_piece', '¿Quiero ser?', 'Rey de los piratas'),
('MIKEL', 'ALBO', NULL, NULL),
('NANA', 'OLA', 'NANA', 'NANA'),
('Nami', 'one_piece', '¿Mi capitan es?', 'Luffy'),
('POPO', 'POPO', 'POPO', 'POPO'),
('PRUEB', 'FACIL', '1', '2'),
('PRUEB4', 'FACIL', '1', '2'),
('Robin', 'one_piece', '¿Mi capitan es?', 'Luffy'),
('Ruben', 'asdf', '¿Eres tonto?', 'Si'),
('Sanji', 'one_piece', '¿Mi capitan es?', 'Luffy'),
('Usopp', 'one_piece', '¿Mi capitan es?', 'Luffy'),
('Zoro', 'one_piece', '¿Mi capitan es?', 'Luffy'),
('admin', 'admin', '¿Quién soy?', 'admin'),
('ef', 'fa', 'ef', 'ef'),
('juan', '1234', 'afsagf', 'gsfg'),
('mikel', 'mi', '¿quien soy?', 'yo'),
('prueba1', '1', '1', '1'),
('ruben', '1234', '¿Sí?', 'Si');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ADMIN`
--
ALTER TABLE `ADMIN`
  ADD CONSTRAINT `ADMIN_ibfk_1` FOREIGN KEY (`NOMBRE`) REFERENCES `USUARIO` (`NOMBRE`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `JUGADO`
--
ALTER TABLE `JUGADO`
  ADD CONSTRAINT `JUGADO_ibfk_1` FOREIGN KEY (`NOMBRE_JUG`) REFERENCES `JUGADOR` (`NOMBRE`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `JUGADO_ibfk_2` FOREIGN KEY (`ID_SUDOKU`) REFERENCES `SUDOKU` (`ID_S`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `JUGADOR`
--
ALTER TABLE `JUGADOR`
  ADD CONSTRAINT `JUGADOR_ibfk_1` FOREIGN KEY (`NOMBRE`) REFERENCES `USUARIO` (`NOMBRE`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `LOGRO`
--
ALTER TABLE `LOGRO`
  ADD CONSTRAINT `LOGRO_ibfk_1` FOREIGN KEY (`ID_SUDOKU`) REFERENCES `SUDOKU` (`ID_S`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `LOGRO_PTOX`
--
ALTER TABLE `LOGRO_PTOX`
  ADD CONSTRAINT `LOGRO_PTOX_ibfk_1` FOREIGN KEY (`ID_L`) REFERENCES `LOGRO` (`ID_L`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `LOGRO_PTOXY`
--
ALTER TABLE `LOGRO_PTOXY`
  ADD CONSTRAINT `LOGRO_PTOXY_ibfk_1` FOREIGN KEY (`ID_L`) REFERENCES `LOGRO` (`ID_L`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `LOGRO_RES`
--
ALTER TABLE `LOGRO_RES`
  ADD CONSTRAINT `LOGRO_RES_ibfk_1` FOREIGN KEY (`ID_L`) REFERENCES `LOGRO` (`ID_L`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `PARTIDA`
--
ALTER TABLE `PARTIDA`
  ADD CONSTRAINT `PARTIDA_ibfk_1` FOREIGN KEY (`RETO`) REFERENCES `RETO` (`ID_R`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `PARTIDA_ibfk_2` FOREIGN KEY (`NOMBRE_JUG`) REFERENCES `JUGADOR` (`NOMBRE`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `PARTIDA_ibfk_3` FOREIGN KEY (`ID_SUDOKU`) REFERENCES `SUDOKU` (`ID_S`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `RETO`
--
ALTER TABLE `RETO`
  ADD CONSTRAINT `RETO_ibfk_1` FOREIGN KEY (`NOMBRE_RETADOR`) REFERENCES `JUGADOR` (`NOMBRE`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `RETO_ibfk_2` FOREIGN KEY (`NOMBRE_RETADO`) REFERENCES `JUGADOR` (`NOMBRE`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `RETO_ibfk_3` FOREIGN KEY (`ID_SUDOKU`) REFERENCES `SUDOKU` (`ID_S`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `TIENE`
--
ALTER TABLE `TIENE`
  ADD CONSTRAINT `TIENE_ibfk_1` FOREIGN KEY (`NOMBRE_JUG`) REFERENCES `JUGADOR` (`NOMBRE`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `TIENE_ibfk_2` FOREIGN KEY (`ID_LOGRO`) REFERENCES `LOGRO` (`ID_L`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
