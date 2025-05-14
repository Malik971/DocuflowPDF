<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Déclaration URSSAF</title>
    <style>
        body { font-family: Arial; padding: 30px; color: #333; }
        h1 { color: #2b6cb0; }
        .section { margin-bottom: 20px; }
        .info { margin-bottom: 8px; }
    </style>
</head>
<body>
    <h1>Déclaration URSSAF</h1>
    <div class="section">
        <div class="info"><strong>Nom :</strong> ${nom}</div>
        <div class="info"><strong>Email :</strong> ${email}</div>
        <div class="info"><strong>Adresse :</strong> ${adresse}</div>
        <div class="info"><strong>Date :</strong> ${date}</div>
    </div>
    <div class="section">
        <div class="info"><strong>Type de contrat :</strong> ${typeContrat}</div>
        <div class="info"><strong>Montant déclaré :</strong> ${montant}</div>
        <div class="info"><strong>Durée :</strong> ${duree}</div>
        <div class="info"><strong>Numéro URSSAF :</strong> [à compléter]</div>
    </div>
    <footer>
        <p>Généré automatiquement via DocuFlow - ${date}</p>
    </footer>
</body>
</html>
