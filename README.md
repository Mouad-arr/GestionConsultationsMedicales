# 🏥 Gestion des Consultations Médicales

Application web de gestion des consultations médicales développée dans le cadre d'un projet académique, en utilisant **Java 21, Jakarta EE, JSP, Servlets et Hibernate**.

L'application a pour objectif de faciliter la gestion des informations médicales et l'organisation des consultations à travers une interface web simple et intuitive.

## 📋 Table des matières

- [Présentation du projet](#-présentation-du-projet)
- [Fonctionnalités](#-fonctionnalités)
- [Architecture du projet](#-architecture-du-projet)
- [Technologies utilisées](#-technologies-utilisées)
- [Prérequis](#-prérequis)
- [Installation et configuration](#-installation-et-configuration)
- [Exécution du projet](#-exécution-du-projet)
- [Équipe de réalisation](#-équipe-de-réalisation)

## 📌 Présentation du projet

Ce projet consiste à développer une application web permettant de gérer les consultations médicales.

L'application repose sur une architecture MVC avec une séparation en plusieurs couches (DAO, Service, Servlet et JSP), afin d'assurer une meilleure organisation du code et de faciliter sa maintenance.

La persistance des données est assurée par Hibernate, basé sur JPA, avec MySQL comme système de gestion de base de données.

## ✨ Fonctionnalités

L'application permet notamment de gérer :

- Les informations des patients.
- Les informations des médecins.
- Les consultations médicales.
- Les opérations de gestion des données (ajout, consultation, modification et suppression), selon les fonctionnalités implémentées.

## 🏗️ Architecture du projet

Le projet adopte une architecture MVC organisée en plusieurs couches :

| Couche | Responsabilité |
|---|---|
| Model | Représentation des entités métier. |
| DAO | Accès aux données et opérations de persistance. |
| Service | Gestion de la logique métier. |
| Controller | Traitement des requêtes HTTP à travers les Servlets. |
| View | Présentation des interfaces utilisateur avec JSP. |

### Fonctionnement général

```text
          Utilisateur
               |
               v
          Interface JSP
               |
               v
           Servlet
          (Controller)
               |
               v
          Service Layer
               |
               v
            DAO Layer
               |
               v
       JPA / Hibernate
               |
               v
          MySQL Database
```

## 🛠️ Technologies utilisées

### Backend
- Java 21
- Jakarta EE
- JSP (JavaServer Pages)
- Servlets
- JPA
- Hibernate ORM
- JDBC

### Frontend
- HTML5
- CSS3
- Bootstrap
- JavaScript

### Base de données
- MySQL

### Outils de développement
- IntelliJ IDEA
- Apache Maven
- Apache Tomcat
- DBeaver
- Git & GitHub
- Draw.io

## ⚙️ Prérequis

Avant de lancer le projet, assurez-vous de disposer des outils suivants :

- Java Development Kit (JDK) 21.
- Apache Maven.
- Apache Tomcat compatible avec la version de Jakarta EE utilisée.
- MySQL Server.
- IntelliJ IDEA (recommandé).
- DBeaver (facultatif, pour gérer la base de données).

## 🚀 Installation et configuration

### 1. Cloner le dépôt

```bash
git clone https://github.com/Mouad-arr/GestionConsultaionsMedicales
```

Accéder au répertoire du projet :

```bash
cd GestionConsultaionsMedicales
```

### 2. Configurer la base de données

- Démarrer le serveur MySQL.
- Créer la base de données utilisée par l'application.
- Exécuter le script SQL fourni dans le projet, s'il est disponible.
- Vérifier les paramètres de connexion à la base de données dans la configuration JPA/Hibernate.

Les paramètres de connexion doivent correspondre à votre environnement local :

```properties
database.url=jdbc:mysql://localhost:3306/<NOM_BASE>
database.username=<UTILISATEUR>
database.password=<MOT_DE_PASSE>
```

*Remarque : ces paramètres sont indicatifs. Adaptez-les au fichier de configuration réellement utilisé dans le projet.*

### 3. Installer les dépendances

À la racine du projet, exécuter :

```bash
mvn clean install
```

Cette commande permet de compiler le projet et de télécharger les dépendances déclarées dans le fichier `pom.xml`.

## ▶️ Exécution du projet

1. Ouvrir le projet dans IntelliJ IDEA.
2. Vérifier la configuration du JDK 21.
3. Configurer Apache Tomcat comme serveur d'exécution.
4. Configurer le déploiement de l'application.
5. Démarrer le serveur Tomcat.
6. Accéder à l'application depuis le navigateur à l'adresse correspondant au contexte de déploiement.

Exemple :

```text
http://localhost:8080/GestionConsultaionsMedicales
```

## 👥 Équipe de réalisation

Projet académique réalisé dans le cadre de la formation en informatique à l'École Nationale des Sciences Appliquées d'Agadir (ENSA Agadir).

**Membres de l'équipe :**
- Mouad Ouchelh
- Mohammed Laadimi

---

**Projet académique – Gestion des consultations médicales**