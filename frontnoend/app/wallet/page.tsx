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
  const [isLoginPopupOpen, setLoginPopupOpen] = useState(user === null);
  const [hideDetails, setHideDetails] = useState<boolean>(true);

  const handleClosePopup = () => setLoginPopupOpen(false);

  const transactionData: Transaction[] = useFetchTransactions(user?.id || "");
  const tiktokCardDetails = useFetchTiktokCard(user?.id || "");
  const walletDetails = useFetchWallet(user?.id || "");

  let incoming: number = 0;
  let outgoing: number = 0;

  transactionData.forEach((transaction) => {
    if (transaction.type === "incoming") incoming += transaction.amount;
    else outgoing += transaction.amount;
  });

  // if (!user) {
  //   return (
  //     <div className="flex_center min-h-screen bg-gray-100 text-gray-800">
  //       {isLoginPopupOpen ? (
  //         <LoginPopup isOpen={isLoginPopupOpen} onClose={handleClosePopup} />
  //       ) : (
  //         <LoginRequired />
  //       )}
  //     </div>
  //   );
  // }

  return (
    <section className="bg-gray-100 text-gray-800 flex flex-col w-full justify-start items-center gap-4 sm:gap-6 px-4 sm:px-6 py-6 sm:py-8">
      <div className="bg-white rounded-xl p-4 sm:p-6 shadow-md w-full border border-tiktok-cyan">
        <h1 className="font-bold text-2xl sm:text-3xl md:text-4xl">
          Hello! Good to see you{" "}
          <span className="capitalize text-tiktok-cyan">{user?.firstName}</span>
        </h1>
      </div>

      <div className="grid grid-cols-1 lg:grid-cols-3 gap-4 sm:gap-6 w-full">
        {/* Your Tiktok Card Section */}
        <div className="bg-white p-4 sm:p-6 rounded-xl shadow-md flex flex-col justify-start items-center border border-tiktok-cyan">
          <h2 className="text-tiktok-cyan text-center w-full text-xl sm:text-2xl md:text-3xl font-bold mb-4">
            Your TikTok Card
          </h2>
          <div className="w-full flex flex-col sm:flex-row justify-center sm:justify-evenly items-center gap-3 mb-6">
            <button
              className="flex items-center justify-center gap-2 bg-tiktok-cyan hover:bg-[#00D2CA] text-white rounded-md px-4 py-2 transition duration-300 w-full sm:w-auto"
              onClick={() => setHideDetails((prev) => !prev)}
            >
              <Image
                src={hideDetails ? "/icons/eyeOpen.svg" : "/icons/eyeClose.svg"}
                alt="icon"
                height={22}
                width={22}
              />
              <p className="font-medium">{hideDetails ? "Show" : "Hide"} Details</p>
            </button>
            <button
              className="flex items-center justify-center gap-2 bg-tiktok-red hover:bg-[#E0004A] text-white rounded-md px-4 py-2 transition duration-300 w-full sm:w-auto"
              onClick={() => {}}
            >
              <Image
                src="/icons/freeze.svg"
                alt="icon"
                height={20}
                width={20}
              />
              <p className="font-medium">Freeze Card</p>
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
        <div className="bg-white rounded-xl p-4 sm:p-6 flex flex-col justify-start items-center relative border border-tiktok-cyan shadow-md">
          <h2 className="text-tiktok-cyan text-xl sm:text-2xl md:text-3xl font-bold mb-4 sm:mb-6 text-center">
            Your TikTok Wallet
          </h2>

          <div className="flex justify-center items-baseline gap-3 mb-4">
            <Image
              src="/icons/fiatCoins.svg"
              alt="fiat"
              height={24}
              width={36}
              className="-rotate-3 relative top-1"
            />
            <p className="text-2xl sm:text-3xl text-center font-semibold text-gray-800">
              {hideDetails ? '****' : `${walletDetails?.fiatAmount}`} {walletDetails?.currency}
            </p>
          </div>

          <div className="flex justify-center items-baseline gap-3 mb-6 sm:mb-8">
            <Image
              src="/icons/tiktokCoins.svg"
              alt="fiat"
              height={24}
              width={36}
              className="-rotate-3 relative top-1"
            />
            <p className="text-2xl sm:text-3xl text-center font-semibold text-gray-800">
              {hideDetails ? '****' : `${walletDetails?.tiktokCoins}`} TikTok Coins
            </p>
          </div>

          <div className="flex flex-col sm:flex-row items-center justify-center sm:justify-between gap-4 sm:gap-6 w-full">
            <div className="flex flex-col items-center justify-center">
              <Link
                href="/wallet/topup"
                className="text-tiktok-cyan hover:text-[#00D2CA] font-medium text-lg sm:text-xl inline-flex items-center transition duration-300"
              >
                Top Up
                <svg
                  className="w-4 h-4 ms-2 rtl:rotate-180"
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
              <p className="max-w-[150px] text-sm font-normal text-center text-gray-600 mt-2">
                Use credit or gift cards in one click!
              </p>
            </div>

            <div className="flex flex-col items-center justify-center">
              <Link
                href="/wallet/exchange"
                className="text-tiktok-cyan hover:text-[#00D2CA] font-medium text-lg sm:text-xl inline-flex items-center transition duration-300"
              >
                Exchange
                <svg
                  className="w-4 h-4 ms-2 rtl:rotate-180"
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
              <p className="max-w-[150px] text-sm font-normal text-center text-gray-600 mt-2">
                Exchange Cash / Tiktok Coins
              </p>
            </div>

          </div>
        </div>

        {/* Your Summary Section */}
        <div className="bg-white p-4 sm:p-6 flex flex-col justify-start items-center gap-6 sm:gap-8 rounded-xl border border-tiktok-cyan shadow-md">
          <h2 className="text-tiktok-cyan text-xl sm:text-2xl md:text-3xl font-bold mb-2">
            Your Summary
          </h2>

          <div className="flex-1 w-full h-full flex flex-col justify-center items-center gap-4">
            <div className="flex items-center gap-4">
              <Image
                src="/icons/incomingTransactions.svg"
                alt="icon"
                height={48}
                width={48}
              />
              <h2 className="font-bold text-xl sm:text-2xl md:text-3xl text-green-600">
                Incoming:
              </h2>
            </div>
            <p className="text-2xl sm:text-3xl text-center font-semibold text-gray-800">+${hideDetails ? '****' : `${incoming}`}</p>
          </div>

          <div className="flex-1 w-full h-full flex flex-col justify-center items-center gap-4">
            <div className="flex items-center gap-4">
              <Image
                src="/icons/outgoingTransactions.svg"
                alt="icon"
                height={48}
                width={48}
              />
              <h2 className="font-bold text-xl sm:text-2xl md:text-3xl text-tiktok-red">
                Outgoing:
              </h2>
            </div>
            <p className="text-2xl sm:text-3xl text-center font-semibold text-gray-800">-${hideDetails ? '****' : `${outgoing}`}</p>
          </div>
        </div>
      </div>

      <div className="w-full bg-white rounded-xl p-4 sm:p-6 border border-tiktok-cyan shadow-md">
        <DataTable columns={columns} data={transactionData} />
      </div>
    </section>
  );
};

export default WalletPage;