-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-12-2020 a las 23:31:51
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ex01`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alumnes`
--

CREATE TABLE `alumnes` (
  `Nom` varchar(50) NOT NULL,
  `DNI` varchar(9) NOT NULL,
  `Naixement` date NOT NULL,
  `Adreca` varchar(80) NOT NULL,
  `Sexe` char(1) NOT NULL,
  `CP` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `alumnes`
--

INSERT INTO `alumnes` (`Nom`, `DNI`, `Naixement`, `Adreca`, `Sexe`, `CP`) VALUES
('Oriol', '39941338K', '2001-04-01', 'Plaza Morlius', 'H', 43201),
('Jesus', '39965248B', '2001-07-14', 'Calle prim', 'H', 43201);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `poblacio`
--

CREATE TABLE `poblacio` (
  `CP` int(11) NOT NULL,
  `Poblacio` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `poblacio`
--

INSERT INTO `poblacio` (`CP`, `Poblacio`) VALUES
(43001, 'Tarragona'),
(43201, 'Reus');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alumnes`
--
ALTER TABLE `alumnes`
  ADD PRIMARY KEY (`DNI`),
  ADD KEY `CP` (`CP`);

--
-- Indices de la tabla `poblacio`
--
ALTER TABLE `poblacio`
  ADD PRIMARY KEY (`CP`),
  ADD KEY `CP` (`CP`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alumnes`
--
ALTER TABLE `alumnes`
  ADD CONSTRAINT `alumnes_ibfk_1` FOREIGN KEY (`CP`) REFERENCES `poblacio` (`CP`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `poblacio`
--
ALTER TABLE `poblacio`
  ADD CONSTRAINT `poblacio_ibfk_1` FOREIGN KEY (`CP`) REFERENCES `poblacio` (`CP`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
