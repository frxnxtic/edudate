import React, { useState } from 'react';
import styled from 'styled-components';
import Button from '@material-ui/core/Button';
import { Link, useNavigate } from 'react-router-dom';
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

const ErrorText = styled.p`
    color: red;
    font-size: 14px;
    margin-top: 5px;
`;

const ImagePreview = styled.img`
    width: 100%;
    height: auto;
    border-radius: 10px;
    margin-top: 10px;
`;

const UploadImagePage = () => {
    const [selectedImage, setSelectedImage] = useState(null);
    const [imageError, setImageError] = useState(false);

    const handleImageChange = (event) => {
        setSelectedImage(event.target.files[0]);
        setImageError(false); // Reset error state when a new image is selected
    };

    const navigate = useNavigate();
    const handleSubmit = async (event) => {
        event.preventDefault();

        // Check if an image is selected
        if (!selectedImage) {
            setImageError(true);
            return;
        }

        // Prepare the form data
        const formData = new FormData();
        formData.append('file', selectedImage);

        // Get userId from local storage
        const id = localStorage.getItem('id');

        // Make a POST request to the backend
        const response = await fetch(`http://localhost:8080/api/images/upload/${id}`, {
            method: 'POST',
            body: formData
        });

        // Check if the request was successful
        if (response.ok) {
            localStorage.setItem('name', response.name);
            console.log('Image upload successful');
            navigate('/registerTest');
        } else {
            console.log('Image upload failed');
        }
    };

    return (
        <RegisterPageContainer>
            <RegisterForm onSubmit={handleSubmit}>
                <RegisterFormTitle>Upload Image</RegisterFormTitle>
                <input
                    accept="image/*"
                    type="file"
                    onChange={handleImageChange}
                    style={{ margin: '20px 0' }}
                />
                {selectedImage && (
                    <ImagePreview src={URL.createObjectURL(selectedImage)} alt="Selected" />
                )}
                {imageError && (
                    <ErrorText>Please select an image to upload</ErrorText>
                )}
                <Button type="submit" variant="contained" color="primary">
                    Upload
                </Button>
                Already have an account? <Link to="/login">Login</Link>
            </RegisterForm>
        </RegisterPageContainer>
    );
};

export default UploadImagePage;
