"use client"

import { createContext, useContext, useState } from "react";
import { ReactNode } from "react";

type Auth = {
  user : UserDetails | null;
  signIn: (user: UserDetails) => void;
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

  const signIn = (userDetails : UserDetails) => {

    // HARDCODE
    userDetails = {
      userId: "Test",
      email: "chuck@gmail.com",
      firstName: "Chuck",
      lastName: "Lee",
      profilePic:""
    }


    setUser(userDetails);
  }

  const signOut = () => {
    setUser(null);
  }


  return(
    <AuthContext.Provider value={{user, signIn, signOut}}>
      {children}
    </AuthContext.Provider>
  )
}

export const useAuth = () => useContext(AuthContext);