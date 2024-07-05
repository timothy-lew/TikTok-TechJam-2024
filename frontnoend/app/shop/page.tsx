"use client";
import { getBackendUrl } from "@/lib/utils";

import { useEffect, useState } from "react";
import { Button } from "@/components/ui/button";
import { ArrowRight } from "lucide-react";
import Link from "next/link";
import {
  ProductCardSkeleton,
  ProductCard,
} from "@/components/shop/ProductCards";
import { useAuth } from "@/hooks/auth-provider";
import { Product } from "@/types/ShopTypes";
import TikTokLoader from "@/components/shared/TiktokLoader";
import TiktokLoader2 from "@/components/shared/UnavailableComponent";

const ProductGridSection = ({ products }: { products: Product[] }) => {
  return (
    <div className="space-y-4">
      <div className="flex gap-4">
        <h2 className="text-3xl font-bold">Recommended Products</h2>
        <Button variant="outline" className="hover:bg-red-50 hover:text-black" asChild>
          <Link href="/shop" className="space-x-2">
            <span>View More</span>
            <ArrowRight className="size-4" />
          </Link>
        </Button>
      </div>
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
        {products.map((product) => (
          <ProductCard product={product} />
        ))}
      </div>
    </div>
  );
};

const ShopPage = () => {
  const [products, setProducts] = useState<Product[]>([]);
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
    if (!accessToken) return;

    const fetchProducts = async () => {
      try {
        const response = await fetch(`${getBackendUrl()}/api/items`, {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
        });

        if (!response.ok) {
          throw new Error("Network response was not ok");
        }

        const data = await response.json();
        setProducts(data);
        setLoading(false);
      } catch (err) {
        setError(err as Error);
        setLoading(false);
      }
    };

    fetchProducts();
  }, [accessToken]);

  if (loading) {
    return (
      <div className="flex_col_center w-full gap-6">
        <TikTokLoader />
        <TiktokLoader2 />

        <div className="w-full grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
          <ProductCardSkeleton />
          <ProductCardSkeleton />
          <ProductCardSkeleton />
          <ProductCardSkeleton />
          <ProductCardSkeleton />
          <ProductCardSkeleton />
          <ProductCardSkeleton />
          <ProductCardSkeleton />
          <ProductCardSkeleton />
        </div>
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
