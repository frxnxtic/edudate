import React, { useState, useEffect } from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import Button from "@material-ui/core/Button";
import backImage from '../back.jpg';
import LikeCount from './LikeCount';
import LikeDislikeRatioBar from './LikeDislikeRatioBar';

const PageContainer = styled.div`
    min-height: 100vh;
    background-image: url(${backImage});
    background-size: cover;
    background-position: center;
    display: flex;
    flex-direction: column;
    align-items: center;
`;

const Header = styled.div`
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
    box-sizing: border-box;
`;

const Logo = styled.h1`
    margin: 0;
    font-size: 48px;
    color: #333333;
    text-align: center;
    background-color: #ffffff;
    border: 3px solid #333333;
    border-radius: 20px;
    padding: 20px;
    letter-spacing: 5px;
`;

const ButtonContainer = styled.div`
    margin-top: 20px;
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 20px;
`;

const ContentContainer = styled.div`
    display: flex;
    margin-top: 20px;
    gap: 20px;
    justify-content: space-between;
    flex-grow: 1;
    width: 100%;
`;

const StatisticsContainer = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    background-color: rgba(255, 255, 255, 0.8);
    border-radius: 20px;
    padding: 20px;
    box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.2);
    height: 300px; /* Уменьшаем высоту контейнера */
    width: calc(50% - 10px);
`;

const LikedByContainer = styled.div`
    display: flex;
    flex-direction: column;
    align-items: stretch;
    background-color: rgba(255, 255, 255, 0.8);
    border-radius: 20px;
    padding: 20px;
    box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.2);
    max-height: 300px; /* Уменьшаем высоту контейнера */
    overflow-y: auto;
    width: calc(50% - 10px);
`;

const UserContainer = styled.div`
    display: flex;
    align-items: center;
    margin-bottom: 20px;
`;

const UserImage = styled.img`
    width: 150px;
    height: 150px;
    object-fit: cover;
    border-radius: 50%;
    margin-right: 20px;
`;

const UserInfo = styled.div`
    display: flex;
    flex-direction: column;
    text-align: left; /* Выравниваем контент слева */
`;

const UserName = styled.h2`
    margin: 0;
    color: #333333;
    font-size: 20px;
`;

const ContactButton = styled(Button)`
    && {
        border-radius: 10px;
        background-color: #4caf50;
        color: #fff;
        &:hover {
            background-color: #388e3c;
        }
    }
`;

const Notification = styled.div`
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #ffffff;
    border-radius: 20px;
    padding: 30px;
    box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.2);
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 20px;
`;

const NotificationTitle = styled.h2`
    margin: 0;
    font-size: 24px;
    color: #333333;
`;

const NotificationInfo = styled.div`
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 10px;
`;

const NotificationCloseButton = styled(Button)`
    && {
        border-radius: 10px;
        background-color: #f44336;
        color: #fff;
        &:hover {
            background-color: #d32f2f;
        }
    }
`;

const Footer = styled.footer`
    width: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 60px;
    background-color: rgb(51, 51, 51);
    color: #ffffff;
 
`;

const StatisticsPage = () => {
    const [userData, setUserData] = useState({
        username: 'example_username',
        image: 'https://via.placeholder.com/200',
    });
    const [likeCount, setLikeCount] = useState(0); // Количество лайков
    const [dislikeCount, setDislikeCount] = useState(0); // Количество дизлайков
    const [likedUsers, setLikedUsers] = useState([
        { username: 'user1', image: 'https://via.placeholder.com/100' },
        { username: 'user2', image: 'https://via.placeholder.com/100' },
        { username: 'user3', image: 'https://via.placeholder.com/100' },
        { username: 'user4', image: 'https://via.placeholder.com/100' },
        { username: 'user5', image: 'https://via.placeholder.com/100' },
        { username: 'user6', image: 'https://via.placeholder.com/100' },
    ]);
    const [contactedUser, setContactedUser] = useState(null); // Состояние для хранения данных контактируемого пользователя

    useEffect(() => {
        const fetchData = async () => {
            try {
                // Fetch user data from database
                // Fetch liked users data from database
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };

        fetchData();
    }, []);

    const handleContact = (user) => {
        setContactedUser(user);
    };

    const calculateLikeRatio = () => {
        const total = likeCount + dislikeCount;
        if (total === 0) {
            return 0;
        }
        return (likeCount / total) * 100;
    };

    const calculateDislikeRatio = () => {
        const total = likeCount + dislikeCount;
        if (total === 0) {
            return 0;
        }
        return (dislikeCount / total) * 100;
    };
    const YourStatisticsTitle = styled.h2`
    margin-bottom: 10px;
    font-size: 24px;
    color: #333333;
`;

    const LikedTitle = styled.h2`
    margin-bottom: 10px;
    font-size: 24px;
    color: #333333;
`;


    const LikeCountContainer = styled.div`
    display: flex;
    align-items: center;
    gap: 5px;
`;

    const LikeCountNumber = styled.span`
    font-size: 20px;
    color: #333333;
`;

    const LikeCountLabel = styled.span`
    font-size: 16px;
    color: #666666;
`;

    return (
        <PageContainer>
            <Header>
                <Logo>Edudate</Logo>
                <ButtonContainer>
                    <Button type="submit" variant="contained" color="primary" component={Link} to={"/match"} >
                        Main
                    </Button>
                </ButtonContainer>
            </Header>
            <ContentContainer>
                <StatisticsContainer>
                    <YourStatisticsTitle>Your Statistics</YourStatisticsTitle>
                    <UserImage src={userData.image} alt={userData.username} />
                    <UserInfo>
                        <UserName>{userData.username}</UserName>
                    </UserInfo>
                    <LikeCountContainer>
                        <LikeCountNumber>{likeCount}</LikeCountNumber>
                        <LikeCountLabel>Likes</LikeCountLabel>
                    </LikeCountContainer>
                    <LikeDislikeRatioBar likeRatio={calculateLikeRatio()} dislikeRatio={calculateDislikeRatio()} />
                </StatisticsContainer>
                <LikedByContainer>
                    <LikedTitle>Liked:</LikedTitle>
                    {likedUsers.map((user, index) => (
                        <UserContainer key={index}>
                            <UserImage src={user.image} alt={user.username} />
                            <UserInfo>
                                <UserName>{user.username}</UserName>
                                <ContactButton onClick={() => handleContact(user)}>Contact</ContactButton>
                            </UserInfo>
                        </UserContainer>
                    ))}
                </LikedByContainer>
            </ContentContainer>
            <Footer>
                © 2024 Edudate. All rights reserved.
            </Footer>
            {contactedUser && (
                <Notification>
                    <NotificationTitle>Contact Information</NotificationTitle>
                    <UserImage src={contactedUser.image} alt={contactedUser.username} />
                    <NotificationInfo>
                        <div>Name: {contactedUser.name}</div>
                        <div>Email: {contactedUser.email}</div>
                        <div>Social Media: {contactedUser.socialMedia}</div>
                    </NotificationInfo>
                    <NotificationCloseButton onClick={() => setContactedUser(null)}>Close</NotificationCloseButton>
                </Notification>
            )}
        </PageContainer>
    );
};

export default StatisticsPage;