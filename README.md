# 🎯Objectif du projet
### Créer une application Java Spring Boot capable de :

Recevoir des données via une API REST,

Générer dynamiquement des contrats PDF à partir d'un template FreeMarker,

Envoyer automatiquement ces PDF par email aux utilisateurs,

Proposer un déploiement en ligne pour rendre l'application accessible à tous.

### Ce projet a été pensé pour :

Renforcer mes compétences en Java, Spring Boot, API REST, déploiement cloud,

Montrer ma capacité à construire une application complète (backend + emailing + PDF),

Présenter un projet concret et professionnel sur mon portfolio GitHub.

## ⚙️ Choix de l’environnement
🖥️ Matériel :
Ordinateur Windows 11

Visual Studio Code (VSCode) comme éditeur de code

☕ Langage principal :
Java 17 (via OpenJDK)

📦 Frameworks et outils techniques :

## Outil & Utilité
Spring Boot • 3.4.5	Créer l'API REST et gérer l'architecture backend
Maven •	Automatiser le build du projet
FreeMarker •	Générer dynamiquement du HTML pour la création de PDF
OpenHTML2PDF •	Convertir du HTML en PDF
H2 Database •	Base de données en mémoire pour stocker temporairement les contrats
Postman	• Tester les requêtes API en local
Render.com •	Déployer gratuitement l’application en ligne

## 🛠️ Configuration initiale
Installation de Java 17 ✅

Installation de Maven (gestionnaire de dépendances Java) ✅

Installation et configuration de Spring Boot Extension Pack sur VSCode ✅

Création d'un projet Spring Boot avec les dépendances :

• Spring Web

• Spring Data JPA

• MySQL Driver (remplacé par H2 pour commencer)

• FreeMarker

• Lombok (pour éviter d’écrire du code répétitif comme les getters/setters)

## ✍️ Première API REST créée
POST /api/generate-document

Reçoit les données JSON d'un utilisateur

Stocke les données dans H2 Database pour la première version

Exemple de requête POST envoyée via Postman :

json
Copier
Modifier
{
  "nom": "Malik",
  "adresse": "123 rue Moto",
  "typeContrat": "Assurance Moto",
  "date": "2025-05-01",
  "email": "malik97un@gmail.com"
}


# 🔥 Liste complète des obstacles techniques que j'ai rencontré
## 1️⃣ Erreur : Java non reconnu dans le terminal
### ❌ Lors du premier ./mvnw spring-boot:run, Java n'était pas reconnu.

Cause :

• Mauvaise version de Java installée

• Java 17 nécessaire pour Spring Boot 3+, mais autre version active

### Solution mise en place :
✅ Installation de OpenJDK 17 via Adoptium
✅ Vérification avec java -version
✅ Nettoyage des anciennes versions (gestion du PATH)


## 2️⃣ Erreur : Maven ne trouve pas Java 17
### ❌ release version 17 not supported

Cause :

• Maven compilait avec une version incorrecte de Java

### Solution mise en place :
✅ Ajout des propriétés correctes dans pom.xml :

xml
Copier
Modifier
<properties>
    <java.version>17</java.version>
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
</properties>
✅ Rebuild complet avec ./mvnw clean package


## 3️⃣ Erreur : Port 8080 déjà utilisé
### ❌ Web server failed to start. Port 8080 was already in use.

Cause :

Ancienne instance de serveur Spring Boot encore active

### Solution mise en place :
✅ Kill manuel du processus occupé (avec netstat ou via gestionnaire de tâches)
✅ Relancement propre du serveur


## 4️⃣ Erreur : Pas de datasource configuré
### ❌ Failed to configure a DataSource: 'url' attribute is not specified

Cause :

Pas de configuration de base de données dans application.properties

### Solution mise en place :
✅ Ajout d'une base de données H2 en mémoire pour simplifier le développement :

properties
Copier
Modifier
spring.datasource.url=jdbc:h2:mem:docuflowdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true


## 5️⃣ Problème : Accès interdit aux API (401 Unauthorized)
### ❌ Erreur 401 sur Postman : The request is unauthenticated.

Cause :

Par défaut, Spring Security bloque toutes les requêtes non authentifiées

### Solution mise en place :
✅ Ajout d'une configuration de sécurité personnalisée :

java
Copier
Modifier
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .build();
    }
}
✅ Toutes les requêtes sont désormais libres pendant la phase de développement.


## 6️⃣ Problème : Permissions sur Render (./mvnw Permission Denied)
### ❌ bash: ./mvnw: Permission denied

Cause :

Sur Windows, les fichiers ne sont pas automatiquement executable pour Unix (Render utilise Linux)

### Solution mise en place :
✅ Ajout du droit d'exécution en local :

bash
Copier
Modifier
chmod +x mvnw
git add mvnw
git commit -m "Fix mvnw permission for Render deploy"
git push


## 7️⃣ Problème : mvn non trouvé sur Render (mvn: command not found)
### ❌ bash: line 1: mvn: command not found

Cause :

Render ne supporte pas directement Maven sans Docker ou configuration spéciale

### Solution mise en place :
✅ Suppression du mode "Web Service" classique
✅ Passage au mode Docker avec un vrai Dockerfile :

Dockerfile
Copier
Modifier
FROM maven:3.9.4-eclipse-temurin-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
✅ Application maintenant auto-buildée correctement avec un .jar sur Render


## 8️⃣ Problème : Fichier application-secrets.properties non trouvé
### ❌ Config data resource 'class path resource [application-secrets.properties]' does not exist

Cause :

Dépendance à un fichier local non présent sur Render

### Solution mise en place :
✅ Utilisation de variables d'environnement Render pour SMTP Gmail :


Clé	Valeur
SPRING_MAIL_USERNAME	ton.email@gmail.com
SPRING_MAIL_PASSWORD	ton mot de passe Gmail application
SPRING_MAIL_HOST	smtp.gmail.com
SPRING_MAIL_PORT	587

✅ Refactor du application.properties pour lire ces valeurs dynamiquement :

properties
Copier
Modifier
spring.mail.host=${SPRING_MAIL_HOST}
spring.mail.port=${SPRING_MAIL_PORT}
spring.mail.username=${SPRING_MAIL_USERNAME}
spring.mail.password=${SPRING_MAIL_PASSWORD}
spring.mail.properties.mail.smtp.auth=${SPRING_MAIL_PROPERTIES_MAIL_SMTP_AUTH}
spring.mail.properties.mail.smtp.starttls.enable=${SPRING_MAIL_PROPERTIES_MAIL_SMTP_STARTTLS_ENABLE}