// "use client"

// import { useState, useEffect } from 'react';

// export type Wallet = {
//   id: string
//   fiatAmount: number
//   currency: string
//   tiktokCoins: number
// }

// export function useFetchWallet( userId: string){

//   const [walletData, setWalletData] = useState<Wallet | null>(null);

//   useEffect(()=>{

//     const fetchWalletData = async () => {

//       try{
//         // const response = await fetch('');
//         // const data = await response.json();

//         // Mock Data
//         const data: Wallet = {
//           id: "",
//           fiatAmount: 1000.78,
//           currency: "SGD",
//           tiktokCoins: 5000
//         }

//         setWalletData(data);
//       }
//       catch(e){
//         console.log(e);
//       }

//     }

//     fetchWalletData();
//   }, [userId])

//   return { walletData, setWalletData};
// }
