"use client"

import { login, logout, signup, getAccessToken } from "@/lib/auth";
import { createContext, useContext, useState } from "react";
import { ReactNode } from "react";


type Auth = {
  user : UserDetails | null;
  signIn: (user: UserSignInDetails) => void;
  signUp: (user: UserSignUpDetails) => void;
  signOut: () => void;
  obtainAccessToken: () => Promise<string | undefined>;
  userWallet: UserWallet | null;
  setUserWallet: React.Dispatch<React.SetStateAction<UserWallet | null>>;
}


const AuthContext = createContext<Auth | null>(null);

export const AuthProvider = ( {children} : {children: ReactNode}) => {

  const [user, setUser] = useState<UserDetails | null>(null);

  const [userWallet, setUserWallet] = useState<UserWallet | null>(null);



  const signIn = async (userSignInDetails : UserSignInDetails) => {
    try{
      const userDetails = await login(userSignInDetails);
      console.log("Inside sign in auth provider:");
      console.log(userDetails);
      setUser(userDetails);
      setUserWallet(userDetails.wallet || null);
    }
    catch(error){
      console.log(`Error in sign in: ${error}`)
    }
  }
  
  const signUp = async (userSignUpDetails : UserSignUpDetails) => {
    try{
      const userDetails = await signup(userSignUpDetails);
      console.log("Inside sign up auth provider:");
      console.log(userDetails);
      setUser(userDetails);
      setUserWallet(userDetails.wallet || null);
    }
    catch(error){
      console.log("Error in auth provider");
      console.log(`Error in sign up: ${error}`)
    }
  }


  const signOut = async () => {
    await logout();
    setUser(null);
  }


  const obtainAccessToken = async () => {
    try {
      const accessToken = await getAccessToken();
      return accessToken;
    } catch (error) {
      // alert(`Error getting access token: ${error}`);
      return undefined;
    }
  };



  return(
    <AuthContext.Provider value={{user, signIn, signUp, signOut, obtainAccessToken, userWallet, setUserWallet}}>
      {children}
    </AuthContext.Provider>
  )
}

export const useAuth = () => useContext(AuthContext);