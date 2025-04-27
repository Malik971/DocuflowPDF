# 🧾 DocuFlow – Générateur de documents Java (Spring Boot + FreeMarker + PDF)

DocuFlow est une application backend Spring Boot qui permet de :

- Recevoir des données via une API REST (`POST /api/generate-document`)
- Générer dynamiquement un document HTML avec FreeMarker
- Convertir ce HTML en PDF grâce à OpenHTML2PDF
- Envoyer le PDF en tant que réponse téléchargeable
- (À venir : enregistrement sur disque, envoi par mail, nom dynamique...)

## ✅ Fonctionnalités actuelles

### 1. API REST : `/api/generate-document`

- Reçoit un JSON contenant les informations d'un contrat
- Enregistre les données en base (H2)
- Retourne un message de confirmation

### 2. API REST : `/api/generate-pdf`

- Reçoit les mêmes données JSON
- Injecte les données dans un template `.ftl` FreeMarker
- Génére un PDF avec `openhtmltopdf-pdfbox`
- Retourne le fichier PDF en téléchargement via HTTP

### Exemple de JSON envoyé via Postman

```json
{
  "nom": "Malik",
  "adresse": "123 rue Moto",
  "typeContrat": "Assurance Moto",
  "date": "2025-05-01"
}

## Technologies utilisées

- Java 17
- Spring Boot 3.4.4
- Spring Web, Data JPA
- FreeMarker (.ftl)
- H2 (en développement) / MySQL (en production)
- Maven
- OpenHTML2PDF
- Postman (tests API)