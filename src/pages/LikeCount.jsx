import React from 'react';
import styled from 'styled-components';

const CountContainer = styled.div`
  margin-top: 10px;
`;

const LikeCount = ({ count }) => {
    return <CountContainer>{count} Likes</CountContainer>;
};

export default LikeCount;