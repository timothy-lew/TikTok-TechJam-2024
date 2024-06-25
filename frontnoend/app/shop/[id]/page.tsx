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
  id: string
  username: string
  email: string
  firstName: string
  lastName: string
  roles: string[]
  buyerProfile: BuyerProfile
  sellerProfile: SellerProfile
  wallet: Wallet
}

type BuyerProfile = {
  id: string
  shippingAddress: string
  billingAddress: string
  defaultPaymentMethod: string
}

type SellerProfile = {
  id: string
  businessName: string
  businessDescription: string
}

type Wallet = {
  id: string
  cashBalance: number
  coinBalance: number
}


export default function Page({ params }) {
  const [product, setProduct] = useState<Product | null>(null);
  const [sellerId, setSellerId] = useState<string|null>(null);
  const [sellerUsername, setSellerUsername] = useState<string|null>(null);
  const [userInfo,setUserInfo] = useState<UserInfo|null> (null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<Error | null>(null);
  const [accessToken, setAccessToken] = useState<string | undefined>(undefined);

  const auth = useAuth();
  const user = auth?.user || null;

  useEffect(() => {
    const getAccessToken = async () => {
      const token = await auth?.obtainAccessToken();
      setAccessToken(token);
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
        setSellerId(data.sellerProfileId)
        console.log("seller id: " + data.sellerProfileId)

        const seller_response = await fetch(
          `http://localhost:8080/api/users/${data.sellerProfileIdtqtq}`,
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
        console.log(sellerData)
        console.log(sellerData.username)
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
      <h1>{sellerUsername}</h1>
      {product && <ProductCardDetails product={product} sellerUsername={sellerUsername} />}
    </>
  );
}

function ProductCardDetails({
  product,
  sellerUsername,
}: {
  product: Product;
  sellerUsername: string | null;
}) {
  const { id, name, description, price, quantity, imageUrl } = product;
  return (
    <div className="w-full sm:w-1/2 mx-auto">
      <Card className="flex overflow-hidden flex-col">
        <div className="relative w-full h-auto aspect-video">
          {imageUrl && (
            <Image src={`data:image/png;base64,${imageUrl}`} fill alt={name} className="object-contain w-full h-full" />
          )}
        </div>
        <CardHeader>
          <CardTitle>{name}</CardTitle>
          <CardDescription>${price} or {Math.round(price*100)} TikTok Coins </CardDescription>
          <CardDescription>Sold by {sellerUsername}</CardDescription>
        </CardHeader>
        <CardContent className="flex-grow">
          <h3 className="line-clamp-4">{description}</h3>
          <p>Quantity available: {quantity}</p>
        </CardContent>
        <CardFooter>
          <Button asChild size="lg" className="w-full">
            <Link href={`/shop/${id}/`}>Buy Now</Link>
          </Button>
        </CardFooter>
      </Card>
    </div>
  );
}

