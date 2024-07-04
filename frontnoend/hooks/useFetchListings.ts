import { useAuth } from "@/hooks/auth-provider";
import { access } from "fs";
import { list } from "postcss";
import { useEffect, useState } from "react";



type FetchListingsProps = {
    userId: string, 
    accessToken: string,
    // vv these are hardcoded
    toEdit: string | null
    createListing: boolean | "created"
    isDeleted: "deleted" | "normal"
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

export function useFetchListings( {userId, accessToken, toEdit, createListing, isDeleted}: FetchListingsProps) {
    const [listing, setListing] = useState<Listing[]>([])
    useEffect(() => {
        // console.log("accessToken", accessToken)
        console.log(isDeleted)
        if (accessToken == "") {
            return
        }
        if (toEdit != "edited" && createListing != "created" && isDeleted=="normal") { // crazy hardcoded
            console.log("fetching listings")
        
            fetch(`${process.env.NEXT_PUBLIC_BACKEND_URL}/api/items/user/${userId}`, {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${accessToken}`
                }
            }).then(res => res.json())
            .then(res => setListing(res))
        }

    }, [accessToken, toEdit, createListing, isDeleted])

    return listing
}