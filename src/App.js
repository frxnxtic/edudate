import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import RegisterPage from "./pages/RegisterPage";
import SecondRegisterPage from "./pages/SecondRegisterPage";
import MatchPage from "./pages/MatchPage";
import RegisterTest from "./pages/RegisterTest";
import ProfilePage from "./pages/ProfilePage";
import StatisticsPage from "./pages/StatisticsPage";
import LikeCount from "./pages/LikeCount";
import LikeDislikeRatioBar from "./pages/LikeDislikeRatioBar";

const App = () => {
    return (
        <Router>
            <Routes>
                <Route exact path="/" element={<HomePage />} />
                <Route path="/login" element={<LoginPage />} />
                <Route path="/register" element={<RegisterPage />} />
                <Route path="/secondregister" element={<SecondRegisterPage />} />
                <Route path="/match" element={<MatchPage />} />
                <Route path="/registerTest" element={<RegisterTest />} />
                <Route path="/profile" element={<ProfilePage />} />
                <Route path="/statistics" element={<StatisticsPage />} />
                <Route path="/likecounter" element={<LikeCount />} />
                <Route path="/likedislikeratiobar" element={<LikeDislikeRatioBar />} />
            </Routes>
        </Router>
    );
};

export default App;
