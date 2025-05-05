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
      <form onSubmit={handleSubmit}>
        <input name="nom" placeholder="Nom" required />
        <input name="email" placeholder="Email" required />
        <input name="typeContrat" placeholder="Type de contrat" />
        <button type="submit">Générer PDF</button>
      </form>
      {message && <p>{message}</p>}
    </>
  );
};

export default FormulaireContrat;
