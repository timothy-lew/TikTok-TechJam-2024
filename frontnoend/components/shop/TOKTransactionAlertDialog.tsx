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
  import { useState, useEffect } from "react";
  import { Product } from "@/types/ShopTypes";
  
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
    const [countdown, setCountdown] = useState(300); // Set initial countdown time  5 mins
    const [showUnsuccessfulMessage, setShowUnsuccessfulMessage] = useState(false);
  
    const formatTime = (time: number) => {
      const minutes = Math.floor(time / 60);
      const seconds = time % 60;
      return `${minutes}:${seconds < 10 ? "0" : ""}${seconds}`;
    };
  
    useEffect(() => {
      let timer: NodeJS.Timeout;
      if (isOpen && alertDialogContent === "") {
        setCountdown(300); // Reset countdown each time the dialog opens
        setShowUnsuccessfulMessage(false);
        timer = setInterval(() => {
          setCountdown((prevCountdown) => {
            if (prevCountdown <= 0) {
              clearInterval(timer);
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
  
      return () => clearInterval(timer);
    }, [isOpen, alertDialogContent, onClose, onCancelTransaction]);
  
    return (
      <AlertDialog open={isOpen} onOpenChange={onClose}>
        <AlertDialogContent className="w-[1200px]">
          <AlertDialogHeader>
            <AlertDialogTitle>Transaction Status</AlertDialogTitle>
            <AlertDialogDescription>
              {alertDialogContent}
              {showUnsuccessfulMessage ? (
                <p>Transaction unsuccessful. Please try again.</p>
              ) : (
                alertDialogContent === "" && (
                  <>
                    <p>
                      
                      Please make payment of <strong>{unitTOKTokenCost * quantity} TOK Coins</strong>{" "}
                      to the following
                    </p>
                    <p>
                      Seller's Wallet Address:
                      <WalletAddressBar
                        wallet_address={product?.sellerWalletAddress}
                      ></WalletAddressBar>
                    </p>
                    <p className="font-medium text-red-600">
                      Time left to complete the transaction: {formatTime(countdown)}
                    </p>
                  </>
                )
              )}
            </AlertDialogDescription>
          </AlertDialogHeader>
          <AlertDialogFooter>
            {!showUnsuccessfulMessage && alertDialogContent === "" && (
              <AlertDialogAction
                onClick={() => {
                  setShowUnsuccessfulMessage(true);
                  onCancelTransaction();
                }}
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
  