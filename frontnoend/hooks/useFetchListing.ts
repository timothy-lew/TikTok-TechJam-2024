
import { useEffect, useState } from "react";



type FetchListingProps = {
    itemId: string,
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

export function useFetchListing( {itemId, accessToken}: FetchListingProps) {
    const [listing, setListing] = useState<Listing|null>(null)
    useEffect(() => {
        if (!accessToken) {
            return
        }
        fetch(`${process.env.NEXT_PUBLIC_BACKEND_URL}/api/items/${itemId}`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${accessToken}`
            }
        }).then(res => res.json())
        .then(res => setListing(res))

    }, [accessToken])

    return listing
}