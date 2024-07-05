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
import { Progress } from "@/components/ui/progress"

type WalletActionProps = {
  icon: string;
  label: string;
  variant: 'button' | 'link';
  onClick?: () => void;
  href?: string;
};

const WalletAction: React.FC<WalletActionProps> = ({ icon, label, variant, onClick, href }) => {
  const commonClasses = "flex items-center justify-center gap-2 bg-slate-200 hover:bg-slate-300 text-white rounded-full px-2 py-2 transition duration-100 w-full sm:w-auto";

  return (
    <div className="flex justify-start items-center gap-2 w-full">
      {variant === 'button' ? (
        <div className="flex justify-start items-center gap-2 w-full">
          <button className={commonClasses} onClick={onClick}>
            <Image src={icon} alt={label} height={22} width={22} />
          </button>
          <p className="hidden md:inline font-medium md:text-sm">{label}</p>
        </div>
      ) : (
        <div className="flex justify-start items-center gap-2 w-full">
          <Link href={href || ''} className={commonClasses}>
            <Image src={icon} alt={label} height={22} width={22} />
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

  const transactionData: Transaction[] = useFetchTransactions(user?.buyerProfile?.id || "", "buyer");
  const tiktokCardDetails = useFetchTiktokCard(user?.id || "");
  
  const { toast } = useToast()
  const walletData = auth?.userWallet;

  let incoming: number = 0;
  let outgoing: number = 0;

  // Conversion is excluded
  transactionData.forEach((transaction) => {
    if (transaction.transactionType === 'PURCHASE') {
      outgoing += transaction.purchaseDetails?.purchaseAmount || 0;
    } else if (transaction.transactionType === 'TOPUP') {
      incoming += transaction.topUpDetails?.topUpAmount || 0;
    }
  });

  return (
    <section className="text-gray-800 flex flex-col lg:flex-row w-full justify-start items-start gap-4 sm:gap-6 px-4 sm:px-6 py-6 sm:py-8 max-w-[1400px] mx-auto">
      <div className="w-full lg:w-3/4 space-y-6">
        {/* Wallet and Card Section */}
        <div className="bg-white shadow-md w-full flex justify-center items-start border border-slate-200 rounded-xl p-4 sm:p-6">
          <div className="w-full flex_col_center gap-4">
            <h2 className="text-tiktok-red text-center w-full text-xl sm:text-2xl md:text-3xl font-bold mb-4">
              Your <Image src="/tiktokIconNoBg.svg" alt="icon" width={42} height={42} className="inline rotate-6"/> Wallet
            </h2>
            <div className="flex_col_center gap-3">
              <h3 className="text-slate-900 font-semibold text-xl">Available in Wallet:</h3>
              <div className="flex flex-col sm:flex-row justify-center items-center gap-2">
                <p className="text-slate-800 text-xl">${walletData?.cashBalance.toFixed(2)} <span className="text-lg">SGD</span></p>
                <p className="text-slate-800 text-xl">{walletData?.tokTokenBalance} <span className="text-lg">Tok Coins</span></p>
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
                  frozen={freezeCard}
                  noDetails={false}
                  variant="blue"
                />
                <div className="flex_col_center gap-2">
                  <WalletAction
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
                  />
                  <WalletAction
                    icon="/icons/topup.svg"
                    label="Top Up"
                    variant="link"
                    href="/wallet/topup"
                  />
                  <WalletAction
                    icon="/icons/exchange.svg"
                    label="Convert Currency"
                    variant="link"
                    href="/wallet/exchange"
                  />
                  <WalletAction
                    icon="/icons/withdraw.svg"
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
        <div className="bg-white rounded-xl p-4 sm:p-6 shadow-md w-full border border-slate-200">
          <h2 className="text-tiktok-red text-xl sm:text-2xl md:text-3xl font-bold mb-4 text-center">
            Your Transactions
          </h2>
          <div className="flex justify-around items-center mb-6">
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
              <p className="text-2xl sm:text-3xl text-center font-semibold text-gray-800">+${incoming.toFixed(2)}</p>
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
              <p className="text-2xl sm:text-3xl text-center font-semibold text-gray-800">-${outgoing.toFixed(2)}</p>
            </div>
          </div>

          <DataTable columns={columns} data={transactionData} />
        </div>
      </div>

      {/* Card Tiers Section - Only visible on large screens */}
      <div className="hidden lg:block w-1/4 bg-white shadow-md border border-slate-200 rounded-xl p-4">
        <h2 className="text-tiktok-red text-2xl font-bold mb-6 text-center">Card Tiers</h2>
        
        <div className="space-y-8">
          <div>
            <h3 className="text-xl font-semibold mb-2">Red Tier</h3>
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
              <Progress value={60} className="w-full" />
              <p className="text-sm text-center">Spend <span className="font-semibold">$500</span> more to reach Red tier!</p>
            </div>
            <div className="flex flex-col items-start justify-center mt-4 space-y-2 text-sm">
              <TiktokCardBenefits desc="24/7 Priority Customer Support" />
              <TiktokCardBenefits desc="11% Cashback on TikTok Shop Purchases" />
              <TiktokCardBenefits desc="Exclusive Red Tier Events" />
            </div>
          </div>

          <div>
            <h3 className="text-xl font-semibold mb-2">Black Tier</h3>
            <TiktokCard
              number={["***", "***", "***", "***"]}
              cardName={"Your Name"}
              cvc={"***"}
              expiryDate={"99/99"}
              hideDetails={false}
              frozen={false}
              noDetails={true}
              variant="black"
            />
            <div className="mt-4 space-y-2">
              <Progress value={30} className="w-full" />
              <p className="text-sm text-center">Spend <span className="font-semibold">$2000</span> more to reach Black tier!</p>
            </div>
            <div className="flex flex-col items-start justify-center mt-4 space-y-2 text-sm">
              <TiktokCardBenefits desc="24/7 Priority Customer Support" />
              <TiktokCardBenefits desc="12% Cashback on TikTok Shop Purchases" />
              <TiktokCardBenefits desc="Free Shipping" />
              <TiktokCardBenefits desc="Exclusive Black Tier Events" />
              <TiktokCardBenefits desc="Personalized Shopping Concierge" />
            </div>
          </div>
        </div>
      </div>
    </section>
  );
};

export default WalletPage;