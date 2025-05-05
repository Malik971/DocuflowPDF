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

    public byte[] generatePdf(DocumentData data) throws Exception {
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

        String modeleContrat = data.getModeleContrat();
            if (modeleContrat == null) {
            modeleContrat = "modele1"; // celui par "default"
        }

        String nomTemplate = switch (modeleContrat) {
            case "modele2" -> "contrat_premium.ftl";
            case "modele3" -> "contrat_jeune.ftl";
            case "modele4" -> "contrat_franchise.ftl";
            default -> "contrat.ftl"; // modèle 1
        };

        // Chargement du template
        Template template = freemarkerConfig.getTemplate(nomTemplate);

        // Rendu HTML
        StringWriter stringWriter = new StringWriter();
        template.process(model, stringWriter);
        String htmlContent = stringWriter.toString();

        // Nom du fichier PDF
        String filename = String.format("contrat-%s-%s.pdf",
                // Remplace les espaces par des underscores et enlève les caractères spéciaux
                // regex expliquation : \\s+ signifie un ou plusieurs espaces, et \\W+ signifie un ou plusieurs caractères non alphanumériques
                data.getNom().replaceAll("\\s+", "_"),
                data.getDate());

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

        // Sauvegarde sur le disque
        try (OutputStream fileOut = new FileOutputStream(outputFile)) {
            fileOut.write(pdfBytes);
        }

        System.out.println("✅ PDF enregistré dans le fichier : " + outputFile.getAbsolutePath());

        return pdfBytes;
    }
}
