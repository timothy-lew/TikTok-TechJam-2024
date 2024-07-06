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


const useFetchShopItem = ({itemId} : {itemId: string}) => {
  
  const [loading, setLoading] = useState(true);
  const [shopItem, setShopItem] = useState<ShopItem | null>(null);


  const auth = useAuth();

  useEffect(()=>{

    const fetchShopItem = async () => {
      try{
        setLoading(true);
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
      finally{
        setLoading(false);
      }

    }

    fetchShopItem();
  })


  return { loading, shopItem }
}

export { useFetchShopItem }
