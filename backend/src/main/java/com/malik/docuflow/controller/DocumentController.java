package com.malik.docuflow.controller;

import com.malik.docuflow.model.DocumentData;
import com.malik.docuflow.service.MailService;
import com.malik.docuflow.service.PdfService;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*") // * pour autoriser toutes les origines durant le développement
// CrossOrigin : Permet de gérer les requêtes CORS (Cross-Origin Resource
// Sharing)
// @RestController : Indique que cette classe est un contrôleur REST
@RestController
// @RequestMapping : Définit le chemin de base pour toutes les requêtes de ce
// contrôleur
@RequestMapping("/api")
public class DocumentController {
    // @Autowired : Injecte automatiquement les dépendances dans la classe
    // une dépendance est une classe dont on a besoin pour faire fonctionner notre
    // classe
    @Autowired
    private PdfService pdfService;

    @Autowired
    private MailService mailService;

    // @PostMapping : Définit le chemin pour la méthode generatePdf
    @PostMapping("/generate-pdf")
    // ResponseEntity : Représente la réponse HTTP complète, y compris le corps, les
    // en-têtes et le statut
    // ResponseEntity viens de la bibliothèque Spring sont importation est : import
    // org.springframework.http.ResponseEntity;
    // byte[] : Tableau d'octets, utilisé pour représenter le contenu binaire du PDF
    public ResponseEntity<byte[]> generatePdf(@RequestBody DocumentData data) {

        try {
            byte[] pdfBytes = pdfService.generatePdf(data);

            String filename = String.format("contrat-%s-%s.pdf",
                    data.getNom().replaceAll("\\s+", "_"),
                    data.getDate());

            // Chemin du fichier sauvegardé
            File outputFile = new File("output", filename);

            // ✅ Appel de MailService
            mailService.envoyerContratAvecPdf(data, outputFile);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(
                    ContentDisposition.attachment().filename(filename).build());
            System.out.println("✅ PDF envoyé par mail à : " + data.getEmail());
            // retourne nouvelle réponse HTTP avec le tableau d'octets du PDF, les en-têtes
            // et le statut OK
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/test")
    public String test() {
        return "Hello Malik ✅";
        // pour tester si le serveur fonctionne
        // requête GET à l'URL https://docuflowpdf.onrender.com/api/test
    }
}
