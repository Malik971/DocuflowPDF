package com.malik.docuflow.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void envoyerContratAvecPdf(String destinataire, File fichierPdfOriginal) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(destinataire);
        helper.setSubject("Votre contrat d'assurance DocuFlow 🚀");

        // 🖌️ Utilisation de HTML ici
        String contenuHtml = """
        <!DOCTYPE html>
        <html lang="fr">
        <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Votre contrat est prêt</title>
        </head>
        <body style="font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f9f9f9;">

        <div style="max-width: 600px; margin: auto; background: #ffffff; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.05);">
            <h2 style="color: #007bff; text-align: center;">Votre contrat est prêt !</h2>

            <p style="font-size: 16px; color: #333333;">Bonjour,</p>

            <p style="font-size: 16px; color: #333333;">
            Merci de votre confiance. Vous trouverez en pièce jointe votre <strong>contrat d'assurance moto</strong> généré automatiquement.
            </p>

            <div style="text-align: center; margin: 30px 0;">
            <a href="http://localhost:8080/fake-url" target="_blank" style="
                display: inline-block;
                padding: 12px 24px;
                font-size: 18px;
                color: #ffffff;
                background-color: #007bff;
                text-decoration: none;
                border-radius: 5px;">
                📄 Télécharger le contrat
            </a>
            </div>

            <p style="font-size: 14px; color: gray; text-align: center;">
            DocuFlow • Propulsé par Malik 🚀
            </p>
        </div>

        <div style="max-width: 600px; margin: auto; padding: 10px; text-align: center; font-size: 12px; color: gray;">
            Merci de ne pas répondre à cet email automatique.
        </div>

        </body>
        </html>
        """;

        helper.setText(contenuHtml, true);

        // 🧷 Pièce jointe PDF
        File fichierTmp = new File("/tmp/" + fichierPdfOriginal.getName());
        FileSystemResource file = new FileSystemResource(fichierTmp);
        helper.addAttachment(fichierTmp.getName(), file);

        mailSender.send(message);
        System.out.println("📨 Email HTML envoyé à : " + destinataire);
    }
}

