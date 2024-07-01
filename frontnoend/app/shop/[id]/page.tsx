"use client";
import { useEffect, useState, useRef } from "react";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { ArrowLeft, Gift } from "lucide-react";
import { useAuth } from "@/hooks/auth-provider";
import {
  ProductCardDetails,
  ProductDetailsSkeleton,
} from "@/components/shop/ProductCard";
import { MdOutlineShoppingCart } from "react-icons/md";

import TOKTransactionAlertDialog from "@/components/shop/TOKTransactionAlertDialog";
import { BuyerInfo, Product } from "@/types/ShopTypes";
import { Alert, AlertTitle, AlertDescription } from "@/components/ui/alert";

interface PageProps {
  params: {
    id: string;
  };
}

export default function ProductDetailsPage({ params }: PageProps) {
  const [product, setProduct] = useState<Product | null>(null);
  const [sellerId, setSellerId] = useState<string | null>(null);
  const [sellerBusinessName, setBusinessName] = useState<string | null>(null);
  const [transactionTOKCancelled, setTransactionTOKCancelled] = useState(false);

  const [buyerInfo, setBuyerInfo] = useState<BuyerInfo | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<Error | null>(null);
  const [accessToken, setAccessToken] = useState<string | undefined>(undefined);

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [quantity, setQuantity] = useState<number>(1);

  const openModal = () => setIsModalOpen(true);
  const closeModal = () => setIsModalOpen(false);

  const [isAlertDialogOpen, setIsAlertDialogOpen] = useState(false);
  const [alertDialogContent, setAlertDialogContent] = useState("");
  const router = useRouter();

  const transactionTOKCancelledRef = useRef(transactionTOKCancelled);

  useEffect(() => {
    transactionTOKCancelledRef.current = transactionTOKCancelled;
  }, [transactionTOKCancelled]);

  const confirmPurchaseTiktokCoin = async () => {
    if (!buyerInfo || !product || !sellerId) {
      console.error("Missing required information for purchase");
      return;
    }

    const payload = {
      buyerProfileId: buyerInfo.buyerProfile.id,
      sellerProfileId: sellerId,
      itemId: product.id,
      quantity: quantity,
      purchaseType: "TOK_TOKEN",
    };

    console.log("Payload for purchase:", payload);

    setIsAlertDialogOpen(true); // Open the AlertDialog to show the address and countdown
    setTransactionTOKCancelled(false); // Reset cancellation status

    setIsAlertDialogOpen(true); // Open the AlertDialog to show the address and countdown

    try {
      const response = await fetch(
        "http://localhost:8080/api/transactions/purchase",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${accessToken}`,
          },
          body: JSON.stringify(payload),
        }
      );

      if (!response.ok) {
        throw new Error("Network response was not ok");
      }

      const data = await response.json();
      const transactionID = data.id;
      console.log("Transaction initiated:", data);
      console.log("Transaction ID: " + data.id);

      if (!transactionID) {
        throw new Error("Transaction ID is null");
      }

      const checkTransactionStatus = async () => {
        if (transactionTOKCancelledRef.current) {
          return false;
        }
        const statusResponse = await fetch(
          `http://localhost:8080/api/transactions/status/${transactionID}`,
          {
            headers: {
              Authorization: `Bearer ${accessToken}`,
            },
          }
        );

        if (!statusResponse.ok) {
          throw new Error("Network response was not ok");
        }

        const statusText = await statusResponse.text();
        console.log("Status text:", statusText);

        const success = statusText.trim() === "true";

        return success;
      };

      const startTime = Date.now();
      const timeout = 5 * 60 * 1000; // 5 minutes in milliseconds
      const interval = 5000; // 5 seconds in milliseconds

      const pollTransactionStatus = async () => {
        while (Date.now() - startTime < timeout) {
          if (transactionTOKCancelledRef.current) {
            return;
          }

          const status = await checkTransactionStatus();

          console.log("Status to close modal fail or success: " + status);
          if (status && !transactionTOKCancelledRef.current) {
            setAlertDialogContent(
              "Purchase successful! Redirecting to home page..."
            );
            setTimeout(() => {
              setIsAlertDialogOpen(false); // Close the alert dialog
              closeModal();
              console.log("Redirecting to homepage...");
              router.push("/shop");
            }, 3000); // 3-second delay to show redirect to shop home
            return;
          } else {
            await new Promise((resolve) => setTimeout(resolve, interval));
          }
        }

        if (!transactionTOKCancelledRef.current) {
          throw new Error("Transaction timed out");
        }
      };

      pollTransactionStatus();
    } catch (error) {
      console.error("Error during purchase:", error);
      setAlertDialogContent(
        `Error during purchase. Please try again. ${error}`
      );

      setTimeout(() => {
        setIsAlertDialogOpen(false);
        closeModal();
      }, 3000);
    }
  };

  const auth = useAuth();
  const user = auth?.user || null;

  useEffect(() => {
    console.log("transactionTOKCancelled status: " + transactionTOKCancelled);
  }, [transactionTOKCancelled]);

  useEffect(() => {
    const getAccessToken = async () => {
      const token = await auth?.obtainAccessToken();
      setAccessToken(token);
      if (user) {
        console.log("User details:", user);
        const transformedBuyerInfo: BuyerInfo = {
          id: user.id,
          username: user.username,
          email: user.email,
          firstName: user.firstName,
          lastName: user.lastName,
          roles: user.roles,
          buyerProfile: user.buyerProfile || {
            id: "",
            shippingAddress: "",
            billingAddress: "",
            defaultPaymentMethod: "",
          },
          sellerProfile: user.sellerProfile || {
            id: "",
            businessName: "",
            businessDescription: "",
          },
          wallet: user?.wallet || null,
        };
        setBuyerInfo(transformedBuyerInfo);
      }
    };

    getAccessToken();
  }, [user]);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        if (!accessToken) {
          throw new Error("No access token available");
        }

        const prod_response = await fetch(
          `http://localhost:8080/api/items/${params.id}`,
          {
            headers: {
              Authorization: `Bearer ${accessToken}`,
            },
          }
        );

        if (!prod_response.ok) {
          throw new Error("Network response was not ok");
        }

        const data = await prod_response.json();
        console.log(data);
        setProduct(data);
        setSellerId(data.sellerProfileId);
        setBusinessName(data.businessName);
        console.log("seller wallet address: " + data.sellerWalletAddress);
        console.log("seller id: " + data.sellerProfileId);
        console.log("Business Name" + data.businessName);
      } catch (err) {
        setError(err as Error);
        setLoading(false);
      }
    };

    fetchProducts();
  }, [params.id, accessToken]);

  const cancelTransaction = () => {
    setTransactionTOKCancelled(true);
    setIsAlertDialogOpen(false);
    setAlertDialogContent("");
  };

  if (loading) {
    return <ProductDetailsSkeleton />;
  }

  return (
    <>
      <Link href="/shop" className="flex items-center space-x-2">
        <ArrowLeft className="size-4" />
        <span>Return to Shop</span>
      </Link>
      {product && (
        <ProductCardDetails
          product={product}
          sellerBusinessName={sellerBusinessName}
          openModal={openModal}
        />
      )}
      {isModalOpen && product && buyerInfo && (
        <>
          <ConfirmPurchaseModal
            product={product}
            quantity={quantity}
            buyer={buyerInfo}
            setQuantity={setQuantity}
            onClose={closeModal}
            onConfirmTiktokCoin={confirmPurchaseTiktokCoin}
            shippingAddress={user?.buyerProfile?.shippingAddress || ""}
          />
          <TOKTransactionAlertDialog
            isOpen={isAlertDialogOpen}
            onClose={() => {
              setIsAlertDialogOpen(false);
              setTransactionTOKCancelled(true);
            }}
            alertDialogContent={alertDialogContent}
            product={product}
            quantity={quantity}
            onCancelTransaction={cancelTransaction}
          />
        </>
      )}
    </>
  );
}

type ConfirmPurchaseModalProps = {
  product: Product;
  quantity: number;
  buyer: BuyerInfo;
  setQuantity: (quantity: number) => void;
  onClose: () => void;
  onConfirmTiktokCoin: () => void;
  shippingAddress: string;
};

const ConfirmPurchaseModal = ({
  product,
  quantity,
  buyer,
  setQuantity,
  onClose,
  onConfirmTiktokCoin,
  shippingAddress,
}: ConfirmPurchaseModalProps) => {
  const handleQuantityChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setQuantity(parseInt(event.target.value, 10));
  };

  const increaseQuantity = () => {
    if (product) {
      const newQuantity = Math.min(quantity + 1, product.quantity);
      setQuantity(newQuantity);
    }
  };

  const decreaseQuantity = () => {
    const newQuantity = Math.max(quantity - 1, 1);
    setQuantity(newQuantity);
  };

  return (
    <div className="fixed inset-0 z-10 overflow-y-auto flex items-center justify-center bg-gray-500 bg-opacity-75">
      <div className="relative bg-white rounded-lg shadow-xl w-full max-w-lg mx-auto p-6">
        <div className="flex items-center justify-center mb-4">
          <div className="flex-shrink-0 flex items-center justify-center h-24 w-24 rounded-full bg-blue-100">
            <MdOutlineShoppingCart className="h-12 w-12 text-black-600" />
          </div>
        </div>
        <h3 className="text-2xl leading-6 font-medium text-gray-900 text-center mb-4">
          Confirm Purchase
        </h3>
        <Alert className="bg-yellow-100 border-l-4 border-yellow-500 text-yellow-700 p-4 mb-4">
          <div className="flex items-center">
            <Gift className="h-5 w-5 mr-2" />
            <div className="text-m">
              <AlertTitle className="font-bold">Enjoy 10% Off!</AlertTitle>
              <AlertDescription className="text-m">
                Checkout using TOK Coin as your payment method to enjoy 10%
                savings!
              </AlertDescription>
            </div>
          </div>
        </Alert>
        <div className="border border-gray-300 rounded-lg p-4">
          <div className="text-sm text-gray-900 space-y-2">
            <p className="font-bold">Please review your purchase details:</p>
            <p className="p-1">Seller: {product.businessName}</p>
            <p className="p-1">Seller ID: {product.sellerProfileId}</p>
            <p className="p-1">Product: {product.name}</p>
            <p className="p-1">
              Recipient: {buyer.firstName + " " + buyer.lastName}
            </p>
            <p className="p-1">Shipping Address: {shippingAddress}</p>
            <p className="p-1">
              Price:{" "}
              <strong>
                ${(product.price * quantity).toFixed(2)} or{" "}
                {product.tokTokenPrice * quantity} TOK Coins
              </strong>
            </p>
            <div className="p-1 flex items-center space-x-2">
              <span>Quantity:</span>
              <button
                onClick={decreaseQuantity}
                className="px-2 py-1 border rounded"
              >
                -
              </button>
              <input
                type="number"
                min="1"
                max={product.quantity}
                value={quantity}
                onChange={handleQuantityChange}
                className="w-12 text-center border rounded"
              />
              <button
                onClick={increaseQuantity}
                className="px-2 py-1 border rounded"
              >
                +
              </button>
            </div>
          </div>
        </div>
        <div className="px-3 py-3 sm:px-6 sm:flex sm:flex-row-reverse">
          <button
            type="button"
            className="w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-red-500 text-base font-medium text-white hover:bg-red-600 sm:ml-3 sm:w-auto sm:text-sm"
          >
            Buy with Cash
          </button>
          <button
            type="button"
            className="w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-red-500 text-base font-medium text-white hover:bg-red-600 sm:ml-3 sm:w-auto sm:text-sm"
            onClick={onConfirmTiktokCoin}
          >
            Buy with TOK Coin
          </button>
          <button
            type="button"
            className="mt-3 w-full inline-flex justify-center rounded-md border border-gray-300 shadow-sm px-4 py-2 bg-white text-base font-medium text-gray-700 hover:bg-gray-50 sm:mt-0 sm:w-auto sm:text-sm"
            onClick={onClose}
          >
            Cancel
          </button>
        </div>
      </div>
    </div>
  );
};
