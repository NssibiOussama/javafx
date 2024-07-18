-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Lun 08 Juillet 2024 à 13:35
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `emploidutemps_db`
--

-- --------------------------------------------------------

--
-- Structure de la table `classes`
--

CREATE TABLE IF NOT EXISTS `classes` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nom` (`nom`),
  KEY `id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `classes`
--

INSERT INTO `classes` (`id`, `nom`) VALUES
(12, '1ere'),
(5, '2eme'),
(3, '3eme'),
(6, '4eme'),
(7, '5eme'),
(8, '6eme');

-- --------------------------------------------------------

--
-- Structure de la table `enseignants`
--

CREATE TABLE IF NOT EXISTS `enseignants` (
  `nom` varchar(40) NOT NULL,
  `matricule` varchar(12) NOT NULL,
  `contact` varchar(12) NOT NULL,
  PRIMARY KEY (`matricule`),
  UNIQUE KEY `matricule` (`matricule`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `enseignants`
--

INSERT INTO `enseignants` (`nom`, `matricule`, `contact`) VALUES
('Nimp', '2536987', '2536977');

-- --------------------------------------------------------

--
-- Structure de la table `etudiants`
--

CREATE TABLE IF NOT EXISTS `etudiants` (
  `nom` varchar(40) NOT NULL,
  `matricule` varchar(12) NOT NULL,
  `contact` varchar(12) NOT NULL,
  PRIMARY KEY (`matricule`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `seances`
--

CREATE TABLE IF NOT EXISTS `seances` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classe` int(11) NOT NULL,
  `matiere` varchar(255) NOT NULL,
  `jour` varchar(25) NOT NULL,
  `heure` varchar(255) NOT NULL,
  `enseignant` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_enseignant` (`enseignant`),
  KEY `fk_classe` (`classe`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Contenu de la table `seances`
--

INSERT INTO `seances` (`id`, `classe`, `matiere`, `jour`, `heure`, `enseignant`) VALUES
(20, 12, 'Math', 'Jeudi', '14h', '2536987');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `seances`
--
ALTER TABLE `seances`
  ADD CONSTRAINT `fk_classe` FOREIGN KEY (`classe`) REFERENCES `classes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_enseignant` FOREIGN KEY (`enseignant`) REFERENCES `enseignants` (`matricule`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
