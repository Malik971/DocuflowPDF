import React from 'react';
import FormulaireContrat from './components/FormulaireContrat';

function App() {
  return (
    <div className="min-h-screen bg-blue-50 flex flex-col items-center justify-start py-10">
      <h1 className="text-4xl font-bold text-center mb-4">Bienvenue sur DocuFlow 🚀</h1>
      <p className="text-center text-lg text-gray-700 mb-6 max-w-2xl">
        Générez votre contrat PDF personnalisé – projet imaginé pour la <strong>Mutuelle des Motards</strong>.
      </p>
      <FormulaireContrat />
    </div>
  );
}

export default App;
