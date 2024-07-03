"use client"

import { useFetchListings } from "@/hooks/useFetchListings"
import { useAuth } from "@/hooks/auth-provider";
import { use, useEffect, useState } from "react";
import { DataTableDemo } from "./table";
import EditListing from "./EditListing";
import { Button } from "@/components/ui/button";
import NewListing from "./NewListing";


const page = () => {

    const auth = useAuth();
    // const user = auth?.user || null;
    // const accessToken = await auth?.obtainAccessToken() || "";
    // const accessToken = await auth?.obtainAccessToken() || "";
    const [accessToken, setAccessToken] = useState<string>("")
    const [toEdit, setToEdit] = useState<string|null>(null)

    useEffect(() => {
        const fetchAccessToken = () => {
            auth?.obtainAccessToken().then((res) => {
                setAccessToken(res||"none")
            })
        }
        fetchAccessToken()
    }, [])
      
    const [createListing, setCreateListing] = useState<boolean>(false)

    const listing = useFetchListings({userId: "66813c84f8bd211375579165", accessToken})

    // const listing = "hellp"

    // listing.map((item) => {
    //   item.imageUrl = "Dummy"
    //   return item
    // })

    return (
    <div>
        <Button className="my-4" onClick={() => setCreateListing(true)}>Create Listing</Button>
        <DataTableDemo
          data={listing}
          setToEdit={setToEdit}
        />
        {toEdit && <EditListing itemId={toEdit} setEditListing={setToEdit}/>}
        {createListing && <NewListing setCreateListing={setCreateListing}/>}
    </div>
  )
}

export default page