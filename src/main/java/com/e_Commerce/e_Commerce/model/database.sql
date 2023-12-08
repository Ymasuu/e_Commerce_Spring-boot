CREATE DATABASE IF NOT EXISTS E_commerce;
USE e_commerce;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

-- CREATION TABLES :

CREATE TABLE IF NOT EXISTS Utilisateur (
	id_utilisateur int AUTO_INCREMENT UNIQUE PRIMARY KEY,
	nom varchar(20) NOT NULL,
	prenom varchar(20) NOT NULL,
	mail varchar(30) NOT NULL UNIQUE,
	mot_de_passe varchar(20) NOT NULL,
	type_de_compte enum('Admin', 'Moderateur', 'Client') NOT NULL
);

CREATE TABLE IF NOT EXISTS `Client` (
    id_client int UNIQUE PRIMARY KEY,
    compte_bancaire_num varchar(19),
    compte_bancaire_solde decimal(10,2) CHECK (compte_bancaire_solde >= 0),
    points int CHECK (points >= 0),
    FOREIGN KEY (id_client) REFERENCES Utilisateur(id_utilisateur)
);

CREATE TABLE IF NOT EXISTS Commande (
	id_commande int AUTO_INCREMENT UNIQUE PRIMARY KEY,
    id_client int,
	prix decimal(10,2) NOT NULL,
	status varchar(50) NOT NULL,
    FOREIGN KEY (id_client) REFERENCES Client(id_client)
);


CREATE TABLE IF NOT EXISTS `Moderateur` (
	id_moderateur int UNIQUE PRIMARY KEY,
	droits varchar(5) NOT NULL,
    FOREIGN KEY (id_moderateur) REFERENCES Utilisateur(id_utilisateur)
);

CREATE TABLE IF NOT EXISTS Produit (
	id_produit int AUTO_INCREMENT UNIQUE PRIMARY KEY,
	nom varchar(50) NOT NULL UNIQUE,
	prix decimal(10,2) NOT NULL,
	description text,
	stock int CHECK (stock >= 0)
);


CREATE TABLE IF NOT EXISTS Commande_Produit (
    id_commande_prod int AUTO_INCREMENT UNIQUE PRIMARY KEY,
    id_commande INT,
    id_produit INT,
    quantite INT,
    FOREIGN KEY (id_commande) REFERENCES Commande(id_commande),
    FOREIGN KEY (id_produit) REFERENCES Produit(id_produit)
);



-- Ajout des données 
INSERT IGNORE INTO Utilisateur (nom, prenom, mail, mot_de_passe, type_de_compte) VALUES
	('Belbouab', 'Samy', 'Ymasuu@gmail.com', '1234', 'Admin'),
	('Anderson', 'Warren', 'Mugiwarren@gmail.com', '1234', 'Moderateur'),
	('Pinto', 'Ethan', 'Hanabi@gmail.com', '1234', 'Client'),
	('NASCIMENTO ARDILES', 'Renato', 'RDNATOS@gmail.com', '1234', 'Client');

INSERT IGNORE INTO Produit (nom, prix, description, stock) VALUES
	('Table', 79.99, 'Table LACK ,55x55cm, Le design de cette table LACK permet de l\'utiliser de nombreuses façons et d\'associer facilement à d\'autres meubles dans votre maison', 230),
	('Chaise', 24.99, 'Une chaise confortable, solide, légère et empilable. Elle se combine facilement avec différentes tabless et différents styles, que ce soit dans la salle à manger, dans l\'entrée ou encore à côté du lit', 400),
	('Tasse', 9.99, 'Grâce à sa construction unique en double paroi rempli d\'air, la tasse garde les boissons chaudes plus longtemps sans vous brûler les doigts. Idéal pour les amateurs de thé et de café', 53),
	('ordinateur', 1299.99, 'Ordinateur Portable Lenovo V14 IIL(82C400D2FF),intel Core i5-1035G1', 10),
	('Casserole', 14.99, 'Casserole 1.0l. Composée d’acier inoxydable elle est composée un matériau résistant et facile à nettoyer.Son fond épais est composé d’une couche d’aluminium entre deux couches d’acier inoxydable. La chaleur reste constante et vous réalisez des économies d’énergie tandis que les aliments risquent moins de brûler ou d’attacher dans le fond.', 90),
	('Rideaux', 25.99, '2 pièces, blanc avec oeillets, 145x250 cm', 100),
	('Plaid', 30.59, 'Ce plaid saura vous réchauffer quand les températures seront faibles, il allie confort et douceur, 130x170 cm', 90),
	('Parapluie', 7.50, 'Ce parapluie solide est le meilleur moyen pour vous proteger correctement de la pluie inondante dehors', 150),
	('ballon de foot', 4.99, 'Ballon de Foot rond gonflable, noir et blanc, en plastique, de 25 cm de diamètre. Que vous jouiez dans l’arrière-cour, sur la plage ou dans le parc, notre ballon de football gonflable est toujours prêt pour un match.', 60),
	('Cadre', 8.99, 'Noir, 61x91 cm. Ce cadre en bois peut être accroché ou posé selon l’espace disponible. Il a également une taille réglable pour pouvoir accueillir une plus grande photo.', 60),
	('Horloge mural', 16.79, '26 cm. Grâce au mouvement à quartz silencieux de l’horloge, vous n’entendrez aucun « tic-tac » gênant.', 60),
	('Nappe', 13.99, '145x320 cm. La nappe protège la t,able tout en l’habillant avec élégance. En coton, un matériau naturel doux et facile d’entretien qui ne craint pas les lavages fréquents en machine.', 60),
	('Serviette de table', 9.39, 'Avec des serviettes en textile, vous créez un joli décor de table à peu de frais autant pour le quotidien, très utile pour les occasions spéciales !.', 70),
	('Canapé d’angle', 2150.99, 'Profitez du bien-être d’un véritable lit grâce au canapé d’angle 4 places."\r" n cuir marron, il offre une touche d’élégance et d’authenticité dans un salon aux lignes contemporaines."\r"Ce canapé convertible est équipé d’un matelas grand confort et de coussins bien rembourrés.', 120),
	('Boîte aux lettres de Noël ', 10.99, 'La Boîte aux lettres de Noël classique en métal rouge, blanc et doré - Lot de 2', 60),
	('Sapin de noël ', 30.99, 'Avec le sapin artificiel Tsuga de Triumph Tree c’est Noël qui s’invite à la maison. Ce superbe sapin se distingue par ses aiguilles rigides dont les pointes blanches évoquent le givre de l’hiver. Ces jolies branches arrondies alternent avec des branches dans une autre nuance de vert. Ce mélange accentue l’effet naturel. Ce modèle a les dimensions suivantes : hauteur 155 cm, diamètre 104 cm.', 110),
	('Apple AirPods Pro', 183.99, 'Les Apple AirPods Pro 2ᵉ génération  ont été repensés pour offrir jusqu’à 2x plus de Réduction active du bruit. La Transparence adaptative réduit les bruits extérieurs. La Connexion automatique,Bluetooth  V5.3 et Allumage automatique', 60),
	('Clavier gaming', 18.99, 'Ce clavier gaming MEETION K9520 peut répondre à vos besoins quotidiens, rétroéclairage RGB, peut être commuté à volonté, et possède des fonctions conviviales, telles que le rétroéclairage RGB peut être personnalisé, 26 touches anti-blocage, 12 touches multimédia, etc. Le câble USB tressé standard mesure 1,8 mètres.', 50),
	('Ampoule LED', 10.09, 'Cette sublime ampoule LED à filaments de 12,5 cm de diamètre est fabriquée en verre ambré pour vous offrir une lumière douce, chaleureuse et déco ! Composée d’une guirlande LED à l’intérieur de son globe, elle apporte un éclairage déco féerique et délicat qui viendra illuminer la pièce d’une belle lumière d’ambiance, avec un bel effet de ciel étoilé. Caractéristiques : - Douille : E27 - Puissance : 1,5W - IP : - Matière : glass ', 150);


INSERT IGNORE INTO Client (id_client, compte_bancaire_num, compte_bancaire_solde, points)
	SELECT id_utilisateur, '0000 0000 0000 0000', 0, 0
	FROM Utilisateur
	WHERE type_de_compte = 'Client';
    
INSERT IGNORE INTO Moderateur (id_moderateur, droits)
	SELECT id_utilisateur, '101'
	FROM Utilisateur
	WHERE type_de_compte = 'Moderateur';

