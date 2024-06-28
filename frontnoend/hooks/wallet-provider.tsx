"use client"
import { ReactNode } from "react";

import { useState, useContext, createContext, useEffect } from 'react';

import { Wallet, useFetchWallet } from '@/hooks/useFetchWallet';

interface WalletContextType {
  walletData : Wallet | null;
  setWalletData : React.Dispatch<React.SetStateAction<Wallet | null>>;
}

const WalletContext = createContext<WalletContextType | undefined>(undefined);

export const WalletProvider = ( {children, userId} : {children: ReactNode, userId: string}) => {

  const [walletData, setWalletData] = useState<Wallet | null>(null);
  const {walletData: fetchWalletData, setWalletData: setFetchedWalletData } = useFetchWallet(userId);


  useEffect(()=>{

    if (fetchWalletData) setWalletData(fetchWalletData);

  }, [fetchWalletData])

  return(
    <WalletContext.Provider value = {{walletData, setWalletData}}>
      {children}
    </WalletContext.Provider>
  )
}

export const useWallet = () => {
  const context = useContext(WalletContext);
  if (context === undefined) {
    throw new Error('useWallet must be used within a WalletProvider');
  }
  return context;
};