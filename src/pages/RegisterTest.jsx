import React, { useState } from 'react';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
import backImage from '../back.jpg';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';

const TestPageContainer = styled.div`
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

const TestForm = styled.form`
    display: flex;
    flex-direction: column;
    align-items: center;
    width: 350px;
    padding: 30px;
    border-radius: 20px;
    background-color: rgba(255, 255, 255, 0.9);
    box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
`;

const TestFormTitle = styled.h1`
    margin-bottom: 20px;
    font-size: 24px;
    color: #333;
`;

const TestInputLabel = styled.label`
    margin-bottom: 15px;
    font-size: 18px;
    color: #555;
`;

const TestCheckboxContainer = styled.div`
    display: flex;
    align-items: center;
`;

const TestCheckboxLabel = styled.span`
    margin-left: 10px;
    font-size: 16px;
    color: #555;
`;

const SubmitButton = styled(Button)`
    && {
        margin-top: 20px;
        width: 100%;
        border-radius: 8px;
    }
`;

const ErrorText = styled.p`
    color: red;
    font-size: 14px;
    margin-top: 10px;
`;

const RegisterTest = () => {
    const [interest1, setInterest1] = useState(false);
    const [interest2, setInterest2] = useState(false);
    const [interest3, setInterest3] = useState(false);
    const [interest4, setInterest4] = useState(false);
    const [interest5, setInterest5] = useState(false);
    const [interest6, setInterest6] = useState(false);
    const [formError, setFormError] = useState(false);

    const navigate = useNavigate();

    const handleSubmit = async (event) => {
        event.preventDefault();

        // Считаем количество выбранных интересов
        const selectedInterests = [interest1, interest2, interest3, interest4, interest5, interest6].filter(interest => interest);

        // Проверяем, что выбрано как минимум два интереса
        if (selectedInterests.length < 2) {
            setFormError(true);
            return;
        }

        // Здесь можно добавить логику для сохранения ответов на тест

        // Перенаправляем пользователя на другую страницу после отправки ответов
        navigate('/match');
    };

    return (
        <TestPageContainer>
            <TestForm onSubmit={handleSubmit}>
                <TestFormTitle>Interests Test</TestFormTitle>
                <TestInputLabel>
                    <TestCheckboxContainer>
                        <input
                            type="checkbox"
                            checked={interest1}
                            onChange={() => setInterest1(!interest1)}
                        />
                        <TestCheckboxLabel>Do you like sports?</TestCheckboxLabel>
                    </TestCheckboxContainer>
                </TestInputLabel>
                <TestInputLabel>
                    <TestCheckboxContainer>
                        <input
                            type="checkbox"
                            checked={interest2}
                            onChange={() => setInterest2(!interest2)}
                        />
                        <TestCheckboxLabel>Do you enjoy reading?</TestCheckboxLabel>
                    </TestCheckboxContainer>
                </TestInputLabel>
                <TestInputLabel>
                    <TestCheckboxContainer>
                        <input
                            type="checkbox"
                            checked={interest3}
                            onChange={() => setInterest3(!interest3)}
                        />
                        <TestCheckboxLabel>Are you interested in cooking?</TestCheckboxLabel>
                    </TestCheckboxContainer>
                </TestInputLabel>
                <TestInputLabel>
                    <TestCheckboxContainer>
                        <input
                            type="checkbox"
                            checked={interest4}
                            onChange={() => setInterest4(!interest4)}
                        />
                        <TestCheckboxLabel>Do you like traveling?</TestCheckboxLabel>
                    </TestCheckboxContainer>
                </TestInputLabel>
                <TestInputLabel>
                    <TestCheckboxContainer>
                        <input
                            type="checkbox"
                            checked={interest5}
                            onChange={() => setInterest5(!interest5)}
                        />
                        <TestCheckboxLabel>Are you into music?</TestCheckboxLabel>
                    </TestCheckboxContainer>
                </TestInputLabel>
                <TestInputLabel>
                    <TestCheckboxContainer>
                        <input
                            type="checkbox"
                            checked={interest6}
                            onChange={() => setInterest6(!interest6)}
                        />
                        <TestCheckboxLabel>Do you enjoy gardening?</TestCheckboxLabel>
                    </TestCheckboxContainer>
                </TestInputLabel>
                <SubmitButton variant="contained" color="primary" type="submit">
                    Submit
                </SubmitButton>
                {formError && <ErrorText>Select at least two interests.</ErrorText>}
            </TestForm>
        </TestPageContainer>
    );
};

export default RegisterTest;
