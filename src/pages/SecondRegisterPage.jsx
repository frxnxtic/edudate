import React, { useState } from 'react';
import styled from 'styled-components';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import {useNavigate} from 'react-router-dom';
import backImage from '../back.jpg';
import moment from "moment";

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
    const [name, setName] = useState('');
    const [surname, setSurname] = useState('');
    const [dob, setDob] = useState('');
    const [facultyError, setFacultyError] = useState(false);
    const [firstNameError, setFirstNameError] = useState(false);
    const [lastNameError, setLastNameError] = useState(false);
    const [birthdateError, setBirthdateError] = useState(false);

    const handleFacultyChange = (event) => {
        setFaculty(event.target.value);
    };

    const handleFirstNameChange = (event) => {
        setName(event.target.value);
    };

    const handleLastNameChange = (event) => {
        setSurname(event.target.value);
    };

    const handleBirthdateChange = (event) => {
        setDob(event.target.value);
    };

    const navigate = useNavigate();
    const handleSubmit = async (event) => {
        event.preventDefault();

        // Проверяем, что все поля заполнены
        if (!name || !surname || !faculty || !dob) {
            setFirstNameError(!name);
            setLastNameError(!surname);
            setFacultyError(!faculty);
            setBirthdateError(!dob);
            return;
        }

        const dobFormatted = moment(dob).format('YYYY-MM-DD');
        console.log('Date of Birth:', dobFormatted); // Отладочная информация

        // Готовим данные формы
        const formData = {
            username: localStorage.getItem('username'),
            name: name,
            surname: surname,
            faculty: faculty,
            dob: dobFormatted
        };

        console.log('Form Data:', formData); // Отладочная информация

        // Выполняем POST запрос к серверу
        const response = await fetch('http://localhost:8080/api/auth/signup-2', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify(formData)
        });

        // Проверяем успешность запроса
        if (response.ok) {
            console.log('Регистрация успешна');
            navigate('/uploadimage');
        } else {
            console.log('Ошибка регистрации');
            const errorData = await response.json();
            console.log('Ошибка:', errorData); // Выводим подробности ошибки
        }
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
                    value={name}
                    onChange={handleFirstNameChange}
                    margin="normal"
                    error={firstNameError}
                />
                <TextField
                    required
                    type="text"
                    label="Last Name"
                    variant="outlined"
                    value={surname}
                    onChange={handleLastNameChange}
                    margin="normal"
                    error={lastNameError}
                />
                <TextField
                    required
                    select
                    label="Faculty"
                    defaultValue="FPV"
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
                    label="Date of Birth"
                    InputLabelProps={{
                        shrink: true,
                    }}
                    variant="outlined"
                    value={dob}
                    onChange={handleBirthdateChange}
                    margin="normal"
                    error={birthdateError}
                />
                <Button type="submit" variant="contained" color="primary">
                    Register
                </Button>
            </RegisterForm>
        </RegisterPageContainer>
    );
};

export default RegisterPage;
