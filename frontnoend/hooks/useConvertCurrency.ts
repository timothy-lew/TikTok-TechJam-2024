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

  const convertCurrency = async ({accessToken, userId, cashToConvert, tokTokenToConvert, conversionType}: ConvertCurrencyProps) => {

    setIsConverting(true);
    setSuccess(false);
    setError(null);

    console.log('Inside convert Currency with');
    console.log(`Access Token: ${accessToken}`);
    console.log(`userId: ${userId}`);
    console.log(`cashToConvert: ${cashToConvert}`);
    console.log(`tokTokenToConvert: ${tokTokenToConvert}`);
    console.log(`conversionType: ${conversionType}`);


    try{

      const response = await fetch('http://localhost:8080/api/transactions/conversion', {
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

      // const result = {
      //   "id": "667e5c946a4555794aea0a22",
      //   "transactionType": "CONVERSION",
      //   "transactionDate": "2024-06-28T14:47:48.681908",
      //   "userId": "667c1b41d2687e76c2bfca09",
      //   "purchaseDetails": null,
      //   "topUpDetails": null,
      //   "conversionDetails": {
      //       "conversionRate": 0.1,
      //       "cashToConvert": null,
      //       "tokTokenToConvert": 2000.0,
      //       "convertedAmount": 200.0,
      //       "conversionType": "TOKTOKEN_TO_CASH"
      //   },
      //   "withdrawDetails": null
      //  }

       if (conversionType==="CASH_TO_TOKTOKEN"){
        return {
          cashConverted: Number(result.conversionDetails.cashToConvert) * -1,
          coinsConverted: Number(result.conversionDetails.convertedAmount)
        }
       }
   
       else{
        return {
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
      cashConverted: 0,
      coinsConverted: 0
    };

  }
  return { convertCurrency, success, isConverting, error };

}