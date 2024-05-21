import React, { useState } from 'react';
import styled from 'styled-components';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import {useNavigate} from 'react-router-dom';
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

const RegisterPage = () => {
    const [faculty, setFaculty] = useState('FPV');
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [birthdate, setBirthdate] = useState('');
    const [facultyError, setFacultyError] = useState(false);
    const [firstNameError, setFirstNameError] = useState(false);
    const [lastNameError, setLastNameError] = useState(false);
    const [birthdateError, setBirthdateError] = useState(false);



    const handleFacultyChange = (event) => {
        setFaculty(event.target.value);
    };

    const handleFirstNameChange = (event) => {
        setFirstName(event.target.value);
    };

    const handleLastNameChange = (event) => {
        setLastName(event.target.value);
    };

    const handleBirthdateChange = (event) => {
        setBirthdate(event.target.value);
    };

    const navigate = useNavigate();
    const handleSubmit = async (event) => {
        event.preventDefault();
        // Проверяем, что все поля заполнены
        if (!firstName || !lastName || !faculty || !birthdate) {
            setFirstNameError(!firstName);
            setLastNameError(!lastName);
            setFacultyError(!faculty);
            setBirthdateError(!birthdate);
            return;
        }
        // Здесь можно добавить логику для отправки данных на сервер
        console.log('Submitting register form...');
        navigate('/registerTest');
    };


    return (
        <RegisterPageContainer>
            <RegisterForm onSubmit={handleSubmit}>
                <RegisterFormTitle>Register</RegisterFormTitle>

                <TextField
                    required
                    type="text"
                    label="First Name"
                    variant="outlined"
                    value={firstName}
                    onChange={handleFirstNameChange}
                    margin="normal"
                    error={firstNameError}
                />
                <TextField
                    required
                    type="text"
                    label="Last Name"
                    variant="outlined"
                    value={lastName}
                    onChange={handleLastNameChange}
                    margin="normal"
                    error={lastNameError}
                />
                <TextField
                    required
                    select
                    label="Faculty"
                    defaultValue="Engineering"
                    value={faculty}
                    onChange={handleFacultyChange}
                    variant="outlined"
                    margin="normal"
                    error={facultyError}
                >
                    <option value="FPV">FPV</option>
                    <option value="FF">FF</option>
                    <option value="PrF">PrF</option>
                    <option value="FTVZ">FTVZ</option>
                    <option value="EF">EF</option>
                    <option value="PdF">PdF</option>
                </TextField>
                <TextField
                    required
                    type="date"
                    label=""
                    variant="outlined"
                    value={birthdate}
                    onChange={handleBirthdateChange}
                    margin="normal"
                    error={birthdateError}
                />
                <Button type="submit" variant="contained" color="primary" onSubmit={handleSubmit}>
                    Register
                </Button>
            </RegisterForm>
        </RegisterPageContainer>
    );
};

export default RegisterPage;
