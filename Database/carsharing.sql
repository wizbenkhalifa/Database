-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Creato il: Dic 21, 2016 alle 21:39
-- Versione del server: 10.1.19-MariaDB
-- Versione PHP: 7.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `carsharing`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `auto`
--

CREATE TABLE `auto` (
  `targa` varchar(10) NOT NULL,
  `marca` varchar(10) NOT NULL,
  `modello` varchar(50) NOT NULL,
  `costoGiornaliero` decimal(6,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `auto`
--

INSERT INTO `auto` (`targa`, `marca`, `modello`, `costoGiornaliero`) VALUES
('AA222DS', 'FIAT', '500', '27.00'),
('AB009FG', 'SEAT', 'IBIZA', '25.00'),
('BB333EE', 'FORD', 'ESPACE', '50.00'),
('BC111KL', 'SEAT', 'LEON', '30.00');

-- --------------------------------------------------------

--
-- Struttura della tabella `noleggi`
--

CREATE TABLE `noleggi` (
  `codiceNoleggio` int(11) NOT NULL,
  `auto` varchar(10) NOT NULL,
  `socio` varchar(16) NOT NULL,
  `inizio` date NOT NULL,
  `fine` date NOT NULL,
  `autoRestituita` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `noleggi`
--

INSERT INTO `noleggi` (`codiceNoleggio`, `auto`, `socio`, `inizio`, `fine`, `autoRestituita`) VALUES
(1, 'AA222DS', 'BNCLGO68B80E111T', '2016-02-15', '2016-12-11', 0),
(2, 'AA222DS', 'DMALDA18D91A000A', '2016-09-05', '2016-12-06', 0),
(3, 'AB009FG', 'RSSLCA21A78A000Q', '2016-06-21', '2016-11-16', 0),
(4, 'BB333EE', 'RSSLCA21A78A000Q', '2016-09-05', '2016-12-05', 1),
(5, 'BC111KL', 'RSSMRA19T54A000Z', '2016-11-06', '2016-12-23', 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `soci`
--

CREATE TABLE `soci` (
  `cf` varchar(16) NOT NULL,
  `cognome` varchar(50) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `indirizzo` varchar(100) NOT NULL,
  `telefono` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dump dei dati per la tabella `soci`
--

INSERT INTO `soci` (`cf`, `cognome`, `nome`, `indirizzo`, `telefono`) VALUES
('BNCLGO68B80E111T', 'BIANCHI', 'OLGA', 'VIA XXIV GIUGNO, 100/A ROMA', ''),
('DMALDA18D91A000A', 'ADAMI', 'ALDO', 'VICOLO ITALIA, 120 TREVISO', '333889900112'),
('RSSLCA21A78A000Q', 'ROSSI', 'LUCA', 'VIALE ROMANO, 17 MILANO', '34789891234'),
('RSSMRA19T54A000Z', 'ROSSI', 'MARIO', 'VIA DEL SOLE, 41 TREVISO', '34511223344'),
('VRDNNA41C66S456W', 'VERDI', 'ANNA', 'VIA PIAVE, 18 TREVISO', '34511223344');

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `auto`
--
ALTER TABLE `auto`
  ADD PRIMARY KEY (`targa`);

--
-- Indici per le tabelle `noleggi`
--
ALTER TABLE `noleggi`
  ADD PRIMARY KEY (`codiceNoleggio`);

--
-- Indici per le tabelle `soci`
--
ALTER TABLE `soci`
  ADD PRIMARY KEY (`cf`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `noleggi`
--
ALTER TABLE `noleggi`
  MODIFY `codiceNoleggio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
