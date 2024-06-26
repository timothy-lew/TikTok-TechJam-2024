"use client";
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import Link from "next/link";
import { Button } from "@/components/ui/button";
import { useEffect, useState } from "react";
import Image from "next/image";
import { useAuth } from "@/hooks/auth-provider";
import { MdOutlineShoppingCart } from "react-icons/md";
import { ArrowLeft } from "lucide-react";

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
  const confirmPurchaseTiktokCoin = async () => {
    if (!userInfo || !product || !sellerId) {
      console.error('Missing required information for purchase');
      return;
    }
  
    const payload = {
      buyerProfileId: userInfo.buyerProfile.id,
      sellerProfileId: sellerId,
      itemId: product.id,
      quantity: quantity,
      purchaseType: "TOK_TOKEN"
    };
  
    console.log("Payload for purchase:", payload);
  
    try {
      const response = await fetch('http://localhost:8080/api/transactions/purchase', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${accessToken}`, // Include access token if needed
        },
        body: JSON.stringify(payload),
      });
  
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
  
      const data = await response.json();
      console.log('Purchase successful:', data);
    } catch (error) {
      console.error('Error during purchase:', error);
      
    }
    closeModal();
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

  if (loading) {
    return <p>loading...</p>;
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
        <Modal
          product={product}
          quantity={quantity}
          setQuantity={setQuantity}
          onClose={closeModal}
          onConfirmTiktokCoin={confirmPurchaseTiktokCoin}
          shippingAddress={user?.buyerProfile?.shippingAddress || ""}
        />
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
          <CardDescription>Sold by {sellerUsername}</CardDescription>
        </CardHeader>
        <CardContent className="flex-grow">
          <h3 className="line-clamp-4">{description}</h3>
          <p>Quantity available: {quantity}</p>
        </CardContent>
        <CardFooter>
          <Button onClick={openModal} size="lg" className="w-full">
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
    setQuantity((prevQuantity: number) =>
      Math.min(prevQuantity + 1, product.quantity)
    );
  };

  const decreaseQuantity = () => {
    setQuantity((prevQuantity: number) => Math.max(prevQuantity - 1, 1));
  };

  return (
    <div className="fixed inset-0 z-10 overflow-y-auto">
      <div className="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity"></div>
      <div className="flex items-center justify-center min-h-screen px-4 text-center sm:block sm:p-0">
        <div className="relative inline-block transform bg-white rounded-lg text-left shadow-xl transition-all sm:my-8 sm:align-middle sm:max-w-lg w-full">
          <div className="bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4">
            <div className="sm:flex sm:items-start">
              <div className="mx-auto flex-shrink-0 flex items-center justify-center h-12 w-12 rounded-full bg-blue-100 sm:mx-0 sm:h-10 sm:w-10">
                <MdOutlineShoppingCart></MdOutlineShoppingCart>
              </div>
              <div className="mt-3 text-center sm:mt-0 sm:ml-4 sm:text-left">
                <h3
                  className="text-lg leading-6 font-medium text-gray-900"
                  id="modal-title"
                >
                  Confirm Purchase
                </h3>
                <div className="mt-2">
                  <p className="text-sm font-semibold text-gray-500">
                    Review your purchase details:
                  </p>
                  <br></br>
                  <p className="text-sm text-gray-500">
                    Seller: {product.sellerProfileId}
                  </p>
                  <p className="text-sm text-gray-500">
                    Product: {product.name}
                  </p>
                  <p className="text-sm text-gray-500">
                    Price: ${product.price * quantity} or{" "}
                    {Math.round(product.price * 100 * quantity)} TikTok Coins
                  </p>
                  <p className="text-sm text-gray-500">
                    Quantity:
                    <button
                      onClick={decreaseQuantity}
                      className="ml-2 px-2 border rounded"
                    >
                      -
                    </button>
                    <input
                      type="number"
                      min="1"
                      max={product.quantity}
                      value={quantity}
                      onChange={handleQuantityChange}
                      className="mx-2 border rounded"
                      style={{ width: "50px", textAlign: "center" }}
                    />
                    <button
                      onClick={increaseQuantity}
                      className="px-2 border rounded"
                    >
                      +
                    </button>
                  </p>
                  <p className="text-sm text-gray-500">
                    Shipping Address: {shippingAddress}
                  </p>
                </div>
              </div>
            </div>
          </div>
          <div className="bg-gray-50 px-4 py-3 sm:px-6 sm:flex sm:flex-row-reverse">
            <button
              type="button"
              className="w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-blue-600 text-base font-medium text-white hover:bg-blue-700 sm:ml-3 sm:w-auto sm:text-sm"
            >
              Buy with Credit Card
            </button>
            <button
              type="button"
              className="w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-blue-600 text-base font-medium text-white hover:bg-blue-700 sm:ml-3 sm:w-auto sm:text-sm"
              onClick={onConfirmTiktokCoin}
            >
              Buy with TikTok Coins
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
    </div>
  );
};
