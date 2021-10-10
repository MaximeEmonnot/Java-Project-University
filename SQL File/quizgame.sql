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

/*Creation de la table questions */

DROP TABLE IF EXISTS `questions`;
create table questions(
	id int auto_increment primary key,
    code_question varchar(20) unique,
    id_prof varchar(50) not null,
    domaine varchar(50) not null,
    categorie varchar(50) not null,
    niveau varchar(50) not null,
    difficulte varchar(50) not null,
    question varchar(150) not null,
    code_reponses varchar(20) not null,
    reponseA varchar(150) not null,
    reponseB varchar(150) not null,
    reponseC varchar(150) not null,
    reponseD varchar(150) not null
);

/*Creation de la table administrateur*/

DROP TABLE IF EXISTS `admin`;
create table admin(
	id int auto_increment primary key,
    nom varchar(30),
    prenom varchar(30),
    tel varchar(20) unique,
    mail varchar(20) unique,
    passwd varchar(150)
);