"use client";

import { Button } from "@/components/ui/button";
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import Link from "next/link";
import { useEffect, useState } from "react";
import Image from "next/image";

export default function Page({ params }) {
  const [product, setProduct] = useState<Product | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<Error | null>(null);
  useEffect(() => {
    const fetchProducts = async () => {
      try {
        //const token = middleware.request.cookies.get('accessToken')?.value;
        const token =
          "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2Njc1MzBmMmQ2MmFmMTZmNThjNjM3ODkiLCJpYXQiOjE3MTkxNTYwMjUsImV4cCI6MTcxOTI0MjQyNX0.z8QOGDVK26-DZWhMHWZ8vESwClUO7111NOaox0HsjSQ";
        if (!token) {
          throw new Error("No access token available");
        }

        const response = await fetch(
          "http://localhost:8080/api/items/" + params.id,
          {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          }
        );

        if (!response.ok) {
          throw new Error("Network response was not ok");
        }

        const data = await response.json();
        console.log(data);
        setProduct(data);
        setLoading(false);
      } catch (err) {
        setError(err as Error);
        setLoading(false);
      }
    };

    fetchProducts();
  }, []);

  {
    if (loading) {
      return <p>loading...</p>;
    }
  }
  return (
    <>
      <p>details about product {params.id}</p>
      <ProductCardDetails {...product}></ProductCardDetails>
    </>
  );
}

type Product = {
  id: string;
  sellerProfileId: string;
  name: string;
  description: string;
  price: number;
  quantity: number;
  imageUrl: string;
};

export function ProductCardDetails({
  id,
  sellerProfileId,
  name,
  description,
  price,
  quantity,
  imageUrl,
}: Product) {
  return (
    <Card className="flex overflow-hidden flex-col">
      <div className="relative w-full h-1/2">
        {" "}
        {/* Adjust the height as needed */}
        <Image src={imageUrl} layout="fill" objectFit="cover" alt={name} />
      </div>
      <CardHeader>
        <CardTitle>{name}</CardTitle>
        <CardDescription>${price}</CardDescription>
        <CardDescription>Sold by {sellerProfileId}</CardDescription>
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
  );
}
