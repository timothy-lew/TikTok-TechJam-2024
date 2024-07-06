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
import { Bar, BarChart, LabelList, Legend, ResponsiveContainer, Text, XAxis, YAxis } from "recharts"



const page = () => {
  
    const auth = useAuth();
    const user = auth?.user || null;

    const transactionData = useFetchTransactions(user?.sellerProfile?.id || "", "seller");

    var totalSales = 0
    for (let i = 0; i < transactionData.length; i++) {
        totalSales += transactionData[i].amount;
    }

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

    const NUMBER_DAYS = 7;
    const NUMBER_MONTHS = 5;
    const NUMBER_YEARS = 2;

    // append clean data with dummy transection NUMBER_DAYS days back
    // append clean data with dummy transection NUMBER_MONTHS months back
    // append clean data with dummy transection NUMBER_YEARS years back
    for (let i = 0; i <= NUMBER_DAYS; i++) {
        cleanDataDate.push({
            amount: 0,
            transactionDate: new Date(new Date().setDate(new Date().getDate() - i))
        })
    }

    for (let i = 0; i <= NUMBER_MONTHS; i++) {
        cleanDataDate.push({
            amount: 0,
            transactionDate: new Date(new Date().setMonth(new Date().getMonth() - i))
        })
    }

    for (let i = 0; i <= NUMBER_YEARS; i++) {
        cleanDataDate.push({
            amount: 0,
            transactionDate: new Date(new Date().setFullYear(new Date().getFullYear() - i))
        })
    }

    //bug in date logic

    type groupByType = "day" | "month" | "year";
    const [groupBy, setGroupBy] = useState<groupByType>("day");
    // if day, show 10 days, if month, show 5 months, if year, show 1 year
    const numberToShow = groupBy === "day" ? NUMBER_DAYS : groupBy === "month" ? NUMBER_MONTHS : NUMBER_YEARS;

    cleanDataDate = cleanDataDate.sort((a, b) => {
        return a.transactionDate.getTime() - b.transactionDate.getTime();
    })

    // console.log(cleanDataDate);

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
            dummyDate: value?.[0].transactionDate,
            total: value?.reduce((acc, item) => acc + item.amount, 0) ?? 0
        }
    })

    // data2 = data2.sort((a, b) => {
    //     return a.date.localeCompare(b.date);
    // })

    // reduce to only 5 data
    data2 = data2.reverse().slice(0, numberToShow).reverse();

      
    return (
    <div>

        <div className="flex flex-col justify-between items-center mt-2">
          <div className="mb-2">
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
          <p className="text-2xl sm:text-3xl text-center font-semibold text-gray-800">Total Sales</p>
          <ResponsiveContainer height={400}>
            <BarChart data={data2}
                height={400}
                margin={{ top: 30, right: 30, left: 20, bottom: 20 }}
              >
                <Bar
                  dataKey="total"
                  style={
                    {
                      fill: "hsl(var(--foreground))",
                      opacity: 0.9,
                    } as React.CSSProperties
                  }
                >
                  <LabelList dataKey="total" position="top" formatter={(value: number) => {
                    if (value === 0) {
                      return ""
                    }
                    return `$${value.toFixed(2)}`
                  }} />
                </Bar>
                <XAxis dataKey="date" />
                <YAxis />
              </BarChart>
          </ResponsiveContainer>
        </div>

        {/* Transaction Summary Section */}
        <div className="bg-white rounded-xl p-4 sm:p-6 shadow-md w-full border border-tiktok-red">
          <h2 className="text-tiktok-red text-xl sm:text-2xl md:text-3xl font-bold mb-4 text-center">
            Your Sales
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
          <DataTable columns={columns} data={transactionData}/>
        </div>

        

    </div>
  )
}

export default page