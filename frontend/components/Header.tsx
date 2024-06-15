'use client';

import Image from 'next/image';
import React, { useState, useContext } from 'react';
import { FaSearch, FaUser, FaBell, FaWallet } from 'react-icons/fa';
import LoginPopup from '@/components/LoginPopup';
import { LoginContext } from '@/app/layout';


const Header: React.FC = () => {
  const [isLoginPopupOpen, setLoginPopupOpen] = useState(false);
  // const { loggedIn, setLoggedIn } = useContext(LoginContext);


  const handleOpenPopup = () => setLoginPopupOpen(true);
  const handleClosePopup = () => setLoginPopupOpen(false);

  return (
    <header className="bg-white border-b border-gray-200 p-4">
      <div className="container mx-auto flex justify-between items-center">
        {/* Logo */}
        <div className="flex items-center space-x-2">
          <Image
            src="https://www.svgrepo.com/show/452114/tiktok.svg"
            alt="Logo"
            width={30}
            height={30}
            
          />
          <div className="text-2xl font-bold text-black">TikTok</div>
        </div>

        {/* Search Bar */}
        <div className="flex items-center bg-gray-100 rounded-full px-4 py-2 w-1/3">
          <FaSearch className="text-gray-500" />
          <input
            type="text"
            placeholder="Search"
            className="bg-transparent ml-2 outline-none w-full"
          />
        </div>

        {/* Icons if logged in, if not log in button */}
        <div className="flex items-center space-x-6">
          {/* {!loggedIn && (
            <button onClick={handleOpenPopup} className="p-2 bg-red-500 text-white rounded">
              Log In
            </button>
          )}
          {loggedIn && (
            <>
              <FaUser className="text-gray-800 w-6 h-6 cursor-pointer" />
              <FaWallet className="text-gray-800 w-6 h-6 cursor-pointer" />
              <FaBell className="text-gray-800 w-6 h-6 cursor-pointer" />
            </>
          )} */}
        </div>
      </div>
      {/* <LoginPopup isOpen={isLoginPopupOpen} onClose={handleClosePopup} /> */}
    </header>
  );
};

export default Header;
