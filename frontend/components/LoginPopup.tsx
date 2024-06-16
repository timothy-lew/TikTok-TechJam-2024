import { LoginContext } from "@/app/layout";
import Image from 'next/image';
import React, { useContext, useState } from "react";
import { FaTimes } from "react-icons/fa";

interface LoginPopupProps {
  onClose: () => void;
  isOpen: boolean;
}

const LoginPopup: React.FC<LoginPopupProps> = ({ onClose, isOpen }) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const { loggedIn, setLoggedIn } = useContext(LoginContext);

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    // Check if email and password are filled
    if (!email || !password) {
      alert("Please enter both email and password.");
      return;
    }

    // Perform authentication logic here (e.g., API call, validation)
    // For demonstration purposes, just setting a dummy token
    const userToken = "dummyToken123"; // Replace with actual token logic

    // Store token in localStorage
    localStorage.setItem("usertoken", userToken);

    // For now any email and password combination is allowed
    // Change context to logged in, so that rest of the application knows
    setLoggedIn(true);

    // Close the popup
    onClose();
  };

  if (!isOpen) return null;

  return (
    <div
      id="login-popup"
      tabIndex={-1}
      className="bg-black/50 overflow-y-auto overflow-x-hidden fixed top-0 right-0 left-0 z-50 h-full flex items-center justify-center"
    >
      <div className="relative p-4 w-full max-w-md h-full md:h-auto">
        <div className="relative bg-white rounded-lg shadow">
          <button
            type="button"
            className="absolute top-3 right-2.5 text-gray-400 bg-transparent hover:bg-gray-200 hover:text-gray-900 rounded-lg text-sm p-1.5 ml-auto inline-flex items-center"
            onClick={onClose}
          >
            <FaTimes className="w-5 h-5" fill="#c6c7c7" />
            <span className="sr-only">Close popup</span>
          </button>

          <div className="p-5">
            <div className="text-center">
              <p className="mb-3 text-2xl font-semibold leading-5 text-slate-900">
                Login to your account
              </p>
              <p className="mt-2 text-sm leading-4 text-slate-600">
                You must be logged in to perform this action.
              </p>
            </div>

            <div className="mt-7 flex flex-col gap-2">
              <button className="inline-flex h-10 w-full items-center justify-center gap-2 rounded border border-slate-300 bg-white p-2 text-sm font-medium text-black outline-none focus:ring-2 focus:ring-[#333] focus:ring-offset-1 disabled:cursor-not-allowed disabled:opacity-60">
                <Image
                  src="https://www.svgrepo.com/show/511330/apple-173.svg"
                  alt="Apple"
                  className="h-[18px] w-[18px]"
                  width={30}
                  height={30}
                />
                Continue with Apple
              </button>

              <button className="inline-flex h-10 w-full items-center justify-center gap-2 rounded border border-slate-300 bg-white p-2 text-sm font-medium text-black outline-none focus:ring-2 focus:ring-[#333] focus:ring-offset-1 disabled:cursor-not-allowed disabled:opacity-60">
                <Image
                  src="https://www.svgrepo.com/show/475656/google-color.svg"
                  alt="Google"
                  className="h-[18px] w-[18px]"
                  width={30}
                  height={30}
                />
                Continue with Google
              </button>

              <button className="inline-flex h-10 w-full items-center justify-center gap-2 rounded border border-slate-300 bg-white p-2 text-sm font-medium text-black outline-none focus:ring-2 focus:ring-[#333] focus:ring-offset-1 disabled:cursor-not-allowed disabled:opacity-60">
                <Image
                  src="https://www.svgrepo.com/show/475647/facebook-color.svg"
                  alt="FaceBook"
                  className="h-[18px] w-[18px]"
                  width={30}
                  height={30}
                />
                Continue with FaceBook
              </button>
            </div>

            <div className="flex w-full items-center gap-2 py-6 text-sm text-slate-600">
              <div className="h-px w-full bg-slate-200"></div>
              OR
              <div className="h-px w-full bg-slate-200"></div>
            </div>

            <form onSubmit={handleSubmit} className="w-full">
              <label htmlFor="email" className="sr-only">
                Email address
              </label>
              <input
                name="email"
                type="email"
                autoComplete="email"
                required
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                className="block w-full rounded-lg border text-black border-gray-300 px-3 py-2 shadow-sm outline-none placeholder:text-gray-400 focus:ring-2 focus:ring-black focus:ring-offset-1"
                placeholder="Email Address"
              />
              <label htmlFor="password" className="sr-only">
                Password
              </label>
              <input
                name="password"
                type="password"
                autoComplete="current-password"
                required
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                className="mt-2 block w-full rounded-lg border text-black border-gray-300 px-3 py-2 shadow-sm outline-none placeholder:text-gray-400 focus:ring-2 focus:ring-black focus:ring-offset-1"
                placeholder="Password"
              />
              <p className="mb-3 mt-2 text-sm text-gray-500">
                <a
                  href="/forgot-password"
                  className="text-blue-800 hover:text-blue-600"
                >
                  Reset your password?
                </a>
              </p>
              <button
                type="submit"
                className="inline-flex w-full items-center justify-center rounded-lg bg-black p-2 py-3 text-sm font-medium text-white outline-none focus:ring-2 focus:ring-black focus:ring-offset-1 disabled:bg-gray-400"
              >
                Continue
              </button>
            </form>

            <div className="mt-6 text-center text-sm text-slate-600">
              {"Don't have an account? "}
              <a href="/signup" className="font-medium text-[#4285f4]">
                Sign up
              </a>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default LoginPopup;
