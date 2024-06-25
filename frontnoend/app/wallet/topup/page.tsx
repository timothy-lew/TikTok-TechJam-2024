"use client";

import { useState } from "react";
import { useAuth } from "@/hooks/auth-provider";
import Image from "next/image";
import Link from "next/link";
import { useFetchWallet } from "@/hooks/useFetchWallet";

type TopUpMethod = "creditCard" | "giftCard";

const TopUpPage: React.FC = () => {
  const auth = useAuth();
  const user = auth?.user || null;
  const walletDetails = useFetchWallet(user?.id || "");

  const [amount, setAmount] = useState<string>("");
  const [topUpMethod, setTopUpMethod] = useState<TopUpMethod>("creditCard");

  // Credit Card State
  const [cardNumber, setCardNumber] = useState<string>("");
  const [expiryDate, setExpiryDate] = useState<string>("");
  const [cvv, setCvv] = useState<string>("");

  // Gift Card State
  const [giftCardCode, setGiftCardCode] = useState<string>("");

  const handleTopUp = () => {
    // Implement top-up logic here
    console.log("Top-up initiated");
  };

  return (
    <section className="bg-background text-foreground flex flex-col w-full justify-start items-center gap-4 sm:gap-6 px-4 sm:px-6 py-6 sm:py-8">
      <div className="bg-card rounded-xl p-4 sm:p-6 shadow-md w-full border border-tiktok-cyan">
        <h1 className="font-bold text-2xl sm:text-3xl md:text-4xl text-tiktok-cyan">
          Top Up Your Wallet
        </h1>
      </div>

      <div className="bg-card rounded-xl p-4 sm:p-6 shadow-md w-full max-w-2xl border border-tiktok-cyan">
        <div className="mb-6">
          <h2 className="text-lg font-semibold mb-2">Current Balance</h2>
          <p className="text-muted-foreground">Fiat: ${walletDetails?.fiatAmount}</p>
          <p className="text-muted-foreground">TikTok Coins: {walletDetails?.tiktokCoins}</p>
        </div>

        <div className="mb-6">
          <div className="flex rounded-md overflow-hidden border border-tiktok-cyan">
            <button
              onClick={() => setTopUpMethod("creditCard")}
              className={`flex-1 py-2 px-4 ${
                topUpMethod === "creditCard"
                  ? "bg-tiktok-cyan text-white"
                  : "bg-card text-tiktok-cyan"
              } transition duration-300`}
            >
              Credit Card
            </button>
            <button
              onClick={() => setTopUpMethod("giftCard")}
              className={`flex-1 py-2 px-4 ${
                topUpMethod === "giftCard"
                  ? "bg-tiktok-cyan text-white"
                  : "bg-card text-tiktok-cyan"
              } transition duration-300`}
            >
              Gift Card
            </button>
          </div>
        </div>

        <div className="mb-6">
          <label htmlFor="amount" className="block text-sm font-medium text-foreground mb-2">
            Amount to Top Up
          </label>
          <div className="flex items-center">
            <input
              type="number"
              id="amount"
              value={amount}
              onChange={(e) => setAmount(e.target.value)}
              className="flex-grow px-3 py-2 border border-input rounded-md focus:outline-none focus:ring-2 focus:ring-tiktok-cyan"
              placeholder="Enter amount"
            />
          </div>
        </div>

        {topUpMethod === "creditCard" ? (
          <div className="space-y-4">
            <div>
              <label htmlFor="cardNumber" className="block text-sm font-medium text-foreground mb-2">
                Card Number
              </label>
              <input
                type="text"
                id="cardNumber"
                value={cardNumber}
                onChange={(e) => setCardNumber(e.target.value)}
                className="w-full px-3 py-2 border border-input rounded-md focus:outline-none focus:ring-2 focus:ring-tiktok-cyan"
                placeholder="1234 5678 9012 3456"
              />
            </div>
            <div className="flex space-x-4">
              <div className="flex-1">
                <label htmlFor="expiryDate" className="block text-sm font-medium text-foreground mb-2">
                  Expiry Date
                </label>
                <input
                  type="text"
                  id="expiryDate"
                  value={expiryDate}
                  onChange={(e) => setExpiryDate(e.target.value)}
                  className="w-full px-3 py-2 border border-input rounded-md focus:outline-none focus:ring-2 focus:ring-tiktok-cyan"
                  placeholder="MM/YY"
                />
              </div>
              <div className="flex-1">
                <label htmlFor="cvv" className="block text-sm font-medium text-foreground mb-2">
                  CVV
                </label>
                <input
                  type="text"
                  id="cvv"
                  value={cvv}
                  onChange={(e) => setCvv(e.target.value)}
                  className="w-full px-3 py-2 border border-input rounded-md focus:outline-none focus:ring-2 focus:ring-tiktok-cyan"
                  placeholder="123"
                />
              </div>
            </div>
          </div>
        ) : (
          <div>
            <label htmlFor="giftCardCode" className="block text-sm font-medium text-foreground mb-2">
              Gift Card Code
            </label>
            <input
              type="text"
              id="giftCardCode"
              value={giftCardCode}
              onChange={(e) => setGiftCardCode(e.target.value)}
              className="w-full px-3 py-2 border border-input rounded-md focus:outline-none focus:ring-2 focus:ring-tiktok-cyan"
              placeholder="Enter gift card code"
            />
          </div>
        )}

        <button
          onClick={handleTopUp}
          className="w-full bg-tiktok-red text-white py-3 rounded-md hover:bg-tiktok-red/90 transition duration-300 font-semibold mt-6"
        >
          Top Up Wallet
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

export default TopUpPage;