"use client"

import { useFetchListing } from "@/hooks/useFetchListings"
import { useAuth } from "@/hooks/auth-provider";
import { use, useEffect, useState } from "react";
import { DataTableDemo } from "./table";


const page = () => {

    const auth = useAuth();
    // const user = auth?.user || null;
    // const accessToken = await auth?.obtainAccessToken() || "";
    // const accessToken = await auth?.obtainAccessToken() || "";
    const [accessToken, setAccessToken] = useState<string>("")

    useEffect(() => {
        const fetchAccessToken = () => {
            auth?.obtainAccessToken().then((res) => {
                setAccessToken(res||"none")
            })
        }
        fetchAccessToken()
    }, [])
      

    const listing = useFetchListing({userId: "66813c84f8bd211375579165", accessToken})

    // const listing = "hellp"

    // listing.map((item) => {
    //   item.imageUrl = "Dummy"
    //   return item
    // })

    return (
    <div>
        <DataTableDemo
          data={listing}
        />
    </div>
  )
}

export default page