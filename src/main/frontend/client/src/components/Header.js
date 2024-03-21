import styled from 'styled-components';
import logo from '../assets/logo.png';
import { useAuth } from '../contexts/AuthContext'; // Adjust the path as necessary


const Header = () => {
    const { isLoggedIn, dispatch } = useAuth();

    const handleLogout = () => {
        dispatch({ type: 'LOGOUT' });
    };

    return (
        <View className='header'>
            <img src={logo} alt="Logo" style={{ width: '150px', height: '150px', padding: '0 0 0 100px' }} />
            <h1>Health Clubs</h1>
            {isLoggedIn && (
                <LogoutButton onClick={handleLogout}>Logout</LogoutButton>
            )}
        </View>
    )
};

export default Header;

const View = styled.div`
    display: flex;
    align-items: center;
    font-family: Arial, sans-serif;
    background-color: #0047AB;
    color: white;
    font-size: 20px;
`;


const LogoutButton = styled.button`
    padding: 10px 20px;
    background-color: #grey; 
    margin: 0px 30px 20px auto;
    align-self: flex-end;
    color: black;
    border: none;
    cursor: pointer;
    font-size: 16px;
    border-radius: 5px;

    &:hover {
        background-color: lightblue; /* Darken on hover */
    }
`;