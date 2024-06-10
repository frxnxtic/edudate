import React, { useState, useEffect } from 'react';
import styled, {createGlobalStyle} from 'styled-components';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import backImage from '../back.jpg';
import { Link } from "react-router-dom";
const GlobalStyle = createGlobalStyle`
    body, html {
        margin: 0;
        padding: 0;
        overflow-x: hidden;
        width: 100%;
        height: 100%;
    }
`;
const Header = styled.div`
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 20px;
`;

const Logo = styled.h1`
    margin: 0;
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

const PageContainer = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: space-between;
    align-items: center;
    min-height: 100vh;
    background-image: url(${backImage});
    background-size: cover;
    background-position: center;
`;

const ProfileForm = styled.form`
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 40px;
    background-color: rgba(255, 255, 255, 0.89);
    border-radius: 20px;
    box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.2);
`;

const ProfileImage = styled.img`
    width: 150px;
    height: 150px;
    object-fit: cover;
    border-radius: 50%;
    margin-bottom: 20px;
`;

const ProfileTextField = styled(TextField)`
    && {
        margin: 10px 0;
        width: 300px;
        border-radius: 10px;
    }
`;

const ActionButton = styled(Button)`
    && {
        margin-top: 20px;
        width: 150px;
        border-radius: 10px;
        background-color: #4caf50;
        color: #fff;
        &:hover {
            background-color: #388e3c;
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


const formatDate = (dateString) => {
    const options = { year: 'numeric', month: 'long', day: 'numeric' };
    return new Date(dateString).toLocaleDateString("sk-sk", options);
};

const ProfilePage = () => {
    const [userData, setUserData] = useState(null);
    const [editable, setEditable] = useState(false);


    useEffect(() => {
        const token = localStorage.getItem('token');

        const fetchData = async () => {

            const id = localStorage.getItem('id');
            try {
                const response = await fetch(`http://localhost:8080/api/${id}`, {
                    method: 'GET',
                    headers: {
                        'Content-Type': 'application/json',
                        'Accept': 'application/json',
                        'Authorization': `Bearer ${token}`
                    }
                });
                const data = await response.json();
                setUserData(data);
            } catch (error) {
                console.error('Error fetching data: ', error);
            }
        };

        fetchData();
    }, []);






    if (!userData) {
        return <p>Loading...</p>;
    }

    return (
        <>
            <GlobalStyle />
            <PageContainer>
                <Header>
                    <Logo>Edudate</Logo>
                    <ButtonContainer>
                        <Button type="submit" variant="contained" color="primary" component={Link} to={"/match"} >
                            Main
                        </Button>
                    </ButtonContainer>
                </Header>
                <ProfileForm >
                    <ProfileImage src={userData.image} alt={userData.username} />
                    {editable ? (
                        <>
                            <ProfileTextField
                                label="Username"
                                name="username"
                                value={userData.username}
                                margin="normal"
                                variant="outlined"
                            />
                            <ProfileTextField
                                label="Name"
                                name="name"
                                value={userData.name}
                                margin="normal"
                                variant="outlined"
                            />
                            <ProfileTextField
                                label="Date of Birth"
                                name="dateOfBirth"
                                value={userData.dateOfBirth}
                                margin="normal"
                                variant="outlined"
                                type="date"
                                InputLabelProps={{
                                    shrink: true,
                                }}
                            />
                            <ProfileTextField
                                label="Faculty"
                                name="faculty"
                                value={userData.faculty}
                                margin="normal"
                                variant="outlined"
                            />
                            <ActionButton type="submit">Save</ActionButton>
                        </>
                    ) : (
                        <>
                            <p>Username: {userData.username}</p>
                            <p>Name: {userData.name}</p>
                            <p>Date of Birth: {formatDate(userData.dateOfBirth)}</p>
                            <p>Faculty: {userData.faculty}</p>

                        </>
                    )}
                </ProfileForm>
                <Footer>
                    © 2024 Edudate. All rights reserved.
                </Footer>
            </PageContainer>
        </>
    );
};

export default ProfilePage;