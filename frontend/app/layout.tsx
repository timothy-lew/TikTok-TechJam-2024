"use client"

import { Inter } from "next/font/google";
import "./globals.css";

import { createContext, useContext, useState } from 'react';
import Header from '@/components/Header';
import SideBar from '@/components/SideBar'

interface LoginContextType {
  loggedIn: boolean;
  setLoggedIn: React.Dispatch<React.SetStateAction<boolean>>;
}

const initialLoginContext: LoginContextType = {
  loggedIn: false,
  setLoggedIn: () => {},
};

export const LoginContext = createContext(initialLoginContext);


const inter = Inter({ subsets: ["latin"] });

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {

  const [loggedIn, setLoggedIn] = useState(false);

  const loginContextValue: LoginContextType = {
    loggedIn,
    setLoggedIn,
  };

  return (
    <html lang="en">
      <body className={inter.className}>
        <LoginContext.Provider value={loginContextValue}>
          <main>
            <section className="top-0 h-16 bg-black w-full absolute">
              <Header />
            </section>
            
            <section className="flex justify-start items-center pt-20">
              <nav className="w-40 min-h-screen">
                <SideBar />
              </nav>
              <div className="w-full min-h-screen">
                {children}
              </div>
            </section>
          </main>          
        
        </LoginContext.Provider>
      </body>
    </html>
  );
}
