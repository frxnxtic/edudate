import React from 'react';
import styled from 'styled-components';
import Button from '@material-ui/core/Button';
import image from '../image.jpg';
import { AiOutlineLike, AiOutlineDislike } from 'react-icons/ai';
import {Link} from "react-router-dom"; // Добавляем импорт иконок

const PageContainer = styled.div`
    display: flex;
    flex-direction: column;
    max-height: 100vh;
    justify-content: space-between;
    align-items: center;
    background-image: url(${image});
    background-repeat: no-repeat;
    background-size: cover;
    background-position: center;
    width: auto;
    height: 100vh;
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

const Footer = styled.footer`
    width: 100%;
    //padding: 20px;
    background-color: #333;
    color: #fff;
    text-align: center;
`;

const LikeButton = styled(Button)`
    && {
        /* Стили для кнопки "Like" */
        position: absolute;
        top: 50%;
        left: 450px; /* Изменяем позицию на левую сторону */
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
        /* Стили для кнопки "Dislike" */
        position: absolute;
        top: 50%;
        right: 450px; /* Изменяем позицию на правую сторону */
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
    font-size: 2rem; /* Размер иконки в 2 раза больше */
`;

const LargeDislikeIcon = styled(AiOutlineDislike)`
    font-size: 2rem; /* Размер иконки в 2 раза больше */
`;

const MatchPage = () => {
    // Данные пользователя (заглушки, вы можете заменить их на реальные данные из вашей базы данных)
    const user = {
        username: 'example_username',
        name: 'Example Name',
        age: 25,
        gender: 'Male',
        faculty: 'Example Faculty',
        image: 'https://via.placeholder.com/350',
    };

    return (
        <PageContainer>
            <Header>
                <Logo>Edudate</Logo>
                <ButtonContainer>
                    <Button type="submit" variant="contained" color="primary" component={Link} to={"/profile"} >
                        Profile
                    </Button>
                    <Button type="submit" variant="contained" color="primary" component={Link} to={"/statistics"} >
                        statistics
                    </Button>
                </ButtonContainer>
            </Header>
            <MatchCardContainer>
                <MatchCard>
                    <MatchName>{user.username}</MatchName>
                    <MatchImage src={user.image} alt={user.username} />
                    <UserData>
                        <p>Name: {user.name}</p>
                        <p>Age: {user.age}</p>
                        <p>Gender: {user.gender}</p>
                        <p>Faculty: {user.faculty}</p>
                    </UserData>
                    <LikeButton variant="contained" color="primary">
                        <LargeLikeIcon />
                    </LikeButton>
                    <DislikeButton variant="contained" color="secondary">
                        <LargeDislikeIcon />
                    </DislikeButton>
                </MatchCard>
            </MatchCardContainer>
            
            <Footer>
                © 2024 Edudate. All rights reserved.
            </Footer>
        </PageContainer>
    );
};

export default MatchPage;
