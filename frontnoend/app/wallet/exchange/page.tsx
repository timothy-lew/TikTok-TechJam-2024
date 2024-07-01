"use client";

import { useState, useEffect } from "react";
import { useAuth } from "@/hooks/auth-provider";
import Link from "next/link";
import { useConvertCurrency } from "@/hooks/useConvertCurrency";
import { useToast } from "@/components/ui/use-toast"

import {
  AlertDialog,
  AlertDialogAction,
  AlertDialogCancel,
  AlertDialogContent,
  AlertDialogDescription,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogTitle,
  AlertDialogTrigger,
} from "@/components/ui/alert-dialog"
import { WalletAddressBar } from "@/components/shop/WalletAddressBar";

const CurrencyExchangePage: React.FC = () => {
  const auth = useAuth();

  if (!auth) return 

  const user = auth?.user || null;
  
  // const {walletData, setWalletData} = useWallet();

  const walletData = auth.userWallet;

  const { convertCurrency, success, isConverting, error } = useConvertCurrency();

  const [amount, setAmount] = useState<number | null>(null);
  const [conversionType, setConversionType] = useState<"CASH_TO_TOKTOKEN" | "TOKTOKEN_TO_CASH">("TOKTOKEN_TO_CASH");

  const EXCHANGE_RATE = 10;
  const TIKTOK_WALLET_ADDRESS : string = "0xac0974bec39a17e36ba4a6b4d238ff944bacb478cbed5efcae784d7bf4f2ff80";

  // Popup dependencies
  const [isAlertDialogOpen, setIsAlertDialogOpen] = useState(false);
  const [autoCloseTimer, setAutoCloseTimer] = useState<ReturnType<typeof setTimeout> | null>(null);
  const AUTO_CANCEL_TIMEOUT = 5 * 60 * 1000; // 5 minutes

  const { toast } = useToast()

  const handleExchange = async () => {
    const userId = auth?.user?.id || null;
    const accessToken = await auth?.obtainAccessToken();

    if (!auth?.userWallet || !userId || !accessToken) {
      return;
    }

    const convertedResult = await convertCurrency({
      accessToken,
      userId,
      cashToConvert: conversionType === "CASH_TO_TOKTOKEN" ? Number(amount) : 0,
      tokTokenToConvert: conversionType === "CASH_TO_TOKTOKEN" ? 0 : Number(amount),
      conversionType,
    });

    if (convertedResult.status == "success"){
      auth.setUserWallet((prev) => {
        if (!prev) return null;
        return {
          ...prev,
          cashBalance: prev.cashBalance + convertedResult.cashConverted,
          tokTokenBalance: prev.tokTokenBalance + convertedResult.coinsConverted,
        };
      });
  
      toast({
        title: "Success!",
        description: "Your wallet has been updated",
      })
    }
    else{
      toast({
        title: "Unsuccessful!",
        description: "Something went wrong!",
      })
    }


    setIsAlertDialogOpen(true);
    setAutoCloseTimer(
      setTimeout(() => {
        setIsAlertDialogOpen(false);
      }, AUTO_CANCEL_TIMEOUT)
    );
  };

  useEffect(() => {
    return () => {
      if (autoCloseTimer) {
        clearTimeout(autoCloseTimer);
      }
    };
  }, [autoCloseTimer]);
  

  const calculatedAmount = amount && amount * (conversionType === "CASH_TO_TOKTOKEN" ? EXCHANGE_RATE : 1 / EXCHANGE_RATE);

  return (
    <section className="bg-background text-foreground flex flex-col w-full justify-start items-center gap-4 sm:gap-6 px-4 sm:px-6 py-6 sm:py-8">
      <div className="bg-card rounded-xl p-4 sm:p-6 shadow-md w-full border border-tiktok-red">
        <h1 className="font-bold text-2xl sm:text-3xl md:text-4xl text-tiktok-red">
          Currency Exchange
        </h1>
      </div>

      <div className="bg-card rounded-xl p-4 sm:p-6 shadow-md w-full max-w-2xl border border-tiktok-red">
        <div className="flex flex-col sm:flex-row justify-between items-center mb-6">
          <div className="mb-4 sm:mb-0">
            <h2 className="text-lg font-semibold mb-2">Your Balance</h2>
            <p className="text-muted-foreground">Fiat: ${walletData?.cashBalance}</p>
            <p className="text-muted-foreground">TikTok Coins: {walletData?.tokTokenBalance}</p>
          </div>
          <div>
            <h2 className="text-lg font-semibold mb-2">Exchange Rate</h2>
            <p className="text-muted-foreground">1 SGD = {EXCHANGE_RATE} TikTok Coins</p>
          </div>
        </div>

        <div className="mb-6">
          <div className="flex rounded-md overflow-hidden border border-tiktok-red">
            <button
              onClick={() => setConversionType("CASH_TO_TOKTOKEN")}
              className={`flex-1 py-2 px-4 ${
                conversionType === "CASH_TO_TOKTOKEN"
                  ? "bg-tiktok-red text-white"
                  : "bg-card text-tiktok-red"
              } transition duration-300`}
            >
              Fiat to TikTok Coins
            </button>
            <button
              onClick={() => setConversionType("TOKTOKEN_TO_CASH")}
              className={`flex-1 py-2 px-4 ${
                conversionType === "TOKTOKEN_TO_CASH"
                  ? "bg-tiktok-red text-white"
                  : "bg-card text-tiktok-red"
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
              // type="number"
              id="amount"
              value={amount || ""}
              onChange={(e) => setAmount(Number(e.target.value))}
              className="flex-grow px-3 py-2 border border-input rounded-l-md focus:outline-none focus:ring-2 focus:ring-tiktok-red"
              placeholder="Enter amount"
            />
            <span className="bg-muted text-muted-foreground px-4 py-2 rounded-r-md">
              {conversionType === "CASH_TO_TOKTOKEN" ? 'SGD' : "TikTok Coins"}
            </span>
          </div>
        </div>

        <div className="mb-6">
          <h3 className="text-lg font-semibold mb-2">You will receive:</h3>
          <p className="text-2xl font-bold text-tiktok-red">
            {calculatedAmount ?  calculatedAmount.toFixed(2) : ""}{" "}
            {conversionType === "CASH_TO_TOKTOKEN" ? "TikTok Coins" : 'SGD'}
          </p>
        </div>
        
        {conversionType==="CASH_TO_TOKTOKEN" ?
          <button
          onClick={handleExchange}
          disabled={!amount}
          className="w-full bg-tiktok-red text-white py-3 rounded-md hover:bg-tiktok-red/90 transition duration-300 font-semibold disabled:bg-red-200"
        >
          Exchange Cash
        </button>
        :
        <AlertDialog open={isAlertDialogOpen}>
          <AlertDialogTrigger asChild>
            <button
              onClick={handleExchange}
              disabled={!amount}
              className="w-full bg-tiktok-red text-white py-3 rounded-md hover:bg-tiktok-red/90 transition duration-300 font-semibold disabled:bg-red-200"
            >
              Exchange Tokens
            </button>
          </AlertDialogTrigger>
          <AlertDialogContent>
            <AlertDialogHeader>
              <AlertDialogTitle>Initiate your Transfer</AlertDialogTitle>
              <AlertDialogDescription>
                <p>Please send {amount} Tok Tokens to this wallet address:</p>
                <WalletAddressBar wallet_address={TIKTOK_WALLET_ADDRESS}></WalletAddressBar>
                <p>
                  Transactions can take up to 3 minutes to be processed<br />Your patience is appreciated!
                </p>
              </AlertDialogDescription>
            </AlertDialogHeader>
            <AlertDialogFooter>
              <AlertDialogCancel onClick={()=>setIsAlertDialogOpen(false)} className="hover:bg-red-50 hover:text-black">Cancel</AlertDialogCancel>
              <AlertDialogAction onClick={()=>setIsAlertDialogOpen(false)} className="hover:bg-red-600">Transferred</AlertDialogAction>
            </AlertDialogFooter>
          </AlertDialogContent>
        </AlertDialog>
        }
        

      </div>

      <Link
        href="/wallet"
        className="text-tiktok-red hover:text-tiktok-red-dark font-medium text-lg sm:text-xl inline-flex items-center transition duration-300"
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