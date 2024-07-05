import {
  AlertDialog,
  AlertDialogAction,
  AlertDialogContent,
  AlertDialogDescription,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogTitle,
} from "@/components/ui/alert-dialog";
import { WalletAddressBar } from "@/components/shop/WalletAddressBar";
import { useState, useEffect, useRef } from "react";
import { Product } from "@/types/ShopTypes";
import { FaCheckCircle } from "react-icons/fa";
import { IoTimeOutline } from "react-icons/io5";

interface TOKTransactionAlertDialogProps {
  isOpen: boolean;
  onClose: () => void;
  alertDialogContent: string;
  product: Product;
  quantity: number;
  unitTOKTokenCost: number;
  onCancelTransaction: () => void;
}

const TOKTransactionAlertDialog = ({
  isOpen,
  onClose,
  alertDialogContent,
  product,
  quantity,
  unitTOKTokenCost,
  onCancelTransaction,
}: TOKTransactionAlertDialogProps) => {
  const [countdown, setCountdown] = useState(300); // Set initial countdown time 5 mins
  const [showUnsuccessfulMessage, setShowUnsuccessfulMessage] = useState(false);
  const timerRef = useRef<NodeJS.Timeout | null>(null);

  const formatTime = (time: number) => {
    const minutes = Math.floor(time / 60);
    const seconds = time % 60;
    return `${minutes}:${seconds < 10 ? "0" : ""}${seconds}`;
  };

  useEffect(() => {
    if (isOpen) {
      setCountdown(300); // Reset countdown each time the dialog opens
      setShowUnsuccessfulMessage(false);
      if (timerRef.current) {
        clearInterval(timerRef.current);
      }
      timerRef.current = setInterval(() => {
        setCountdown((prevCountdown) => {
          if (prevCountdown <= 0) {
            clearInterval(timerRef.current as NodeJS.Timeout);
            setShowUnsuccessfulMessage(true);
            setTimeout(() => {
              onCancelTransaction();
              onClose();
            }, 3000); // Show message for 3 seconds
            return 0;
          }
          return prevCountdown - 1;
        });
      }, 1000);
    }

    return () => clearInterval(timerRef.current as NodeJS.Timeout);
  }, [isOpen, onClose, onCancelTransaction]);

  const handleCancel = () => {
    clearInterval(timerRef.current as NodeJS.Timeout);
    onCancelTransaction();
  };

  return (
    <AlertDialog open={isOpen} onOpenChange={onClose}>
      <AlertDialogContent className="w-[1200px]">
        <AlertDialogHeader>
          <AlertDialogTitle>Transaction Status</AlertDialogTitle>
          <AlertDialogDescription>
            {alertDialogContent.includes("Purchase successful") ? (
              <div className="flex items-center">
                <FaCheckCircle className="text-green-500 mr-2" size={24} />
                <p>{alertDialogContent}</p>
              </div>
            ) : (
              <>
                {alertDialogContent}
                {showUnsuccessfulMessage ? (
                  <div className="flex items-center">
                    <IoTimeOutline className="text-red-500 mr-2" size={24} />
                    <p>Transaction timed out. Please try again.</p>
                  </div>
                ) : (
                  alertDialogContent === "" && (
                    <>
                      <p>
                        Please make payment of{" "}
                        <strong>{unitTOKTokenCost * quantity} Tok Coins</strong>{" "}
                        to the following
                      </p>
                      <p>
                        Seller's Wallet Address:
                        <WalletAddressBar
                          wallet_address={product?.sellerWalletAddress}
                        />
                        <p>
                          Transactions can take a few seconds to be processed
                          <br />
                          Your patience is appreciated!
                          <br />
                        </p>
                      </p>
                      <p className="mt-4 font-medium text-red-600">
                        Time left to complete the transaction:{" "}
                        {formatTime(countdown)}
                      </p>
                    </>
                  )
                )}
              </>
            )}
          </AlertDialogDescription>
        </AlertDialogHeader>
        <AlertDialogFooter>
          {!showUnsuccessfulMessage && alertDialogContent === "" && (
            <AlertDialogAction
              onClick={handleCancel}
              className="bg-red-500 hover:bg-red-600 text-white"
            >
              Cancel
            </AlertDialogAction>
          )}
        </AlertDialogFooter>
      </AlertDialogContent>
    </AlertDialog>
  );
};

export default TOKTransactionAlertDialog;
