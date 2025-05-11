import React, { useState } from 'react';
import ProgressOverlay from './ProgressOverlay';

const FormulaireContrat = () => {
  const [isLoading, setIsLoading] = useState(false);
  const [message, setMessage] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    setMessage('');

    const formData = new FormData(e.target);
    const data = {};
    formData.forEach((v, k) => data[k] = v);

    try {
      const response = await fetch('https://docuflowpdf.onrender.com/api/generate-pdf', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data)
      });

      if (response.ok) {
        setMessage("✅ Contrat envoyé par email !");
        e.target.reset();
      } else {
        setMessage("❌ Une erreur est survenue.");
      }
    } catch (err) {
      setMessage("❌ Impossible de contacter le serveur.");
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <>
      <ProgressOverlay isVisible={isLoading} />
      <form onSubmit={handleSubmit} className="bg-white p-8 rounded-lg shadow-lg w-full max-w-md mx-auto flex flex-col space-y-4 border border-gray-200"
      >
        <input className="border border-gray-300 p-3 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
 type="text" name="nom" placeholder="Nom complet" required />
        <input className="border border-gray-300 p-3 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
 type="text" name="adresse" placeholder="Adresse complète" required />
        <input className="border border-gray-300 p-3 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
 type="email" name="email" placeholder="Email" required />
        <input className="border border-gray-300 p-3 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
 type="date" name="date" placeholder="Date du contrat" required />
        <input className="border border-gray-300 p-3 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
 type="text" name="typeContrat" placeholder="Type de contrat" required />
        <input className="border border-gray-300 p-3 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
 type="text" name="montant" placeholder="Montant de l'assurance" />
        <input className="border border-gray-300 p-3 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
 type="text" name="duree" placeholder="Durée du contrat (ex: 1 an)" />
        <input className="border border-gray-300 p-3 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
 type="text" name="marque" placeholder="Marque du véhicule" />
        <input className="border border-gray-300 p-3 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
 type="text" name="modele" placeholder="Modèle du véhicule" />
        <input className="border border-gray-300 p-3 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
 type="text" name="kilometrage" placeholder="Kilométrage (ex: 12000 km)" />
        <input className="border border-gray-300 p-3 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
 type="text" name="cylindree" placeholder="Cylindrée (ex: 125cc)" />
        <input className="border border-gray-300 p-3 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
 type="text" name="garantiesSupplementaires" placeholder="Garanties supplémentaires souhaitées" />
        <input className="border border-gray-300 p-3 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
 type="text" name="franchise" placeholder="Franchise (ex: 250€)" />
        <input className="border border-gray-300 p-3 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
 type="text" name="plafondRemboursement" placeholder="Plafond de remboursement (ex: 10 000€)" />
        <input className="border border-gray-300 p-3 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
 type="text" name="periodicitePaiement" placeholder="Périodicité de paiement (ex: Mensuelle)" />
        <select className="border border-gray-300 p-3 rounded-md focus:outline-none focus:ring-2 focus:ring-blue-500"
 name="modeleContrat" required>
          <option value="">Choisissez un modèle de contrat</option>
          <option value="modele1">🛵 Moto basique</option>
          <option value="modele2">🏍️ Moto premium</option>
          <option value="modele3">🧒 Scoot’ jeune</option>
          <option value="modele4">🔒 Franchise renforcée</option>
        </select>
        <button type="submit">🚀 Générer et recevoir par mail</button>
        {message && <p className="text-center font-bold">{message}</p>}
      </form>
    </>
  );
};

export default FormulaireContrat;
