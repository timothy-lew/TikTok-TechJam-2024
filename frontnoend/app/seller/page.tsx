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
import { BarChart } from "lucide-react";


const page = () => {
  
    const auth = useAuth();
    const user = auth?.user || null;

    const transactionData = useFetchTransactions(user?.sellerProfile?.id || "", "seller");

    // const [incoming, setIncoming] = useState<number>(0);

    var totalSales = 0
    for (let i = 0; i < transactionData.length; i++) {
        totalSales += transactionData[i].amount;
    }
    
    return (
    <div>
       {/* <Card className="">
          <CardHeader>
            <CardTitle>Create project</CardTitle>
            <CardDescription>Deploy your new project in one-click.</CardDescription>
          </CardHeader>
          <CardContent>
          <BarChart data={data}>
            <Bar
              dataKey="goal"
              style={
                {
                  fill: "hsl(var(--foreground))",
                  opacity: 0.9,
                } as React.CSSProperties
              }
            />
          </BarChart>
          </CardContent>
          <CardFooter className="flex justify-between">
            <Button variant="outline">Cancel</Button>
            <Button>Deploy</Button>
          </CardFooter>
        </Card> */}

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

          <DataTable columns={columns} data={transactionData} />
        </div>

        

    </div>
  )
}

export default page