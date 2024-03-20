import styled from 'styled-components';
import logo from '../assets/logo.png';

const Header = () => {
    return (
        <View className='header'>
            <img src={logo} alt="Logo" style={{ width: '150px', height: '150px', padding: '0 0 0 100px' }} />
            <h1>Health Clubs</h1>
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