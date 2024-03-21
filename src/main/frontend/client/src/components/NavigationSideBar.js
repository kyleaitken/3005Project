import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom'; // Import Link for navigation

const SideBarContainer = styled.div`
  display: flex;
  flex-direction: column;
  width: 250px; 
  height: 100vh;
  background-color: #f0f0f0; 
  padding: 20px;
`;

const StyledLink = styled(Link)`
  text-decoration: none;
  color: black; 
  font-size: 25px;
  font-weight: bold;
  margin: 30px 0; 
  &:hover {
    color: #0047AB; 
  }
`;

const NavigationSideBar = () => {
  return (
    <SideBarContainer>
      <StyledLink to="/schedule">Schedule</StyledLink>
      <StyledLink to="/sessions">Sessions</StyledLink>
      <StyledLink to="/classes">Classes</StyledLink>
      <StyledLink to="/exercise-routines">Exercise Routines</StyledLink>
      <StyledLink to="/goals">Goals</StyledLink>
      <StyledLink to="/invoices">Invoices</StyledLink>
      <StyledLink to="/health-information">Health Information</StyledLink>
      <StyledLink to="/personal-information">Personal Information</StyledLink>
    </SideBarContainer>
  );
};

export default NavigationSideBar;