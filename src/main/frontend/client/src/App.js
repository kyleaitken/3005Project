import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import styled from 'styled-components';
import Header from './components/Header';
import { AuthContextProvider, useAuth } from './contexts/AuthContext';
import AppContent from './containers/AppContent';



function App() {

  return (
    <AuthContextProvider>
      <Router>
        <MainView>
          <Header />
          <AppContent />
        </MainView>
      </Router>
    </AuthContextProvider>
  );
}

export default App;

const MainView = styled.div`
  display: flex;
  flex-direction: column;
  margin: 0 auto; 
`;