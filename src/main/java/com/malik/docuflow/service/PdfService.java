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
        model.put("typeContrat", data.getTypeContrat());
        model.put("date", data.getDate());
    
        Template template = freemarkerConfig.getTemplate("contrat.ftl");
    
        StringWriter stringWriter = new StringWriter();
        template.process(model, stringWriter);
        String htmlContent = stringWriter.toString();
    
        // Nom du fichier PDF
        String filename = String.format("contrat-%s-%s.pdf",
                data.getNom().replaceAll("\\s+", "_"),
                data.getDate());
    
    
    
        // Écriture vers un fichier local
        File outputFile = new File("output", filename);
        OutputStream fileOut = new FileOutputStream(outputFile);
        System.out.println("✅ PDF enregistré dans le fichier : " + outputFile.getAbsolutePath());
    
        // Génération PDF
        ByteArrayOutputStream memoryStream = new ByteArrayOutputStream();
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.useFastMode();
        builder.withHtmlContent(htmlContent, null);
        builder.toStream(memoryStream);
        builder.run();
    
        byte[] pdfBytes = memoryStream.toByteArray();
    
        // Écriture sur disque
        fileOut.write(pdfBytes);
        fileOut.close();
    
        return pdfBytes; // Toujours renvoyer le PDF aussi
    }
}    
