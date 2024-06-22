"use client"

import { useAuth } from "@/hooks/auth-provider"

const HomePage = () => {
  
  const auth = useAuth();

  const user = auth?.user || null;


  // HARDCODE
  const userDetails = {
    username: "chucky",
    password: "TODO:hash",
  }
  
  return (
    <div>
      {user?
      <div>
        <p>Signed in with {user.email}, {user.name}</p>
        <button className="bg-red-400 px-4 py-2 rounded-lg" onClick={()=>{auth?.signOut()}}>Log out</button>
      </div>
      :
      <div>
        <button className="bg-green-400 px-4 py-2 rounded-lg" onClick={()=>{auth?.signIn(userDetails)}}>
        Log In (Hardcode)
      </button>
      </div>
      }
    </div>
  )
}

export default HomePage