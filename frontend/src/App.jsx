// App.jsx
import React from 'react';
import FormulaireContrat from './components/FormulaireContrat';
import './App.css'; 
import DarkModeToggle from './components/Darkmode';

function App() {
  return (
    <div className="app-container">
      <DarkModeToggle />
      <h1 className="main-title">Bienvenue sur DocuFlow 🚀</h1>
      <p className="main-description">
        Générez votre contrat PDF personnalisé – projet imaginé pour la <strong>Mutuelle des Motards</strong>.
      </p>
      <FormulaireContrat />
    </div>
  );
}

export default App;
