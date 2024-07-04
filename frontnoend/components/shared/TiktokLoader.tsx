import React from 'react';
import styled, { keyframes } from 'styled-components';

const orbit = keyframes`
  0%, 100% {
    transform: translateX(-12px) ;
    z-index: 1;
  }
  25% {
    transform: translateX(0) ;
    z-index: 1;
  }
  50% {
    transform: translateX(12px) ;
    z-index: 0;
  }
  75% {
    transform: translateX(0) ;
    z-index: 0;
  }
`;

const LoaderWrapper = styled.div`
  width: 60px;
  height: 30px;
  position: relative;
  perspective: 200px;
`;

const Ball = styled.div`
  width: 20px;
  height: 20px;
  border-radius: 50%;
  position: absolute;
  top: 50%;
  left: 50%;
  transform-origin: center center;
`;

const RedBall = styled(Ball)`
  background-color: #FE2C55;
  animation: ${orbit} 1.5s linear infinite;
`;

const CyanBall = styled(Ball)`
  background-color: #25F4EE;
  animation: ${orbit} 1.5s linear infinite 0.75s;
`;

const TikTokLoader = () => {
  return (
    <LoaderWrapper>
      <RedBall />
      <CyanBall />
    </LoaderWrapper>
  );
};

export default TikTokLoader;