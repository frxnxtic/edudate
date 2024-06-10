import React, { useState } from 'react';
import styled from 'styled-components';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import { useNavigate } from 'react-router-dom';
import backImage from '../back.jpg';

const LoginPageContainer = styled.div`
    width: 100%;
    height: 100vh;
    background-image: url(${backImage});
    background-size: cover;
    background-repeat: no-repeat;
    background-position: center center;
    display: flex;
    justify-content: center;
    align-items: center;
`;

const LoginForm = styled.form`
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 300px;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 60px;
    background-color: rgb(255, 255, 255);
    z-index: 1;
`;

const LoginFormTitle = styled.h1`
    margin-bottom: 20px;
`;

const LoginPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const fetchUserId = async (username) => {
        const response = await fetch(`http://localhost:8080/api/user/${username}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            }
        });

        if (response.ok) {
            return await response.json();
        } else {
            console.log('Failed to fetch user data');
            return null;
        }
    };

    const handleUsernameChange = (event) => {
        setUsername(event.target.value);
    };

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        try {
            const response = await fetch('http://localhost:8080/api/auth/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                },
                body: JSON.stringify({ username, password })
            });

            if (response.ok) {
                const data = await response.json();
                localStorage.setItem('token', data.token);
                const userId = await fetchUserId(username);
                localStorage.setItem('id', userId);
                localStorage.setItem('name', data.name);
                navigate('/match');
            } else {
                console.error('Login failed');
            }
        } catch (error) {
            console.error('Error:', error);
        }
    };

    return (
        <LoginPageContainer>
            <LoginForm onSubmit={handleSubmit}>
                <LoginFormTitle>Login</LoginFormTitle>
                <TextField
                    type="text"
                    label="Username"
                    variant="outlined"
                    value={username}
                    onChange={handleUsernameChange}
                    margin="normal"
                />
                <TextField
                    type="password"
                    label="Password"
                    variant="outlined"
                    value={password}
                    onChange={handlePasswordChange}
                    margin="normal"
                />
                <Button type="submit" variant="contained" color="primary">
                    Login
                </Button>
                Do not have an account? <a href="/register">Register</a>
            </LoginForm>
        </LoginPageContainer>
    );
};

export default LoginPage;
