import React from 'react';
import { useTranslation } from 'react-i18next';

const LanguageSelector = () => {
  const { i18n } = useTranslation();

  const changeLanguage = (lng) => {
    i18n.changeLanguage(lng);
  };

  return (
    <div className="language-selector">
      <button onClick={() => changeLanguage('en')}>
        <img src="https://upload.wikimedia.org/wikipedia/commons/a/a4/Flag_of_the_United_States.svg" alt="English" />
        <div>English</div>
      </button>
      <button onClick={() => changeLanguage('es')}>
        <img src="https://upload.wikimedia.org/wikipedia/commons/9/9a/Flag_of_Spain.svg" alt="Español" />
        <div>Español</div>
      </button>
      <button onClick={() => changeLanguage('pt')}>
        <img src="https://upload.wikimedia.org/wikipedia/commons/0/05/Flag_of_Brazil.svg" alt="Português" />
        <div>Português</div>
      </button>
    </div>
  );
};

export default LanguageSelector;
