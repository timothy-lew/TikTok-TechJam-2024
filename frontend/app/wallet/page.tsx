"use client";

import { useState } from "react";
import { useAuth } from '@/hooks/auth-provider'
import LoginPopup from "@/components/shared/LoginPopup";
import LoginRequired from "@/components/shared/LoginRequired";
import Link from "next/link";
import Image from "next/image";
import { Transaction, columns, transactionData } from "@/components/tables/transactions";
import { DataTable } from "@/components/ui/data-table";



const WalletPage: React.FC = () => {
  const auth = useAuth();
  // TODO: Fix this if not auth display pop
  const [isLoginPopupOpen, setLoginPopupOpen] = useState(auth===null);

  const handleClosePopup = () => setLoginPopupOpen(false);
  if (!auth) {
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

  const user = auth.user || null;

  // Process Transaction Data
  const data : Transaction[] = transactionData;

  let incoming : number = 0;
  let outgoing : number = 0;

  transactionData.forEach((transaction)=>{
    if (transaction.type==="incoming") incoming += transaction.amount;
    else outgoing += transaction.amount;
  })

  // Need to implement logic to get balance and recent transaction history from api
  return (
      <section className="bg-white dark:bg-gray-900 flex flex-col w-full justify-start items-start gap-4 md:gap-6">

        <div className="bg-gray-50 p-4 rounded-md shadow-sm w-full">
          <h1 className="font-bold text-2xl md:text-3xl">Hello! Good to see you <span className="capitalize">{user?.firstName || "NOT LOGGED IN"}</span></h1>
        </div>

        <div className="w-full grid grid-cols-1 md:grid-cols-3 gap-4">
          <div className="bg-gray-50 dark:bg-gray-800 rounded-lg p-4 col-span-1 md:col-span-2 shadow-sm">
            
            <div className="flex justify-center items-start">
              <div className="flex-1">
                <h2 className="text-gray-900 dark:text-white text-xl md:text-2xl font-bold mb-2">
                  Wallet Balance
                </h2>
                <p className="text-3xl font-normal text-gray-500 dark:text-gray-400 mb-6">
                  2000.03
                </p>
              </div>

              <div className="flex-1 flex flex-col justify-start items-center">
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
                <p className="max-w-[150px] text-sm font-normal text-center text-gray-500 dark:text-gray-400 mb-4">
                  Use credit or gift cards in one click!
                </p>
              </div>

              <div className="flex-1 flex flex-col justify-start items-center">
                
                <Link
                  href="/wallet/exchange"
                  className="text-blue-600 dark:text-blue-500 hover:underline font-medium text-lg inline-flex items-center"
                >
                  Trade Currency
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
                <p className="text-sm font-normal text-center text-gray-500 dark:text-gray-400 mb-4">
                  Exchange cash to TikTok Coin.
                </p>
              </div>

            </div>

            <div className="bg-gray-50 dark:bg-gray-800 flex_center gap-2">

              <div className="flex-1 w-full h-full flex_col_center gap-3">
                <div className="flex_center gap-3">
                  <Image src="/icons/incomingTransactions.svg" alt="icon" height={45} width={45}/>
                  <h2 className="font-bold text-lg md:text-xl text-green-600">Incoming:</h2>
                </div>
                <p className="text-2xl font-medium text-black">+${incoming}</p>
              </div>

              <div className="flex-1 w-full h-full flex_col_center gap-3">
                <div className="flex_center gap-3">
                  <Image src="/icons/outgoingTransactions.svg" alt="icon" height={45} width={45}/>
                  <h2 className="font-bold text-lg md:text-xl text-yellow-600">Outgoing:</h2>
                </div>
                <p className="text-2xl font-medium text-black">-${outgoing}</p>
              </div>
            </div>

          </div>

          <div className="col-span-1 bg-gray-50 p-4 rounded-md shadow-sm w-full flex flex-col justify-center items-start">
            <h2 className="text-gray-900 dark:text-white text-xl md:text-2xl font-bold mb-2">Your Tiktok Card</h2>
            <Image src="/images/tiktokCard.svg" alt="tiktokcard" height={150} width={200}/>
          </div>
        </div>

        <div className="w-full">
          <DataTable columns={columns} data={data} />
        </div>
      </section>
  );
};

export default WalletPage;
