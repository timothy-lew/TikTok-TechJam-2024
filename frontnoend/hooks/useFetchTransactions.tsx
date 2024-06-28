"use client"

import { useState, useEffect } from 'react';



export type TransactionResponse = {
  id: string,
  transactionType: 'PURCHASE' | 'CONVERSION' | 'TOPUP',
  transactionDate: String,
  userId: string,
  purchaseDetails: PurchaseDetails | null,
  topUpDetails: TopUpDetails | null,
  ConversionDetails: ConversionDetails | null,
}

export type PurchaseDetails = {
  buyerProfileId: string,
  buyerUserName: string,
  sellerProfileId: string,
  sellerBusinessName: string,
  itemId: string,
  quantity: number,
  purchaseAmount: number,
  purchaseType: 'TOK_TOKEN' | 'CASH',
}


export type TopUpDetails = {
  topUpType: 'GIFT_CARD' | 'CREDIT_CARD',
  topUpAmount: number,
}

export type ConversionDetails = {
  conversionRate: number,
  cashBalance: number,
  coinBalance: number,
  conversionType: 'CASH_TO_TOKTOKEN' | 'TOKTOKEN_TO_CASH',
}

export type Transaction = TransactionResponse & {
  amount: number,
  desc: string,
};

// Mock Data
const mockData: TransactionResponse[] = [
  {
    id: "t1",
    transactionType: "PURCHASE",
    transactionDate: "2024-06-20T10:30:00Z",
    userId: "user123",
    purchaseDetails: {
      buyerProfileId: "buyer456",
      buyerUserName: "JohnDoe",
      sellerProfileId: "seller789",
      sellerBusinessName: "TechGadgets",
      itemId: "item001",
      quantity: 2,
      purchaseAmount: 100,
      purchaseType: "TOK_TOKEN",
    },
    topUpDetails: null,
    ConversionDetails: null,
  },
  {
    id: "t2",
    transactionType: "TOPUP",
    transactionDate: "2024-06-19T15:45:00Z",
    userId: "user123",
    purchaseDetails: null,
    topUpDetails: {
      topUpType: "CREDIT_CARD",
      topUpAmount: 100,
    },
    ConversionDetails: null,
  },
  {
    id: "t3",
    transactionType: "CONVERSION",
    transactionDate: "2024-06-18T09:00:00Z",
    userId: "user123",
    purchaseDetails: null,
    topUpDetails: null,
    ConversionDetails: {
      conversionRate: 100,
      cashBalance: 50,
      coinBalance: 5000,
      conversionType: "CASH_TO_TOKTOKEN",
    },
  },
  {
    id: "t4",
    transactionType: "PURCHASE",
    transactionDate: "2024-06-17T14:20:00Z",
    userId: "user123",
    purchaseDetails: {
      buyerProfileId: "buyer456",
      buyerUserName: "JohnDoe",
      sellerProfileId: "seller101",
      sellerBusinessName: "FashionStore",
      itemId: "item002",
      quantity: 1,
      purchaseAmount: 75,
      purchaseType: "CASH",
    },
    topUpDetails: null,
    ConversionDetails: null,
  },
  {
    id: "t5",
    transactionType: "TOPUP",
    transactionDate: "2024-06-16T11:10:00Z",
    userId: "user123",
    purchaseDetails: null,
    topUpDetails: {
      topUpType: "GIFT_CARD",
      topUpAmount: 50,
    },
    ConversionDetails: null,
  },
  {
    id: "t6",
    transactionType: "CONVERSION",
    transactionDate: "2024-06-15T16:30:00Z",
    userId: "user123",
    purchaseDetails: null,
    topUpDetails: null,
    ConversionDetails: {
      conversionRate: 0.01,
      cashBalance: 100,
      coinBalance: 9000,
      conversionType: "TOKTOKEN_TO_CASH",
    },
  },
  {
    id: "t7",
    transactionType: "PURCHASE",
    transactionDate: "2024-06-14T13:45:00Z",
    userId: "user123",
    purchaseDetails: {
      buyerProfileId: "buyer456",
      buyerUserName: "JohnDoe",
      sellerProfileId: "seller202",
      sellerBusinessName: "HomeDecor",
      itemId: "item003",
      quantity: 3,
      purchaseAmount: 150,
      purchaseType: "TOK_TOKEN",
    },
    topUpDetails: null,
    ConversionDetails: null,
  },
  {
    id: "t8",
    transactionType: "TOPUP",
    transactionDate: "2024-06-13T10:00:00Z",
    userId: "user123",
    purchaseDetails: null,
    topUpDetails: {
      topUpType: "CREDIT_CARD",
      topUpAmount: 200,
    },
    ConversionDetails: null,
  },
  {
    id: "t9",
    transactionType: "CONVERSION",
    transactionDate: "2024-06-12T17:20:00Z",
    userId: "user123",
    purchaseDetails: null,
    topUpDetails: null,
    ConversionDetails: {
      conversionRate: 100,
      cashBalance: 150,
      coinBalance: 15000,
      conversionType: "CASH_TO_TOKTOKEN",
    },
  },
  {
    id: "t10",
    transactionType: "PURCHASE",
    transactionDate: "2024-06-11T12:30:00Z",
    userId: "user123",
    purchaseDetails: {
      buyerProfileId: "buyer456",
      buyerUserName: "JohnDoe",
      sellerProfileId: "seller303",
      sellerBusinessName: "ElectronicsHub",
      itemId: "item004",
      quantity: 1,
      purchaseAmount: 200,
      purchaseType: "CASH",
    },
    topUpDetails: null,
    ConversionDetails: null,
  },
];

export function useFetchTransactions( userId: string){

  const [transactionData, setTransactionData] = useState<Transaction[]>([]);

  useEffect(()=>{

    const fetchTransactions = () => {

      try{
        // const response = await fetch('');
        // const data = await response.json();

        const data = mockData.map((transaction)=>{
          let amount;
          let desc = "";
          const transactionType = transaction.transactionType;
          switch(transactionType){
            case 'PURCHASE':
              amount = transaction.purchaseDetails?.purchaseAmount || 0;
              desc = `Purchased of ${transaction.purchaseDetails?.purchaseAmount} ${transaction.purchaseDetails?.purchaseType==='CASH' ? 'cash' : 'tokcoins'} from ${transaction.purchaseDetails?.sellerBusinessName}`
              break;
            case 'CONVERSION':
              amount = transaction.ConversionDetails?.cashBalance || 0;
              if (transaction.ConversionDetails?.conversionType==="CASH_TO_TOKTOKEN")
                desc = `Converted SGD${transaction.ConversionDetails?.cashBalance} to ${transaction.ConversionDetails?.coinBalance} tokcoins`
              else
                desc = `Converted ${transaction.ConversionDetails?.coinBalance} tokcoins to SGD${transaction.ConversionDetails?.cashBalance}`
              break;
            case 'TOPUP':
              amount = transaction.topUpDetails?.topUpAmount || 0;
              desc = `Topped up SGD${amount}`;
              break;
          }
          
          return({
          ...transaction,
          amount,
          desc
        })})

        setTransactionData(data);
      }
      catch(e){
        console.log(e);
      }

    }

    fetchTransactions();
  }, [])

  return transactionData;
}