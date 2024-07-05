"use client"


import { useEffect, useState } from "react"
import { Button } from "@/components/ui/button"

import { redirect } from 'next/navigation'
import Image from "next/image";

import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select"
import { useAuth } from "@/hooks/auth-provider";
// import { SellerTransection, useFetchSellerTransactions } from "@/hooks/useFetchSellerTransections";
import { columns } from "./transactionsSeller";
import { DataTable } from "@/components/ui/data-table";
import { useFetchTransactions } from "@/hooks/useFetchTransactions";
import { Bar, BarChart, Legend, ResponsiveContainer, XAxis, YAxis } from "recharts"



const page = () => {
  
    const auth = useAuth();
    const user = auth?.user || null;

    const transactionData = useFetchTransactions(user?.sellerProfile?.id || "", "seller");

    // const [incoming, setIncoming] = useState<number>(0);

    var totalSales = 0
    for (let i = 0; i < transactionData.length; i++) {
        totalSales += transactionData[i].amount;
    }

  //   {
  //     "id": "6687c7644e98e30ca88f37bb",
  //     "transactionType": "PURCHASE",
  //     "transactionDate": "2024-07-05T18:13:56.454",
  //     "userId": "null",
  //     "purchaseDetails": {
  //         "buyerProfileId": "66813c7ef8bd211375579161",
  //         "buyerUserName": "buyer",
  //         "sellerProfileId": "66813c81f8bd211375579164",
  //         "sellerBusinessName": "Vintage Closet",
  //         "itemId": "667c2015fe08ec33cf32e736",
  //         "quantity": 1,
  //         "purchaseAmount": 19.9,
  //         "purchaseType": "CASH"
  //     },
  //     "topUpDetails": null,
  //     "conversionDetails": null,
  //     "withdrawDetails": null
  // },

    // const getYear = (date: string) => {
    //   // "2024-07-05T18:13:56.454" to "2024"
    //   return date.split("-")[0];
    // }

    // const getMonth = (date: string) => {
    //   // "2024-07-05T18:13:56.454" to "07"
    //   return date.split("-")[1];
    // }

    const stringToDate = (date: String) => {
      // "2024-07-05T18:13:56.454" to Date
      return new Date(date.toString());
    }




    var cleanDataDate = transactionData.map((item) => {
        return {
            amount: item.amount,
            transactionDate: stringToDate(item.transactionDate)
        }
    })

    const now = new Date();

    // append clean data with dummy transection 10 days back and 5 monthes back
    for (let i = 0; i < 10; i++) {
        cleanDataDate.push({
            amount: 0,
            transactionDate: new Date(now.setDate(now.getDate() - i))
        })
    }

    for (let i = 0; i < 5; i++) {
        cleanDataDate.push({
            amount: 0,
            transactionDate: new Date(now.setMonth(now.getMonth() - i))
        })
    }

    //bug in date logic

    type groupByType = "day" | "month" | "year";
    const [groupBy, setGroupBy] = useState<groupByType>("month");

    cleanDataDate = cleanDataDate.sort((a, b) => {
        return a.transactionDate.getTime() - b.transactionDate.getTime();
    })

    const data = Object.groupBy(cleanDataDate, (item) => {
        if (groupBy === "day") {
            return item.transactionDate.toLocaleDateString();
        } else if (groupBy === "month") {
            return (item.transactionDate.getFullYear().toString()+"/"+item.transactionDate.getMonth().toString());
        } else {
            return item.transactionDate.getFullYear().toString();
        }
    })

    var data2 = Object.entries(data).map(([key, value]) => {
        return {
            date: key,
            goal: value?.reduce((acc, item) => acc + item.amount, 0) ?? 0
        }
    })

    data2 = data2.sort((a, b) => {
        return a.date.localeCompare(b.date);
    })

    // reduce to only 5 data
    data2 = data2.reverse().slice(0, 5).reverse();


    // // reduce to only 5 data
    // data2 = data2.slice(0, 5);
    
    
    return (
    <div>

        <div className="flex flex-col justify-between items-center">
          <div className="mb-6">
            <div className="flex rounded-md overflow-hidden border border-tiktok-red">
              <button
                onClick={() => setGroupBy("day")}
                className={`flex-1 py-2 px-4 ${
                  groupBy === "day"
                    ? "bg-tiktok-red text-white"
                    : "bg-card text-tiktok-red"
                } transition duration-300`}
              >
                Day
              </button>
              <button
                onClick={() => setGroupBy("month")}
                className={`flex-1 py-2 px-4 ${
                  groupBy === "month"
                    ? "bg-tiktok-red text-white"
                    : "bg-card text-tiktok-red"
                } transition duration-300`}
              >
                Month
              </button>
              <button
                onClick={() => setGroupBy("year")}
                className={`flex-1 py-2 px-4 ${
                  groupBy === "year"
                    ? "bg-tiktok-red text-white"
                    : "bg-card text-tiktok-red"
                } transition duration-300`}
              >
                Year
              </button>
            </div>
          </div>
          <p className="text-2xl sm:text-3xl text-center font-semibold text-gray-800">Total Sales: ${totalSales.toFixed(2)}</p>
          <ResponsiveContainer width={600} height={400}>
            <BarChart data={data2}
                width={600}
                height={400}
                margin={{ top: 5, right: 30, left: 20, bottom: 5 }}
              >
                <Bar
                  dataKey="goal"
                  style={
                    {
                      fill: "hsl(var(--foreground))",
                      opacity: 0.9,
                    } as React.CSSProperties
                  }
                />
                <XAxis dataKey="date" />
                <YAxis />
                <Legend />
              </BarChart>
          </ResponsiveContainer>
        </div>

        {/* Transaction Summary Section */}
        <div className="bg-white rounded-xl p-4 sm:p-6 shadow-md w-full border border-tiktok-red">
          <h2 className="text-tiktok-red text-xl sm:text-2xl md:text-3xl font-bold mb-4 text-center">
            Your Sales help
          </h2>
          <div className="flex justify-around items-center">
            <div className="flex flex-col items-center">
              <div className="flex items-center gap-4">
                <Image
                  src="/icons/incomingTransactions.svg"
                  alt="icon"
                  height={48}
                  width={48}
                />
                <h2 className="font-bold text-lg sm:text-xl md:text-3xl text-green-600">
                  Total Sales:
                </h2>
              </div>
              <p className="text-2xl sm:text-3xl text-center font-semibold text-gray-800">+${totalSales.toFixed(2)}</p>
            </div>
            {/* <div className="flex flex-col items-center">
              <div className="flex items-center gap-4">
                <Image
                  src="/icons/outgoingTransactions.svg"
                  alt="icon"
                  height={48}
                  width={48}
                />
                <h2 className="font-bold text-lg sm:text-xl md:text-3xl text-tiktok-red">
                  Outgoing:
                </h2>
              </div>
              <p className="text-2xl sm:text-3xl text-center font-semibold text-gray-800">-${outgoing.toFixed(2)}</p>
            </div> */}
          </div>

          <DataTable columns={columns} data={transactionData} />
        </div>

        

    </div>
  )
}

export default page