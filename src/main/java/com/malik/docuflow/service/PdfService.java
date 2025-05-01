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

        String nomTemplate = switch (data.getModeleContrat()) {
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
                data.getNom().replaceAll("\\s+", "_"),
                data.getDate());

        File outputDir = new File("/tmp");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }

        File outputFile = new File(outputDir, filename);

        // Génération PDF
        ByteArrayOutputStream memoryStream = new ByteArrayOutputStream();
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.useFastMode();
        builder.withHtmlContent(htmlContent, null);
        builder.toStream(memoryStream);
        builder.run();

        byte[] pdfBytes = memoryStream.toByteArray();

        // Sauvegarde sur le disque
        try (OutputStream fileOut = new FileOutputStream(outputFile)) {
            fileOut.write(pdfBytes);
        }

        System.out.println("✅ PDF enregistré dans le fichier : " + outputFile.getAbsolutePath());

        return pdfBytes;
    }
}
