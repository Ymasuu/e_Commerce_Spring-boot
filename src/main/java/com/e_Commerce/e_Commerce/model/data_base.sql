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
	description varchar(500),
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
	('Table', 79.99, "Table LACK ,55x55cm, Le design de cette table LACK permet de l'utiliser de nombreuses façons et d'associer facilement à d'autres meubles dans votre maison", 230),
	('Chaise', 24.99, "Une chaise confortable,solide,légère et empilable.Elle se combine facilement avec différentes tabless et différents styles, que ce soit dans la salle à manger, dans l'entré ou à côté du lit", 400),
	('ventilateur', 81.63, "Le ventilateur industriel avec oscillation automatiques à la puissance maximale 110W, 3 vitesses,Moteur  en cuivre, Ajustable", 40),
	('tasse', 9.99, "Grâce à sa construction unique en double paroi rempli d'air,la tasse garde les boissons chaudes plus longtemps sans vous brûler les doigts.Idéal pour les amateurs de thé et de café", 53),
	('ordinateur', 1299.99, 'Ordinateur Portable Lenovo V14 IIL(82C400D2FF),intel Core i5-1035G1', 10),
	('Bain de soleil', 25.99, 'Ce produit est solide et facile à entretenir, car sa structure en acier est peinte époxy et ses cordes sont tressées à même la structure.Les roulettes facilitent le déplacement.Le revêtement du coussin est imperméable et ne craint pas une petite averse.', 60),
	('Casserole', 14.99, 'Casserole 1.0l,Composé d’acier inoxydable, un matériau résistant et facile à nettoyer.Fond épais composé d’une couche d’aluminium entre deux couches d’acier inoxydable. La chaleur reste constante et vous réalisez des économies d’énergie tandis que les aliments risquent moins de brûler ou d’attacher dans le fond.', 90),
	('Housse pour meubles d’extérieur', 20.99, 'Housse pour meubles d’extérieur, table et chaises/noir, 190x80 cm.Cette housse imperméable prolonge la durée de vie de votre bar de jardin pour quatre, ou de votre barbecue et de l’îlot. Protège contre la pluie, le soleil, la saleté et la poussière', 60),
	('Rideaux', 25.99, '2 pièces, blanc avec oeillets, 145x250 cm', 100),
	('Plaid', 30.59, 'blanc cassé, 130x170 cm', 90),
	('Sac pour vêtements', 5.99, 'lot de 3, motif carreaux/gris noir.Ce petit sac est parfait pour ranger des sous-vêtements, des chaussettes ou des écouteurs.', 60),
	('Parapluie', 7.50, 'pliable rouge', 150),
	('ballon de foot', 4.99, 'Ballon de Foot rond gonflable, noir et blanc, en plastique, de 25 cm de diamètre.Que vous jouiez dans l’arrière-cour, sur la plage ou dans le parc, notre ballon de football gonflable est toujours prêt pour un match.', 60),
	('Cadre', 8.99, 'Noir, 61x91 cm,Peut être accroché ou posé selon l’espace disponible.Peut également être utilisé sans passe-partout pour accueillir une plus grande photo.', 60),
	('Horloge mural', 16.79, 'basse tension/bleu, 26 cm.Grâce au mouvement à quartz silencieux de l’horloge, vous n’entendrez aucun « tic-tac » gênant.', 60),
	('Nappe', 13.99, 'motif rayé rouge, 145x320 cm, La nappe protège la table tout en l’habillant avec élégance.En coton, un matériau naturel doux et facile d’entretien qui ne craint pas les lavages fréquents en machine.', 60),
	('Serviette de table', 9.39, 'Avec des serviettes en textile, vous créez un joli décor de table à peu de frais autant pour le quotidien que pour les occasions spéciales.100% coton, un matériau doux et durable qui s’adoucit à chaque lavage.Les couleurs sont préservées lavage après lavage grâce à la teinture sur fil du coton.', 70),
	('Ménagère', 24.99, 'Ménagère 24 pièces, couleur laiton . Apportez une touche festive à votre table avec ces couverts aux lignes épurées et au ton cuivré.', 60),
	('Couronne de Noël lumineuse', 11.99, 'Couronne de Noël lumineuse branchages artificiels marron, verts et rouges.Hauteur : 35 cm et Poids:0.3kg ', 60),
	('Canapé d’angle convertible', 2150.99, 'Profitez du bien-être d’un véritable lit grâce au canapé d’angle 4 places."\r" n cuir marron, il offre une touche d’élégance et d’authenticité dans un salon aux lignes contemporaines."\r"Ce canapé convertible est équipé d’un matelas grand confort et de coussins bien rembourrés.', 120),
	('Boîte aux lettres de Noël ', 10.99, 'La Boîte aux lettres de Noël en métal rouge, blanc et doré - Lot de 2', 60),
	('Sapin de noël ', 30.99, 'Avec le sapin artificiel Tsuga de Triumph Tree c’est Noël qui s’invite à la maison. Ce superbe sapin se distingue par ses aiguilles rigides dont les pointes blanches évoquent le givre de l’hiver. Ces jolies branches arrondies alternent avec des branches dans une autre nuance de vert. Ce mélange accentue l’effet naturel.Ce modèle a les dimensions suivantes : hauteur 155 cm, diamètre 104 cm.', 110),
	('Appel AirPods Pro', 23.99, 'Les Apple AirPods Pro 2ᵉ génération  ont été repensés pour offrir jusqu’à 2x plus de Réduction active du bruit. La Transparence adaptative réduit les bruits extérieurs.La Connexion automatique,Bluetooth  V5.3 et Allumage automatique', 60),
	('Clavier gaming', 18.99, 'Ce clavier gaming MEETION K9520 peut répondre à vos besoins quotidiens, rétroéclairage RGB, peut être commuté à volonté, et possède des fonctions conviviales, telles que le rétroéclairage RGB peut être personnalisé, 26 touches anti-blocage, 12 touches multimédia, etc. Le câble USB tressé standard mesure 1,8 mètres.', 50),
	('Ampoule LED', 10.09, 'Cette sublime ampoule LED à filaments de 12,5 cm de diamètre est fabriquée en verre ambré pour vous offrir une lumière douce, chaleureuse et déco ! Composée d’une guirlande LED à l’intérieur de son globe, elle apporte un éclairage déco féerique et délicat qui viendra illuminer la pièce d’une belle lumière d’ambiance, avec un bel effet de ciel étoilé. Caractéristiques : - Douille : E27 - Puissance : 1,5W - IP : - Matière : glass ', 150),
	('Banc à chaussures', 33.99, 'Découvrez notre Banc à chaussures à 2 niveaux - L73 cm pour offrir à votre entrée une solution de rangement pratique au quotidien. Disposant de deux niveaux, ce banc à chaussures vous permettra de stocker toutes vos paires de chaussures. La tablette supérieure permet de s’asseoir pour se chausser facilement.Le design industriel s’adaptera à tous les styles d’intérieur. Caractéristiques produits : Matière principale: Panneau de particules, Acier. Dimensions produit: Longueur 73 cm x Hauteur 45 cm x Profondeur 30 cm - Poids net : 6,8 kg. Dimensions colis: Colis n°1 : Hauteur 83 cm x Largeur 35,5 cm x Profondeur 10,2 cm - Poids brut : 7,6 kg.', 60),
	('Miroir rectangulaire sur pied noir', 30, 'Pour un intérieur contemporain ou plus industriel, son encadrement noir sera tout indiqué."\r" Ses dimensions de 50x170 cm rendront ce miroir pratique pour vous observer de la tête aux pieds.', 75);


INSERT IGNORE INTO Client (id_client, compte_bancaire_num, compte_bancaire_solde, points)
	SELECT id_utilisateur, '0000 0000 0000 0000', 0, 0
	FROM Utilisateur
	WHERE type_de_compte = 'Client';
    
INSERT IGNORE INTO Moderateur (id_moderateur, droits)
	SELECT id_utilisateur, '101'
	FROM Utilisateur
	WHERE type_de_compte = 'Moderateur';

