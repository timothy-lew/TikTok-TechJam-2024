"use client";

import { useState } from "react";
import { useAuth } from "@/hooks/auth-provider";
import Link from "next/link";
import Image from "next/image";
import { columns } from "@/components/tables/transactions";
import { DataTable } from "@/components/ui/data-table";
import { useToast } from "@/components/ui/use-toast"
import {
  type Transaction,
  useFetchTransactions,
} from "@/hooks/useFetchTransactions";
import { TiktokCard, TiktokCardBenefits } from "@/components/shared/TiktokCard";
import {
  type TiktokCardDetails,
  useFetchTiktokCard,
} from "@/hooks/useFetchTiktokCard";
import { Progress } from "@/components/ui/progress";
import { TokCoin, TokCoins } from '@/components/shared/TokCoin';

type WalletActionProps = {
  icon: string;
  iconSize: number;
  label: string;
  variant: 'button' | 'link';
  onClick?: () => void;
  href?: string;
};

function calculateMonthlyTotals(transactions: Transaction[], targetMonth: number, targetYear: number) {
  const incoming = transactions
    .filter(t => {
      const date = new Date(t.transactionDate);
      return date.getMonth() === targetMonth && date.getFullYear() === targetYear && t.transactionType === 'TOPUP';
    })
    .reduce((sum, t) => sum + (t.topUpDetails?.topUpAmount ?? 0), 0);

  const outgoing = transactions
    .filter(t => {
      const date = new Date(t.transactionDate);
      return date.getMonth() === targetMonth && date.getFullYear() === targetYear && t.transactionType === 'PURCHASE';
    })
    .reduce((sum, t) => sum + (t.purchaseDetails?.purchaseAmount ?? 0), 0);

  return { incoming, outgoing };
}

const WalletAction: React.FC<WalletActionProps> = ({ icon, iconSize, label, variant, onClick, href }) => {
  const commonClasses = "flex items-center justify-center gap-2 bg-gray-200 hover:bg-slate-300 text-white rounded-full px-2 py-2 transition duration-100 w-full sm:w-auto";

  return (
    <div className="flex justify-start items-center gap-2 w-full">
      {variant === 'button' ? (
        <div className="flex justify-start items-center gap-2 w-full">
          <button className={commonClasses} onClick={onClick}>
            <Image src={icon} alt={label} height={iconSize} width={iconSize} />
          </button>
          <p className="hidden md:inline font-medium md:text-sm">{label}</p>
        </div>
      ) : (
        <div className="flex justify-start items-center gap-2 w-full">
          <Link href={href || ''} className={commonClasses}>
            <Image src={icon} alt={label} height={iconSize} width={iconSize} />
          </Link>
          <p className="hidden md:inline font-medium md:text-sm">{label}</p>
        </div>
      )}
    </div>
  );
};


const WalletPage: React.FC = () => {
  const auth = useAuth();
  const user = auth?.user || null;
  const [hideDetails, setHideDetails] = useState<boolean>(true);
  const [freezeCard, setFreezeCard] = useState<boolean>(false);

  const {transactionData, loadingTransactionData} = useFetchTransactions(user?.buyerProfile?.id || "", "buyer");

  const tiktokCardDetails = useFetchTiktokCard(user?.id || "");
  
  const { toast } = useToast()
  const walletData = auth?.userWallet;

  const blueCardSpending : number = 1000;
  const redCardSpending : number = 5000;
  const obsidianCardSpending : number = 50000;

  const currentDate = new Date();
  const currentMonth = currentDate.getMonth();
  const currentYear = currentDate.getFullYear();
  const lastMonth = currentMonth === 0 ? 11 : currentMonth - 1;
  const lastMonthYear = currentMonth === 0 ? currentYear - 1 : currentYear;

  const allTimeIncoming = transactionData
    .filter(t => t.transactionType === 'TOPUP')
    .reduce((sum, t) => sum + (t.topUpDetails?.topUpAmount || 0), 0);

  const allTimeOutgoing = transactionData
    .filter(t => t.transactionType === 'PURCHASE')
    .reduce((sum, t) => sum + (t.purchaseDetails?.purchaseAmount || 0), 0);

  const thisMonth = calculateMonthlyTotals(transactionData, currentMonth, currentYear);
  const lastMonthTotals = calculateMonthlyTotals(transactionData, lastMonth, lastMonthYear);

  const incomingChange = ((thisMonth.incoming - lastMonthTotals.incoming) / lastMonthTotals.incoming) * 100;
  const outgoingChange = ((thisMonth.outgoing - lastMonthTotals.outgoing) / lastMonthTotals.outgoing) * 100;

  return (
    <section className="text-gray-800 flex flex-col lg:flex-row w-full justify-start items-start gap-4 sm:gap-6 px-4 sm:px-6 py-6 sm:py-8 max-w-[1400px] mx-auto">

      <div className="w-full lg:w-3/4 space-y-6 ">
        {/* Wallet and Card Section */}
        <div className="bg-white w-full flex justify-center shadow-sm items-start border border-slate-200 rounded-xl p-4 sm:p-6">
          <div className="w-full flex_col_center gap-4">
            <h2 className="text-tiktok-red text-center w-full text-xl sm:text-2xl md:text-4xl font-bold mb-4">
              Your <Image src="/tiktokIconNoBg.svg" alt="icon" width={42} height={42} className="inline rotate-6"/> Wallet
            </h2>
            <div className="flex_col_center gap-3">
              {/* <h3 className="text-slate-900 font-semibold text-xl">Available in Wallet:</h3> */}
              <div className="flex flex-col md:flex-row justify-center items-center gap-4 md:gap-7=8">
                
                <div className="flex_center gap-2">
                  <Image src="/icons/cash.svg" alt="$" height={40} width={40} className="-rotate-6"/>
                  <p className="text-slate-800 text-xl md:text-2xl">${walletData?.cashBalance.toFixed(2)} <span className="text-lg">SGD</span></p>
                </div>
                
                <div className="flex_center gap-2">
                  <TokCoins />
                  <p className="text-slate-800 text-xl md:text-2xl">{walletData?.tokTokenBalance} <span className="text-lg">Tok Coins</span></p>
                </div>
              </div>
            </div>
            
            {tiktokCardDetails ? (
              <div className="flex_center gap-4 w-full">
                {/* <TiktokCard
                  number={tiktokCardDetails.number}
                  cardName={tiktokCardDetails.cardName}
                  cvc={tiktokCardDetails.cvc}
                  expiryDate={tiktokCardDetails.expiryDate}
                  hideDetails={hideDetails}
                  frozen={freezeCard}
                  noDetails={false}
                  variant="blue"
                /> */}
                <div className="flex_center gap-6">
                  {/* <WalletAction
                    icon={hideDetails ? "/icons/eyeOpen.svg" : "/icons/eyeClose.svg"}
                    label={hideDetails ? "Show Details" : "Hide Details"}
                    variant="button"
                    onClick={() => setHideDetails((prev) => !prev)}
                  />
                  <WalletAction
                    icon="/icons/freeze.svg"
                    label="Freeze Card"
                    variant="button"
                    onClick={() => {
                      setFreezeCard((prev) => !prev);
                      toast({
                        title: freezeCard ? "Card Unfrozen!" : "Card Frozen!",
                      });
                    }}
                  /> */}
                  <WalletAction
                    icon="/icons/topup2.svg"
                    iconSize={18}
                    label="Top Up"
                    variant="link"
                    href="/wallet/topup"
                  />
                  <WalletAction
                    icon="/icons/exchange.svg"
                    iconSize={24}
                    label="Exchange"
                    variant="link"
                    href="/wallet/exchange"
                  />
                  <WalletAction
                    icon="/icons/withdraw.svg"
                    iconSize={24}
                    label="Withdraw"
                    variant="link"
                    href="/wallet"
                  />
                </div>
              </div>
            ) : <p>Loading...</p>}
          </div>
        </div>

        {/* Transaction Summary Section */}
        <div className="bg-white rounded-xl p-6 sm:p-8 shadow-sm w-full border border-slate-200">
            <h2 className="text-tiktok-red text-center w-full text-xl sm:text-2xl md:text-4xl font-bold mb-4">
              Your <Image src="/tiktokIconNoBg.svg" alt="icon" width={42} height={42} className="inline rotate-6"/> Transactions
            </h2>
          <p className="text-sm text-center w-full text-slate-400 mb-6">*Withdrawals / Currency Exchange are not included in calculation</p>
          
          <div className="grid grid-cols-1 lg:grid-cols-2 gap-6 mb-8">
            <div className="flex flex-col items-center p-6 bg-slate-100/80 rounded-xl">
              <h3 className="font-bold text-xl mb-4 text-gray-700">All Time</h3>
              <div className="flex-grow flex flex-col justify-evenly">
                <div className="flex justify-center items-center gap-2">
                  <Image src="/icons/incomingTransactions.svg" alt="icon" height={40} width={40} className="relative bottom-2"/>
                  <p className="text-green-600 text-2xl sm:text-3xl md:text-3xl font-bold mb-2">
                    {isNaN(allTimeIncoming) ? "-" : `+$${allTimeIncoming.toFixed(2)}`}
                  </p>
                </div>
                <div className="flex justify-center items-center gap-2">
                  <Image src="/icons/outgoingTransactions.svg" alt="icon" height={40} width={40}/>
                  <p className="text-amber-500 text-2xl sm:text-3xl md:text-3xl font-bold">
                    {isNaN(allTimeOutgoing) ? "-" : `-$${allTimeOutgoing.toFixed(2)}`}
                  </p>
                </div>
              </div>
            </div>
            
            <div className="flex flex-col items-center p-6 bg-slate-100/80 rounded-xl">
              <h3 className="font-bold text-xl mb-4 text-gray-700">This Month</h3>
              <div className="flex flex-col items-center mb-4">
                <p className="text-green-600 text-2xl sm:text-3xl md:text-3xl font-bold mb-1">
                  {isNaN(thisMonth.incoming) ? "-" : `+$${thisMonth.incoming.toFixed(2)}`}
                </p>
                {!isNaN(incomingChange) && <div className="flex items-center space-x-1 text-sm">
                  <span className={`${!isNaN(incomingChange) && incomingChange >= 0 ? "text-green-600" : "text-red-600"} font-semibold`}>
                    {incomingChange.toFixed(0)}%
                  </span>
                    <Image
                      src={incomingChange >= 0 ? "/icons/greenIncrease.svg" : "/icons/redDecrease.svg"}
                      alt={incomingChange >= 0 ? "Increase" : "Decrease"}
                      width={16}
                      height={16}
                    />
                  <span className="text-gray-500">vs prev month</span>
                </div>}
              </div>
              <div className="flex flex-col items-center">
                <p className="text-amber-500 text-2xl sm:text-3xl md:text-3xl font-bold mb-1">
                  {isNaN(thisMonth.outgoing) ? "-" : `-$${thisMonth.outgoing.toFixed(2)}`}
                </p>
                {!isNaN(thisMonth.outgoing) && <div className="flex items-center space-x-1 text-sm">
                  <span className={`${!isNaN(outgoingChange) && outgoingChange >= 0 ? "text-green-600" : "text-red-600"} font-semibold`}>
                    {outgoingChange.toFixed(0)}%
                  </span>
                    <Image
                      src={outgoingChange >= 0 ? "/icons/greenIncrease.svg" : "/icons/redDecrease.svg"}
                      alt={outgoingChange >= 0 ? "Increase" : "Decrease"}
                      width={16}
                      height={16}
                    />
                  <span className="text-gray-500">vs prev month</span>
                </div>}
              </div>
            </div>
          </div>

          <DataTable columns={columns} data={transactionData} isLoading={loadingTransactionData}/>
        </div>
      </div>

      {/* Card Tiers Section - Only visible on large screens */}
      <div className="hidden lg:block w-1/4 bg-white shadow-sm border border-slate-200 rounded-xl p-4">

        <div className="flex_col_center w-full gap-2 mb-6">
          <h2 className="text-tiktok-red text-2xl font-bold mx-auto">
            <div className="flex_center gap-1">
              <Image src="/tiktokIconNoBg.svg" alt="icon" width={32} height={38} className="inline rotate-6 relative bottom-0.5"/>
              <span>Cards</span>
            </div>
          </h2>
          <span className="hidden md:inline text-xs px-1.5 py-0.5 bg-tiktok-red text-white font-bold rounded-xl">Coming Soon</span>
        </div>
        
        
        <div className="space-y-8">

          <div>
            {/* <h3 className="text-xl text-center font-semibold mb-2">Blue Tier</h3> */}
            <TiktokCard
              number={["***", "***", "***", "***"]}
              cardName={"Your Name"}
              cvc={"***"}
              expiryDate={"99/99"}
              hideDetails={false}
              frozen={false}
              noDetails={true}
              variant="blue"
            />
            <div className="mt-4 space-y-2">
              <Progress value={(allTimeOutgoing/blueCardSpending)*100} className="w-full" />
              <p className="text-sm text-center">Spend <span className="font-semibold">${blueCardSpending-allTimeOutgoing}</span> more to reach Blue tier!</p>
            </div>
            <div className="flex flex-col items-start justify-center mt-4 space-y-2 text-sm">
              <TiktokCardBenefits desc="10% Cashback on TikTok Shop Purchases" />
              <TiktokCardBenefits desc="Exclusive Blue Tier Discounts" />
            </div>
          </div>

          <div>
            {/* <h3 className="text-xl text-center font-semibold mb-2">Red Tier</h3> */}
            <TiktokCard
              number={["***", "***", "***", "***"]}
              cardName={"Your Name"}
              cvc={"***"}
              expiryDate={"99/99"}
              hideDetails={false}
              frozen={false}
              noDetails={true}
              variant="red"
            />
            <div className="mt-4 space-y-2">
              <Progress value={(allTimeOutgoing/redCardSpending)*100} className="w-full" />
              <p className="text-sm text-center">Spend <span className="font-semibold">${redCardSpending-allTimeOutgoing}</span> more to reach Red tier!</p>
            </div>
            <div className="flex flex-col items-start justify-center mt-4 space-y-2 text-sm">
              <TiktokCardBenefits desc="24/7 Priority Customer Support" />
              <TiktokCardBenefits desc="11% Cashback on TikTok Shop Purchases" />
              <TiktokCardBenefits desc="Exclusive Red Tier Discounts" />
            </div>
          </div>

          <div>
            {/* <h3 className="text-xl text-center font-semibold mb-2">Obsidian Tier</h3> */}
            <TiktokCard
              number={["***", "***", "***", "***"]}
              cardName={"Your Name"}
              cvc={"***"}
              expiryDate={"99/99"}
              hideDetails={false}
              frozen={false}
              noDetails={true}
              variant="obsidian"
            />
            <div className="mt-4 space-y-2">
              <Progress value={(allTimeOutgoing/obsidianCardSpending)*100} className="w-full" />
              <p className="text-sm text-center">Spend <span className="font-semibold">${obsidianCardSpending-allTimeOutgoing}</span> more to reach Obsidian tier!</p>
            </div>
            <div className="flex flex-col items-start justify-center mt-4 space-y-2 text-sm">
              <TiktokCardBenefits desc="24/7 Priority Customer Support" />
              <TiktokCardBenefits desc="12% Cashback on TikTok Shop Purchases" />
              <TiktokCardBenefits desc="Free Shipping" />
              <TiktokCardBenefits desc="Exclusive Obsidian Tier Discounts" />
              <TiktokCardBenefits desc="Personalized Shopping Concierge" />
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default WalletPage;