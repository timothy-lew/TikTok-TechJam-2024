"use client"

import Post from "@/components/shared/Post";
import Link from "next/link";
import { useAuth } from "@/hooks/auth-provider"

const HomePage = () => {
  
  const auth = useAuth();

  const user = auth?.user || null;


  // HARDCODE
  const buyerDetails = {
    username: "buyer",
    password: "password",
  }

  const sellerDetails = {
    username: "seller",
    password: "password",
  }

  const userDetails = {
    username: "user",
    password: "password",
  }
  
  return (
    <div className="flex_col_center">
      {user?
      <div>
        <p>Signed in with {user.email}, {user.firstName}</p>
        <button className="bg-red-400 px-4 py-2 rounded-lg" onClick={()=>{auth?.signOut()}}>Log out</button>
      </div>
      :
      <div>
        <button className="bg-green-400 px-4 p-2 m-2 rounded-lg" onClick={()=>{auth?.signIn(buyerDetails)}}>
        Quick Buyer Log In (Hardcode)
        </button>
        <button className="bg-green-400 px-4 p-2 m-2 rounded-lg" onClick={()=>{auth?.signIn(sellerDetails)}}>
        Quick Seller Log In (Hardcode)
        </button>
        <button className="bg-green-400 px-4 p-2 m-2 rounded-lg" onClick={()=>{auth?.signIn(userDetails)}}>
        Quick User Log In (Hardcode)
        </button>
      </div>
      }

      <Link href="/testing">
        Test Page
      </Link>
      
      <Post
        image="/images/homePost.png"
        name="TikTok Shop Singapore"
        verified={true}
        desc="Shop with great discounts now!"
        profile="/images/profilepics/tiktokshop_sg.jpeg"
        likes={13.1}
        comments={503}
        bookmarks={325}
        shares={112}
      />

    </div>
  )
}

export default HomePage