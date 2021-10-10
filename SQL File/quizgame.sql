create database `quizgame`;

/* Création de la table utilisateur*/

Drop table if exists `utilisateur`;
create table `utilisateur`(
	`id_utilisateur` int not null auto_increment,
    `nom` varchar(30) not null,
    `prenom` varchar(50) not null,
    `email` varchar(50) unique not null,
    `telephone` varchar(10) unique not null,
    `motDePass` varchar(20) not null,
    `adresse` varchar(60),
    constraint pk_utilisateur primary key (id_utilisateur)
);

/* Création de la table statistique */

Drop table if exists `statistique`;
create table `statistique`(
	`id_statistique` int not null,
    `score` int,
    `PourcentageDeProgression` float,
    `domaineDeQuiz` varchar(30),
	constraint fk_stats_utilisateur foreign key (id_statistique) references utilisateur (id_utilisateur) 
);

/* Création de la table professeur */

Drop table if exists `professeur`;
create table `professeur`(
	`id_professeur` int not null auto_increment,
    `nom` varchar(30) not null,
    `prenom` varchar(50) not null,
    `email` varchar(50) unique not null,
    `telephone` varchar(10) unique not null,
    `motDePass` varchar(20) not null,
    `specialite` varchar(30) not null,
    `adresse` varchar(60),
    constraint pk_professeur primary key (id_professeur)
);

