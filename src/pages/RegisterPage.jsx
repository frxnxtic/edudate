import React, { useState } from 'react';
import styled from 'styled-components';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import {Link, useNavigate} from 'react-router-dom';
import backImage from '../back.jpg';

const RegisterPageContainer = styled.div`
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

const RegisterForm = styled.form`
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

const RegisterFormTitle = styled.h1`
    margin-bottom: 20px;
`;

const ErrorText = styled.p`
    color: red;
    font-size: 14px;
    margin-top: 5px;
`;

const RegisterPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [email, setEmail] = useState('');
    const [usernameError, setUsernameError] = useState(false);
    const [passwordError, setPasswordError] = useState(false);
    const [emailError, setEmailError] = useState(false);

    const handleUsernameChange = (event) => {
        setUsername(event.target.value);
    };

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };

    const handleEmailChange = (event) => {
        setEmail(event.target.value);
    };

    const navigate = useNavigate();
    const handleSubmit = async (event) => {
        event.preventDefault();
        // Проверяем, что все поля заполнены
        if (!username || !password || !email) {
            setUsernameError(!username);
            setPasswordError(!password);
            setEmailError(!email);
            return;
        }
        // Здесь можно добавить логику для отправки данных на сервер
        console.log('Submitting register form...');
        navigate('/secondregister');
    };

    return (
        <RegisterPageContainer>
            <RegisterForm onSubmit={handleSubmit}>
                <RegisterFormTitle>Register</RegisterFormTitle>
                <TextField
                    type="text"
                    label="Username"
                    variant="outlined"
                    value={username}
                    required={true}
                    onChange={handleUsernameChange}
                    margin="normal"
                    error={usernameError} // Устанавливаем состояние ошибки
                />
                <TextField
                    required={true}
                    type="password"
                    label="Password"
                    variant="outlined"
                    value={password}
                    onChange={handlePasswordChange}
                    margin="normal"
                    error={passwordError} // Устанавливаем состояние ошибки
                />
                <TextField
                    required={true}
                    type="email"
                    label="Email"
                    variant="outlined"
                    value={email}
                    onChange={handleEmailChange}
                    margin="normal"
                    error={emailError}
                />
                { // Выводим текст ошибки, если поле не заполнено
                    (usernameError || passwordError || emailError) && (
                        <ErrorText>All fields are required</ErrorText>
                    )
                }
                <Button type="submit" variant="contained" color="primary" onSubmit={handleSubmit}>
                    Register
                </Button>
                Already have an account? <Link to="/login">Login</Link>
            </RegisterForm>
        </RegisterPageContainer>
    );
};

export default RegisterPage;
