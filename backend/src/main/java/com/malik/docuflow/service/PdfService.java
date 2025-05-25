package com.malik.docuflow.service;

import com.malik.docuflow.model.DocumentData;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class PdfService {

    @Autowired
    // frreemarkerConfig est une instance de la classe Configuration de la bibliothèque FreeMarker
    // il y a d'autres templates que j'aurais pu charger quel que: Report, Jasper, etc.
    private Configuration freemarkerConfig;

    // byte[] est un tableau de bytes qui va contenir le contenu du PDF généré
    // byte[] un tableau de bytes c'est un type de données qui permet de stocker des données binaires
    // byte[] viens de la bibliothèque java.io
    public byte[] generatePdf(DocumentData data) throws Exception {
        // 1. Préparation des données pour le modèle
        Map<String, Object> model = new HashMap<>();
        model.put("nom", data.getNom());
        model.put("adresse", data.getAdresse());
        model.put("email", data.getEmail());
        model.put("date", data.getDate());
        model.put("typeContrat", data.getTypeContrat());
        model.put("montant", data.getMontant());
        model.put("duree", data.getDuree());
        model.put("marque", data.getMarque());
        model.put("modele", data.getModele());
        model.put("kilometrage", data.getKilometrage());
        model.put("cylindree", data.getCylindree());
        model.put("garantiesSupplementaires", data.getGarantiesSupplementaires());
        model.put("franchise", data.getFranchise());
        model.put("plafondRemboursement", data.getPlafondRemboursement());
        model.put("periodicitePaiement", data.getPeriodicitePaiement());

        // 2. Sélection dynamique du modèle FreeMarker
        String modeleContrat = data.getModeleContrat();
            if (modeleContrat == null || modeleContrat.isBlank()) {
            modeleContrat = "contrat"; // celui par "default"
        }

        // 3. Mapping entre le choix utilisateur et le fichier .ftl
        String nomTemplate = switch (modeleContrat) {
            case "alternance" -> "alternance.ftl";
            case "caf" -> "caf.ftl";
            case "sejour" -> "sejour.ftl";
            case "urssaf" -> "urssaf.ftl";
            default -> "contrat.ftl"; // fallback si aucune correspondance
        };

        // 4. Chargement du template et rendu HTML
        Template template = freemarkerConfig.getTemplate(nomTemplate);
        StringWriter stringWriter = new StringWriter();
        template.process(model, stringWriter);
        String htmlContent = stringWriter.toString();

        // 5. Génération du nom de fichier PDF (temporaire, local)
        String sanitizedNom = data.getNom().replaceAll("\\s+", "_").replaceAll("\\W+", "");
        String filename = String.format("contrat-%s-%s.pdf", sanitizedNom, data.getDate());

        File outputDir = new File("/tmp");
        // Crée le répertoire de sortie s'il n'existe pas
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        File outputFile = new File(outputDir, filename); 

        // Génération PDF
        // Utilisation de ByteArrayOutputStream pour éviter de créer un fichier temporaire
        // ByteArrayOutputStream viens de la bibliothèque java.io
        ByteArrayOutputStream memoryStream = new ByteArrayOutputStream();
        // Utilisation de PdfRendererBuilder pour générer le PDF
        // et l'écrire dans le ByteArrayOutputStream
        PdfRendererBuilder builder = new PdfRendererBuilder();
        // Utilisation de la méthode useFastMode pour améliorer les performances
        builder.useFastMode();
        // Utilisation de la méthode withHtmlContent pour ajouter le contenu HTML
        builder.withHtmlContent(htmlContent, null);
        // Utilisation de la méthode toStream pour écrire le PDF dans le ByteArrayOutputStream
        builder.toStream(memoryStream);
        // Utilisation de la méthode run pour générer le PDF
        builder.run();

        // Conversion du ByteArrayOutputStream en tableau de bytes
        // pour pouvoir l'envoyer dans la réponse HTTP
        byte[] pdfBytes = memoryStream.toByteArray();

        // 7. Sauvegarde sur le disque (utile pour logs ou tests locaux)
        try (OutputStream fileOut = new FileOutputStream(outputFile)) {
            fileOut.write(pdfBytes);
        }

        System.out.println("✅ PDF enregistré dans le fichier : " + outputFile.getAbsolutePath());
        System.out.println("modèle choisi : " + modeleContrat);

        return pdfBytes;
    }
}
