import React, { useState } from 'react';
import styled from 'styled-components';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import backImage from '../back.jpg';
import {Link} from "react-router-dom"; // Предположим, что у вас есть фоновое изображение
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
    background-size: cover; /* Установите фоновое изображение в полный размер */
    background-position: center; /* Центрируйте изображение по центру */
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


const ProfilePage = () => {
    const initialUserData = {
        username: 'example_username',
        name: 'Example Name',
        age: 25,
        gender: 'Male',
        faculty: 'Example Faculty',
        image: 'https://via.placeholder.com/200', // URL изображения пользователя
    };

    const [userData, setUserData] = useState(initialUserData);
    const [editable, setEditable] = useState(false);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setUserData({ ...userData, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        setEditable(true); // Завершаем редактирование при сохранении
        // Отправляем данные на сервер
        fetch('сюда_вставьте_ссылку_на_API_для_отправки_данных', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userData),
        })
            .then(response => response.json())
            .then(data => {
                console.log('Данные успешно отправлены:', data);
                // Добавьте здесь дополнительную логику или обновление состояния, если это необходимо
            })
            .catch(error => {
                console.error('Ошибка при отправке данных:', error);
                // Обработайте ошибку, если это необходимо
            });
    };

    const handleEdit = () => {
        setEditable(true); // Разрешаем редактирование при нажатии кнопки "Change"
    };

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
            <ProfileForm onSubmit={handleSubmit}>
                <ProfileImage src={userData.image} alt={userData.username} />
                {editable ? (
                    <>
                        <ProfileTextField
                            label="Username"
                            name="username"
                            value={userData.username}
                            onChange={handleChange}
                            margin="normal"
                            variant="outlined"
                        />
                        <ProfileTextField
                            label="Name"
                            name="name"
                            value={userData.name}
                            onChange={handleChange}
                            margin="normal"
                            variant="outlined"
                        />
                        <ProfileTextField
                            label="Age"
                            name="age"
                            value={userData.age}
                            onChange={handleChange}
                            margin="normal"
                            variant="outlined"
                        />
                        <ProfileTextField
                            label="Gender"
                            name="gender"
                            value={userData.gender}
                            onChange={handleChange}
                            margin="normal"
                            variant="outlined"
                        />
                        <ProfileTextField
                            label="Faculty"
                            name="faculty"
                            value={userData.faculty}
                            onChange={handleChange}
                            margin="normal"
                            variant="outlined"
                        />
                        <ActionButton type="submit">Save</ActionButton>
                    </>
                ) : (
                    <>
                        <p>Username: {userData.username}</p>
                        <p>Name: {userData.name}</p>
                        <p>Age: {userData.age}</p>
                        <p>Gender: {userData.gender}</p>
                        <p>Faculty: {userData.faculty}</p>
                        <ActionButton onClick={handleEdit}>Change</ActionButton>
                    </>
                )}
            </ProfileForm>
            <Footer>
                © 2024 Edudate. All rights reserved.
            </Footer>
        </PageContainer>
    );
};

export default ProfilePage;
