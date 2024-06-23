"use client"

import { useState, useEffect } from 'react';

export type TiktokCardDetails = {
  number: string[]; // length of 4
  cardName: string;
  cvc: string;
  expiryDate: string;
}

export function useFetchTiktokCard( userId: string){

  const [tiktokCardDetails, setTiktokCardDetails] = useState<TiktokCardDetails>();

  useEffect(()=>{

    const fetchCard = async () => {

      try{
        // const response = await fetch('');
        // const data = await response.json();

        // Mock Data
        const cardDetails : TiktokCardDetails = {
          number: ["1234", "5678", "9101", "1121"],
          cardName: "John Doe",
          cvc: "123",
          expiryDate: "12/24",
        };

        setTiktokCardDetails(cardDetails);
      }
      catch(e){
        console.log(e);
      }

    }

    fetchCard();
  }, [])

  return tiktokCardDetails;
}