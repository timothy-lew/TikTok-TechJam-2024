import { useState } from 'react';

type TopUpWalletProps = {
  accessToken: string,
  userId: string,
  topUpTransactionType: string, // CREDIT_CARD
  topUpAmount: number,
  giftCardCode : string,
}

export function useTopUpWallet() {
  
  const [success, setSuccess] = useState<boolean | null>(null);
  const [isToppingUp, setIsToppingUp] = useState<boolean>(false);
  const [error, setError] = useState<Error | null>(null);

  const topUpWallet = async ({accessToken, userId, topUpTransactionType, topUpAmount, giftCardCode}: TopUpWalletProps) => {
    try {
      setIsToppingUp(true);
      setSuccess(null);
      setError(null);
      console.log("Sending to top up API")
      const response = await fetch('http://localhost:8080/api/transactions/topup', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${accessToken}`
        },
        body: JSON.stringify({
          userId,
          topUpType: topUpTransactionType,
          topUpAmount,
          giftCardCode,
        })
      });

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      
      // TODO: handle case where we get a true response from server that gift card is invalid/redeemed
      const result = await response.json();


      setSuccess(true);

      return result;

    } catch (error) {
      setError(error instanceof Error ? error : new Error('An error occurred'));
      setSuccess(false);
      return null;

    } finally {
      setIsToppingUp(false);
    }
  };

  return { topUpWallet, success, isToppingUp, error };
}