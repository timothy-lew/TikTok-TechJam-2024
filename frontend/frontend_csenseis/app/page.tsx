'use client'

import React, { createContext, useContext, useState } from 'react';
import Header from '@/components/Header';


interface LoginContextType {
  loggedIn: boolean;
  setLoggedIn: React.Dispatch<React.SetStateAction<boolean>>;
}

const initialLoginContext: LoginContextType = {
  loggedIn: false,
  setLoggedIn: () => {},
};
export const LoginContext = createContext(initialLoginContext);

export default function Home() {
  const [loggedIn, setLoggedIn] = useState(false);

  const loginContextValue: LoginContextType = {
    loggedIn,
    setLoggedIn,
  };

  return (
    <LoginContext.Provider value={loginContextValue}>
      <main>
        <Header />
        <h1>Insert fake home page here</h1>
      </main>
    </LoginContext.Provider>
  );
}
