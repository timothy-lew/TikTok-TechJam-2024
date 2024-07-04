"use client"

import { useFetchListings } from "@/hooks/useFetchListings"
import { useAuth } from "@/hooks/auth-provider";
import { use, useEffect, useState } from "react";
import { DataTableDemo } from "./table";
import EditListing from "./EditListing";
import { Button } from "@/components/ui/button";
import NewListing from "./NewListing";
import EditListing2 from "./EditListing2";


const page = () => {

    const auth = useAuth();
    // const user = auth?.user || null;
    // const accessToken = await auth?.obtainAccessToken() || "";
    // const accessToken = await auth?.obtainAccessToken() || "";
    const [accessToken, setAccessToken] = useState<string>("")
    const [toEdit, setToEdit] = useState<string|null|"edited">(null)
    const [isDelete, setIsDelete] = useState<"deleted"| "normal">("normal")

    useEffect(() => {
        const fetchAccessToken = () => {
            auth?.obtainAccessToken().then((res) => {
                setAccessToken(res||"none")
            })
        }
        fetchAccessToken()
    }, [])

    useEffect(() => {
        if (toEdit == "edited") {
            setTimeout(() => {
                setToEdit(null)
            }, 1000)
        }
    }, [toEdit])
      
    const [createListing, setCreateListing] = useState<boolean|"created">(false)

    useEffect(() => {
        if (createListing == "created") {
            setTimeout(() => {
                setCreateListing(false)
            }, 1000)
        }
    }, [createListing])

    useEffect(() => {
        console.log(isDelete)
        if (isDelete == "deleted") {
            setTimeout(() => {
                setIsDelete("normal")
            }, 1000)
        }
    }, [isDelete])

    const listing = useFetchListings({userId: "66813c84f8bd211375579165", accessToken, toEdit, createListing, isDeleted: isDelete})

    return (
    <div>
        <Button className="my-4" onClick={() => setCreateListing(true)}>Create Listing</Button>
        <DataTableDemo
          data={listing}
          setToEdit={setToEdit}
          setIsDelete={setIsDelete}
        />
        {/* {toEdit && <EditListing itemId={toEdit} setEditListing={setToEdit}/>} */}
        {toEdit && toEdit!="edited" && <EditListing2 itemId={toEdit} setEditListing={setToEdit}/>}
        {createListing && createListing!="created" && <NewListing setCreateListing={setCreateListing} accessToken={accessToken}/>}
    </div>
  )
}

export default page