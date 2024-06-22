"use client"
import { useState } from "react";

import { useAuth } from "@/hooks/auth-provider";

export const Testing = () => {

    const [accessToken, setAccessToken] = useState("");
    const [refreshToken, setRefreshToken] = useState("");
    
    const auth = useAuth();


    const callAPI = async () => {

        // Sign Up
        try {

            const userSignUpDetails : UserSignUpDetails = {
                "username": "alex",
                "password": "password",
                "email": "alex@gmail.com",
                "firstName": "alex",
                "lastName": "lee",
                "roles": ["ROLE_BUYER", "ROLE_SELLER"],
                "shippingAddress": "Block 123",
                "billingAddress": "Block 123",
                "defaultPaymentMethod": "Card",
                "businessName": "Shop 123",
                "businessDescription": "Tools 123"
            }
            
            auth?.signUp(userSignUpDetails);


        }
        catch(error){
            console.log("Error in testing page");
            console.log(error);
        }

    }
  
  
    return (
    <div className="flex_col_center w-full">

        <button
            onClick={callAPI}
            className="bg-green-400 px-2 py-1"
        >
            Test
        </button>
        
        <p>{accessToken}</p>

    </div>
  )
}

export default Testing
