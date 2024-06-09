import React, { useState } from 'react';
import styled from 'styled-components';
import { useNavigate } from 'react-router-dom';
import backImage from '../back.jpg';
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

const NavigationButton = styled(Button)`
    && {
        margin: 10px;
        width: 100px;
    }
`;

const interests = [
    'SPORT',
    'MUSIC',
    'TRAVEL',
    'READING',
    'ART',
    'MOVIES',
    'COOKING',
    'PHOTOGRAPHY',
    'FITNESS',
    'TECHNOLOGY',
    'FASHION',
    'GAMING',
    'NATURE',
    'HISTORY',
    'DIY',
];

const ITEMS_PER_PAGE = 4;

const RegisterTest = () => {
    const [selectedInterests, setSelectedInterests] = useState({});
    const [formError, setFormError] = useState(false);
    const [currentPage, setCurrentPage] = useState(0);

    const navigate = useNavigate();

    const handleCheckboxChange = (interest) => {
        setSelectedInterests((prev) => ({
            ...prev,
            [interest]: !prev[interest],
        }));
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        const selectedCount = Object.values(selectedInterests).filter(Boolean).length;

        if (selectedCount < 2) {
            setFormError(true);
            return;
        }

        // Save selected interests to the backend
        const userId = localStorage.getItem('id');
        const interestsArray = Object.keys(selectedInterests).filter(key => selectedInterests[key]);

        try {
            const response = await fetch(`http://localhost:8080/api/interests/set/${userId}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('token')}`
                },
                body: JSON.stringify(interestsArray)
            });

            if (response.ok) {
                console.log('Interests set successfully');
                navigate('/match');
            } else {
                console.error('Failed to set interests');
            }
        } catch (error) {
            console.error('Error setting interests:', error);
        }
    };

    const handleNextPage = () => {
        if ((currentPage + 1) * ITEMS_PER_PAGE < interests.length) {
            setCurrentPage(currentPage + 1);
        }
    };

    const handlePrevPage = () => {
        if (currentPage > 0) {
            setCurrentPage(currentPage - 1);
        }
    };

    const currentInterests = interests.slice(currentPage * ITEMS_PER_PAGE, (currentPage + 1) * ITEMS_PER_PAGE);

    return (
        <TestPageContainer>
            <TestForm onSubmit={handleSubmit}>
                <TestFormTitle>Interests Test</TestFormTitle>
                {currentInterests.map((interest) => (
                    <TestInputLabel key={interest}>
                        <TestCheckboxContainer>
                            <input
                                type="checkbox"
                                checked={!!selectedInterests[interest]}
                                onChange={() => handleCheckboxChange(interest)}
                            />
                            <TestCheckboxLabel>Do you like {interest.toLowerCase()}?</TestCheckboxLabel>
                        </TestCheckboxContainer>
                    </TestInputLabel>
                ))}
                <div>
                    <NavigationButton variant="contained" onClick={handlePrevPage} disabled={currentPage === 0}>
                        Previous
                    </NavigationButton>
                    <NavigationButton variant="contained" onClick={handleNextPage} disabled={(currentPage + 1) * ITEMS_PER_PAGE >= interests.length}>
                        Next
                    </NavigationButton>
                </div>
                <SubmitButton variant="contained" color="primary" type="submit">
                    Submit
                </SubmitButton>
                {formError && <ErrorText>Select at least two interests.</ErrorText>}
            </TestForm>
        </TestPageContainer>
    );
};

export default RegisterTest;
