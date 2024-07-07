import React from 'react';
import styled, { keyframes } from 'styled-components';
import Link from 'next/link';
const fade = keyframes`
  0%, 100% { opacity: 0.2; }
  50% { opacity: 1; }
`;

const LoaderWrapper = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
`;

const Dot = styled.div`
  width: 20px;
  height: 20px;
  border-radius: 50%;
  margin: 0 10px;
  animation: ${fade} 3s infinite;

  &:nth-child(1) {
    background-color: #FE2C55;
    animation-delay: 0s;
  }

  &:nth-child(2) {
    background-color: #25F4EE;
    animation-delay: 0.5s;
  }

  &:nth-child(3) {
    background-color: #FE2C55;
    animation-delay: 1s;
  }
`;

const UnavailableComponent = () => (
  <div className="w-full min-h-screen flex_col_center gap-4">

    <LoaderWrapper>
      <Dot />
      <Dot />
      <Dot />
    </LoaderWrapper>
    <p className="text-center">This page is not available for demo :(<br/>check out our other demo pages!</p>

    <div className="flex_center gap-4 ">
      <Link href="/" className="underline text-tiktok-red ">Home</Link>
      <Link href="/" className="underline text-tiktok-red ">Wallet</Link>
      <Link href="/" className="underline text-tiktok-red ">Shop</Link>
    </div>


</div>

);

export default UnavailableComponent;