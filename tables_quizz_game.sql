create table questions(
	id int auto_increment,
    code_question varchar(20) primary key,
    id_prof varchar(50),
    domaine varchar(50),
    categorie varchar(50),
    niveau varchar(50),
    difficulte varchar(50),
    question varchar(150),
    code_reponses varchar(20),
    reponseA varchar(150),
    reponseB varchar(150),
    reponseC varchar(150),
    reponseD varchar(150)
);