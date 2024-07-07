"use client"

import { useAuth } from "@/hooks/auth-provider";
import useSellerDetails from "@/hooks/useSellerDetails";
import { useEffect, useState } from "react";
import Image from "next/image";
import { Button } from "@/components/ui/button";
import { set } from "date-fns";
import { getBackendUrl } from "@/lib/utils";


const Page = () => {
    const auth = useAuth();

    const [accessToken, setAccessToken] = useState<string>("");

    useEffect(() => {
        const fetchAccessToken = () => {
        auth?.obtainAccessToken().then((res) => {
            setAccessToken(res || "none");
        });
        };
        fetchAccessToken();
    }, []);

    const user = auth?.user || null;

    const [editName, setEditName] = useState<"edit" | "saving" | "saved">("saved");
    const [editDescription, setEditDescription] = useState<"edit" | "saving" | "saved">("saved");

    const saveNameHandler = (name: string) => {
        const formData = {
            businessName: name,
            businessDescription: user?.sellerProfile?.businessDescription || undefined,
        }

        setEditName("saving");
        fetch(`${getBackendUrl()}/api/profiles/seller/${user?.id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${accessToken}`,
            },
            body: JSON.stringify(formData),
        })    
    }

    const saveDescriptionHandler = (description: string) => {
        console.log("description", description)
        const formData = {
            businessName: user?.sellerProfile?.businessName || undefined,
            businessDescription: description,
        }

        setEditDescription("saving");
        fetch(`${getBackendUrl()}/api/profiles/seller/${user?.id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${accessToken}`,
            },
            body: JSON.stringify(formData),
        })    
    }

    // const sellerDetails = user?.sellerProfile;
    const [sellerDetails, setSellerDetails] = useState<{businessName: string, businessDescription:string} | null>(null);

    useEffect(() => {
        if (user?.sellerProfile == null){
            return;
        } else {
            setSellerDetails(user?.sellerProfile);
        }
    }, [user])

    useEffect(() => {
        if (editName == "saving" || editDescription == "saving") {
            setTimeout(() => {
                setEditName("saved")
                setEditDescription("saved")
                fetch(`${getBackendUrl()}/api/profiles/seller/${user?.id}`, {
                    headers: {
                        Authorization: `Bearer ${accessToken}`,
                    }
                }).then((response) => response.json())
                .then((data) => {
                    setSellerDetails(data);
                });
            }, 1000)
        }
    }, [editName, editDescription])


    return (
        <div>
            {sellerDetails? (
                <div className="bg-white shadow-md w-full flex justify-center items-start border border-slate-200 rounded-xl p-4 sm:p-6">
                    <div className="w-full flex_col_center gap-4">
                        <div className="w-full flex flex-col justify-center items-center gap-4">
                            {(editName == "saved" || editName == "saving") ? <h2 className="font-bold flex flex-row text-lg sm:text-xl md:text-3xl text-tiktok-red">
                                {sellerDetails.businessName}
                                <button className="ml-4">
                                    <Image src="/icons/edit.svg" alt="edit" width={24} height={24} onClick={
                                        () => {
                                            setEditName("edit");
                                        }
                                    } />
                                </button>
                            </h2>:
                            <form className="flex flex-row gap-4"
                                onSubmit={
                                    (e: any) => {
                                        e.preventDefault();
                                        console.log(e)
                                        saveNameHandler(e.target.name.value);
                                    }
                                }
                            >
                                <input
                                    name="name"
                                    type="text"
                                    className="border border-gray-300 p-2 rounded-lg"
                                    defaultValue={sellerDetails.businessName}
                                />
                                <Button>Save</Button>
                            </form>}
                            
                            {/* <p className="text-l text-center font-semibold text-gray-800">
                                {sellerDetails.businessDescription}
                                <button className="ml-4">
                                    <Image src="/icons/edit.svg" alt="edit" width={20} height={20} onClick={
                                        () => {
                                            alert("Edit seller details")
                                        }
                                    } />
                                </button>
                            </p> */}
                            {
                                editDescription == "saved" || editDescription == "saving" ? <p className="text-l text-center font-semibold text-gray-800">
                                    {sellerDetails.businessDescription}
                                    <button className="ml-4">
                                        <Image src="/icons/edit.svg" alt="edit" width={20} height={20} onClick={
                                            () => {
                                                setEditDescription("edit");
                                            }
                                        } />
                                    </button>
                                </p>: <form className="flex flex-row gap-4 w-3/5 h-24"
                                    onSubmit={
                                        (e: any) => {
                                            e.preventDefault();
                                            console.log(e)
                                            saveDescriptionHandler(e.target.description.value);
                                        }
                                    }
                                >
                                    <textarea
                                        name="description"
                                        className="border border-gray-300 p-2 rounded-lg w-full"
                                        defaultValue={sellerDetails.businessDescription}
                                    />
                                    <Button>Save</Button>
                                </form>
                            }
                        </div>
                    </div>
                </div>
            ) : (
                <div>
                    <h1>Not a seller</h1>
                </div>
            )}
        </div>
    );

}
export default Page;
