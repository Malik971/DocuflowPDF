import React, { useState } from "react";
import ProgressOverlay from "./ProgressOverlay";
import "./Formulaire.css";

export default function FormulaireContrat() {
  const [form, setForm] = useState({
    nom: "",
    adresse: "",
    email: "",
    date: "",
    typeContrat: "",
    montant: "",
    duree: "",
    marque: "",
    modele: "",
    kilometrage: "",
    cylindree: "",
    garantiesSupplementaires: "",
    franchise: "",
    plafondRemboursement: "",
    periodicitePaiement: "",
    modeleContrat: "",
  });
  const [isLoading, setIsLoading] = useState(false);
  const [message, setMessage] = useState("");

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    try {
      const response = await fetch("http://localhost:8080/api/documents/genererContrat", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(form),
      });

      if (response.ok) {
        setMessage("✅ Contrat généré et envoyé avec succès !");
        setForm({
          nom: "",
          adresse: "",
          email: "",
          date: "",
          typeContrat: "",
          montant: "",
          duree: "",
          marque: "",
          modele: "",
          kilometrage: "",
          cylindree: "",
          garantiesSupplementaires: "",
          franchise: "",
          plafondRemboursement: "",
          periodicitePaiement: "",
          modeleContrat: "",
        });
      } else {
        setMessage("❌ Une erreur est survenue lors de l'envoi.");
      }
    } catch (error) {
      console.error("Erreur lors de l'envoi :", error);
      setMessage("❌ Erreur réseau ou serveur inaccessible.");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <>
      <ProgressOverlay isVisible={isLoading} />
      <form className="formulaire" onSubmit={handleSubmit}>
        <h2>📝 Remplissez votre contrat d'assurance</h2>

        <div className="form-group">
          <center><h3>Modèle de contrat</h3>
          <select name="modeleContrat" value={form.modeleContrat} onChange={handleChange}>
            <option value="">Choisissez un modèle</option>
            <option value="modele1">🛵 Moto basique</option>
            <option value="modele2">🏍️ Moto premium</option>
            <option value="modele3">🧒 Scoot’ jeune</option>
            <option value="modele4">🔒 Franchise renforcée</option>
          </select></center>
        </div>

        <div className="form-group">
          <label>Nom *</label>
          <input type="text" name="nom" placeholder="Nom *" value={form.nom} onChange={handleChange} required />
        </div>

        <div className="form-group">
          <label>Adresse</label>
          <input type="text" name="adresse" placeholder="Pas obligé c'est une démo" value={form.adresse} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Email *</label>
          <input type="email" name="email" placeholder="Jean@gmail.com" value={form.email} onChange={handleChange} required />
        </div>

        <div className="form-group">
          <label>Date *</label>
          <input type="date" name="date" value={form.date} onChange={handleChange} required />
        </div>

        <div className="form-group">
          <label>Type de contrat *</label>
          <input type="text" name="typeContrat" value={form.typeContrat} onChange={handleChange} required/>
        </div>

        <div className="form-group">
          <label>Montant de l'assurance</label>
          <input type="text" name="montant" value={form.montant} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Durée du contrat</label>
          <input type="text" name="duree" value={form.duree} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Marque du véhicule</label>
          <input type="text" name="marque" value={form.marque} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Modèle du véhicule</label>
          <input type="text" name="modele" value={form.modele} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Kilométrage</label>
          <input type="text" name="kilometrage" value={form.kilometrage} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Cylindrée</label>
          <input type="text" name="cylindree" value={form.cylindree} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Garanties supplémentaires</label>
          <input type="text" name="garantiesSupplementaires" value={form.garantiesSupplementaires} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Franchise</label>
          <input type="text" name="franchise" value={form.franchise} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Plafond de remboursement</label>
          <input type="text" name="plafondRemboursement" value={form.plafondRemboursement} onChange={handleChange} />
        </div>

        <div className="form-group">
          <label>Périodicité de paiement</label>
          <input type="text" name="periodicitePaiement" value={form.periodicitePaiement} onChange={handleChange} />
        </div>

        <button type="submit" className="submit-button">🚀 Générer et recevoir par mail</button>
        {message && <p className="message">{message}</p>}
      </form>
    </>
  );
}