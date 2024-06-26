"use client"

import {useEffect, useState} from 'react';
import { useAuth } from "@/hooks/auth-provider"


// const TestPage = () => {
//   const auth = useAuth();
//   const user = auth?.user || null;

//   const [accessToken, setAccessToken] = useState<undefined | string>(undefined);
//   const [refreshToken, setRefreshToken] = useState<undefined | string>(undefined);

//   useEffect(() => {
    
//     const getAccessToken = async () => {
//       const accessToken = await auth?.obtainAccessToken();

//       setAccessToken(accessToken);
//     }

//     getAccessToken();
    
//   }, [user])
  


//   return (
//     <div className="w-full flex_col_center gap-4">
//       <p>
//         Email: {user?.email}
//       </p>

//       <p>
//         Access Token: {accessToken}
//       </p>

//     </div>
//   )
// }

// export default TestPage

import { useTopUpWallet } from '@/hooks/useTopUpWallet';

function WalletTopUp() {
  const { topUpWallet, success, isToppingUp, error } = useTopUpWallet();
  const [amount, setAmount] = useState<number>(0);

  const handleTopUp = async () => {
    await topUpWallet({
      accessToken: 'your-access-token', // You'd typically get this from your auth context or state
      userId: 'user-id', // Similarly, this would usually come from your user context or state
      topUpTransactionType: 'CREDIT_CARD',
      topUpAmount: amount
    });
  };

  return (
    <div>
      <h2>Top Up Your Wallet</h2>
      <input
        type="number"
        value={amount}
        onChange={(e) => setAmount(Number(e.target.value))}
        placeholder="Enter amount"
        className="bg-slate-300 px-2 py-2"
      />
      <button onClick={handleTopUp} disabled={isToppingUp} className={`${isToppingUp ? 'bg-yellow-400' : 'bg-green-600'} px-2 py-2 rounded-lg`}>
        {isToppingUp ? 'Processing...' : 'Top Up'}
      </button>

      {isToppingUp && <p>Topping up your wallet...</p>}

      {success && <p style={{ color: 'green' }}>Top up successful!</p>}

      {error && (
        <p style={{ color: 'red' }}>
          Error occurred: {error.message}
        </p>
      )}
    </div>
  );
}

export default WalletTopUp;