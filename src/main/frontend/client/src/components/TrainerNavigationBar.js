import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom'; 

const SideBarContainer = styled.div`
  display: flex;
  flex-grow: 0;
  flex-shrink: 0;
  flex-basis: 250px;
  flex-direction: column;
  width: 250px; 
  background-color: #f0f0f0; 
  padding: 20px;
  height: 100vh;
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

const TrainerNavigationBar = () => {
  return (
    <SideBarContainer>
      <StyledLink to="/trainerSchedule">Manage Schedule</StyledLink>
      <StyledLink to="/trainerMembers">View Members</StyledLink>
    </SideBarContainer>
  );
};

export default TrainerNavigationBar;