"use client";

import { useState } from "react";
import { useAuth } from '@/hooks/auth-provider'
import LoginPopup from "@/components/shared/LoginPopup";
import LoginRequired from "@/components/shared/LoginRequired";
import Link from "next/link";

import { Transaction, columns, transactionData } from "@/components/tables/transactions";
import { DataTable } from "@/components/ui/data-table";



const WalletPage: React.FC = () => {
  // const { loggedIn } = useContext(LoginContext);
  const auth = useAuth();

  const loggedIn = auth!==null;

  const data : Transaction[] = transactionData;

  const [isLoginPopupOpen, setLoginPopupOpen] = useState(!loggedIn);

  const handleClosePopup = () => setLoginPopupOpen(false);

  if (!loggedIn) {
    return (
      <div className="flex_center min-h-screen bg-background text-foreground">
        {isLoginPopupOpen ? (
          <LoginPopup isOpen={isLoginPopupOpen} onClose={handleClosePopup} />
        ) : (
          // Display that login is required
          <LoginRequired></LoginRequired>
        )}
      </div>
    );
  }

  // Need to implement logic to get balance and recent transaction history from api
  return (
    <>
      <section className="bg-white dark:bg-gray-900">
        <div className="py-8 px-4 mx-auto max-w-screen-xl lg:py-16">
          <div className="bg-gray-50 dark:bg-gray-800 border border-gray-200 dark:border-gray-700 rounded-lg p-8 md:p-12 mb-8">
            <h1 className="text-gray-900 dark:text-white text-3xl md:text-5xl font-extrabold mb-2">
              Wallet Balance
            </h1>
            <p className="text-3xl font-normal text-gray-500 dark:text-gray-400 mb-6">
              2000.03
            </p>
            <p className="text-lg font-normal text-gray-500 dark:text-gray-400 mb-4">
              Recent Transactions:
            </p>
          </div>
          <div className="grid md:grid-cols-2 gap-8">
            <div className="bg-gray-50 dark:bg-gray-800 border border-gray-200 dark:border-gray-700 rounded-lg p-8 md:p-12">
              <h2 className="text-gray-900 dark:text-white text-3xl font-extrabold mb-2">
                Top Up Wallet
              </h2>
              <p className="text-lg font-normal text-gray-500 dark:text-gray-400 mb-4">
                Use your credit card or gift card to top up your wallet balance.
              </p>
              <Link
                href="/wallet/topup"
                className="text-blue-600 dark:text-blue-500 hover:underline font-medium text-lg inline-flex items-center"
              >
                Top Up
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
              </Link>
            </div>
            <div className="bg-gray-50 dark:bg-gray-800 border border-gray-200 dark:border-gray-700 rounded-lg p-8 md:p-12">
              <h2 className="text-gray-900 dark:text-white text-3xl font-extrabold mb-2">
                Convert Between Fiat money and Coin
              </h2>
              <p className="text-lg font-normal text-gray-500 dark:text-gray-400 mb-4">
                Exchange between fiat money and TikTok Coin.
              </p>
              <Link
                href="/wallet/exchange"
                className="text-blue-600 dark:text-blue-500 hover:underline font-medium text-lg inline-flex items-center"
              >
                Convert Now
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
              </Link>
            </div>
          </div>
        </div>

        <DataTable columns={columns} data={data} />
      </section>
    </>
  );
};

export default WalletPage;
