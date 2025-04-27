package com.malik.docuflow.controller;

import com.malik.docuflow.model.DocumentData;
import com.malik.docuflow.service.MailService;
import com.malik.docuflow.service.PdfService;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DocumentController {

    @Autowired
    private PdfService pdfService;

    @Autowired
    private MailService mailService;

    @PostMapping("/generate-pdf")
    public ResponseEntity<byte[]> generatePdf(@RequestBody DocumentData data) {
        try {
            byte[] pdfBytes = pdfService.generatePdf(data);

            String filename = String.format("contrat-%s-%s.pdf",
                data.getNom().replaceAll("\\s+", "_"),
                data.getDate());

            // Chemin du fichier sauvegardé
            File outputFile = new File("output", filename);

            // ✅ Appel de MailService
            mailService.envoyerContratAvecPdf(data.getEmail(), outputFile);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(
                ContentDisposition.attachment().filename(filename).build()
        );

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/test")
    public String test() {
        return "Hello Malik ✅";
    }
}
