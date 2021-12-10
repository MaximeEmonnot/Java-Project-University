# QuizGame

QuizGame est un outil d'apprentissage mêlant Quiz et Mini-jeux. Cela permet d'apprendre tout en s'amusant, effaçant l'aspect de corvée que peut prendre la révision.
***
## Déroulement et Contenu
L'utilisateur a d'abord le choix entre créer un compte Etudiant ou créer un compte Enseignant.
### Partie Etudiant
Avec son compte Etudiant, l'utilisateur a la possibilité de répondre à une suite de question, dans un domain bien précis. Pour répondre à ces questions, il doit résoudre les différents mini-jeux qui lui sont proposés, dans un temps imparti (10s, 15s ou 20s) :
 - Des mini-jeux à deux possibilités de réponse :
   * FingerNoseQuestion : Un nez se trouve en haut de l'écran, la souris contrôle un doigt qui doit aller dans la narine correspondant à la bonne réponse.
   * BubbleBoxQuestion : Deux cadeaux sautillent, et l'utilisateur doit cliquer sur le bon cadeau.
   * HandCoinQuestion : Deux mains fermées sont présentées à l'utilisateur, qui doit cliquer sur la bonne main pour répondre.
- Des mini-jeux à trois possibilités de réponse :
   * EggQuestion : Trois oeufs sont présentés à l'utilisateur, qui doit taper plusieurs fois sur l'oeuf correspondant à la bonne réponse.
   * BoxQuestion : Trois boîtes sont présentées à l'utilisateur, qui doit cliquer sur la boîte correspondant à la bonne réponse.
   * AppleQuestion : Trois pommes et un couteau sont présentés à l'utilisateur, qui doit glisser le couteau de haut en bas sur la pomme correspondant à la bonne réponse.
- Des mini-jeux à quatre possibilités de réponse :
   * CatsLightQuestion : Un interrupteur est présenté en haut à gauche. L'utilisateur doit cliquer sur l'interrupteur pour éteindre et allumer la lumière. Lorsque la lumière se rallume, des chats apparaissent à des positions aléatoires avec des poses aléatoires. L'utilisateur a ensuite le choix de cliquer sur le chat correspondant à la bonne réponse, ou de cliquer de nouveau sur l'interrupteur pour avoir d'autres chats si ce chat n'est pas disponible.
   * CardQuestion : Quatre cartes sont présentées à l'utilisateur, qui doit cliquer sur la carte correspondant à la bonne réponse.
   * CardBoardQuestion : Quatre cartons sont présentés à l'utilisateur, qui doit cliquer sur la carte correspondant à la bonne réponse.


L'utilisateur a également la possibilité d'accéder à ses statisiques, mises à jour à chaque fois qu'il fait un meilleur score dans un domaine/catégorie. Il peut également accéder à son profil pour modifier son mot de passe. Enfin, il peut aussi se rendre sur la partie forum, où il peut poser des questions sur des sujets qu'il ne comprend pas, si jamais ce sujet n'est pas traité dans les questions du systèmes. Ses questions seront traitées par des enseignants, et des étudiants peuvent également proposer des réponses aux enseignants.

---
### Partie Enseignant
Si l'utilisateur a créé un compte Enseignant, quatre possibilités s'offrent à lui :
 - Ajouter un domaine / catégorie pour les quiz
 - Ajouter une question dans un domaine / catégorie
 - Supprimer l'une des questions qu'il a ajoutées
 - Accéder à la partie Enseignant du forum

 Pour ajouter un domaine / catégorie, l'enseignant doit inscrire le domaine, la catégorie et définir le niveau de la catégorie (Primaire, Collège, Lycée, Supérieur). Il doit ensuite cliquer sur le bouton Add domain pour valider l'ajout.

 Pour ajouter une question, l'enseignant doit sélectionner un domaine, une catégorie, un niveau et une difficulté qui sera utilisée pour le temps nécessaire à la réponse (Facile : 10s, Moyen : 15s, Difficile : 20s).
 Il doit ensuite inscrire l'intitulé de la question, écrire les propositions (entre 2 et 4 propositions), et sélectionner les propositions correctes.
 L'utilisateur valide son ajout avec le bouton Add question.

 Pour supprimer une question, l'enseignant doit seulement cliquer sur Delete, à la ligne correspondant à la question qu'il souhaite supprimer.

 Enfin, dans la partie Enseignant du forum, l'utilisateur peut accéder aux questions non répondues des étudiants, et a le choix entre répondre directement à l'étudiant ou sélectionner une proposition des étudiants. Après avoir validé son entrée, ou sélectionné la réponse, le sujet est clos, et tous les étudiants auront accès à la réponse de l'enseignant.

 ***
 ## Comment installer

 Tout d'abord, un logiciel de base de données est nécessaire pour le fonctionnement de ce logiciel. Sans cela, impossible de se connecter au système. 
 Nous vous conseillons d'utiliser MySQL WorkBench, car le développement s'est déroulé avec ce logiciel, mais n'importe quel logiciel de traitement SQL fait l'affaire.

 Une fois le logiciel de base de données intallé, ouvrez le fichier quizgame.sql situé dans SQL File et exécutez le dans le système de BDD.
 Une fois l'exécution terminée, vous pourrez directement exécuter le programme.

 Une première scène s'affichera, où vous devrez inscrire le nom du schéma de votre base de données, ainsi que votre mot de passe. Tout mot de passe que vous écrivez est masqué, et n'est jamais enregistré par le système.

 Une fois le système connecté à la base de données, amusez vous bien!

 ***
 ## Membres du projet

**Chef de projet** : Maxime Emonnot

**Membres** : 
 - Mamadou Cire Camara
 - Thierno Amadou Diallo
 - Lansana Diakite
 - Godfree Akakpo
 - Alhousseiny Diallo
 - Mamadou Saliou Diallo