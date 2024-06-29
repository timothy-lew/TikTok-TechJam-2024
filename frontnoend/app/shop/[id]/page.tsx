"use client";
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
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
} from "@/components/ui/alert-dialog";
import Link from "next/link";
import { Button } from "@/components/ui/button";
import { useEffect, useState } from "react";
import Image from "next/image";
import { useAuth } from "@/hooks/auth-provider";
import { MdOutlineShoppingCart } from "react-icons/md";
import { ArrowLeft } from "lucide-react";
import { useRouter } from "next/navigation";
import { ProductDetailsSkeleton } from "@/components/shop/ProductCard";
import { WalletAddressBar } from "@/components/shop/WalletAddressBar";
import { BuyerInfo, Product } from "@/types/ShopTypes";

interface PageProps {
  params: {
    id: string;
  };
}

export default function ProductDetailsPage({ params }: PageProps) {
  const [product, setProduct] = useState<Product | null>(null);
  const [sellerId, setSellerId] = useState<string | null>(null);
  const [sellerBusinessName, setBusinessName] = useState<string | null>(null);
  const [sellerWalletAddress, setSellerWalletAddress] = useState<string | null>(
    null
  );
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
  const [countdown, setCountdown] = useState(300);
  const router = useRouter();

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

    setTimeout(async () => {
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
        console.log("Purchase successful:", data);
        setAlertDialogContent(
          "Purchase successful! Redirecting to home page..."
        );
      } catch (error) {
        console.error("Error during purchase:", error);
        setAlertDialogContent("Error during purchase. Please try again.");
      } finally {
        setTimeout(() => {
          setIsAlertDialogOpen(false); // Close the alert dialog
          closeModal();
          console.log("redirecting to homepage...");
          router.push("/shop");
        }, 3000); // 3 second delay to show redirect to shop home
      }
    }, 10000); // 10 seconds delay before making the purchase API call
  };

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
          // wallet: user.wallet || { id: "", cashBalance: 0, coinBalance: 0 }, - OLD
          wallet: user.wallet || null,
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
        setSellerWalletAddress(data.sellerWalletAddress);
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

  useEffect(() => {
    if (isAlertDialogOpen) {
      const timer = setInterval(() => {
        setCountdown((prevCountdown) => {
          if (prevCountdown <= 0) {
            clearInterval(timer);
            onCloseAlert();
            return 0;
          }
          return prevCountdown - 1;
        });
      }, 1000);

      return () => clearInterval(timer);
    }
  }, [isAlertDialogOpen]);

  const onCloseAlert = () => {
    setIsAlertDialogOpen(false);
    closeModal();
    router.push("/shop");
  };

  const formatTime = (time: number) => {
    const minutes = Math.floor(time / 60);
    const seconds = time % 60;
    return `${minutes}:${seconds < 10 ? "0" : ""}${seconds}`;
  };

  if (loading) {
    return <ProductDetailsSkeleton></ProductDetailsSkeleton>;
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
          <Modal
            product={product}
            quantity={quantity}
            buyer={buyerInfo}
            setQuantity={setQuantity}
            onClose={closeModal}
            onConfirmTiktokCoin={confirmPurchaseTiktokCoin}
            shippingAddress={user?.buyerProfile?.shippingAddress || ""}
          />
          <AlertDialog
            open={isAlertDialogOpen}
            onOpenChange={setIsAlertDialogOpen}
          >
            <AlertDialogContent>
              <AlertDialogHeader>
                <AlertDialogTitle>Transaction Status</AlertDialogTitle>
                <AlertDialogDescription>
                  {alertDialogContent}
                  {alertDialogContent === "" && (
                    <>
                      <p>
                        Please make your payment of <strong>
                           {product.tokTokenPrice * quantity} TikTok Coins
                        </strong> to the following 
                      </p>
                      <p>
                        Seller's Wallet Address:
                        <WalletAddressBar
                          wallet_address={product?.sellerWalletAddress}
                        ></WalletAddressBar>
                      </p>
                      <p className="font-medium text-red-600">
                        Time left to complete the transaction:{" "}
                        {formatTime(countdown)}
                      </p>
                    </>
                  )}
                </AlertDialogDescription>
              </AlertDialogHeader>
              <AlertDialogFooter>
                {alertDialogContent === "" && (
                  <AlertDialogAction
                    onClick={confirmPurchaseTiktokCoin}
                    className="bg-red-500 hover:bg-red-600 text-white"
                  >
                    Confirm Purchase
                  </AlertDialogAction>
                )}
                {/* <AlertDialogCancel onClick={onCloseAlert}>
                  Cancel
                </AlertDialogCancel> */}
              </AlertDialogFooter>
            </AlertDialogContent>
          </AlertDialog>
        </>
      )}
    </>
  );
}

function ProductCardDetails({
  product,
  sellerBusinessName,
  openModal,
}: {
  product: Product;
  sellerBusinessName: string | null;
  openModal: () => void;
}) {
  const { id, name, description, price, quantity, imageUrl } = product;
  return (
    <div className="w-full sm:w-1/2 mx-auto">
      <Card className="flex overflow-hidden flex-col">
        <div className="relative w-full h-auto aspect-video">
          {imageUrl && (
            <Image
              src={`data:image/png;base64,${imageUrl}`}
              fill
              alt={name}
              className="object-contain w-full h-full"
            />
          )}
        </div>
        <CardHeader>
          <CardTitle>{name}</CardTitle>
          <CardDescription>
            ${price} or {product.tokTokenPrice} TikTok Coins
          </CardDescription>
          <CardDescription>
            Sold by{" "}
            <Link
              className="underline"
              href={`/shop/seller/${product.sellerProfileId}`}
            >
              {sellerBusinessName}
            </Link>
          </CardDescription>
        </CardHeader>
        <CardContent className="flex-grow">
          <h3 className="line-clamp-4">{description}</h3>
          <p>Quantity available: {quantity}</p>
        </CardContent>
        <CardFooter>
          <Button
            onClick={openModal}
            size="lg"
            className="w-full bg-red-500 hover:bg-red-600"
          >
            Buy Now
          </Button>
        </CardFooter>
      </Card>
    </div>
  );
}
type ModalProps = {
  product: Product;
  quantity: number;
  buyer: BuyerInfo;
  setQuantity: (quantity: number) => void;
  onClose: () => void;
  onConfirmTiktokCoin: () => void;
  shippingAddress: string;
};

const Modal = ({
  product,
  quantity,
  buyer,
  setQuantity,
  onClose,
  onConfirmTiktokCoin,
  shippingAddress,
}: ModalProps) => {
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
        <div className="text-sm px-4 py-3 text-gray-700 space-y-2">
          <p className="font-semibold">Please review your purchase details:</p>
          <p className="p-1">Seller: {product.businessName}</p>
          <p className="p-1">Seller ID: {product.sellerProfileId}</p>
          <p className="p-1">Product: {product.name}</p>
          <p className="p-1">
            Recipient: {buyer.firstName + " " + buyer.lastName}
          </p>
          <p className="p-1">Shipping Address: {shippingAddress}</p>
          <p className="p-1 ">
            Price: ${(product.price * quantity).toFixed(2)} or{" "}
            {product.tokTokenPrice * quantity} TikTok Coins
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
            Buy with TikTok Coin
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
