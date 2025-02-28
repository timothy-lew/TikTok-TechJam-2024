"use client";

import { useState, useEffect } from "react";
import { useAuth } from "@/hooks/auth-provider";
import Link from "next/link";
import { useConvertCurrency } from "@/hooks/useConvertCurrency";
import { useFetchExchangeRate } from "@/hooks/useFetchExchangeRate";
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
import TransactionAlert from "@/components/shared/TransactionAlert";
import { getBackendUrl } from "@/lib/utils";

const CurrencyExchangePage: React.FC = () => {
  const auth = useAuth();

  if (!auth) return 

  const user = auth?.user || null;
  
  const walletData = auth.userWallet;

  const { convertCurrency, checkTransactionStatus, success, isConverting, error } = useConvertCurrency();

  const [amount, setAmount] = useState<number | null>(null);
  const [conversionType, setConversionType] = useState<"CASH_TO_TOKTOKEN" | "TOKTOKEN_TO_CASH">("CASH_TO_TOKTOKEN");

  const { exchangeRate } = useFetchExchangeRate();

  const [EXCHANGE_RATE, setExchangeRateNow] = useState<null | number>(null);
  
  const [walletBalance, setWalletBalance] = useState({ cashBalance: -99, tokTokenBalance: -99 });
  const [accessToken, setAccessToken] = useState<string | undefined>(undefined);
  const [transactionSuccess, setTransactionSuccess] = useState(false);
  

  useEffect(() => {
    const getAccessToken = async () => {
      const token = await auth?.obtainAccessToken();
      if (token) {
        setAccessToken(token);
      } else {
        console.error('Failed to retrieve access token');
      }
    };
  
    getAccessToken();
  }, [auth, user]);
  

  useEffect(() => {
    const fetchWalletBalance = async () => {
      if (!accessToken) {
        console.error('Access token is not available');
        return;
      }
  
      try {
        const response = await fetch(`${getBackendUrl()}/api/wallet/${user?.id}`, {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        });
  
        if (!response.ok) {
          throw new Error(`Failed to fetch wallet balance: ${response.status} ${response.statusText}`);
        }
  
        const data = await response.json();
        console.log("Wallet balance Cash: " + data.cashBalance + ", Tok Coin: " + data.tokTokenBalance);
        setWalletBalance({
          cashBalance: data.cashBalance,
          tokTokenBalance: data.tokTokenBalance,
        });
      } catch (err) {
        console.error(err);
      }
    };
  
    fetchWalletBalance();
  }, [accessToken, user, transactionSuccess]);

  // Functions related to TransactionAlert
  const [isOpen, setIsOpen] = useState(false);
  const [alertContent, setAlertContent] = useState('');

  const handleOpen = (message: string) => {
    setIsOpen(true);
    setAlertContent(message);
  };

  const handleClose = () => {
    setIsOpen(false);
    setAlertContent(''); 
  };


  useEffect(() => {
    setExchangeRateNow(exchangeRate);
  
  }, [exchangeRate])
  
  const [toUpdateCoins, setToUpdateCoins] = useState(0);
  const [toUpdateCash, setToUpdateCash] = useState(0);

  // const TIKTOK_WALLET_ADDRESS : string = "0xac0974bec39a17e36ba4a6b4d238ff944bacb478cbed5efcae784d7bf4f2ff80"; - i think this is old wallet
  const TIKTOK_WALLET_ADDRESS : string = "0xf39Fd6e51aad88F6F4ce6aB8827279cffFb92266"

  // Popup dependencies
  const givenTime = 5 * 60;
  const [isAlertDialogOpen, setIsAlertDialogOpen] = useState(false);
  const [remainingTime, setRemainingTime] = useState<number>(givenTime);
  const [autoCloseTimer, setAutoCloseTimer] = useState<ReturnType<typeof setTimeout> | null>(null);
  
  const formatTime = (time: number): string => {
    const minutes = Math.floor(time / 60);
    const seconds = time % 60;
    return `${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
  };
  const [transactionID, settransactionID] = useState<string | null>(null);

  useEffect(() => {
    let timer: NodeJS.Timeout;
    if (isAlertDialogOpen && remainingTime > 0) {
      timer = setInterval(() => {
        setRemainingTime((prevTime) => prevTime - 1);
      }, 1000);
    }
  
    return () => {
      if (timer) clearInterval(timer);
    };
  }, [isAlertDialogOpen, remainingTime]);

  const { toast } = useToast()

  const handleExchange = async () => {
    const userId = auth?.user?.id || null;
    const accessToken = await auth?.obtainAccessToken();
    setTransactionSuccess(false);

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

    if (convertedResult.status === "success"){

      if (conversionType==="TOKTOKEN_TO_CASH"){
        setToUpdateCoins(convertedResult.coinsConverted)
        setToUpdateCash(convertedResult.cashConverted)
        settransactionID(convertedResult.transactionID);
        setIsAlertDialogOpen(true);
        setRemainingTime(givenTime);
      }
      else{
        auth.setUserWallet((prev) => {
          if (!prev) return null;
          return {
            ...prev,
            cashBalance: prev.cashBalance + convertedResult.cashConverted,
            tokTokenBalance: prev.tokTokenBalance + convertedResult.coinsConverted,
          };
        });
    
        handleOpen(`Success! Your wallet has been updated.`)
        setTransactionSuccess(true)
        setAmount(null);
        return;
      }

    }
    else{
      handleOpen("Unsuccessful! Something went wrong!")
      setAmount(null);
      return;
    }

    setIsAlertDialogOpen(true);
    setRemainingTime(givenTime); // Reset timer
    setAmount(null);
    
    // the first input which is the function will get called after `AUTO_CANCEL_TIMEOUT` milliseconds
    setAutoCloseTimer(
      setTimeout(() => {
        setIsAlertDialogOpen(false);
        setRemainingTime(givenTime); // Reset timer
        toast({
          variant: "destructive",
          title: "Exchange Aborted",
          description: "Time out for transaction",
        })
      }, givenTime*1000)
    );

  };

  // background polling while dialog is open
  const [isPolling, setIsPolling] = useState(false);

  const handleSuccessfulTransfer = () => {
    setIsAlertDialogOpen(false);
    if (autoCloseTimer) {
      clearTimeout(autoCloseTimer);
    }
    
    // Update wallet balance
    // auth.setUserWallet((prev) => {
    //   if (!prev) return null;
    //   return {
    //     ...prev,
    //     cashBalance: prev.cashBalance + calculatedAmount!,
    //     tokTokenBalance: prev.tokTokenBalance - Number(amount),
    //   };
    // });

    auth.setUserWallet((prev) => {
      if (!prev) return null;
      return {
        ...prev,
        cashBalance: prev.cashBalance + toUpdateCash,
        tokTokenBalance: prev.tokTokenBalance + toUpdateCoins,
      };
    });
    setTransactionSuccess(true)
    handleOpen("Success! Your wallet has been updated.")
  };

  useEffect(() => {
    let pollingInterval: NodeJS.Timeout;
    
    // only background poll if alert dialog is open
    if (isAlertDialogOpen && transactionID) {
      setIsPolling(true);
      pollingInterval = setInterval(async () => {
        const accessToken = await auth?.obtainAccessToken();
        if (accessToken) {
          const success = await checkTransactionStatus({ transactionID, accessToken });
          if (success) {
            handleSuccessfulTransfer();
          }
        }
      }, 3000); // Poll every 3 seconds
    }

    return () => {
      if (pollingInterval) {
        clearInterval(pollingInterval);
      }
      setIsPolling(false);
    };
  }, [isAlertDialogOpen, transactionID]);

  useEffect(() => {
    return () => {
      if (autoCloseTimer) {
        clearTimeout(autoCloseTimer);
      }
    };
  }, [autoCloseTimer]);

  const handleTransferConfirmation = async () => {
    if (!transactionID) return;

    const accessToken = await auth?.obtainAccessToken();
    if (!accessToken) return;

    const success = await checkTransactionStatus({ transactionID, accessToken });

    if (success) {
      // Update wallet balance
      auth.setUserWallet((prev) => {
        if (!prev) return null;
        return {
          ...prev,
          cashBalance: prev.cashBalance + calculatedAmount!,
          tokTokenBalance: prev.tokTokenBalance - Number(amount),
        };
      });

      handleOpen("Success! Your wallet has been updated.")
    } else {
      // toast({
      //   title: "Transaction Failed",
      //   description: "The transaction was not completed in time",
      // });
      handleOpen("Transaction Failed. The transaction was not completed in time")
    }

    setIsAlertDialogOpen(false);
    setRemainingTime(5 * 60); // Reset timer
  };
  

  const calculatedAmount = amount && amount * (conversionType === "CASH_TO_TOKTOKEN" ? EXCHANGE_RATE || 1 : 1 / (EXCHANGE_RATE || 1));

  return (
    <section className="bg-background text-foreground flex flex-col w-full justify-start items-center gap-4 sm:gap-6 px-4 sm:px-6 py-6 sm:py-8">

      <div className="bg-card rounded-xl p-4 sm:p-6 shadow-md w-full max-w-2xl border border-slate-300">
        <h1 className="font-bold text-center text-2xl sm:text-3xl md:text-4xl text-tiktok-red mb-6">
          Currency Exchange
        </h1>
        <div className="flex flex-col sm:flex-row justify-between items-center mb-6">
          <div className="mb-4 sm:mb-0">
            <h2 className="text-lg font-semibold mb-2">Your Balance</h2>
            {walletBalance.cashBalance === - 99 ? <p className="text-muted-foreground">Fiat: Loading...</p>: <p className="text-muted-foreground">Fiat: ${walletBalance.cashBalance}</p>}
            {walletBalance.tokTokenBalance === -99 ? <p className="text-muted-foreground">Tok Coins: Loading...</p>: <p className="text-muted-foreground">Tok Coins: {walletBalance.tokTokenBalance}</p>}

          </div>
          <div>
            <h2 className="text-lg font-semibold mb-2">Exchange Rate</h2>
            {EXCHANGE_RATE ?
            <p className="text-muted-foreground">1 SGD = {EXCHANGE_RATE} Tok Coins</p>
            :
            <p className="text-muted-foreground">Loading Exchange Rate....</p>  
            }
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
              Cash to Tok Coins
            </button>
            <button
              onClick={() => setConversionType("TOKTOKEN_TO_CASH")}
              className={`flex-1 py-2 px-4 ${
                conversionType === "TOKTOKEN_TO_CASH"
                  ? "bg-tiktok-red text-white"
                  : "bg-card text-tiktok-red"
              } transition duration-300`}
            >
              Tok Coins to Cash
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
              {conversionType === "CASH_TO_TOKTOKEN" ? 'SGD' : "Tok Coins"}
            </span>
          </div>
        </div>

        <div className="mb-6">
          <h3 className="text-lg font-semibold mb-2">You will receive:</h3>
          <p className="text-2xl font-bold text-tiktok-red">
            {calculatedAmount ?  calculatedAmount.toFixed(2) : ""}{" "}
            {conversionType === "CASH_TO_TOKTOKEN" ? "Tok Coins" : 'SGD'}
          </p>
        </div>
        
        {conversionType==="CASH_TO_TOKTOKEN" ?
          <button
          onClick={()=>{
            handleExchange();
          }}
          disabled={!amount && !EXCHANGE_RATE}
          className="w-full bg-tiktok-red text-white py-3 rounded-md hover:bg-tiktok-red/90 transition duration-300 font-semibold disabled:bg-red-200"
        >
          Exchange Cash
        </button>
        :
        <AlertDialog open={isAlertDialogOpen}>
          <AlertDialogTrigger asChild>
            <button
              onClick={()=>{
                handleExchange();
              }}
              disabled={!amount && !EXCHANGE_RATE}
              className="w-full bg-tiktok-red text-white py-3 rounded-md hover:bg-tiktok-red/90 transition duration-300 font-semibold disabled:bg-red-200"
            >
              Exchange Tok Coin
            </button>
          </AlertDialogTrigger>
          <AlertDialogContent>
            <AlertDialogHeader>
              <AlertDialogTitle>Initiate your Transfer</AlertDialogTitle>
              <AlertDialogDescription>
                <p>Please send {amount} Tok Coins to this wallet address:</p>
                <WalletAddressBar wallet_address={TIKTOK_WALLET_ADDRESS}></WalletAddressBar>
                <p>
                  Transactions can take a few seconds to be processed<br />Your patience is appreciated!
                </p>
                <p className="mt-4 font-bold">Time remaining: {formatTime(remainingTime)}</p>
              </AlertDialogDescription>
            </AlertDialogHeader>
            <AlertDialogFooter>
              <AlertDialogCancel onClick={() => {
                setIsAlertDialogOpen(false);
                setRemainingTime(givenTime); // Reset timer
              }} className="hover:bg-red-50 hover:text-black">
                Cancel
              </AlertDialogCancel>
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
      <TransactionAlert open={isOpen} onClose={handleClose} alertContent={alertContent} />

    </section>
  );
};

export default CurrencyExchangePage;