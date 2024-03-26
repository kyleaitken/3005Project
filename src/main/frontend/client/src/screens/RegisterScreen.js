import React, { useState } from 'react';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
import { registerMember } from '../api/memberApi';

const RegisterScreen = () => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [birthDate, setBirthDate] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [phone, setPhone] = useState('');
    const [address, setAddress] = useState('');
    const [emergencyPhone, setEmergencyPhone] = useState('');

    const navigate = useNavigate();

    const handleFormSubmit = async (event) => {
        event.preventDefault();
        
        // Call the API to register the member
        try {
            const memberInfo = {
                firstName,
                lastName,
                birthDate, 
                email,
                password,
                phone,
                address,
                emergencyPhone
            };
            console.log(memberInfo);
            const res = await registerMember(memberInfo);
            if (res.message === "Success") {
                navigate('/');
            } else {
                window.alert('Failed to register')
            }
        } catch (error) {
            window.alert('Failed to register')
        }
    };

    return (
        <RegisterContainer>
            <h1>Register</h1>
            <FormView onSubmit={handleFormSubmit}>
                <Label>
                    First Name:
                    <input
                        type="text"
                        placeholder="First Name"
                        value={firstName}
                        onChange={(e) => setFirstName(e.target.value)}
                        required
                    />
                </Label>
                <Label>
                    Last Name:
                    <input
                        type="text"
                        placeholder="Last Name"
                        value={lastName}
                        onChange={(e) => setLastName(e.target.value)}
                        required
                    />
                </Label>
                <Label>
                    Birth Date:
                    <input
                        type="date"
                        placeholder="Birth Date"
                        value={birthDate}
                        onChange={(e) => setBirthDate(e.target.value)}
                        required
                    />
                </Label>
                <Label>
                    Email:
                    <input
                        type="email"
                        placeholder="Email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                </Label>
                <Label>
                    Password:
                    <input
                        type="password"
                        placeholder="Password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        required
                    />
                </Label>
                <Label>
                    Phone:
                    <input
                        type="text"
                        placeholder="Phone"
                        value={phone}
                        onChange={(e) => setPhone(e.target.value)}
                        required
                    />
                </Label>
                <Label>
                    Address:
                    <input
                        type="text"
                        placeholder="Address"
                        value={address}
                        onChange={(e) => setAddress(e.target.value)}
                        required
                    />
                </Label>
                <Label>
                    Emergency Phone:
                    <input
                        type="text"
                        placeholder="Emergency Phone"
                        value={emergencyPhone}
                        onChange={(e) => setEmergencyPhone(e.target.value)}
                        required
                    />
                </Label>
                <button type="submit">Register</button>
            </FormView>
        </RegisterContainer>
    );
};

export default RegisterScreen;

const RegisterContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  width: 100%;
  padding: 20px;

  input {
    margin: 10px 0;
    padding: 8px;
    width: 100%;
  }

  button {
    margin: 10px 5px;
    padding: 8px 16px;
    width: 100%;
    align-self: center;
  }
`;

const FormView = styled.form`
  display: flex;
  flex-direction: column;
  width: 400px; // Adjust as necessary
`;

const Label = styled.label`
  display: flex;
  flex-direction: column;
  margin: 10px 0;
`;

