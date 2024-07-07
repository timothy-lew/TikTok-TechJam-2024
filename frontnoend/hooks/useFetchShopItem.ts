import { getBackendUrl } from "@/lib/utils"
import { useState, useEffect } from "react"
import { useAuth } from "@/hooks/auth-provider";

// not the full attribute received but what is needed only
interface ShopItem {
  id: string,
  sellerProfileId: string,
  name: string,
  descruption: string,
  price: number,
  tokTokenPrice: number,
  imageUrl: string,
  businessName: string,
}


const fetchShopItem = async (itemId: string) => {
  try{
    const auth = useAuth();

    const accessToken = await auth?.obtainAccessToken();

    const response = await fetch(`${getBackendUrl}/api/items${itemId}`,{
      method: 'GET',
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${accessToken}`,
      },
    })

    const data : ShopItem = await response.json();

    console.log(data);
  }
  catch(error){
    console.log(error);
  }

}




export { fetchShopItem }
