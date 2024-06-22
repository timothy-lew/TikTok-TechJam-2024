"use client";

import { useState } from "react";
import { useAuth } from "@/hooks/auth-provider";
import LoginPopup from "@/components/shared/LoginPopup";
import LoginRequired from "@/components/shared/LoginRequired";
import Link from "next/link";
import Image from "next/image";
import { columns } from "@/components/tables/transactions";
import { DataTable } from "@/components/ui/data-table";

import {
  type Transaction,
  useFetchTransactions,
} from "@/hooks/useFetchTransactions";
import TiktokCard from "@/components/shared/TiktokCard";
import {
  type TiktokCardDetails,
  useFetchTiktokCard,
} from "@/hooks/useFetchTiktokCard";
import { type Wallet, useFetchWallet } from "@/hooks/useFetchWallet";

const WalletPage: React.FC = () => {
  const auth = useAuth();

  const user = auth?.user || null;

  // TODO: Fix this if not auth display pop
  const [isLoginPopupOpen, setLoginPopupOpen] = useState(user === null);

  const handleClosePopup = () => setLoginPopupOpen(false);
  if (!user) {
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

  // Process Transaction Data
  const transactionData: Transaction[] = useFetchTransactions(user?.id || "");

  let incoming: number = 0;
  let outgoing: number = 0;

  transactionData.forEach((transaction) => {
    if (transaction.type === "incoming") incoming += transaction.amount;
    else outgoing += transaction.amount;
  });

  const tiktokCardDetails = useFetchTiktokCard(user?.id || "");
  const [hideDetails, setHideDetails] = useState<boolean>(true);

  const walletDetails = useFetchWallet(user?.id || "");

  // Need to implement logic to get balance and recent transaction history from api
  return (
    <section className="bg-white dark:bg-gray-900 flex flex-col w-full justify-start items-center gap-4 px-6">
      <div className="bg-gray-50 rounded-xl p-4 shadow-sm w-full">
        <h1 className="font-bold text-2xl md:text-3xl">
          Hello! Good to see you{" "}
          <span className="capitalize">{user?.firstName || "NOT LOGGED IN"}</span>
        </h1>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-3 gap-4 w-full">
        {/* Your Tiktok Card Section */}
        <div className="bg-gray-50 p-4 rounded-md shadow-sm flex flex-col justify-start items-center">
          <h2 className="text-gray-900 dark:text-white text-center w-full text-xl md:text-2xl font-bold mb-2">
            Your Tiktok Card
          </h2>
          <div className="w-full flex justify-evenly items-center mb-4">
            <button
              className="flex items-center gap-1.5 border-2 border-slate-200 hover:bg-slate-100 rounded-md px-2 py-1"
              onClick={() => {
                setHideDetails((prev) => !prev);
              }}
            >
              <Image
                src={hideDetails ? "/icons/eyeOpen.svg" : "/icons/eyeClose.svg"}
                alt="icon"
                height={22}
                width={22}
              />
              <p className="text-sm">{hideDetails ? "Show" : "Hide"} Details</p>
            </button>
            <button
              className="flex items-center gap-1.5 border-2 border-slate-200 hover:bg-slate-100 rounded-md px-2 py-1"
              onClick={() => {}}
            >
              <Image
                src="/icons/freeze.svg"
                alt="icon"
                height={20}
                width={20}
              />
              <p className="text-sm">Freeze Card</p>
            </button>
          </div>
          {tiktokCardDetails && (
            <TiktokCard
              number={tiktokCardDetails.number}
              cardName={tiktokCardDetails.cardName}
              cvc={tiktokCardDetails.cvc}
              expiryDate={tiktokCardDetails.expiryDate}
              hideDetails={hideDetails}
            />
          )}
        </div>

        {/* Your Tiktok Wallet Section */}
        <div className="bg-gray-50 rounded-xl p-4 flex flex-col justify-start items-center relative">
          <h2 className="text-gray-900 dark:text-white text-xl md:text-2xl font-bold mb-4 text-center">
            Your Tiktok Wallet
          </h2>

          <div className="flex justify-center items-baseline gap-2 mb-4">
            <Image
              src="/icons/fiatCoins.svg"
              alt="fiat"
              height={20}
              width={30}
              className="-rotate-3 relative top-1"
            />
            <p className="text-2xl text-center font-normal text-gray-500 dark:text-gray-400">
              {walletDetails?.fiatAmount} {walletDetails?.currency}
            </p>
          </div>

          <div className="flex justify-center items-baseline gap-2 mb-6">
            <Image
              src="/icons/tiktokCoins.svg"
              alt="fiat"
              height={20}
              width={30}
              className="-rotate-3 relative top-1"
            />
            <p className="text-2xl text-center font-normal text-gray-500 dark:text-gray-400">
              {walletDetails?.tiktokCoins} Tiktok Coins
            </p>
          </div>

          <div className="flex flex-col md:flex-row items-center justify-center md:justify-between gap-4 md:gap-0 w-full">
            <div className="flex flex-col items-center mb-4 md:mb-0">
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
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    strokeWidth="2"
                    d="M1 5h12m0 0L9 1m4 4L9 9"
                  />
                </svg>
              </Link>
              <p className="max-w-[150px] text-sm font-normal text-center text-gray-500 dark:text-gray-400 mt-2">
                Use credit or gift cards in one click!
              </p>
            </div>

            <div className="flex flex-col items-center">
              <button
                onClick={() => {}}
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
                    strokeLinecap="round"
                    strokeLinejoin="round"
                    strokeWidth="2"
                    d="M1 5h12m0 0L9 1m4 4L9 9"
                  />
                </svg>
              </button>
              <p className="max-w-[150px] text-sm font-normal text-center text-gray-500 dark:text-gray-400 mt-2">
                Exchange cash to TikTok Coin.
              </p>
            </div>
          </div>
        </div>

        {/* Your Summary Section */}
        <div className="bg-gray-50 dark:bg-gray-800 p-4 flex flex-col justify-start items-center gap-6">
          <h2 className="text-gray-900 dark:text-white text-xl md:text-2xl font-bold mb-2">
            Your Summary
          </h2>

          <div className="flex-1 w-full h-full flex flex-col justify-center items-center gap-3">
            <div className="flex items-center gap-3">
              <Image
                src="/icons/incomingTransactions.svg"
                alt="icon"
                height={45}
                width={45}
              />
              <h2 className="font-bold text-lg md:text-xl text-green-600">
                Incoming:
              </h2>
            </div>
            <p className="text-2xl font-medium text-black">+${incoming}</p>
          </div>

          <div className="flex-1 w-full h-full flex flex-col justify-center items-center gap-3">
            <div className="flex items-center gap-3">
              <Image
                src="/icons/outgoingTransactions.svg"
                alt="icon"
                height={45}
                width={45}
              />
              <h2 className="font-bold text-lg md:text-xl text-yellow-600">
                Outgoing:
              </h2>
            </div>
            <p className="text-2xl font-medium text-black">-${outgoing}</p>
          </div>
        </div>
      </div>

      <div className="w-full">
        <DataTable columns={columns} data={transactionData} />
      </div>
    </section>
  );
};

export default WalletPage;
