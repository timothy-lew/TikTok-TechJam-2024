"use client"

import { access } from 'fs';
import { useState } from 'react';

type ConvertCurrencyProps = {
  accessToken: string,
  userId: string,
  cashToConvert: number | null,
  tokTokenToConvert: number | null,
  conversionType: "CASH_TO_TOKTOKEN" | "TOKTOKEN_TO_CASH"
}

export function useConvertCurrency() {

  const [success, setSuccess] = useState<boolean>(false);
  const [isConverting, setIsConverting] = useState<boolean>(false);
  const [error, setError] = useState<Error | null>(null);
  
  // no poll, polling done on caller
  const checkTransactionStatus = async ({transactionID, accessToken}: {transactionID: string, accessToken: string}): Promise<boolean> => {
    try {
      const response = await fetch(`${process.env.NEXT_PUBLIC_BACKEND_URL}/api/transactions/status/${transactionID}`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${accessToken}`
        }
      });

      const result = await response.json();
      console.log("received transaction status:", result);

      return result;
    } catch (error) {
      console.log("Error in check status", error);
      return false;
    }
  };

  // old implementation - polling done here
  // const checkTransactionStatus = async ({transactionID, accessToken} : {transactionID: string, accessToken: string}) => {

  //   const pollInterval = 5000 // poll every 5 seconds
  //   const maxAttempts = 66 // window is 5 minutes long, added 30 more seconds of buffer

  //   return new Promise((resolve)=>{
  //     let attempts = 0;
  
  //     const poll = () => {
  //       setTimeout(async () => {
  //         try {
  //           const response = await fetch(`${process.env.NEXT_PUBLIC_BACKEND_URL}/api/transactions/status/${transactionID}`, {
  //             method: 'GET',
  //             headers: {
  //               'Content-Type': 'application/json',
  //               'Authorization': `Bearer ${accessToken}`
  //             }
  //           });
      
  //           const result = await response.json(); // result is simply a boolean
      
  //           console.log("received transaction status:", result);
  
  //           if (result){
  //             console.log("transaction successful");
  //             resolve(true);
  //           }
  //           else if (attempts < maxAttempts){
  //             attempts ++;
  //             poll();
  //           }
  //           else{
  //             console.log("waitied for 5mins");
  //             resolve(false);
  //           }
            
  //         }
  //         catch(error){
  //           console.log("Error in check status")
  //           resolve(false);
  //         }
  //       })
  //     }
  
  //     poll();
  //   })


  // }

  const convertCurrency = async ({accessToken, userId, cashToConvert, tokTokenToConvert, conversionType}: ConvertCurrencyProps) => {

    setIsConverting(true);
    setSuccess(false);
    setError(null);

    // console.log(`Exchange in mode: ${conversionType}`);
    // console.log(`userId: ${userId}`);
    // console.log(`cashToConvert: ${cashToConvert}`);
    // console.log(`tokTokenToConvert: ${tokTokenToConvert}`);

    try{

      const response = await fetch(`${process.env.NEXT_PUBLIC_BACKEND_URL}/api/transactions/conversion`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${accessToken}`
        },
        body: JSON.stringify({
          userId,
          cashToConvert,
          tokTokenToConvert,
          conversionType
        })
      });
      
      const result = await response.json();

      console.log(result);


       if (conversionType==="CASH_TO_TOKTOKEN"){
        return {
          status: "success",
          transactionID: result.id,
          cashConverted: Number(result.conversionDetails.cashToConvert) * -1,
          coinsConverted: Number(result.conversionDetails.convertedAmount)
        }
       }
   
       else{
          return {
            status: "success",
            transactionID: result.id,
            cashConverted: Number(result.conversionDetails.convertedAmount),
            coinsConverted: Number(result.conversionDetails.tokTokenToConvert) * -1
          }
        }

    }
    catch(error){
      setError(error instanceof Error ? error : new Error('An error occurred'));
      setSuccess(false);
    }
    finally{
      setIsConverting(false);
    }

    return {
      status: "failed",
      cashConverted: 0,
      coinsConverted: 0
    };

  }
  return { convertCurrency, checkTransactionStatus, success, isConverting, error };

}