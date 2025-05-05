import React, { useState } from 'react';
import axios from 'axios';
import ProgressOverlay from './components/ProgressOverlay';

const FormulaireContrat = () => {
  const [isLoading, setIsLoading] = useState(false);
  const [feedbackMessage, setFeedbackMessage] = useState(null);

  const handleSubmit = async (event) => {
    event.preventDefault();
    setIsLoading(true);
    setFeedbackMessage(null);

    try {
      const response = await axios.post('https://docuflow.onrender.com/api/documents', {
        // insère ici les données de ton formulaire
      });

      setFeedbackMessage('✅ Document envoyé avec succès !');
    } catch (error) {
      setFeedbackMessage('❌ Une erreur est survenue.');
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <>
      {isLoading && <ProgressOverlay />}
      
      <form onSubmit={handleSubmit}>
        {/* champs de formulaire ici */}
        <button type="submit">Envoyer</button>
      </form>

      {feedbackMessage && <p>{feedbackMessage}</p>}
    </>
  );
};

export default FormulaireContrat;
