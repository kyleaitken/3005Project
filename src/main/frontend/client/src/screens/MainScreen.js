import React, { useState } from 'react';
import styled from 'styled-components';
import LoginScreen from './LoginScreen'
import MemberHomeScreen from './MemberHomeScreen';


const MainScreen = () => {
    const [loggedIn, setLoggedIn] = useState(false);
    const [id, setId] = useState(null);
    const [userType, setUserType] = useState("Member");

    return (
        <MainScreenView>
            {!loggedIn ? <LoginScreen setLoggedIn={setLoggedIn} /> :
            userType == "Member" ? <MemberHomeScreen /> : <></>}
        </MainScreenView>
    )

}

const MainScreenView = styled.div`
`;

export default MainScreen;