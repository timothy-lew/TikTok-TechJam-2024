import React, { useState } from 'react';
import LoginPopup from './LoginPopup';

const LoginRequired: React.FC = () => {
  const [isLoginPopupOpen, setIsLoginPopupOpen] = useState(false);

  const handleLoginClick = () => {
    setIsLoginPopupOpen(true);
  };

  const handleClosePopup = () => {
    setIsLoginPopupOpen(false);
  };

  return (
    <>
      <section className="flex justify-center items-center min-h-screen">
        <div className="py-8 px-4 mx-auto max-w-screen-xl lg:py-16 grid lg:grid-cols-2 gap-8 lg:gap-16">
          <div className="flex flex-col justify-center">
            <h1 className="mb-4 text-4xl font-extrabold tracking-tight leading-none text-gray-900 md:text-5xl lg:text-6xl dark:text-white">
              Please log in to continue.
            </h1>
            <button
              className="text-blue-600 dark:text-blue-500 hover:underline font-medium text-lg inline-flex items-center"
              onClick={handleLoginClick}
            >
              Log in or Create Account
              <svg
                className="w-3.5 h-3.5 ms-2 rtl:rotate-180"
                aria-hidden="true"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 14 10"
              >
                <path
                  stroke="currentColor"
                  stroke-linecap="round"
                  stroke-linejoin="round"
                  stroke-width="2"
                  d="M1 5h12m0 0L9 1m4 4L9 9"
                />
              </svg>
            </button>
          </div>
        </div>
      </section>
      <LoginPopup isOpen={isLoginPopupOpen} onClose={handleClosePopup} />
    </>
  );
};

export default LoginRequired;
