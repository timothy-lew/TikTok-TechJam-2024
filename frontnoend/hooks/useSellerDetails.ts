import { getBackendUrl } from "@/lib/utils"
import { use, useEffect, useState } from "react"


type SellerDetails = {
    id: string;
    businessName: string;
    businessDescription: string;
}


const useSellerDetails = (sellerId: string | null, accessToken: string) => {
    const [sellerDetails, setSellerDetails] = useState<SellerDetails | null>(null);



    useEffect(() => {
        if (sellerId === null || !accessToken) {
            return;
        }
        fetch(`${getBackendUrl()}/api/profiles/seller/${sellerId}`
            , {
                headers: {
                    Authorization: `Bearer ${accessToken}`,
                }
            }
            )
            .then((response) => response.json())
            .then((data) => {
                setSellerDetails(data);
            });
    }
    , [sellerId, accessToken])

    return sellerDetails;
}

export default useSellerDetails;