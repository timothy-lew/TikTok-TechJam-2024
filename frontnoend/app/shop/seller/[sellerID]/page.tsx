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

const ProductGridSection = ({ products }: { products: Product[] }) => {
  return (
    <div className="space-y-4">
      <div className="flex gap-4">
        <h2 className="text-3xl font-bold">
          Products from {products[0].businessName}
        </h2>
        <Button variant="outline" asChild>
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

interface PageProps {
  params: {
    sellerID: string;
  };
}

const SellerShopPage = ({ params }: PageProps) => {
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
        const response = await fetch(
          `${getBackendUrl()}/api/items/seller/${params.sellerID}`,
          {
            headers: {
              Authorization: `Bearer ${accessToken}`,
            },
          }
        );

        if (!response.ok) {
          throw new Error("Network response was not ok");
        }

        const data = await response.json();
        console.log(data);
        setProducts(data);
        setLoading(false);
      } catch (err) {
        setError(err as Error);
        setLoading(false);
      }
    };

    fetchProducts();
  }, [params.sellerID, accessToken]);

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

export default SellerShopPage;
