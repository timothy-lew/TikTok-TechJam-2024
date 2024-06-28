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
import { type Wallet } from "@/hooks/useFetchWallet";

import { useWallet } from "@/hooks/wallet-provider";
import { Progress } from "@/components/ui/progress"


const WalletPage: React.FC = () => {
  const auth = useAuth();
  const user = auth?.user || null;
  const [isLoginPopupOpen, setLoginPopupOpen] = useState(user === null);
  const [hideDetails, setHideDetails] = useState<boolean>(false);

  const handleClosePopup = () => setLoginPopupOpen(false);

  const transactionData: Transaction[] = useFetchTransactions(user?.buyerProfile?.id || "");
  const tiktokCardDetails = useFetchTiktokCard(user?.id || "");
  
  const { walletData } = useWallet();

  let incoming: number = 0;
  let outgoing: number = 0;

  // Conversion is excluded
  transactionData.forEach((transaction) => {
    
    switch(transaction.transactionType){

      case 'PURCHASE':
        outgoing += transaction.purchaseDetails?.purchaseAmount || 0;
        break;
      case 'TOPUP':
        incoming += transaction.topUpDetails?.topUpAmount || 0;
        break;        

    } 


    
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
    <section className="text-gray-800 flex flex-col w-full justify-start items-center gap-4 sm:gap-6 px-4 sm:px-6 py-6 sm:py-8 flex-grow">
        <div className="bg-white shadow-md w-full flex justify-center items-start border border-tiktok-red">
        {/* Combined Wallet and Card Section */}
        <div className=" rounded-xl p-4 sm:p-6 w-full flex_col_center gap-4">
          <h2 className="text-tiktok-red text-center w-full text-xl sm:text-2xl md:text-3xl font-bold mb-4">
            Your <Image src="/tiktokIconNoBg.svg" alt="icon" width={42} height={42} className="inline rotate-6"/> Wallet
          </h2>
          <div className="flex_col_center gap-3">
            <h3 className="text-slate-900 font-semibold text-xl">Available in Wallet:</h3>
            <div className="flex flex-col sm:flex-row justify-center items-center gap-2">
              <p className="text-slate-800 text-xl">${walletData?.fiatAmount} <span className="text-lg">SGD</span></p>
              
              <p className="text-slate-800 text-xl">{walletData?.tiktokCoins} <span className="text-lg">Tok Coins</span></p>
            </div>
          </div>
            
          {tiktokCardDetails ? (
            <div className="flex_center gap-4 w-full">
              <TiktokCard
                number={tiktokCardDetails.number}
                cardName={tiktokCardDetails.cardName}
                cvc={tiktokCardDetails.cvc}
                expiryDate={tiktokCardDetails.expiryDate}
                hideDetails={hideDetails}
                variant="blue"
              />
              <div className="flex_col_center gap-2">
                <div className="flex justify-start items-center gap-2 w-full">
                  <button
                    className="flex items-center justify-center gap-2 bg-slate-200 hover:bg-slate-300 text-white rounded-full px-2 py-2 transition duration-300 w-full sm:w-auto"
                    onClick={() => setHideDetails((prev) => !prev)}
                  >
                    <Image
                      src={hideDetails ? "/icons/eyeOpen.svg" : "/icons/eyeClose.svg"}
                      alt="icon"
                      height={32}
                      width={32}
                    />
                  </button>
                  <p className="hidden md:inline font-medium text-xs md:text-sm">{hideDetails ? "Show" : "Hide"} Details</p>
                </div>
                <div className="flex justify-start items-center gap-2 w-full">
                  <button
                    className="flex items-center justify-center gap-2 bg-slate-200 hover:bg-slate-300 text-white rounded-full px-2 py-2 transition duration-300 w-full sm:w-auto"
                    onClick={() => {}}
                  >
                    <Image
                      src="/icons/freeze.svg"
                      alt="icon"
                      height={30}
                      width={30}
                    />
                  </button>
                  <p className="hidden md:inline font-medium md:text-sm">Freeze Card</p>
                </div>
                <div className="flex justify-start items-center gap-2 w-full">
                  <Link
                    href="/wallet/topup"
                    className="flex items-center justify-center gap-2 bg-slate-200 hover:bg-slate-300 text-white rounded-full px-2 py-2 transition duration-300 w-full sm:w-auto"
                  >
                    <Image
                      src="/icons/topup.svg"
                      alt="icon"
                      height={30}
                      width={30}
                    />
                  </Link>
                  <p className="hidden md:inline font-medium md:text-sm">Top Up</p>
              </div>
              <div className="flex justify-start items-center gap-2 w-full">
                  <Link
                    href="/wallet/exchange"
                    className="flex items-center justify-center gap-2 bg-slate-200 hover:bg-slate-300 text-white rounded-full px-2 py-2 transition duration-300 w-full sm:w-auto"
                  >
                    <Image
                      src="/icons/exchange.svg"
                      alt="icon"
                      height={30}
                      width={30}
                    />
                  </Link>
                  <p className="hidden md:inline font-medium md:text-sm">Convert Currency</p>
              </div>
              </div>
            </div>
            ): <p>Loading...</p>}

            
            
        </div>

          <div className="hidden xl:flex flex-col justify-center items-center w-[390px]">

            <div className="flex_col_center py-4 px-1">
              <TiktokCard
                number={["***", "***", "***", "***"]}
                cardName={"Your Name"}
                cvc={"***"}
                expiryDate={"99/99"}
                hideDetails={false}
                variant="red"
              />
              <div className="flex_col_center mt-2 w-full gap-2">
                <div className="w-4/5 h-2">
                  <Progress value={60} className="" />
                </div>
                <p className="text-sm text-center mt-1">Spend <span className="underline">$500</span> more to reach <span className="capitalize text-red-500 font-medium">red</span> tier!</p>
              </div>
            </div>

            <div className="flex_col_center py-4 px-1">
              <TiktokCard
                number={["***", "***", "***", "***"]}
                cardName={"Your Name"}
                cvc={"***"}
                expiryDate={"99/99"}
                hideDetails={false}
                variant="black"
              />
              <div className="flex_col_center mt-2 w-full gap-2">
                <div className="w-4/5 h-2">
                  <Progress value={60} className="" />
                </div>
                <p className="text-sm text-center mt-1">Spend <span className="underline">$2000</span> more to reach <span className="capitalize text-black font-medium">black</span> tier!</p>
              </div>
            </div>


          </div>

        </div>

        {/* Transaction Summary Section */}
        <div className="bg-white rounded-xl p-4 sm:p-6 shadow-md w-full border border-tiktok-red">
          <h2 className="text-tiktok-red text-xl sm:text-2xl md:text-3xl font-bold mb-4 text-center">
            Your Transactions
          </h2>
          <div className="flex justify-around items-center">
            <div className="flex flex-col items-center">
              <div className="flex items-center gap-4">
                <Image
                  src="/icons/incomingTransactions.svg"
                  alt="icon"
                  height={48}
                  width={48}
                />
                <h2 className="font-bold text-lg sm:text-xl md:text-3xl text-green-600">
                  Incoming:
                </h2>
              </div>
              <p className="text-2xl sm:text-3xl text-center font-semibold text-gray-800">+${incoming}</p>
            </div>
            <div className="flex flex-col items-center">
              <div className="flex items-center gap-4">
                <Image
                  src="/icons/outgoingTransactions.svg"
                  alt="icon"
                  height={48}
                  width={48}
                />
                <h2 className="font-bold text-lg sm:text-xl md:text-3xl text-tiktok-red">
                  Outgoing:
                </h2>
              </div>
              <p className="text-2xl sm:text-3xl text-center font-semibold text-gray-800">-{outgoing}</p>
            </div>
          </div>

          <DataTable columns={columns} data={transactionData} />
        </div>

      </section>


  );
};

export default WalletPage;