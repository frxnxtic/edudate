// LikeDislikeRatioBar.jsx
import React from 'react';
import styled from 'styled-components';

const BarContainer = styled.div`
    margin-top: 10px;
    width: 100%;
    height: 10px;
    background-color: rgba(204, 204, 204, 0);
    border-radius: 5px;
`;

const LikeBar = styled.div`
  width: ${(props) => props.likeRatio}%;
  height: 100%;
  background-color: #4caf50;
  border-radius: 5px;
`;

const DislikeBar = styled.div`
  width: ${(props) => props.dislikeRatio}%;
  height: 100%;
  background-color: #f44336;
  border-radius: 5px;
`;

const LikeDislikeRatioBar = ({ likeRatio, dislikeRatio }) => {
    return (
        <BarContainer>
            <LikeBar likeRatio={likeRatio} />
            <DislikeBar dislikeRatio={dislikeRatio} />
        </BarContainer>
    );
};

export default LikeDislikeRatioBar;
