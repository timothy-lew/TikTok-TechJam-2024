import { useState } from 'react';

type TopUpWalletProps = {
  accessToken: string,
  userId: string,
  topUpTransactionType: string, // CREDIT_CARD
  topUpAmount: number,
}

export function useTopUpWallet() {
  
  const [success, setSuccess] = useState<boolean | null>(null);
  const [isToppingUp, setIsToppingUp] = useState<boolean>(false);
  const [error, setError] = useState<Error | null>(null);

  const topUpWallet = async ({accessToken, userId, topUpTransactionType, topUpAmount}: TopUpWalletProps) => {
    try {
      setIsToppingUp(true);
      setSuccess(null);
      setError(null);

      const response = await fetch('http://localhost:8080/api/transactions/topup', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${accessToken}`
        },
        body: JSON.stringify({
          userId,
          topUpTransactionType,
          topUpAmount,
        })
      });

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      const result = await response.json();
      setSuccess(true);
    } catch (error) {
      setError(error instanceof Error ? error : new Error('An error occurred'));
      setSuccess(false);
    } finally {
      setIsToppingUp(false);
    }
  };

  return { topUpWallet, success, isToppingUp, error };
}