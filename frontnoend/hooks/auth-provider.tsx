"use client"

import { login, logout, signup, getAccessToken } from "@/lib/auth";
import { createContext, useContext, useEffect, useState } from "react";
import { ReactNode } from "react";
import TikTokLoader from "@/components/shared/TiktokLoader";

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

  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const initAuth = async () => {
      try {
        const storedUser = localStorage.getItem('tiktokuser');
        if (storedUser) {
          const parsedUser = JSON.parse(storedUser) as UserDetails;
          setUser(parsedUser);
          setUserWallet(parsedUser.wallet || null);

          // Verify the stored user with the backend
          const accessToken = await getAccessToken();
          if (!accessToken) {
            // If no access token, clear the stored user
            localStorage.removeItem('tiktokuser');
            setUser(null);
            setUserWallet(null);
          }
        }
      } catch (error) {
        console.error("Failed to initialize auth:", error);
      } finally {
        setLoading(false);
      }
    };

    initAuth();
  }, []);

    // save updated user wallet details into local storage
    useEffect(()=>{

      const storedUser = localStorage.getItem('tiktokuser');
  
      if (storedUser && userWallet){
        const parsedUser = JSON.parse(storedUser) as UserDetails;
        
        const updatedUser = {
          ...parsedUser,
          wallet: userWallet
        }
  
        console.log('Updated user in local storage:')
        console.log(updatedUser);
  
        localStorage.setItem('tiktokuser', JSON.stringify(updatedUser));
      }
  
    }, [userWallet, user])

  const signIn = async (userSignInDetails : UserSignInDetails) => {
    try{
      const userDetails = await login(userSignInDetails);
      console.log("Inside sign in auth provider:");
      console.log(userDetails);
      setUser(userDetails);
      setUserWallet(userDetails.wallet || null);
      localStorage.setItem('tiktokuser', JSON.stringify(userDetails));
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
      localStorage.setItem('tiktokuser', JSON.stringify(userDetails));
    }
    catch(error){
      console.log("Error in auth provider");
      console.log(`Error in sign up: ${error}`)
    }
  }


  const signOut = async () => {
    await logout();
    setUser(null);
    localStorage.removeItem('tiktokuser');
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

  if (loading) return (<div className="mx-auto w-full"><TikTokLoader/> </div>)


  return(
    <AuthContext.Provider value={{user, signIn, signUp, signOut, obtainAccessToken, userWallet, setUserWallet}}>
      {children}
    </AuthContext.Provider>
  )
}

export const useAuth = () => useContext(AuthContext);