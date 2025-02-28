"use client";
import { getBackendUrl } from "@/lib/utils";

import { useState, useEffect } from "react";
import { useAuth } from "@/hooks/auth-provider";

export type TransactionResponse = {
  id: string;
  transactionType: "PURCHASE" | "CONVERSION" | "TOPUP" | "WITHDRAW";
  transactionDate: string;
  userId: string;
  purchaseDetails: PurchaseDetails | null;
  topUpDetails: TopUpDetails | null;
  conversionDetails: ConversionDetails | null;
  withdrawDetails: withdrawDetails | null;
};

export type Transaction = TransactionResponse & {
  amount: number;
  desc: string;
};

export type PurchaseDetails = {
  buyerProfileId: string;
  buyerUserName: string;
  sellerProfileId: string;
  sellerBusinessName: string;
  itemId: string;
  quantity: number;
  purchaseAmount: number;
  purchaseType: "TOK_TOKEN" | "CASH";
};

export type TopUpDetails = {
  topUpType: "GIFT_CARD" | "CREDIT_CARD";
  topUpAmount: number;
};

export type withdrawDetails = {
  withdrawAmount: number;
};

export type ConversionDetails = {
  conversionRate: number;
  cashToConvert: number | null;
  tokTokenToConvert: number | null;
  convertedAmount: number;
  cashBalance: number;
  coinBalance: number;
  conversionType: "CASH_TO_TOKTOKEN" | "TOKTOKEN_TO_CASH";
};



export function useFetchTransactions(
  id: string,
  buyerSeller: "buyer" | "seller"
) {
  const auth = useAuth();
  const [transactionData, setTransactionData] = useState<Transaction[]>([]);
  const [loadingTransactionData, setLoadingTransactionData] = useState<boolean>(true);

  useEffect(() => {
    const fetchTransactions = async () => {
      setLoadingTransactionData(true);
      const accessToken = await auth?.obtainAccessToken();

      try {
        const response = await fetch(
          `${getBackendUrl()}/api/transactions/${buyerSeller}/${id}`,
          {
            method: "GET",
            headers: {
              "Content-Type": "application/json",
              Authorization: `Bearer ${accessToken}`,
            },
          }
        );

        const data: TransactionResponse[] = await response.json();

        console.log("Received TransactionResponse:");
        console.log(data);

        const finalData = data.map((transaction) => {
          let amount;
          let desc = "";
          const transactionType = transaction.transactionType;
          switch (transactionType) {
            case "PURCHASE":
              amount = transaction.purchaseDetails?.purchaseType === "CASH" ? transaction.purchaseDetails.purchaseAmount : Math.floor(transaction.purchaseDetails?.purchaseAmount || 0);
              desc = `Purchased of ${
                transaction.purchaseDetails?.purchaseAmount
              } ${
                transaction.purchaseDetails?.purchaseType === "CASH"
                  ? "SGD"
                  : "Tok Coins"
              } from ${transaction.purchaseDetails?.sellerBusinessName}`;
              break;
            case "CONVERSION":
              amount = transaction.conversionDetails?.cashBalance || 0;
              if (
                transaction.conversionDetails?.conversionType ===
                "CASH_TO_TOKTOKEN"
              )
                desc = `Converted SGD${transaction.conversionDetails?.convertedAmount} to ${transaction.conversionDetails?.convertedAmount} Tok Coins`;
              else
                desc = `Converted ${transaction.conversionDetails?.convertedAmount || "weird"} Tok Coins to SGD${transaction.conversionDetails?.convertedAmount || "weird"}`;
              break;
            case "TOPUP":
              amount = transaction.topUpDetails?.topUpAmount || 0;
              desc = `Topped up SGD${amount} with ${transaction.topUpDetails?.topUpType==='GIFT_CARD' ? 'gift card' : 'credit card'}`;
              break;
            case "WITHDRAW":
              amount = transaction.withdrawDetails?.withdrawAmount || 0;
          }

          return {
            ...transaction,
            amount,
            desc,
          };
        });

        setTransactionData(finalData);
        setLoadingTransactionData(false);
      } catch (e) {
        console.log(e);
        setLoadingTransactionData(false);
      }
    };

    fetchTransactions();
  }, []);

  return {transactionData, loadingTransactionData};
}
