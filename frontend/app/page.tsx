"use client"
import Post from "@/components/shared/Post";
import { useAuth } from "@/hooks/auth-provider"

const HomePage = () => {
  
  const auth = useAuth();

  const user = auth?.user || null;


  // HARDCODE
  const userDetails = {
    username: "alex2",
    password: "password2",
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
        <button className="bg-green-400 px-4 py-2 rounded-lg" onClick={()=>{auth?.signIn(userDetails)}}>
        Log In (Hardcode)
      </button>
      </div>
      }
      
      <Post
        image="/images/homePost.png"
        name="TikTok Shop Singapore"
        verified={true}
        desc="Check out our new offers at tiktok.com/shop!"
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