"use client";

import { useState } from "react";
import { useAuth } from "@/hooks/auth-provider";
import Image from "next/image";
import Link from "next/link";
import { useFetchWallet } from "@/hooks/useFetchWallet";

type ConversionType = "fiatToTiktok" | "tiktokToFiat";

const CurrencyExchangePage: React.FC = () => {
  const auth = useAuth();
  const user = auth?.user || null;
  const walletDetails = useFetchWallet(user?.id || "");

  const [amount, setAmount] = useState<string>("");
  const [conversionType, setConversionType] = useState<ConversionType>("fiatToTiktok");

  const exchangeRate = 100;

  const handleExchange = () => {
    // Implement exchange logic here
    console.log("Exchange initiated");
  };

  const calculatedAmount = parseFloat(amount) * (conversionType === "fiatToTiktok" ? exchangeRate : 1 / exchangeRate);

  return (
    <section className="bg-background text-foreground flex flex-col w-full justify-start items-center gap-4 sm:gap-6 px-4 sm:px-6 py-6 sm:py-8">
      <div className="bg-card rounded-xl p-4 sm:p-6 shadow-md w-full border border-tiktok-cyan">
        <h1 className="font-bold text-2xl sm:text-3xl md:text-4xl text-tiktok-cyan">
          Currency Exchange
        </h1>
      </div>

      <div className="bg-card rounded-xl p-4 sm:p-6 shadow-md w-full max-w-2xl border border-tiktok-cyan">
        <div className="flex flex-col sm:flex-row justify-between items-center mb-6">
          <div className="mb-4 sm:mb-0">
            <h2 className="text-lg font-semibold mb-2">Your Balance</h2>
            <p className="text-muted-foreground">Fiat: ${walletDetails?.fiatAmount}</p>
            <p className="text-muted-foreground">TikTok Coins: {walletDetails?.tiktokCoins}</p>
          </div>
          <div>
            <h2 className="text-lg font-semibold mb-2">Exchange Rate</h2>
            <p className="text-muted-foreground">1 {walletDetails?.currency} = {exchangeRate} TikTok Coins</p>
          </div>
        </div>

        <div className="mb-6">
          <div className="flex rounded-md overflow-hidden border border-tiktok-cyan">
            <button
              onClick={() => setConversionType("fiatToTiktok")}
              className={`flex-1 py-2 px-4 ${
                conversionType === "fiatToTiktok"
                  ? "bg-tiktok-cyan text-white"
                  : "bg-card text-tiktok-cyan"
              } transition duration-300`}
            >
              Fiat to TikTok Coins
            </button>
            <button
              onClick={() => setConversionType("tiktokToFiat")}
              className={`flex-1 py-2 px-4 ${
                conversionType === "tiktokToFiat"
                  ? "bg-tiktok-cyan text-white"
                  : "bg-card text-tiktok-cyan"
              } transition duration-300`}
            >
              TikTok Coins to Fiat
            </button>
          </div>
        </div>

        <div className="mb-6">
          <label htmlFor="amount" className="block text-sm font-medium text-foreground mb-2">
            Amount to Exchange
          </label>
          <div className="flex items-center">
            <input
              type="number"
              id="amount"
              value={amount}
              onChange={(e) => setAmount(e.target.value)}
              className="flex-grow px-3 py-2 border border-input rounded-l-md focus:outline-none focus:ring-2 focus:ring-tiktok-cyan"
              placeholder="Enter amount"
            />
            <span className="bg-muted text-muted-foreground px-4 py-2 rounded-r-md">
              {conversionType === "fiatToTiktok" ? walletDetails?.currency : "TikTok Coins"}
            </span>
          </div>
        </div>

        <div className="mb-6">
          <h3 className="text-lg font-semibold mb-2">You will receive:</h3>
          <p className="text-2xl font-bold text-tiktok-cyan">
            {isNaN(calculatedAmount) ? "0" : calculatedAmount.toFixed(2)}{" "}
            {conversionType === "fiatToTiktok" ? "TikTok Coins" : walletDetails?.currency}
          </p>
        </div>

        <button
          onClick={handleExchange}
          className="w-full bg-tiktok-red text-white py-3 rounded-md hover:bg-tiktok-red/90 transition duration-300 font-semibold"
        >
          Exchange Currency
        </button>
      </div>

      <Link
        href="/wallet"
        className="text-tiktok-cyan hover:text-tiktok-cyan-dark font-medium text-lg sm:text-xl inline-flex items-center transition duration-300"
      >
        <svg
          className="w-4 h-4 mr-2 rotate-180"
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
        Back to Wallet
      </Link>
    </section>
  );
};

export default CurrencyExchangePage;