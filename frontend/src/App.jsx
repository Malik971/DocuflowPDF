// App.jsx
import React from "react";
import FormulaireContrat from "./components/FormulaireContrat";
import "./App.css";
import DarkModeToggle from "./components/DarkMode";

function App() {
  return (
    <div className="app-container">
      <h1 className="main-title">Bienvenue sur DocuFlow 🚀</h1>
      <p className="main-description">
        Générez votre contrat PDF personnalisé en quelques clics !
        <br />
      </p>
      <div className="darkmode-toggle-wrapper">
        <DarkModeToggle />
      </div>
      <FormulaireContrat />
    </div>
  );
}

export default App;
