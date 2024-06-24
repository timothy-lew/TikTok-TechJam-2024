"use client";

import { useEffect, useState } from "react";
import { Button } from "@/components/ui/button";
import { ArrowRight } from "lucide-react";
import Link from "next/link";
import { Suspense } from "react";
import {
  ProductCardSkeleton,
  ProductCard,
} from "@/components/shop/ProductCard";
import { middleware } from "@/middleware";

type Product = {
  id: string;
  sellerProfileId: string;
  name: string;
  description: string;
  price: number;
  quantity: number;
  imageUrl: string;
};

const ProductGridSection = ({ products }: { products: Product[] }) => {
  return (
    <div className="space-y-4">
      <div className="flex gap-4">
        <h2 className="text-3xl font-bold">Popular Products</h2>
        <Button variant="outline" asChild>
          <Link href="/products" className="space-x-2">
            <span>View More</span>
            <ArrowRight className="size-4" />
          </Link>
        </Button>
      </div>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        
        {products.map((product) => (
          <ProductCard
            key={product.id}
            id={product.id}
            name={product.name}
            priceInCents={product.price}
            description={product.description}
            imagePath={product.imageUrl}
          />
        ))}
      </div>
    </div>
  );
};

const getCookie = (name: string) => {
  if (typeof document === "undefined") {
    return null;
  }

  const value = `; ${document.cookie}`;
  const parts = value.split(`; ${name}=`);

  if (parts.length === 2) {
    return parts.pop()?.split(";").shift() || null;
  }

  return null;
};

const ShopPage = () => {
  const [products, setProducts] = useState<Product[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<Error | null>(null);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const token = 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2Njc1MzBmMmQ2MmFmMTZmNThjNjM3ODkiLCJpYXQiOjE3MTkyMDY2MDUsImV4cCI6MTcxOTI5MzAwNX0.Nb--hvyurb_PfVGUHVcjHJkEli5ptR9HQ0EACkhXwVg'
        const response = await fetch(
          "http://localhost:8080/api/items/user/6673e9fa0913b266363289ab", // currently this is all the items from a particular user, and not the correct one
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
        console.log(data[0])
        setProducts(data);
        setLoading(false);
      } catch (err) {
        setError(err as Error);
        setLoading(false);
      }
    };

    fetchProducts();
  }, []);

  if (loading) {
    return (
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        <ProductCardSkeleton />
        <ProductCardSkeleton />
        <ProductCardSkeleton />
      </div>
    );
  }

  if (error) {
    return <div>Error loading products: {error.message}</div>;
  }

  return (
    <div>
      <ProductGridSection products={products} />
    </div>
  );
};

export default ShopPage;
