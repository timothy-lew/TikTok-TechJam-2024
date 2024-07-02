import { useAuth } from "@/hooks/auth-provider";
import { access } from "fs";
import { useEffect, useState } from "react";



type FetchListingProps = {
    userId: string,
    accessToken: string,
}

export type Listing = {
    id: string
    sellerProfileId: string
    name: string
    description: string
    price: number
    tokTokenPrice: number
    quantity: number
    imageUrl: string
    businessName: string
    sellerWalletAddress: string
}

export function useFetchListing( {userId, accessToken}: FetchListingProps) {
    const [listing, setListing] = useState<Listing[]>([])
    useEffect(() => {
        console.log("accessToken", accessToken)
        fetch(`http://localhost:8080/api/items/user/${userId}`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${accessToken}`
            }
        }).then(res => res.json())
        .then(res => setListing(res))

    }, [accessToken])

    return listing
}