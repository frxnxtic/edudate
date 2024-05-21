import React from 'react';
import styled from 'styled-components';
import backImage from '../back.jpg';
import { Link } from 'react-router-dom';
import Button from '@material-ui/core/Button';

const BackgroundContainer = styled.div`
    width: 100%;
    height: 100%;
    background-image: url(${backImage});
    background-size: cover;
    background-repeat: no-repeat;
    background-position: center center;
    position: relative;
`;

const Container = styled.div`
    padding: 20px;
    overflow-y: auto;
    max-height: 100vh;
`;

const Header = styled.header`
    text-align: left;
`;

const H1 = styled.h1`
    font-size: 48px;
    color: white;
    margin-left: 5px;
    text-shadow: -2px -2px 0 black, 2px -2px 0 black, -2px 2px 0 black, 2px 2px 0 black;
`;

const H2 = styled.h1`
    font-size: 20px;
    color: white;
    margin-left: 5px;
    text-shadow: -1px -1px 0 black, 1px -1px 0 black, -1px 1px 0 black, 1px 1px 0 black;
`;

const ButtonContainer = styled.div`
    max-width: 400px;
    margin: 200px auto 80px;
    background-color: rgba(255, 255, 255, 0.11);
    border: 4px solid #ffffff;
    border-radius: 60px;
    padding: 20px;
    display: flex;
    flex-direction: column;
    align-items: center;
`;

const ButtonText = styled.p`
    color: white;
    font-size: 16px;
    margin: 10px 0;
    padding: 10px;
    border-radius: 10px;
    display: inline;
    text-shadow: -1px -1px 0 black, 1px -1px 0 black, -1px 1px 0 black, 1px 1px 0 black;
`;

const StyledButton = styled(Button)`
    && {
        background-color: aliceblue;
        margin: 10px;
        padding: 10px 20px;
        font-size: 18px;
        font-weight: bold;
        border: 3px solid black;
        border-radius: 30px;
        cursor: pointer;
    }
`;

const BottomTextContainer = styled.div`
    position: relative;
    text-align: center;
    background-color: rgba(255, 255, 255, 0.44);
    padding: 20px;
    border-color: black;
    border-radius: 10px;
    margin-top: 20px;
    max-width: 600px;
    margin: 20px auto;
`;

const Text = styled.p`
    color: #000000;
    font-size: 20px;
    padding: 20px;
    border-radius: 10px;

`;

const Footer = styled.footer`
    text-align: center;
    padding: 20px;
    background-color: #333;
    color: #fff;
`;

const FooterText = styled.p`
    margin: 0;
`;

const HomePage = () => {
    return (
        <BackgroundContainer>
            <Container>
                <Header>
                    <H1>Edudate</H1>
                    <H2>The first dating site for students from your university</H2>
                </Header>

                <ButtonContainer>
                    <StyledButton component={Link} to="/login">Login</StyledButton>
                    <ButtonText> still don't have an account? </ButtonText>
                    <StyledButton component={Link} to="/register">Register</StyledButton>
                </ButtonContainer>
            </Container>

            <BottomTextContainer>
                <Text>
                    The Ultimate Platform<br />
                    for students to connect, discover common interests,<br />
                    and build meaningful relationships within their<br />
                    university community.<br />
                    Join EduDate today and embark on an exciting<br />
                    journey of meeting like-minded individuals, expanding<br />
                    your social network, and creating memorable<br />
                    experiences throughout your academic journey.<br />
                    Let EduDate be your trusted companion in navigating<br />
                    the world of student relationships and connections.
                </Text>
            </BottomTextContainer>

            <Footer>
                <FooterText>© 2024 EduDate. All rights reserved.</FooterText>
            </Footer>
        </BackgroundContainer>
    );
};

export default HomePage;
