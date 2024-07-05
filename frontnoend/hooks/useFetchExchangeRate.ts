import { useAuth } from "./auth-provider";
import { useState, useEffect } from "react";
import { getBackendUrl } from "@/lib/utils";

export function useFetchExchangeRate() {
  const auth = useAuth();

  const [exchangeRate, setExchangeRate] = useState<number | null>(null);

  const getCashToTokenExchangeRate = async () => {
    const accessToken = await auth?.obtainAccessToken();
    try {
      const response = await fetch(
        `${getBackendUrl()}/api/rates/conversion/current`,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${accessToken}`,
          },
        }
      );

      const data = await response.json();

      setExchangeRate(Number(data.rate));
    } catch (error) {
      console.log("Error in fetching exchange rate");
    }
  };

  useEffect(() => {
    getCashToTokenExchangeRate();
  }, []);

  return { exchangeRate };
}
