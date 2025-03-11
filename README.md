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
|------------|-----------------------------------------------------|
| Méthode    | Endpoint                                            |
|------------|-----------------------------------------------------|
| **PUT**    | `/api/users/update`                                 | 
| **POST**   | `/api/users/creat`                                  |  
| **GET**    | `/api/users/{userId}`                               | 
| **DELETE** | `/api/users/{userId}`                               |
| **GET**    | `/api/users/techers`                                | 
| **GET**    | `/api/users/students`                               |
| **GET**    | `/api/users/role/{role}`                            |
| **GET**    | `/api/users/all`                                    | 
| **PUT**    | `/api/quizzes/{quizId}/assos`                       | 
| **PUT**    | `/api/quizzes/update/{quizId}`                      | 
| **POST**   | `/api/quizzes/create`                               | 
| **GET**    | `/api/quizzes/{quizId}`                             | 
| **DELETE** |`/api/quizzes/{quizId}`                              | 
| **GET**    | `/api/quizzes/all`                                  | 
| **GET**    | `/api/quizzes/{quizId}/remove-questions             | 
| **PUT**    | `/api/questions/update`                             | 
| **POST**   | `/api/questions/create`                             | 
| **GET**    |`/api/questions/{questionId}`                        | 
| **GET**    | `/api/questions/exam/{examId}`                      | 
| **GET**    | `/api/questions/all`| Remove question               |
| **DELETE** | `/api/questions/delete/{questionId}`                | 
| **PUT**    |`/api/exams/update`                                  | 
| **POST**   | `/api/exams/{examId}/add-student/{studentId}        | 
| **POST**   | `/api/exams/create`                                 | 
| **GET**    | `/api/exams/{examId}`                               | 
| **DELET**  | `/api/exams/{examId}`                               | 
| **GET**    |`/api/exams/teacher/{teacherId}/First`               | 
| **GET**    | `/api/exams/teacher/{teacherId}/All`                | 
| **GET**    | `/api/exams/all`                                    |
| **DELETE** | `/api/exams/{examId}/remove-student/{studentId}`    | 
| **PUT**    | `/api/courses/update`                               | 
| **POST**   | `/api/courses/{courseId}/add-student/{studentId}    | 
| **POST**   | `/api/courses/create`                               | 
| **GET**    |`/api/courses/{courseId}`                            | 
| **GET**    | `/api/courses/all`                                  | 
| **DELETE** | `/api/courses/{courseId}/remove-student/{studentId}`|
| **DELETE** | `/api/courses/delete/{courseId}`                    | 
|------------|-----------------------------------------------------|

> 🔐 **Note** : Certains endpoints nécessitent des autorisations spécifiques selon le rôle de l’utilisateur.


### 📌 Améliorations Futures
- Intégration d’une base de données PostgreSQL pour la production.
- Mise en place de WebSockets pour la gestion des quiz en temps réel.
- Ajout d’un système de notifications pour les examens.
- Interface plus avancée avec Vue 3 + Composition API.


> 🔐 **Note** : pour la prémiére connexion a l'application il faut se rendre sur swagerui et crée le premier utilisateur
ou bien se rendre sur l'url http://localhost:5173/users pour le crée 