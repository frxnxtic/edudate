import React, { useState } from 'react';
import styled from 'styled-components';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import { Link, useNavigate } from 'react-router-dom';
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

    const handleEmailChange = (event) => {
        const email = event.target.value;
        setEmail(email);

        // Regular expression to check if email ends with @student.umb.sk or @umb.sk
        const emailRegex = /@(student\.)?umb\.sk$/;

        // Set emailError state based on whether the email does NOT match the regex
        setEmailError(!emailRegex.test(email));
    };

    const navigate = useNavigate();
    const handleSubmit = async (event) => {
        event.preventDefault();

        // Check that all fields are filled and email is valid
        if (!username || !password || !email || emailError) {
            setUsernameError(!username);
            setPasswordError(!password);
            setEmailError(!email || emailError);
            return;
        }

        // Prepare the form data
        const formData = {
            username: username,
            password: password,
            email: email
        };

        try {
            // Make a POST request to the backend
            const response = await fetch('http://localhost:8080/api/auth/signup-1', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                },
                body: JSON.stringify(formData)
            });

            // Check if the request was successful
            if (response.ok) {
                console.log('Registration successful');
                const responseText = await response.text();

                // Log response text to debug if it's not a valid JSON
                console.log('Response Text:', responseText);

                // Parse the response text to JSON only if it's not empty
                const data = responseText ? JSON.parse(responseText) : {};
                console.log(data);
                localStorage.setItem('token', data.token);
                localStorage.setItem('username', username);

                // Fetch user ID
                const userId = await fetchUserId(username);
                localStorage.setItem('id', userId);

                console.log('Registration successful');
                navigate('/secondregister');
            } else {
                console.log('Registration failed');
            }
        } catch (error) {
            console.error('Error:', error);
        }
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
                    error={usernameError}
                />
                <TextField
                    required={true}
                    type="password"
                    label="Password"
                    variant="outlined"
                    value={password}
                    onChange={handlePasswordChange}
                    margin="normal"
                    error={passwordError}
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
                {
                    (usernameError || passwordError || emailError) && (
                        <ErrorText>All fields are required</ErrorText>
                    )
                }
                <Button type="submit" variant="contained" color="primary">
                    Register
                </Button>
                Already have an account? <Link to="/login">Login</Link>
            </RegisterForm>
        </RegisterPageContainer>
    );
};

export default RegisterPage;
