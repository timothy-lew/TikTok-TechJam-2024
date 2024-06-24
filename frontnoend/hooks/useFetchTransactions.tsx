"use client"

import { useState, useEffect } from 'react';

export type Transaction = {
  id: string
  transactionType: "shopping" | "transfer"
  amount: number
  receiver: string
  type: "incoming" | "outgoing"
  status: "pending" | "processing" | "success" | "failed"
  date: Date
}

// Mock Data
const data: Transaction[] = [
  {
    id: "728ed52f",
    amount: 100,
    type: "incoming",
    receiver: "@tiktokshop1",
    transactionType: "shopping",
    status: "pending",
    date: new Date("2024-06-19"),
  },
  {
    id: "a4f8b2d1",
    amount: 250,
    type: "incoming",
    receiver: "@tiktokshop2",
    transactionType: "shopping",
    status: "success",
    date: new Date("2024-06-18"),
  },
  {
    id: "c4d3a8b9",
    amount: 75,
    type: "incoming",
    receiver: "@tiktokshop3",
    transactionType: "shopping",
    status: "success",
    date: new Date("2024-06-17"),
  },
  {
    id: "d8f2a9c4",
    amount: 150,
    type: "incoming",
    receiver: "@tiktokshop4",
    transactionType: "shopping",
    status: "failed",
    date: new Date("2024-06-16"),
  },
  {
    id: "e9c2d4f3",
    amount: 300,
    type: "incoming",
    receiver: "@tiktokshop5",
    transactionType: "shopping",
    status: "pending",
    date: new Date("2024-06-15"),
  },
  {
    id: "f5b7e6a2",
    amount: 50,
    type: "incoming",
    receiver: "@tiktokshop3",
    transactionType: "shopping",
    status: "success",
    date: new Date("2024-06-14"),
  },
  {
    id: "g3d8f1b7",
    amount: 120,
    type: "incoming",
    receiver: "@johnloh",
    transactionType: "transfer",
    status: "processing",
    date: new Date("2024-06-13"),
  },
  {
    id: "h6e7c3a5",
    amount: 200,
    type: "incoming",
    receiver: "@evelyngoh",
    transactionType: "transfer",
    status: "failed",
    date: new Date("2024-06-12"),
  },
  {
    id: "i7b9f4c2",
    amount: 90,
    type: "outgoing",
    receiver: "@alex54",
    transactionType: "transfer",
    status: "success",
    date: new Date("2024-06-11"),
  },
  {
    id: "j8a1d2e3",
    amount: 60,
    type: "incoming",
    receiver: "@harryCo",
    transactionType: "transfer",
    status: "pending",
    date: new Date("2024-06-10"),
  },
];

export function useFetchTransactions( userId: string){

  const [transactionData, setTransactionData] = useState<Transaction[]>([]);

  useEffect(()=>{

    const fetchTransactions = () => {

      try{
        // const response = await fetch('');
        // const data = await response.json();

        

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