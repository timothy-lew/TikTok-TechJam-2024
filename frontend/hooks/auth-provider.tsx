"use client"

import { login, logout, signup } from "@/lib/auth";
import { createContext, useContext, useState } from "react";
import { ReactNode } from "react";

type Auth = {
  user : UserDetails | null;
  signIn: (user: UserSignInDetails) => void;
  signUp: (user: UserSignUpDetails) => void;
  signOut: () => void;
}

// const initialAuthContext = {
//   user: {
//     userId: "Test",
//     email: "chuck@gmail.com",
//     firstName: "Chuck",
//     lastName: "Lee",
//     profilePic:""
//   },
//   signIn: () => {},
//   signOut: () => {},
// }


const AuthContext = createContext<Auth | null>(null);

export const AuthProvider = ( {children} : {children: ReactNode}) => {

  const [user, setUser] = useState<UserDetails | null>(null);

  const signIn = async (userSignInDetails : UserSignInDetails) => {
    try{
      const returnedUserDetails = await login(userSignInDetails);
      setUser(returnedUserDetails);
    }
    catch(error){
      console.log(`Error in sign in: ${error}`)
    }
  }

  const signUp = async (userSignUpDetails : UserSignUpDetails) => {


      // HARDCODE - get from `data`
      const tokenType = "";
      const accessToken = "";
      const refreshToken = "";

      const returnedUserDetails : UserDetails = {
        id: "7sdv8w23fn",
        username: "chucky",
        email: "chuck@gmail.com",
        // firstName: "Chuck",
        // lastName: "Lee",
        roles: "ROLE_BUYER",
        name: "Chuck",
        cashBalance: 900.34,
        coinBalance: 98721,
      }

      setUser(returnedUserDetails);
  }


  const signOut = async () => {
    await logout();
    setUser(null);
  }


  return(
    <AuthContext.Provider value={{user, signIn, signUp, signOut}}>
      {children}
    </AuthContext.Provider>
  )
}

export const useAuth = () => useContext(AuthContext);