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

type Product = {
  id: string;
  sellerProfileId: string;
  name: string;
  description: string;
  price: number;
  quantity: number;
  imageUrl: string;
};

export default function Page({ params }) {
  const [product, setProduct] = useState<Product | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<Error | null>(null);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const token =
          "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2Njc1MzBmMmQ2MmFmMTZmNThjNjM3ODkiLCJpYXQiOjE3MTkxNTYwMjUsImV4cCI6MTcxOTI0MjQyNX0.z8QOGDVK26-DZWhMHWZ8vESwClUO7111NOaox0HsjSQ";
        if (!token) {
          throw new Error("No access token available");
        }

        const response = await fetch(
          `http://localhost:8080/api/items/${params.id}`,
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
  }, [params.id]);

  if (loading) {
    return <p>loading...</p>;
  }

  return (
    <>
      <p>details about product {params.id}</p>
      {product && <ProductCardDetails {...product} />}
    </>
  );
}

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
    <div className="w-full sm:w-1/2 mx-auto">
      <Card className="flex overflow-hidden flex-col">
        <div className="relative w-full h-auto aspect-video">
          {imageUrl && (
            <Image src={`data:image/png;base64,${imageUrl}`} fill alt={name} className="object-contain w-full h-full" />
          )}
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
    </div>
  );
}
