import React, { useState } from 'react';
import styled from 'styled-components';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import {Link} from "react-router-dom";
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

    const handleUsernameChange = (event) => {
        setUsername(event.target.value);
    };

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        // Здесь можно добавить логику для отправки данных на сервер
        console.log('Submitting login form...');
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
                <Button type="submit" variant="contained" color="primary" component={Link} to={"/match"} >
                    Login
                </Button>
                Do not have an account? <a href="/register">Register</a>
            </LoginForm>
        </LoginPageContainer>
    );
};

export default LoginPage;
