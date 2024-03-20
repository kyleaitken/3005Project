import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import styled from 'styled-components';
import Header from './components/Header';
import LoginScreen from './screens/LoginScreen';
import MemberHomeScreen from './screens/MemberHomeScreen';
import { AuthContextProvider } from './contexts/AuthContext';

function App() {
  return (
    <AuthContextProvider>
      <Router>
        <MainView>
          <Header />
          <Routes>
            <Route path="/" element={<LoginScreen />} />
            <Route path="/member" element={<MemberHomeScreen />} />
          </Routes>
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