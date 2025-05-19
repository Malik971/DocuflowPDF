import React, { useEffect, useState } from 'react';
import './DarkMode.css';

// function pour changer la page en mode sombre ou clair
function DarkModeToggle() {
  const [darkMode, setDarkMode] = useState(() => {
    return localStorage.getItem('theme') === 'dark';
  });

  useEffect(() => {
    const theme = darkMode ? 'dark' : 'light';
    document.documentElement.setAttribute('data-theme', theme);
    // Enregistre le thème dans le localStorage
    // on utilise le localStorage pour le moment car il n'y a pas de site frontend
    // donc pas de cookie
    localStorage.setItem('theme', theme);
  }, [darkMode]);

  const toggleTheme = () => {
    setDarkMode(prev => !prev);
  };

  return (
    <button className="dark-mode-toggle" onClick={toggleTheme}>
      {darkMode ? '☀️ Mode clair' : '🌙 Mode sombre'}
    </button>
  );
}

export default DarkModeToggle;
