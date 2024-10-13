import React, { useState, useEffect } from 'react';
import { useTranslation } from 'react-i18next';
import axios from './axiosConfig';
import './App.css';

function App() {
  const { t, i18n } = useTranslation();
  const [continents, setContinents] = useState([]);
  const [countries, setCountries] = useState([]);
  const [states, setStates] = useState([]);
  const [selectedContinent, setSelectedContinent] = useState('');
  const [selectedCountry, setSelectedCountry] = useState('');
  const [selectedState, setSelectedState] = useState('');
  const [searchTerm, setSearchTerm] = useState('');

  useEffect(() => {
    axios.get('/continents')
      .then(response => {
        setContinents(response.data);
      })
      .catch(error => {
        console.error('Error fetching continents:', error);
      });
  }, []);

  useEffect(() => {
    if (selectedContinent) {
      axios.get('/countries', { params: { continent: selectedContinent } })
        .then(response => {
          setCountries(response.data);
          setSelectedCountry(''); // Reset country selection
          setSelectedState('');   // Reset state selection
        })
        .catch(error => {
          console.error('Error fetching countries:', error);
        });
    }
  }, [selectedContinent]);

  useEffect(() => {
    if (selectedCountry) {
      axios.get('/states', { params: { country: selectedCountry } })
        .then(response => {
          setStates(response.data);
        })
        .catch(error => {
          console.error('Error fetching states:', error);
        });
    }
  }, [selectedCountry]);

  const handleSearch = () => {
    console.log('Search term:', searchTerm);
    console.log('Selected Continent:', selectedContinent);
    console.log('Selected Country:', selectedCountry);
    console.log('Selected State:', selectedState);
  };

  const changeLanguage = (lng) => {
    i18n.changeLanguage(lng);
  };

  return (
    <div className="App">
      <h1>Radio Finder</h1>
      <div className="language-selector">
        <button onClick={() => changeLanguage('en')}>
          <img src="path/to/english-icon.png" alt="English" /> English
        </button>
        <button onClick={() => changeLanguage('es')}>
          <img src="path/to/spanish-icon.png" alt="Español" /> Español
        </button>
        <button onClick={() => changeLanguage('pt')}>
          <img src="path/to/portuguese-icon.png" alt="Português" /> Português
        </button>
      </div>
      <div className="search-container">
        <input 
          type="text" 
          value={searchTerm} 
          onChange={e => setSearchTerm(e.target.value)} 
          placeholder={t('search')}
        />
        <button onClick={handleSearch}>{t('search')}</button>
      </div>
      <div className="filters-container">
        <select 
          value={selectedContinent} 
          onChange={e => setSelectedContinent(e.target.value)}
        >
          <option value="">{t('select_continent')}</option>
          {continents.map(continent => (
            <option key={continent} value={continent}>{t('continent')}: {continent}</option>
          ))}
        </select>
        <select 
          value={selectedCountry} 
          onChange={e => setSelectedCountry(e.target.value)}
          disabled={!selectedContinent}
        >
          <option value="">{t('select_country')}</option>
          {countries.map(country => (
            <option key={country} value={country}>{country}</option>
          ))}
        </select>
        <select 
          value={selectedState} 
          onChange={e => setSelectedState(e.target.value)}
          disabled={!selectedCountry}
        >
          <option value="">{t('select_state')}</option>
          {states.map(state => (
            <option key={state} value={state}>{state}</option>
          ))}
        </select>
      </div>
    </div>
  );
}

export default App;
