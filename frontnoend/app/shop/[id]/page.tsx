"use client";
import { useEffect, useState, useRef } from "react";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { ArrowLeft } from "lucide-react";
import { useAuth } from "@/hooks/auth-provider";
import {
  ProductCardDetails,
  ProductDetailsSkeleton,
} from "@/components/shop/ProductCards";
import TOKTransactionAlertDialog from "@/components/shop/TOKTransactionAlertDialog";
import CashTransactionAlertDialog from "@/components/shop/CashTransactionAlertDialog";
import { BuyerInfo, Product } from "@/types/ShopTypes";
import { ConfirmPurchaseModal } from "@/components/shop/ConfirmPurchaseModal";

interface PageProps {
  params: {
    id: string;
  };
}

interface PurchasePayload {
  buyerProfileId: string;
  sellerProfileId: string;
  itemId: string;
  quantity: number;
  purchaseType: string;
}

interface PurchaseError extends Error {
  response?: Response;
}

export default function ProductDetailsPage({ params }: PageProps) {
  const [product, setProduct] = useState<Product | null>(null);
  const [sellerId, setSellerId] = useState<string | null>(null);
  const [sellerBusinessName, setBusinessName] = useState<string | null>(null);
  const [buyerInfo, setBuyerInfo] = useState<BuyerInfo | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<Error | null>(null);
  const [accessToken, setAccessToken] = useState<string | undefined>(undefined);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [quantity, setQuantity] = useState<number>(1);
  const [isAlertDialogOpen, setIsAlertDialogOpen] = useState(false);
  const [alertDialogContent, setAlertDialogContent] = useState("");
  const [isCashDialogOpen, setIsCashDialogOpen] = useState(false);
  const [cashAlertDialogContent, setCashAlertDialogContent] = useState("");
  const [transactionTOKCancelled, setTransactionTOKCancelled] = useState(false);
  const [transactionCashCancelled, setTransactionCashCancelled] = useState(false);

  const router = useRouter();
  const transactionControllerRef = useRef<AbortController | null>(null);

  const auth = useAuth();
  const user = auth?.user || null;

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
  }, [auth, user]);

  useEffect(() => {
    const fetchProduct = async () => {
      try {
        if (!accessToken) {
          throw new Error("No access token available");
        }

        const response = await fetch(
          `http://localhost:8080/api/items/${params.id}`,
          {
            headers: {
              Authorization: `Bearer ${accessToken}`,
            },
          }
        );

        if (!response.ok) {
          throw new Error("Failed to fetch product");
        }

        const data = await response.json();
        console.log("Product data:", data);
        setProduct(data);
        setSellerId(data.sellerProfileId);
        setBusinessName(data.businessName);
      } catch (err) {
        setError(err as Error);
      } finally {
        setLoading(false);
      }
    };

    fetchProduct();
  }, [accessToken, params.id]);

  const openModal = () => setIsModalOpen(true);
  const closeModal = () => setIsModalOpen(false);

  const confirmPurchaseTiktokCoin = async () => {
    if (!buyerInfo || !product || !sellerId) {
      console.error("Missing required information for purchase");
      return;
    }

    const payload: PurchasePayload = {
      buyerProfileId: buyerInfo.buyerProfile.id,
      sellerProfileId: sellerId,
      itemId: product.id,
      quantity: quantity,
      purchaseType: "TOK_TOKEN",
    };

    console.log("Payload for purchase:", payload);

    setIsAlertDialogOpen(true);
    setTransactionTOKCancelled(false);
    setAlertDialogContent("");

    if (transactionControllerRef.current) {
      transactionControllerRef.current.abort();
    }

    const controller = new AbortController();
    const signal = controller.signal;
    transactionControllerRef.current = controller;

    try {
      const transactionID = await initiateTransaction(payload, signal);
      await monitorTransactionStatus(transactionID, signal);
    } catch (error) {
      handleError(error as PurchaseError);
    } finally {
      controller.abort();
    }
  };

  const confirmPurchaseCash = async () => {
    if (!buyerInfo || !product || !sellerId) {
      console.error("Missing required information for purchase");
      return;
    }

    const payload: PurchasePayload = {
      buyerProfileId: buyerInfo.buyerProfile.id,
      sellerProfileId: sellerId,
      itemId: product.id,
      quantity: quantity,
      purchaseType: "CASH",
    };

    console.log("Payload for purchase:", payload);

    setIsCashDialogOpen(true);
    setTransactionCashCancelled(false);
    setCashAlertDialogContent("");

    if (transactionControllerRef.current) {
      transactionControllerRef.current.abort();
    }

    const controller = new AbortController();
    const signal = controller.signal;
    transactionControllerRef.current = controller;

    try {
      await initiateTransaction(payload, signal);
      setCashAlertDialogContent(
        "Purchase successful! Redirecting to home page..."
      );
      setTimeout(() => {
        setIsCashDialogOpen(false);
        router.push("/profile");
      }, 3000);
    } catch (error) {
      handleError(error as PurchaseError);
    } finally {
      controller.abort();
    }
  };

  const initiateTransaction = async (
    payload: PurchasePayload,
    signal: AbortSignal
  ): Promise<string> => {
    const response = await fetch(
      "http://localhost:8080/api/transactions/purchase",
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${accessToken}`,
        },
        body: JSON.stringify(payload),
        signal,
      }
    );

    if (!response.ok) {
      throw new Error("Failed to initiate transaction");
    }

    if (payload.purchaseType === "TOK_TOKEN") {
      const data = await response.json();
      if (!data.id) {
        throw new Error("Transaction ID is null");
      }
      console.log("Transaction initiated:", data);
      return data.id;
    }

    return "";
  };

  const monitorTransactionStatus = async (
    transactionID: string,
    signal: AbortSignal
  ): Promise<void> => {
    const timeout = 5 * 60 * 1000;
    const interval = 5000;
    const startTime = Date.now();

    const checkTransactionStatus = async (): Promise<boolean> => {
      const statusResponse = await fetch(
        `http://localhost:8080/api/transactions/status/${transactionID}`,
        {
          headers: { Authorization: `Bearer ${accessToken}` },
          signal,
        }
      );

      if (!statusResponse.ok) {
        throw new Error("Failed to check transaction status");
      }

      return (await statusResponse.text()).trim() === "true";
    };

    while (Date.now() - startTime < timeout) {
      if (await checkTransactionStatus()) {
        setAlertDialogContent(
          "Purchase successful! Redirecting to home page..."
        );
        setTimeout(() => {
          setIsAlertDialogOpen(false);
          router.push("/profile");
        }, 3000);
        return;
      }

      await new Promise((resolve) => setTimeout(resolve, interval));
    }

    throw new Error("Transaction timed out");
  };

  const handleError = (error: PurchaseError) => {
    if (error.name === "AbortError") {
      console.log("Transaction was cancelled.");
      return;
    }

    console.error("Transaction error:", error);
    setAlertDialogContent(
      error.response
        ? `Transaction failed: ${error.response.statusText}`
        : `Transaction failed: ${error.message}`
    );
    setTimeout(() => setIsAlertDialogOpen(false), 3000);
  };

  const cancelTransaction = () => {
    if (transactionControllerRef.current) {
      transactionControllerRef.current.abort();
    }
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
            onConfirmCash={confirmPurchaseCash}
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
            unitTOKTokenCost={
              product.discountedTokTokenPrice
                ? Math.floor(product.discountedTokTokenPrice)
                : product.tokTokenPrice
            }
            onCancelTransaction={cancelTransaction}
          />
          <CashTransactionAlertDialog
            isOpen={isCashDialogOpen}
            onClose={() => setIsCashDialogOpen(false)}
            alertDialogContent={cashAlertDialogContent}
            onCancelTransaction={() => cancelTransaction}
            product={product}
            quantity={quantity}
          />
        </>
      )}
    </>
  );
}
