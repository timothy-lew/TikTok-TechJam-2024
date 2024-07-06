"use client";
import { getBackendUrl } from "@/lib/utils";

import { access } from "fs";
import { useState } from "react";

type ConvertCurrencyProps = {
  accessToken: string;
  userId: string;
  cashToConvert: number | null;
  tokTokenToConvert: number | null;
  conversionType: "CASH_TO_TOKTOKEN" | "TOKTOKEN_TO_CASH";
};

export function useConvertCurrency() {
  const [success, setSuccess] = useState<boolean>(false);
  const [isConverting, setIsConverting] = useState<boolean>(false);
  const [error, setError] = useState<Error | null>(null);

  // no poll, polling done on caller
  const checkTransactionStatus = async ({
    transactionID,
    accessToken,
  }: {
    transactionID: string;
    accessToken: string;
  }): Promise<boolean> => {
    try {
      const response = await fetch(
        `${getBackendUrl()}/api/transactions/status/${transactionID}`,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${accessToken}`,
          },
        }
      );

      const result = await response.json();
      console.log("received transaction status:", result);

      return result;
    } catch (error) {
      console.log("Error in check status", error);
      return false;
    }
  };

  const convertCurrency = async ({
    accessToken,
    userId,
    cashToConvert,
    tokTokenToConvert,
    conversionType,
  }: ConvertCurrencyProps) => {
    setIsConverting(true);
    setSuccess(false);
    setError(null);

    // console.log(`Exchange in mode: ${conversionType}`);
    // console.log(`userId: ${userId}`);
    // console.log(`cashToConvert: ${cashToConvert}`);
    // console.log(`tokTokenToConvert: ${tokTokenToConvert}`);

    try {
      const response = await fetch(
        `${getBackendUrl()}/api/transactions/conversion`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
            Authorization: `Bearer ${accessToken}`,
          },
          body: JSON.stringify({
            userId,
            cashToConvert,
            tokTokenToConvert,
            conversionType,
          }),
        }
      );

      const result = await response.json();

      console.log(result);

      if (conversionType === "CASH_TO_TOKTOKEN") {
        return {
          status: "success",
          transactionID: result.id,
          cashConverted: Number(result.conversionDetails.cashToConvert) * -1,
          coinsConverted: Number(result.conversionDetails.convertedAmount),
        };
      } else {
        return {
          status: "success",
          transactionID: result.id,
          cashConverted: Number(result.conversionDetails.convertedAmount),
          coinsConverted:
            Number(result.conversionDetails.tokTokenToConvert) * -1,
        };
      }
    } catch (error) {
      setError(error instanceof Error ? error : new Error("An error occurred"));
      setSuccess(false);
    } finally {
      setIsConverting(false);
    }

    return {
      status: "failed",
      cashConverted: 0,
      coinsConverted: 0,
    };
  };
  return {
    convertCurrency,
    checkTransactionStatus,
    success,
    isConverting,
    error,
  };
}
