"use client";

import Image from "next/image";
import React, { useState, useContext } from "react";
import { FaSearch, FaUser, FaBell, FaWallet } from "react-icons/fa";
import LoginPopup from "@/components/LoginPopup";
import { LoginContext } from "@/app/layout";
import Link from "next/link";
import { link } from "fs";
// import WalletPage from '@/components/WalletPage'; // Adjust the import based on your project structure

const Header: React.FC = () => {
  const [isLoginPopupOpen, setLoginPopupOpen] = useState(false);
  const [isDropdownOpen, setDropdownOpen] = useState(false);
  const { loggedIn } = useContext(LoginContext);

  const handleOpenLoginPopup = () => setLoginPopupOpen(true);
  const handleCloseLoginPopup = () => setLoginPopupOpen(false);

  const handleToggleDropdown = () => setDropdownOpen(!isDropdownOpen);

  return (
    <>
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
            {!loggedIn && (
              <button
                onClick={handleOpenLoginPopup}
                className="p-2 bg-red-500 text-white rounded"
              >
                Log In
              </button>
            )}
            {/* To add logic to get balance preview from API */}
            {loggedIn && (
              <>
                <FaUser className="text-gray-800 w-6 h-6 cursor-pointer" />
                <div className="relative">
                  <button
                    onClick={handleToggleDropdown}
                    className="hover:bg-red-500 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center inline-flex items-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                  >
                    <FaWallet className="text-gray-800 w-6 h-6 cursor-pointer" />
                  </button>
                  {isDropdownOpen && (
                    <div
                      id="dropdownInformation"
                      className="absolute right-0 mt-2 z-10 bg-white divide-y divide-gray-100 rounded-lg shadow w-44 dark:bg-gray-700 dark:divide-gray-600"
                    >
                      <div className="px-4 py-3 text-sm text-gray-900 dark:text-white">
                        <div>Current Balance</div>
                        <div className="font-medium truncate">2000.03</div>
                      </div>
                      <ul
                        className="py-2 text-sm text-gray-700 dark:text-gray-200"
                        aria-labelledby="dropdownInformationButton"
                      >
                        <li>
                          <a
                            href="#"
                            className="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white"
                          >
                            Transfer
                          </a>
                        </li>
                        <li>
                          <a
                            href="#"
                            className="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white"
                          >
                            History
                          </a>
                        </li>
                        <li>
                          <Link
                            href="/wallet"
                            className="block px-4 py-2 hover:bg-gray-100 dark:hover:bg-gray-600 dark:hover:text-white"
                          >
                            Settings
                          </Link>
                        </li>
                      </ul>
                    </div>
                  )}
                </div>
                <FaBell className="text-gray-800 w-6 h-6 cursor-pointer" />
              </>
            )}
          </div>
        </div>
        <LoginPopup isOpen={isLoginPopupOpen} onClose={handleCloseLoginPopup} />
      </header>
    </>
  );
};

export default Header;
