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
import { redirect } from "next/navigation";
import { useRouter } from "next/navigation";
import { ProductDetailsSkeleton } from "@/components/shop/ProductCard";

type Product = {
  id: string;
  sellerProfileId: string;
  name: string;
  description: string;
  price: number;
  quantity: number;
  imageUrl: string;
};

type UserInfo = {
  id: string;
  username: string;
  email: string;
  firstName: string;
  lastName: string;
  roles: string[];
  buyerProfile: BuyerProfile;
  sellerProfile: SellerProfile;
  wallet: Wallet;
};

type BuyerProfile = {
  id: string;
  shippingAddress: string;
  billingAddress: string;
  defaultPaymentMethod: string;
};

type SellerProfile = {
  id: string;
  businessName: string;
  businessDescription: string;
};

type Wallet = {
  id: string;
  cashBalance: number;
  coinBalance: number;
};

export default function Page({ params }) {
  const [product, setProduct] = useState<Product | null>(null);
  const [sellerId, setSellerId] = useState<string | null>(null);
  const [sellerUsername, setSellerUsername] = useState<string | null>(null);
  const [userInfo, setUserInfo] = useState<UserInfo | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<Error | null>(null);
  const [accessToken, setAccessToken] = useState<string | undefined>(undefined);

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [quantity, setQuantity] = useState(1);

  const openModal = () => setIsModalOpen(true);
  const closeModal = () => setIsModalOpen(false);

  const [isAlertDialogOpen, setIsAlertDialogOpen] = useState(false);
  const [alertDialogContent, setAlertDialogContent] = useState("");
  const [countdown, setCountdown] = useState(300);
  const router = useRouter();

  const confirmPurchaseTiktokCoin = async () => {
    if (!userInfo || !product || !sellerId) {
      console.error("Missing required information for purchase");
      return;
    }

    const payload = {
      buyerProfileId: userInfo.buyerProfile.id,
      sellerProfileId: sellerId,
      itemId: product.id,
      quantity: quantity,
      purchaseType: "TOK_TOKEN",
    };

    console.log("Payload for purchase:", payload);

    setIsAlertDialogOpen(true); // Open the AlertDialog to show the address and countdown

    // Display the address and countdown for 5 seconds before starting the purchase
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
        }, 5000);
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
        const transformedUserInfo: UserInfo = {
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
          wallet: user.wallet || { id: "", cashBalance: 0, coinBalance: 0 },
        };
        setUserInfo(transformedUserInfo);
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
        console.log("seller id: " + data.sellerProfileId);

        const seller_response = await fetch(
          `http://localhost:8080/api/users/${data.sellerProfileId}`,
          {
            headers: {
              Authorization: `Bearer ${accessToken}`,
            },
          }
        );
        if (!seller_response.ok) {
          throw new Error("Failed to fetch seller details");
        }

        const sellerData = await seller_response.json();
        console.log(sellerData);
        console.log(sellerData.username);
        setSellerUsername(sellerData.username);
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
      <h1>{sellerUsername}</h1>
      {product && (
        <ProductCardDetails
          product={product}
          sellerUsername={sellerUsername}
          openModal={openModal}
        />
      )}
      {isModalOpen && product && (
        <>
          <Modal
            product={product}
            quantity={quantity}
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
                      <p className="font-medium">
                        Seller Wallet Address: {product?.sellerProfileId}
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
                <AlertDialogCancel onClick={onCloseAlert}>
                  Close
                </AlertDialogCancel>
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
  sellerUsername,
  openModal,
}: {
  product: Product;
  sellerUsername: string | null;
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
            ${price} or {Math.round(price * 100)} TikTok Coins{" "}
          </CardDescription>
          <CardDescription>Sold by {product?.sellerProfileId}</CardDescription>
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
  setQuantity: (quantity: number) => void;
  onClose: () => void;
  onConfirmTiktokCoin: () => void;
  shippingAddress: string;
};

const Modal = ({
  product,
  quantity,
  setQuantity,
  onClose,
  onConfirmTiktokCoin,
  shippingAddress,
}: ModalProps) => {
  const handleQuantityChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setQuantity(parseInt(event.target.value, 10));
  };

  const increaseQuantity = () => {
    setQuantity((prevQuantity) => Math.min(prevQuantity + 1, product.quantity));
  };

  const decreaseQuantity = () => {
    setQuantity((prevQuantity) => Math.max(prevQuantity - 1, 1));
  };

  return (
    <div className="fixed inset-0 z-10 overflow-y-auto flex items-center justify-center bg-gray-500 bg-opacity-75">
      <div className="relative bg-white rounded-lg shadow-xl w-full max-w-lg mx-auto p-6">
        <div className="flex items-center justify-center mb-4">
          <div className="flex-shrink-0 flex items-center justify-center h-16 w-16 rounded-full bg-blue-100">
            <MdOutlineShoppingCart className="h-8 w-8 text-black-600" />
          </div>
        </div>
        <h3 className="text-2xl leading-6 font-medium text-gray-900 text-center mb-4">
          Confirm Purchase
        </h3>
        <div className="text-sm px-4 py-3 text-gray-700 space-y-2">
          <p className="font-medium">Please review your purchase details:</p>
          <p>Seller: {product.sellerProfileId}</p>
          <p>Product: {product.name}</p>
          <p>
            Price: ${(product.price * quantity).toFixed(2)} or{" "}
            {Math.round(product.price * 100 * quantity)} TikTok Coins
          </p>
          <div className="flex items-center space-x-2">
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
          <p>Shipping Address: {shippingAddress}</p>
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
