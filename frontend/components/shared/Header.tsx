"use client";

import Image from "next/image";
import { useState } from "react";
import { FaSearch, FaUser, FaBell, FaWallet } from "react-icons/fa";
import LoginPopup from "@/components/shared/LoginPopup";
import Link from "next/link";
import { Button } from "@/components/ui/button"
import { useAuth } from "@/hooks/auth-provider";
import {
  Avatar,
  AvatarFallback,
  AvatarImage,
} from "@/components/ui/avatar"

const Header: React.FC = () => {

  const auth = useAuth();

  const user = auth?.user || null;

  const [isLoginPopupOpen, setLoginPopupOpen] = useState(false);
  const [isDropdownOpen, setDropdownOpen] = useState(false);

  const handleOpenLoginPopup = () => setLoginPopupOpen(true);
  const handleCloseLoginPopup = () => setLoginPopupOpen(false);

  const handleToggleDropdown = () => setDropdownOpen(!isDropdownOpen);

  return (
    <>
      <header className="bg-white border-b border-gray-200 h-[60px] flex justify-between items-center">
        
          {/* Logo */}
          <div className="flex_center gap-1 px-4">
            <Image
              src="tiktokIconNoBg.svg"
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
          <div className="flex_center gap-6 mr-6">
            
            <Button variant="outline" className="w-full my-4 border-gray-300 hover:bg-gray-50 rounded-sm"
              onClick={handleOpenLoginPopup} 
            >
              <p className="font-semibold text-gray-700">+ Upload</p>
            </Button>

            {!user ?
              <>
                <Button className="my-4 px-8 bg-red-500 hover:bg-red-600 rounded-sm"
                  onClick={handleOpenLoginPopup} 
                >
                  <p className="font-semibold text-white">Log In</p>
                </Button>
                <Image src="/icons/menuDots.svg" alt="menu" height={40} width={25} className="ml-2 mr-8"/>
              </>
            :
              <div className="flex_center gap-4">
                  <svg className="css-y48l9g-StyledIcon e1nx07zo1" width="26" data-e2e="" height="26" viewBox="0 0 48 48" fill="currentColor" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" clip-rule="evenodd" d="M2.17877 7.17357C2.50304 6.45894 3.21528 6 4.00003 6H44C44.713 6 45.372 6.37952 45.7299 6.99615C46.0877 7.61278 46.0902 8.37327 45.7365 8.99228L25.7365 43.9923C25.3423 44.6821 24.5772 45.0732 23.7872 44.9886C22.9972 44.9041 22.3321 44.3599 22.0929 43.6023L16.219 25.0017L2.49488 9.31701C1.97811 8.72642 1.85449 7.88819 2.17877 7.17357ZM20.377 24.8856L24.531 38.0397L40.5537 10H8.40757L18.3918 21.4106L30.1002 14.2054C30.5705 13.9159 31.1865 14.0626 31.4759 14.533L32.5241 16.2363C32.8136 16.7066 32.6669 17.3226 32.1966 17.612L20.377 24.8856Z"></path></svg>
                  
                  <svg className="css-1g0p6jv-StyledInboxIcon e18kkhh41" width="35" data-e2e="" height="35" viewBox="0 0 32 32" fill="currentColor" xmlns="http://www.w3.org/2000/svg"><path fill-rule="evenodd" clip-rule="evenodd" d="M24.0362 21.3333H18.5243L15.9983 24.4208L13.4721 21.3333H7.96047L7.99557 8H24.0009L24.0362 21.3333ZM24.3705 23.3333H19.4721L17.2883 26.0026C16.6215 26.8176 15.3753 26.8176 14.7084 26.0026L12.5243 23.3333H7.62626C6.70407 23.3333 5.95717 22.5845 5.9596 21.6623L5.99646 7.66228C5.99887 6.74352 6.74435 6 7.66312 6H24.3333C25.2521 6 25.9975 6.7435 26 7.66224L26.0371 21.6622C26.0396 22.5844 25.2927 23.3333 24.3705 23.3333ZM12.6647 14C12.2965 14 11.998 14.2985 11.998 14.6667V15.3333C11.998 15.7015 12.2965 16 12.6647 16H19.3313C19.6995 16 19.998 15.7015 19.998 15.3333V14.6667C19.998 14.2985 19.6995 14 19.3313 14H12.6647Z"></path></svg>
                  
                  {/* <button
                    onClick={handleToggleDropdown}
                    className="hover:bg-red-500 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center inline-flex items-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800"
                  >
                    <FaWallet className="text-gray-800 w-6 h-6 cursor-pointer" />
                  </button> */}

                <Avatar>
                    <AvatarImage src={user.profilePic} alt="dp" className=""/>
                    <AvatarFallback>{user.firstName[0]+user.lastName[0]}</AvatarFallback>
                </Avatar>

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
              }

          </div>
        <LoginPopup isOpen={isLoginPopupOpen} onClose={handleCloseLoginPopup} />
      </header>
    </>
  );
};

export default Header;
