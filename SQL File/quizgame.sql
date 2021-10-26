create database `quizgame`;

/* Création de la table utilisateur*/

Drop table if exists `etudiant`;
create table `etudiant`(
	`id_etudiant` int not null auto_increment unique,
	`code_etudiant` varchar(30) not null,
    `nom` varchar(30) not null,
    `prenom` varchar(50) not null,
    `email` varchar(50) unique not null,
    `telephone` varchar(10) unique not null,
    `motDePass` varchar(20) not null,
    `adresse` varchar(60),
    constraint pk_utilisateur primary key (code_etudiant)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/* Création de la table statistique */

Drop table if exists `statistique`;
create table `statistique`(
	`id_statistique` int not null auto_increment,
    `score` int,
    `PourcentageDeProgression` float,
    `domaineDeQuiz` varchar(30),
    constraint pk_stats primary key (id_statistique),
    `code_etudiant` varchar(30)  not null, 
	constraint fk_stats_etudiant foreign key (code_etudiant) references etudiant (code_etudiant) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/* Création de la table professeur */

Drop table if exists `professeur`;
create table `professeur`(
	`id_professeur` int not null auto_increment unique,
	`code_professeur` varchar(30) not null,
    `nom` varchar(30) not null,
    `prenom` varchar(50) not null,
    `email` varchar(50) unique not null,
    `telephone` varchar(10) unique not null,
    `motDePass` varchar(20) not null,
    `specialite` varchar(30) not null,
    `adresse` varchar(60),
    constraint pk_professeur primary key (code_professeur)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Creation de la table questions */

DROP TABLE IF EXISTS `questions`;
create table questions(
	id_question int not null auto_increment unique,
    code_question varchar(20) not null,
    code_professeur varchar(30) not null,
    domaine varchar(50) not null,
    categorie varchar(50) not null,
    niveau varchar(50) not null,
    difficulte varchar(50) not null,
    question varchar(150) not null,
    code_reponses varchar(20) not null,
    reponseA varchar(150) not null,
    reponseB varchar(150) not null,
    reponseC varchar(150) not null,
    reponseD varchar(150) not null,
    constraint pk_question primary key (code_question),
    constraint fk_question_professeur foreign key (code_professeur) references professeur(code_professeur)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Creation de la table administrateur*/

DROP TABLE IF EXISTS `admin`;
create table admin(
	id_admin int auto_increment unique,
	code_admin varchar(30) not null,
    nom varchar(30),
    prenom varchar(30),
    tel varchar(20) unique,
    mail varchar(20) unique,
    passwd varchar(150),
    constraint pk_admin primary key (code_admin)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;