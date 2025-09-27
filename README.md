# Workout Tracker API

Ce projet est le backend pour une application de suivi d'entraînement. Il fournit une API RESTful permettant aux utilisateurs de s'inscrire, de s'authentifier, de créer des programmes d'entraînement personnalisés et de gérer un catalogue d'exercices.

## Fonctionnalités

*   **Authentification Sécurisée :** Inscription et connexion des utilisateurs avec authentification basée sur les jetons **JWT (JSON Web Token)**.
*   **Gestion des Utilisateurs :** Chaque utilisateur a accès uniquement à ses propres données.
*   **Catalogue d'Exercices :** Un système de **CRUD** complet pour gérer une liste d'exercices (nom, description, groupe musculaire, catégorie).
*   **Données Initiales (Seeding) :** La base de données est automatiquement alimentée avec une liste d'exercices de base au premier démarrage.
*   **Gestion des Programmes d'Entraînement :**
    *   Créer, lister, mettre à jour et supprimer des programmes d'entraînement personnels.
    *   Ajouter des exercices du catalogue à un programme, en spécifiant les **séries, répétitions, poids**, etc.
    *   Consulter le détail d'un programme avec sa liste complète d'exercices.
    *   Modifier ou retirer des exercices d'un programme existant.
*   **Documentation d'API Interactive :** Une interface **Swagger UI** est générée automatiquement pour explorer et tester tous les endpoints de l'API.

## Stack Technique

*   **Langage :** Java 21
*   **Framework :** Spring Boot 3.3.4
*   **Sécurité :** Spring Security 6
*   **Base de Données :** PostgreSQL
*   **Accès aux Données :** Spring Data JPA / Hibernate
*   **Migrations de BDD :** Flyway
*   **Authentification :** JSON Web Tokens (JWT)
*   **Documentation :** SpringDoc OpenAPI (Swagger UI)
*   **Build Tool :** Maven

## Démarrage Rapide

### Prérequis

*   JDK 21 ou supérieur
*   Maven 3.8+
*   Une instance PostgreSQL en cours d'exécution

### Installation

1.  **Clonez le dépôt :**
    ```bash
    git clone https://github.com/votre-nom-utilisateur/votre-repo.git
    cd votre-repo
    ```

2.  **Configurez la base de données :**
    Ouvrez le fichier `src/main/resources/application.properties` et modifiez les lignes suivantes pour correspondre à votre configuration PostgreSQL :
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/votre_db
    spring.datasource.username=votre_user
    spring.datasource.password=votre_mot_de_passe
    ```

3.  **Lancez l'application :**
    Vous pouvez lancer l'application via votre IDE ou en utilisant Maven :
    ```bash
    mvn spring-boot:run
    ```
    L'application démarrera sur `http://localhost:8080`. Flyway s'occupera de créer automatiquement le schéma de la base de données au premier lancement.

## Documentation de l'API

Une fois l'application lancée, une documentation interactive de l'API est disponible via Swagger UI.

*   **URL :** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Utiliser les endpoints protégés

1.  Utilisez l'endpoint `POST /api/v1/auth/login` pour vous connecter.
2.  Copiez le `token` JWT reçu dans la réponse.
3.  Sur la page Swagger UI, cliquez sur le bouton **"Authorize"** en haut à droite.
4.  Dans la fenêtre qui s'ouvre, collez votre token dans le champ `Value` après le mot `Bearer ` (exemple: `Bearer eyJhbGci...`).
5.  Cliquez sur "Authorize". Vous pouvez maintenant exécuter les endpoints protégés directement depuis l'interface.
