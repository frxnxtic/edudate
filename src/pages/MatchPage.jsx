import React, { useState, useEffect } from 'react';
import styled, { createGlobalStyle } from 'styled-components';
import Button from '@material-ui/core/Button';
import image from '../image.jpg';
import { AiOutlineLike, AiOutlineDislike } from 'react-icons/ai';
import { Link } from "react-router-dom";
import {skSK} from "@material-ui/core/locale";

// Global styles to restrict horizontal scrolling
const GlobalStyle = createGlobalStyle`
    body, html {
        margin: 0;
        padding: 0;
        overflow-x: hidden;
        width: 100%;
        height: 100%;
    }
`;

const PageContainer = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
    background-image: url(${image});
    background-repeat: no-repeat;
    background-size: cover;
    background-position: center;
    width: 100vw;
    height: 100vh;
    overflow: hidden;
`;

const Header = styled.div`
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
`;

const Logo = styled.h1`
    font-size: 36px;
    color: #333333;
    text-align: center;
    background-color: #ffffff;
    border: 3px solid #333333;
    border-radius: 20px;
    padding: 10px;
    margin: -10px;
    letter-spacing: 5px;
`;

const ButtonContainer = styled.div`
    margin-top: 20px;
    display: flex;
    gap: 20px;
`;

const MatchCardContainer = styled.div`
    position: relative;
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom: 40px;
`;

const MatchCard = styled.div`
    width: 350px;
    border: 3px solid #000000;
    border-radius: 20px;
    padding: 20px;
    background-color: rgba(255, 255, 255, 0.9);
    box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
    display: flex;
    flex-direction: column;
    align-items: center;
`;

const MatchName = styled.h2`
    margin: 0;
    font-size: 20px;
    color: #333;
`;

const MatchImage = styled.img`
    width: 100%;
    height: 200px;
    object-fit: cover;
    border-radius: 10px;
`;

const UserData = styled.div`
    margin-top: 10px;
    text-align: center;
`;

const InterestList = styled.div`
    display: flex;
    flex-wrap: wrap;
    gap: 5px;
    justify-content: center;
`;

const InterestItem = styled.span`
    background-color: #e0e0e0;
    border-radius: 5px;
    padding: 5px 10px;
    margin: 2px;
    white-space: nowrap;
`;

const Footer = styled.footer`
    width: 100%;
    background-color: #333;
    color: #fff;
    text-align: center;
`;

const LikeButton = styled(Button)`
    && {
        position: absolute;
        top: 50%;
        left: 450px;
        transform: translateY(-50%);
        border-radius: 50%;
        width: 60px;
        height: 60px;
        background-color: #4caf50;
        color: #fff;
        display: flex;
        justify-content: center;
        align-items: center;
    }
`;

const DislikeButton = styled(Button)`
    && {
        position: absolute;
        top: 50%;
        right: 450px;
        transform: translateY(-50%);
        border-radius: 50%;
        width: 60px;
        height: 60px;
        background-color: #f44336;
        color: #fff;
        display: flex;
        justify-content: center;
        align-items: center;
    }
`;

const LargeLikeIcon = styled(AiOutlineLike)`
    font-size: 2rem;
`;

const LargeDislikeIcon = styled(AiOutlineDislike)`
    font-size: 2rem;
`;

const MatchPage = () => {
    const [users, setUsers] = useState([]);
    const [currentIndex, setCurrentIndex] = useState(0);
    const [error, setError] = useState(null);
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
    useEffect(() => {
        const fetchData = async () => {
            const token = localStorage.getItem('token');

            if (!token) {
                setError('No token found, please log in again.');
                return;
            }

            try {
                const id = localStorage.getItem('id');
                const response = await fetch(`http://localhost:8080/api/matching/search/${id}`, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                        'Accept': 'application/json',
                        'Authorization': `Bearer ${token}`
                    }
                });

                if (response.status === 403) {
                    setError('Access forbidden: Invalid or expired token');
                    return;
                }

                if (!response.ok) {
                    const errorData = await response.json();
                    throw new Error(`Error: ${errorData.message || response.statusText}`);
                }

                const result = await response.json();
                setUsers(result);
            } catch (error) {
                console.error('Error fetching data:', error);
                setError(`Error fetching data: ${error.message}`);
            }
        };

        fetchData().then(r => r).catch(e => e);
    }, []);

    const handleLike = async () => {
        const token = localStorage.getItem('token');
        const user = users[currentIndex];

        if (!token) {
            setError('No token found, please log in again.');
            return;
        }

        try {
            const username = user.username
            const userId = await fetchUserId(username);
            const response = await fetch(`http://localhost:8080/api/user-likes/like/${userId}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                }
            });

            if (response.status === 403) {
                setError('Access forbidden: Invalid or expired token');
                return;
            }

            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(`Error: ${errorData.message || response.statusText}`);
            }

            setCurrentIndex((prevIndex) => (prevIndex + 1) % users.length);
        } catch (error) {
            console.error('Error liking user:', error);
            setError(`Error liking user: ${error.message}`);
        }
    };

    const handleDislike = async () => {
        const token = localStorage.getItem('token');
        const user = users[currentIndex];

        if (!token) {
            setError('No token found, please log in again.');
            return;
        }

        try {
            const username = user.username
            const userId = await fetchUserId(username);
            console.log(userId)
            const response = await fetch(`http://localhost:8080/api/user-likes/dislike/${userId}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                }
            });

            if (response.status === 403) {
                setError('Access forbidden: Invalid or expired token');
                return;
            }

            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(`Error: ${errorData.message || response.statusText}`);
            }

            setCurrentIndex((prevIndex) => (prevIndex + 1) % users.length);
        } catch (error) {
            console.error('Error liking user:', error);
            setError(`Error liking user: ${error.message}`);
        }
    };

    if (error) {
        return <div>Error: {error}</div>;
    }

    if (users.length === 0) {
        return <div>Loading...</div>;
    }

    const currentUser = users[currentIndex];

    const formatDate = (dateString) => {
        const options = { year: 'numeric', month: 'long', day: 'numeric' };
        return new Date(dateString).toLocaleDateString("sk-sk", options);
    };

    const interests = Array.isArray(currentUser.interests) ? currentUser.interests : currentUser.interests.split(',').map(interest => interest.trim());

    return (
        <>
            <GlobalStyle />
            <PageContainer>
                <Header>
                    <Logo>Edudate</Logo>
                    <ButtonContainer>
                        <Button type="submit" variant="contained" color="primary" component={Link} to={"/profile"} >
                            Profile
                        </Button>
                        <Button type="submit" variant="contained" color="primary" component={Link} to={"/statistics"} >
                            Statistics
                        </Button>
                    </ButtonContainer>
                </Header>
                {error && <div style={{ color: 'red', marginBottom: '20px' }}>{error}</div>}
                <MatchCardContainer>
                    <MatchCard>
                        <MatchName>{currentUser.username}</MatchName>
                        <MatchImage src={currentUser.image} alt={currentUser.username} />
                        <UserData>
                            <p>Name: {currentUser.name} {currentUser.surname}</p>
                            <p>Date of Birth: {formatDate(currentUser.dateOfBirth)}</p>
                            <InterestList>
                                {interests.map((interest, index) => (
                                    <InterestItem key={index}>{interest}</InterestItem>
                                ))}
                            </InterestList>
                            <p>Faculty: {currentUser.faculty}</p>
                        </UserData>
                        <LikeButton variant="contained" color="primary" onClick={handleLike}>
                            <LargeLikeIcon />
                        </LikeButton>
                        <DislikeButton variant="contained" color="secondary" onClick={handleDislike}>
                            <LargeDislikeIcon />
                        </DislikeButton>
                    </MatchCard>
                </MatchCardContainer>
                <Footer>
                    © 2024 Edudate. All rights reserved.
                </Footer>
            </PageContainer>
        </>
    );
};

export default MatchPage;
