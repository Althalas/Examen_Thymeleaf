# Application de Gestion de Projets

Application web Spring Boot avec Thymeleaf pour la gestion de projets, utilisateurs et tâches.

## Description

Cette application permet de gérer des projets, des utilisateurs et des tâches avec une interface web moderne utilisant Bootstrap. Elle inclut un système d'internationalisation (français/anglais), une validation complète des formulaires et une architecture MVC complète.

## Technologies utilisées

- **Backend** : Spring Boot 3.5.3
- **Template Engine** : Thymeleaf
- **Frontend** : Bootstrap 5.3.2, Bootstrap Icons
- **Langage** : Java 21
- **Build Tool** : Maven
- **Validation** : Spring Validation (Bean Validation)

## Fonctionnalités

### Gestion des utilisateurs
- Création d'utilisateurs avec validation
- Liste des utilisateurs
- Interface responsive
- Validation des noms d'utilisateur (3-50 caractères)

### Gestion des projets
- Création de projets avec attribution d'un créateur
- Liste des projets
- Détails d'un projet avec ses tâches
- Validation des noms de projet (3-100 caractères)

### Gestion des tâches
- Création de tâches avec attribution à un projet et un utilisateur
- Liste des tâches
- Gestion des statuts (TODO, EN_COURS, TERMINÉ)
- Mise à jour du statut des tâches
- Validation des titres de tâches (3-200 caractères)

### Interface utilisateur
- Design moderne avec Bootstrap
- Navigation responsive
- Tableaux interactifs
- Formulaires stylisés avec validation visuelle
- Messages d'erreur contextuels
- Support multilingue

### Internationalisation
- Support français et anglais
- Changement de langue dynamique
- Messages localisés pour tous les éléments

### Validation des formulaires
- Validation côté serveur avec Spring Validation
- Affichage des erreurs en temps réel
- Messages d'erreur personnalisés
- Rétention des données en cas d'erreur
- Classes CSS conditionnelles pour le feedback visuel

## Structure du projet

```
src/main/
├── java/com/humanbooster/Examen/
│   ├── config/
│   │   └── MvcConfig.java
│   ├── controller/
│   │   ├── ProjectController.java
│   │   ├── TaskController.java
│   │   └── UserController.java
│   ├── model/
│   │   ├── Project.java
│   │   ├── Task.java
│   │   ├── TaskStatus.java
│   │   └── User.java
│   ├── service/
│   │   ├── ProjectService.java
│   │   ├── TaskService.java
│   │   └── UserService.java
│   └── ExamenApplication.java
└── resources/
    ├── messages.properties
    ├── messages_en.properties
    ├── static/
    │   └── style.css
    └── templates/
        ├── fragments/
        │   └── layout.html
        ├── create-project.html
        ├── create-task.html
        ├── create-user.html
        ├── details-project.html
        ├── index.html
        ├── list-project.html
        ├── list-task.html
        └── list-user.html
```

## Installation et démarrage

### Prérequis
- Java 17 ou supérieur
- Maven 3.6+

### Installation
1. Cloner le repository
```bash
git clone [url-du-repo]
cd Examen
```

2. Compiler le projet
```bash
mvn clean compile
```

3. Lancer l'application
```bash
mvn spring-boot:run
```

4. Accéder à l'application
```
http://localhost:8080
```

## Architecture

### Modèles (Models)
- **User** : Représente un utilisateur avec un ID et un nom d'utilisateur
- **Project** : Représente un projet avec un nom et un créateur
- **Task** : Représente une tâche avec un titre, un statut, un assigné et un projet
- **TaskStatus** : Enumération des statuts possibles (TODO, IN_PROGRESS, DONE)

### Services
- **UserService** : Gestion des utilisateurs en mémoire avec génération automatique d'ID
- **ProjectService** : Gestion des projets avec relations vers les utilisateurs
- **TaskService** : Gestion des tâches avec relations vers les projets et utilisateurs

### Contrôleurs
- **UserController** : Gestion des routes utilisateurs (/users/*)
- **ProjectController** : Gestion des routes projets (/projects/*)
- **TaskController** : Gestion des routes tâches (/tasks/*)

### Configuration
- **MvcConfig** : Configuration de l'internationalisation

## Fonctionnalités techniques

### Stockage des données
- Stockage en mémoire avec listes statiques
- Génération automatique d'ID avec compteur incrémental
- Données persistantes pendant la session

### Interface utilisateur
- Design responsive avec Bootstrap
- Composants interactifs

## Fonctionnalités avancées

### Validation des formulaires
- Annotations `@NotBlank`, `@Size`, `@NotNull`
- Messages d'erreur personnalisés
- Affichage conditionnel des erreurs

### Internationalisation
- Fichiers de messages séparés (français/anglais)
- Changement de langue via paramètre URL
- Traduction de tous les éléments d'interface

### Gestion des relations
- Relations entre projets, utilisateurs et tâches
- Validation des références
- Gestion des cas d'erreur