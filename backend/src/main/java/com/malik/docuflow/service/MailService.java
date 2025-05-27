 package com.malik.docuflow.service;

import com.malik.docuflow.model.DocumentData;
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

    public void envoyerContratAvecPdf(DocumentData data, File fichierPdfOriginal) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(data.getEmail());
        helper.setSubject("Votre contrat d'assurance moto est prêt – DocuFlow");

        String contenuHtml = String.format("""
        <!DOCTYPE html>
        <html lang="fr">
        <head>
          <meta charset="UTF-8">
          <meta name="viewport" content="width=device-width, initial-scale=1.0">
          <title>Contrat prêt</title>
        </head>
        <body style="font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f4f6f8;">
          <div style="max-width: 600px; margin: auto; background: #ffffff; padding: 30px; border-radius: 10px;">
            <h2 style="color: #007bff; text-align: center;">📄 Contrat d'assurance généré</h2>

            <p style="font-size: 16px;">Bonjour %s,</p>

            <p style="font-size: 16px;">Merci de votre confiance. Votre contrat d'assurance moto est prêt !</p>

            <h3 style="color: #2a4365;">Résumé :</h3>
            <ul style="font-size: 15px; line-height: 1.6;">
              <li><strong>Nom :</strong> %s</li>
              <li><strong>Contrat :</strong> %s</li>
              <li><strong>Date :</strong> %s</li>
              <li><strong>Montant :</strong> %s €</li>
              <li><strong>Durée :</strong> %s</li>
              <li><strong>Véhicule :</strong> %s %s – %s km – %s</li>
              <li><strong>Garanties :</strong> %s</li>
              <li><strong>Franchise :</strong> %s</li>
              <li><strong>Plafond remboursement :</strong> %s</li>
              <li><strong>Paiement :</strong> %s</li>
            </ul>

            <p style="margin-top: 30px;">✅ Vous trouverez votre contrat en pièce jointe.</p>

            <p style="font-size: 14px; color: gray; text-align: center;">
              DocuFlow • Propulsé par Malik Ibo 🚀
            </p>
          </div>

          <div style="text-align: center; font-size: 12px; color: gray; margin-top: 10px;">
            Merci de ne pas répondre à cet email automatique.
          </div>
        </body>
        </html>
        """,
        data.getNom(), data.getNom(), data.getTypeContrat(), data.getDate(),
        data.getMontant(), data.getDuree(),
        data.getMarque(), data.getModele(), data.getKilometrage(), data.getCylindree(),
        data.getGarantiesSupplementaires(), data.getFranchise(), data.getPlafondRemboursement(), data.getPeriodicitePaiement());

        helper.setText(contenuHtml, true);

        File fichierTmp = new File("/tmp/" + fichierPdfOriginal.getName());
        FileSystemResource file = new FileSystemResource(fichierTmp);
        helper.addAttachment(fichierTmp.getName(), file);

        mailSender.send(message);
        System.out.println("📨 Email HTML envoyé à : " + data.getEmail());
    }
}
