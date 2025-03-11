# 📚 Application Web de Gestion des Examens

## 📝 Description  
Cette application web permet la gestion des utilisateurs, des cours, des examens et des quiz, avec un système de rôles définissant les droits d’accès aux différentes fonctionnalités.  

Elle est basée sur un **backend Spring Boot**, une **base de données H2**, et un **frontend Vue.js** pour une interface utilisateur réactive et moderne.  

---

## 🚀 Technologies Utilisées  

### 📌 Backend :  
- **Spring Boot** - Framework Java pour créer des API REST performantes.  
- **Spring Security** - Gestion de l’authentification et des rôles utilisateurs.  
- **JPA (Hibernate)** - Gestion des entités et interactions avec la base de données.  
- **Base de données H2** - Base de données embarquée pour simplifier le développement et les tests.  
- **Maven** - Gestionnaire de dépendances et build du projet.  

### 🎨 Frontend :  
- **Vue.js** - Framework JavaScript pour une interface dynamique.  
- **Vue Router** - Gestion de la navigation entre les différentes pages.  
- **Pinia** - Gestion des états côté frontend.  
- **Axios** - Communication avec les API REST du backend.  
- **Tailwind CSS** (optionnel) - Pour un design moderne et rapide à mettre en place.  

---

## ⚙️ Architecture  

### 🏗 Backend - **Spring Boot**  
L'architecture suit le **Model, Controller, Repositories Service** avec une séparation claire des responsabilités :  

- 📁 **Model** : Définit les entités en lien avec la base de données (JPA).  
- 📁 **Repository** : Interface pour interagir avec la base de données.  
- 📁 **Service** : Contient la logique métier et la gestion des entités.  
- 📁 **Controller** : Expose les fonctionnalités via des **API REST**.  

#### 📌 Entités du Modèle :  
- **User** : Stocke les informations des utilisateurs avec un rôle spécifique.  
- **Role** : Enumération des rôles (**ADMIN, TEACHER, STUDENT**).  
- **Course** : Contient les informations des cours avec relation **ManyToMany** vers les étudiants.  
- **Exam** : Géré par un professeur et associé à un cours et des étudiants.  
- **Quiz** : Composé de plusieurs questions via une relation **ManyToMany**.  
- **Question** : Associée à un examen via une relation **ManyToOne**.  
- **LoginRequest** : DTO utilisé uniquement pour l'authentification.  

💡 **Spring Security** est utilisé pour gérer l’authentification et les rôles d’accès.  

---

### 🎨 Frontend - **Vue.js**  
L’architecture frontend est modulaire et suit les bonnes pratiques Vue.js :  

- 📁 **views/** : Contient les pages principales de l’application (Accueil, Examens, Quiz, etc.).  
- 📁 **components/** : Regroupe les composants réutilisables.  
- 📁 **store/** : Gestion des états avec Pinia pour synchroniser les données.  
- 📁 **router/** : Configuration des routes avec Vue Router.  

🔐 **Gestion des rôles** :  
- Les **admins** peuvent gérer tous les utilisateurs et leurs droits.  
- Les **professeurs** peuvent créer des examens et gérer les quiz.  
- Les **étudiants** ont un accès restreint aux cours et examens.  

---

## 🛠 Installation et Exécution  

### 🔧 Prérequis  
- **Java 17+**  
- **Node.js 16+**  
- **Maven**  
- **Vue CLI**

### 📌 Installation Backend  
1. Cloner le projet :  
   ```bash
   git clone https://github.com/chakibSA2/Exam-Management-Application.git
   cd demo3
2. Installer les dépendances et lancer le serveur:
  ```bash
  mvn clean install
   ```
3. Lancer le serveurs apache avec l'interface ou dans la classe main 
  - L’API sera accessible sur http://localhost:8080.

### 📌 Installation Frontend
1. Se rendre dans le dossier Vue.js :
  ```bash
cd demo3/frontend 
  ```
2. Installer les dépendances :
  ``` bash
npm install
  ```
3. Lancer l’application :
  ``` bash
npm run dev
  ```
- L’interface utilisateur sera accessible sur http://localhost:5173.

### 📌 Documentation D'api 
# Les api's de l'application on toute était documenté et peuvent etre tester sur SwaggerUI 
- L’interface utilisateur sera accessible sur http://localhost:8080/swagger-ui/index.html.

### 📌 Base H2 
- L’interface utilisateur sera accessible sur http://localhost:8080/h2-console.

## 📡 API Endpoints

| Méthode | Endpoint         | Description |
|---------|-----------------|-------------|
| **POST**   | `/auth/login`     | Connexion utilisateur |
| **GET**    | `/users`          | Récupérer tous les utilisateurs |
| **POST**   | `/users`          | Ajouter un utilisateur |
| **PUT**    | `/users/{id}`     | Modifier un utilisateur |
| **DELETE** | `/users/{id}`     | Supprimer un utilisateur |
| **GET**    | `/courses`        | Liste des cours |
| **POST**   | `/courses`        | Ajouter un cours |
| **GET**    | `/exams`          | Liste des examens |
| **POST**   | `/exams`          | Ajouter un examen |
| **POST**   | `/quiz`           | Créer un quiz |

> 🔐 **Note** : Certains endpoints nécessitent des autorisations spécifiques selon le rôle de l’utilisateur.


### 📌 Améliorations Futures
- Intégration d’une base de données PostgreSQL pour la production.
- Mise en place de WebSockets pour la gestion des quiz en temps réel.
- Ajout d’un système de notifications pour les examens.
- Interface plus avancée avec Vue 3 + Composition API.
