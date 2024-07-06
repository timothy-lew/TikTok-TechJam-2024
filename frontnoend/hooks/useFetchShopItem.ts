// useFetchShopItem.ts
import { useState, useEffect } from "react";
import { useAuth } from "@/hooks/auth-provider";
import { getBackendUrl } from "@/lib/utils";

import { type Product } from "@/types/ShopTypes";

export function useFetchShopItem(itemId: string | undefined) {
  const [shopItem, setShopItem] = useState<Product | null>(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<Error | null>(null);
  const auth = useAuth();

  useEffect(() => {
    async function fetchItem() {
      if (!itemId) {
        setLoading(false);
        return;
      }

      try {
        const accessToken = await auth?.obtainAccessToken();
        const response = await fetch(`${getBackendUrl()}/api/items/${itemId}`, {
          method: 'GET',
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${accessToken}`,
          },
        });

        if (!response.ok) {
          throw new Error('Failed to fetch item');
        }

        const data = await response.json();
        setShopItem(data);
      } catch (err) {
        setError(err instanceof Error ? err : new Error('An error occurred'));
      } finally {
        setLoading(false);
      }
    }

    fetchItem();
  }, [itemId, auth]);

  return { shopItem, loading, error };
}