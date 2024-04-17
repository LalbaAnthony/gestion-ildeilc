












-- Création de la table des clients
CREATE TABLE Clients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    telephone VARCHAR(20),
    adresse TEXT NOT NULL
);

-- Création de la table des produits
CREATE TABLE Produits (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(255) NOT NULL,
    description TEXT,
    prix DECIMAL(10, 2) NOT NULL,
    stock INT DEFAULT 0
);

-- Création de la table des commandes
CREATE TABLE Commandes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    client_id INT,
    date_commande DATETIME DEFAULT CURRENT_TIMESTAMP,
    statut VARCHAR(50),  -- Valeurs possibles: 'en attente', 'en cours', 'livrée', 'payée'
    FOREIGN KEY (client_id) REFERENCES Clients(id)
);

-- Création de la table des détails des commandes (produits commandés)
CREATE TABLE DetailsCommande (
    commande_id INT,
    produit_id INT,
    quantite INT,
    FOREIGN KEY (commande_id) REFERENCES Commandes(id),
    FOREIGN KEY (produit_id) REFERENCES Produits(id),
    PRIMARY KEY (commande_id, produit_id)
);

-- Création de la table des factures
CREATE TABLE Factures (
    id INT AUTO_INCREMENT PRIMARY KEY,
    commande_id INT,
    date_facture DATETIME DEFAULT CURRENT_TIMESTAMP,
    montant_total DECIMAL(10, 2) NOT NULL,
    statut VARCHAR(50),  -- Valeurs possibles: 'en cours', 'payée'
    FOREIGN KEY (commande_id) REFERENCES Commandes(id)
);

-- Création de la table des bons de livraison
CREATE TABLE BonsDeLivraison (
    id INT AUTO_INCREMENT PRIMARY KEY,
    commande_id INT,
    date_livraison DATETIME,
    adresse_livraison TEXT,
    FOREIGN KEY (commande_id) REFERENCES Commandes(id)
);
