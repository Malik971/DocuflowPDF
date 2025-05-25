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
    setMessage("");

    const formData = new FormData(e.target);
    const data = {};
    formData.forEach((v, k) => (data[k] = v));

    try {
      const response = await fetch(
        "https://docuflowpdf.onrender.com/api/generate-pdf",
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(data),
        }
      );

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
      <ProgressOverlay
        isVisible={isLoading}
        loadingMessage="Envoi du contrat en cours..."
      />
      <form className="formulaire" onSubmit={handleSubmit}>
        <h2>📝 Remplissez votre contrat d'assurance</h2>

        <div className="form-group">
          <center>
            <h3>Modèle de contrat</h3>
            <select
              name="modeleContrat"
              value={form.modeleContrat}
              onChange={handleChange}
            >
              <option value="">Choisissez un modèle</option>
              <option value="alternance">🛵 alternance</option>
              <option value="caf">🏍️ caf</option>
              <option value="sejour">🧒 sejour</option>
              <option value="urssaf">🔒 urssaf</option>
            </select>
          </center>
        </div>

        <div className="form-group">
          <label>Nom *</label>
          <input
            type="text"
            name="nom"
            placeholder="Nom *"
            value={form.nom}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label>Adresse</label>
          <input
            type="text"
            name="adresse"
            placeholder="Pas obligé c'est une démo"
            value={form.adresse}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>Email *</label>
          <input
            type="email"
            name="email"
            placeholder="Jean@gmail.com"
            value={form.email}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label>Date *</label>
          <input
            type="date"
            name="date"
            value={form.date}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label>Type de contrat *</label>
          <select
            name="typeContrat"
            value={form.typeContrat}
            onChange={handleChange}
            required
          >
            <option value="">Type de contrat *</option>
            <option value="Responsabilité civile">Responsabilité civile</option>
            <option value="Tous risques">Tous risques</option>
            <option value="Vol & incendie">Vol & incendie</option>
          </select>
        </div>

        <div className="form-group">
          <label>Montant de l'assurance</label>
          <input
            type="text"
            name="montant"
            value={form.montant}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>Durée du contrat</label>
          <select name="duree" value={form.duree} onChange={handleChange}>
            <option value="">Durée</option>
            <option value="1 an">1 an</option>
            <option value="2 ans">2 ans</option>
            <option value="3 ans">3 ans</option>
          </select>
        </div>

        <div className="form-group">
          <label>Marque du véhicule</label>
          <input
            type="text"
            name="marque"
            value={form.marque}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>Modèle du véhicule</label>
          <input
            type="text"
            name="modele"
            value={form.modele}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>Kilométrage</label>
          <input
            type="text"
            name="kilometrage"
            value={form.kilometrage}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>Cylindrée</label>
          <input
            type="text"
            name="cylindree"
            value={form.cylindree}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>Garanties supplémentaires</label>
          <select
            name="garantiesSupplementaires"
            value={form.garantiesSupplementaires}
            onChange={handleChange}
          >
            <option value="">Aucune</option>
            <option value="Assistance 0km">Assistance 0km</option>
            <option value="Protection juridique">Protection juridique</option>
            <option value="Equipement pilote">Équipement pilote</option>
          </select>
        </div>

        <div className="form-group">
          <label>Franchise</label>
          <select
            name="franchise"
            value={form.franchise}
            onChange={handleChange}
          >
            <option value="">Niveau de franchise</option>
            <option value="Aucune">Aucune</option>
            <option value="Franchise standard">Standard</option>
            <option value="Franchise élevée">Élevée</option>
          </select>
        </div>

        <div className="form-group">
          <label>Plafond de remboursement</label>
          <input
            type="text"
            name="plafondRemboursement"
            value={form.plafondRemboursement}
            onChange={handleChange}
          />
        </div>

        <div className="form-group">
          <label>Périodicité de paiement</label>
          <select
            name="periodicitePaiement"
            value={form.periodicitePaiement}
            onChange={handleChange}
          >
            <option value="">Choisissez la périodicité</option>
            <option value="Mensuelle">Mensuelle</option>
            <option value="Trimestrielle">Trimestrielle</option>
            <option value="Annuelle">Annuelle</option>
          </select>
        </div>

        <button type="submit" className="submit-button">
          🚀 Générer et recevoir par mail
        </button>
        {message && (
          <p style={{ marginTop: "20px", color: "green", fontWeight: "bold" }}>
            {message}
          </p>
        )}
      </form>
    </>
  );
}
