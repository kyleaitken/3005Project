import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import { useAuth } from '../contexts/AuthContext';
import { attemptLogin } from '../api/authApi'; 
import { useNavigate } from 'react-router-dom';

const LoginScreen = () => {
    const { dispatch, isLoggedIn } = useAuth();
    const navigate  = useNavigate();
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const {userType} = useAuth();

    useEffect(() => {
        if (isLoggedIn) {
          if (userType === "Member")      {
              navigate('/schedule');
          } else if (userType === "Admin") {
              navigate('/adminClasses');
          } else {
            navigate('/trainerSchedule')
          }
        }
    }, [isLoggedIn, navigate, userType]); 

    const handleFormSubmit = async (event) => {
        event.preventDefault(); 
        const loginResponse = await attemptLogin(email, password);
        
        if (loginResponse) {
            console.log('login response from API: ', loginResponse)
            dispatch({
              type: 'LOGIN',
              payload: loginResponse
            });
            if (loginResponse.type === "Member") {
                navigate('/schedule'); 
            }
            if (loginResponse.type === "Admin") {
                navigate('/adminClasses')
            } 
            if (loginResponse.type === "Trainer") {
                navigate('/trainerSchedule')
            }
        }
    };

    return (
        <LoginContainer>
            <h1>Login</h1>
                <FormView onSubmit={handleFormSubmit}>
                    <input
                        type="email"
                        placeholder="Email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                    <input
                        type="password"
                        placeholder="Password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                    <button type="submit">Login</button>
                    <h2>New to SAL?</h2>
                    <button type="button">Register</button> 
                </FormView>
        </LoginContainer>
    );
}

export default LoginScreen;

const LoginContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  padding: 20px;

  input {
    margin: 10px 0;
    padding: 8px;
    width: 400px;
  }

  button {
    margin: 10px 5px;
    padding: 8px 16px;
    width: 150px;
    align-self: center;
  }

  h2 {
    margin-top: 100px;
    align-self:center;
  }
`;

const FormView = styled.form`
  display: flex;
  flex-direction: column;
`