import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom'; // Import Link for navigation

const SideBarContainer = styled.div`
  display: flex;
  flex-grow: 0;
  flex-shrink: 0;
  flex-basis: 250px;
  flex-direction: column;
  width: 250px; 
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
      <StyledLink to="/sessions">Your Sessions</StyledLink>
      <StyledLink to="/classes">Your Classes</StyledLink>
      <StyledLink to="/joinClass">Join a Class</StyledLink>
      <StyledLink to="/routines">Exercise Routines</StyledLink>
      <StyledLink to="/goals">Goals</StyledLink>
      <StyledLink to="/memberInvoices">Invoices</StyledLink>
      <StyledLink to="/healthInfo">Health Information</StyledLink>
      <StyledLink to="/profile">Profile</StyledLink>
    </SideBarContainer>
  );
};

export default NavigationSideBar;


