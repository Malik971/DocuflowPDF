<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8"/>
    <title>Contrat d'assurance moto</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            color: #333;
        }
        h1, h2, h3 {
            color: #2b6cb0;
        }
        .section {
            margin-bottom: 25px;
        }
        .info {
            margin-bottom: 10px;
        }
        .signature {
            margin-top: 60px;
        }
    </style>
</head>
<body>
    <h1>Contrat d'assurance moto</h1>

    <div class="section">
        <h2>Informations personnelles</h2>
        <div class="info">Nom : ${nom}</div>
        <div class="info">Adresse : ${adresse}</div>
        <div class="info">Email : ${email}</div>
        <div class="info">Date de souscription : ${date}</div>
    </div>

    <div class="section">
        <h2>Détails du contrat</h2>
        <div class="info">Type de contrat : ${typeContrat}</div>
        <div class="info">Montant : ${montant}</div>
        <div class="info">Durée : ${duree}</div>
        <div class="info">Franchise : ${franchise}</div>
        <div class="info">Plafond de remboursement : ${plafondRemboursement}</div>
        <div class="info">Périodicité de paiement : ${periodicitePaiement}</div>
    </div>

    <div class="section">
        <h2>Informations sur le véhicule</h2>
        <div class="info">Marque : ${marque}</div>
        <div class="info">Modèle : ${modele}</div>
        <div class="info">Kilométrage : ${kilometrage}</div>
        <div class="info">Cylindrée : ${cylindree}</div>
    </div>

    <div class="section">
        <h2>Garanties supplémentaires</h2>
        <p>
        L’assuré déclare l’exactitude des informations fournies et reconnaît avoir pris connaissance des conditions générales, garanties, exclusions, franchises et montants. Toute fausse déclaration peut annuler le contrat.
        </p>
        <div class="info">${garantiesSupplementaires}</div>
    </div>

    <div class="section signature">
        <div>Signature de l'assuré : ______________________________</div>
        <div>Date : ${date}</div>
    </div>

    <footer>
        <p style="font-size: 0.9em; color: gray; margin-top: 30px;">Ce contrat est généré automatiquement par DocuFlow et ne constitue pas un document contractuel officiel sans validation de la Mutuelle des Motards.</p>
    </footer>
</body>
</html>
