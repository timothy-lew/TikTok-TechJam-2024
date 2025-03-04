"use client"

import Link from "next/link";
import Image from "next/image";
import { usePathname } from "next/navigation"
import { useAuth } from "@/hooks/auth-provider";

import { Separator } from "@/components/ui/separator"
import { Button } from "@/components/ui/button"
import { FaWallet } from "react-icons/fa";
import { FaShop } from "react-icons/fa6";

 
import { displayAccounts, sidebarFooterCompany, sidebarFooterPrograms, sidebarFooterTermsPolicies } from "@/constants";
import FriendsAccount from '@/components/shared/FriendsAccount';

const SideBar = () => {
  
  const pathname = usePathname();

  const auth = useAuth();

  const user = auth?.user || null;


  return (
    <nav className="w-[60px] md:w-[260px] flex flex-col items-start justify-start px-4">
            
      <Link href="/" className="flex justify-start items-center gap-2 hover:bg-gray-50 w-full py-2">
        <svg
          width="32" data-e2e=""
          height="32" viewBox="0 0 48 48"
          fill={`${pathname==='/' ? "rgba(254, 44, 85, 1)" : "rgba(22, 24, 35, 1)"}`}
          xmlns="http://www.w3.org/2000/svg"
        >
          {pathname==="/" ?
          <path fillRule="evenodd" clipRule="evenodd" d="M24.9505 7.84001C24.3975 7.38666 23.6014 7.38666 23.0485 7.84003L6.94846 21.04C6.45839 21.4418 6.2737 22.1083 6.48706 22.705C6.70041 23.3017 7.26576 23.7 7.89949 23.7H10.2311L11.4232 36.7278C11.5409 38.0149 12.6203 39 13.9128 39H21.5C22.0523 39 22.5 38.5523 22.5 38V28.3153C22.5 27.763 22.9477 27.3153 23.5 27.3153H24.5C25.0523 27.3153 25.5 27.763 25.5 28.3153V38C25.5 38.5523 25.9477 39 26.5 39H34.0874C35.3798 39 36.4592 38.0149 36.577 36.7278L37.7691 23.7H40.1001C40.7338 23.7 41.2992 23.3017 41.5125 22.705C41.7259 22.1082 41.5412 21.4418 41.0511 21.04L24.9505 7.84001Z"></path>
          :
          <path fillRule="evenodd" clipRule="evenodd" d="M23.0484 7.84003C23.6014 7.38666 24.3975 7.38666 24.9504 7.84001L41.051 21.04C41.5411 21.4418 41.7258 22.1082 41.5125 22.705C41.2991 23.3017 40.7338 23.7 40.1 23.7H37.769L36.5769 36.7278C36.4592 38.0149 35.3798 39 34.0873 39H13.9127C12.6202 39 11.5409 38.0149 11.4231 36.7278L10.231 23.7H7.89943C7.2657 23.7 6.70035 23.3017 6.487 22.705C6.27364 22.1083 6.45833 21.4418 6.9484 21.04L23.0484 7.84003ZM23.9995 10.9397L12.0948 20.7H12.969L14.369 36H22.4994V28.3138C22.4994 27.7616 22.9471 27.3138 23.4994 27.3138H24.4994C25.0517 27.3138 25.4994 27.7616 25.4994 28.3138V36H33.631L35.031 20.7H35.9045L23.9995 10.9397Z"></path>
          }
          </svg>
        <p className={`hidden md:flex_col_center ${pathname==='/' ? 'text-skin-red' : 'text-skin-base'} font-bold text-lg`}>For You</p>
      </Link>
      
      <Link href="/explore" className="flex justify-start items-center gap-2 hover:bg-gray-50 w-full py-2">
      <svg
          width="32" data-e2e=""
          height="32" viewBox="0 0 48 48"
          fill={`${pathname==='/explore' ? "rgba(254, 44, 85, 1)" : "rgba(22, 24, 35, 1)"}`}
          xmlns="http://www.w3.org/2000/svg"
        >
        {pathname.startsWith('/explore') ?
          <>
            <path d="M24 37.4a13.4 13.4 0 1 0 0-26.8 13.4 13.4 0 0 0 0 26.8ZM40.5 24a16.5 16.5 0 1 1-33 0 16.5 16.5 0 0 1 33 0Z"></path>
            <path d="M27.13 27.18 19 32.1a.6.6 0 0 1-.9-.63l1.84-9.33a2 2 0 0 1 .92-1.32L29 15.9a.6.6 0 0 1 .9.63l-1.84 9.33a2 2 0 0 1-.93 1.32Zm-5.04-.45 3.11-1.89.7-3.57-3.1 1.89-.7 3.57Z"></path>
          </>
          :
          <>
            <path d="M24 40.5a16.5 16.5 0 1 0 0-33 16.5 16.5 0 0 0 0 33Zm4.43-14.54c-.12.6-.49 1.12-1.01 1.44l-8.88 5.37a.65.65 0 0 1-.98-.69l2.01-10.18c.12-.6.49-1.12 1.01-1.44l8.88-5.37a.65.65 0 0 1 .98.69l-2.01 10.18Z"></path><path d="m21.92 26.89 3.4-2.05.76-3.9-3.4 2.06-.76 3.89Z"></path>
          </>
        }
        </svg>
        <p className={`hidden md:flex_col_center ${pathname.startsWith('/explore') ? 'text-skin-red' : 'text-skin-base'} font-bold text-lg`}>Explore</p>
      </Link>

      <Link href="/following" className="pl-1 flex justify-start items-center gap-2 hover:bg-gray-50 w-full py-2">
        <svg fill={`${pathname.startsWith('/following') ? "rgba(254, 44, 85, 1)" : "rgba(22, 24, 35, 1)"}`} width="28" height="28" viewBox="0 0 48 48" xmlns="http://www.w3.org/2000/svg">
        {pathname.startsWith('/following') ?
          <>
            <path d="M18.99 4a10 10 0 1 1 0 20 10 10 0 0 1 0-20Zm0 4a6 6 0 1 0 0 12.01A6 6 0 0 0 19 8ZM18.99 27c2.96 0 5.6.58 7.87 1.65l-3.07 3.06a15.38 15.38 0 0 0-4.8-.71C10.9 31 6.3 36.16 6 44c-.02.55-.46 1-1.02 1h-2c-.55 0-1-.45-.98-1C2.33 33.99 8.7 27 19 27ZM35.7 42.88 31.82 39H45a1 1 0 0 0 1-1v-2a1 1 0 0 0-1-1H31.82l3.88-3.88a1 1 0 0 0 0-1.41l-1.41-1.42a1 1 0 0 0-1.42 0l-7.3 7.3a2 2 0 0 0 0 2.82l7.3 7.3a1 1 0 0 0 1.42 0l1.41-1.42a1 1 0 0 0 0-1.41Z"></path>            
          </>
          :
          <>
            <path d="M18.99 4a10 10 0 1 1 0 20 10 10 0 0 1 0-20Zm0 4a6 6 0 1 0 0 12.01A6 6 0 0 0 19 8ZM18.99 27c2.96 0 5.6.58 7.87 1.65l-3.07 3.06a15.38 15.38 0 0 0-4.8-.71C10.9 31 6.3 36.16 6 44c-.02.55-.46 1-1.02 1h-2c-.55 0-1-.45-.98-1C2.33 33.99 8.7 27 19 27ZM35.7 42.88 31.82 39H45a1 1 0 0 0 1-1v-2a1 1 0 0 0-1-1H31.82l3.88-3.88a1 1 0 0 0 0-1.41l-1.41-1.42a1 1 0 0 0-1.42 0l-7.3 7.3a2 2 0 0 0 0 2.82l7.3 7.3a1 1 0 0 0 1.42 0l1.41-1.42a1 1 0 0 0 0-1.41Z"></path>
          </>
        }
        </svg>
        <p className={`hidden md:flex_col_center ${pathname.startsWith('/following') ? 'text-skin-red' : 'text-skin-base'} font-bold text-lg`}>Following</p>
      </Link>

      {user && <Link href="/friends" className="flex justify-start items-center gap-2 hover:bg-gray-50 w-full py-2">
        <svg fill={`${pathname.startsWith('/friends') ? "rgba(254, 44, 85, 1)" : "rgba(22, 24, 35, 1)"}`} width="32" height="32" viewBox="0 0 48 48" xmlns="http://www.w3.org/2000/svg">
        {pathname.startsWith('/friends') ?
          <>
            <path fillRule="evenodd" clipRule="evenodd" d="M18 12.5C15.5897 12.5 13.5849 14.5018 13.5849 17.0345C13.5849 19.5672 15.5897 21.569 18 21.569C20.4103 21.569 22.4151 19.5672 22.4151 17.0345C22.4151 14.5018 20.4103 12.5 18 12.5ZM10.5849 17.0345C10.5849 12.9017 13.8766 9.5 18 9.5C22.1234 9.5 25.4151 12.9017 25.4151 17.0345C25.4151 21.1673 22.1234 24.569 18 24.569C13.8766 24.569 10.5849 21.1673 10.5849 17.0345ZM18 29.8793C14.0801 29.8793 10.7403 32.5616 9.69697 36.2673C9.5473 36.7989 9.03833 37.1708 8.49337 37.0811L7.50662 36.9189C6.96166 36.8292 6.58837 36.3131 6.72325 35.7776C8.00732 30.6788 12.5509 26.8793 18 26.8793C23.449 26.8793 27.9927 30.6788 29.2767 35.7776C29.4116 36.3131 29.0383 36.8292 28.4934 36.9189L27.5066 37.0811C26.9617 37.1708 26.4527 36.7989 26.303 36.2673C25.2597 32.5616 21.9199 29.8793 18 29.8793Z"></path><path fillRule="evenodd" clipRule="evenodd" d="M33 31.5371C32.2445 31.5371 31.5198 31.668 30.8447 31.9093C30.3246 32.0951 29.7189 31.9243 29.4549 31.4392L28.9769 30.5608C28.713 30.0757 28.8907 29.463 29.4009 29.2516C30.513 28.791 31.7285 28.5371 33 28.5371C37.4554 28.5371 41.1594 31.6303 42.2706 35.7812C42.4135 36.3147 42.0386 36.8308 41.4935 36.9196L40.5065 37.0804C39.9614 37.1692 39.4546 36.7956 39.2894 36.2686C38.4217 33.5 35.91 31.5371 33 31.5371Z"></path><path fillRule="evenodd" clipRule="evenodd" d="M33 18.5C31.6193 18.5 30.5 19.6193 30.5 21C30.5 22.3807 31.6193 23.5 33 23.5C34.3807 23.5 35.5 22.3807 35.5 21C35.5 19.6193 34.3807 18.5 33 18.5ZM27.5 21C27.5 17.9624 29.9624 15.5 33 15.5C36.0376 15.5 38.5 17.9624 38.5 21C38.5 24.0376 36.0376 26.5 33 26.5C29.9624 26.5 27.5 24.0376 27.5 21Z"></path>
          </>
          :
          <>
            <path fillRule="evenodd" clipRule="evenodd" d="M18 12.5C15.5897 12.5 13.5849 14.5018 13.5849 17.0345C13.5849 19.5672 15.5897 21.569 18 21.569C20.4103 21.569 22.4151 19.5672 22.4151 17.0345C22.4151 14.5018 20.4103 12.5 18 12.5ZM10.5849 17.0345C10.5849 12.9017 13.8766 9.5 18 9.5C22.1234 9.5 25.4151 12.9017 25.4151 17.0345C25.4151 21.1673 22.1234 24.569 18 24.569C13.8766 24.569 10.5849 21.1673 10.5849 17.0345ZM18 29.8793C14.0801 29.8793 10.7403 32.5616 9.69697 36.2673C9.5473 36.7989 9.03833 37.1708 8.49337 37.0811L7.50662 36.9189C6.96166 36.8292 6.58837 36.3131 6.72325 35.7776C8.00732 30.6788 12.5509 26.8793 18 26.8793C23.449 26.8793 27.9927 30.6788 29.2767 35.7776C29.4116 36.3131 29.0383 36.8292 28.4934 36.9189L27.5066 37.0811C26.9617 37.1708 26.4527 36.7989 26.303 36.2673C25.2597 32.5616 21.9199 29.8793 18 29.8793Z"></path><path fillRule="evenodd" clipRule="evenodd" d="M33 31.5371C32.2445 31.5371 31.5198 31.668 30.8447 31.9093C30.3246 32.0951 29.7189 31.9243 29.4549 31.4392L28.9769 30.5608C28.713 30.0757 28.8907 29.463 29.4009 29.2516C30.513 28.791 31.7285 28.5371 33 28.5371C37.4554 28.5371 41.1594 31.6303 42.2706 35.7812C42.4135 36.3147 42.0386 36.8308 41.4935 36.9196L40.5065 37.0804C39.9614 37.1692 39.4546 36.7956 39.2894 36.2686C38.4217 33.5 35.91 31.5371 33 31.5371Z"></path><path fillRule="evenodd" clipRule="evenodd" d="M33 18.5C31.6193 18.5 30.5 19.6193 30.5 21C30.5 22.3807 31.6193 23.5 33 23.5C34.3807 23.5 35.5 22.3807 35.5 21C35.5 19.6193 34.3807 18.5 33 18.5ZM27.5 21C27.5 17.9624 29.9624 15.5 33 15.5C36.0376 15.5 38.5 17.9624 38.5 21C38.5 24.0376 36.0376 26.5 33 26.5C29.9624 26.5 27.5 24.0376 27.5 21Z"></path>
          </>
        }
        </svg>
        <p className={`hidden md:flex_col_center ${pathname.startsWith('/friends') ? 'text-skin-red' : 'text-skin-base'} font-bold text-lg`}>Friends</p>
      </Link>}

      

      <Link href="/live" className="pl-0.5 flex justify-start items-center gap-3 hover:bg-gray-50 w-full py-2">
        <svg width="27" data-e2e="" height="27" viewBox="0 0 48 48" fill={`${pathname.startsWith('/live') ? "rgba(254, 44, 85, 1)" : "rgba(22, 24, 35, 1)"}`} xmlns="http://www.w3.org/2000/svg">
          {pathname.startsWith('/live') ? (
            <>
              <path fillRule="evenodd" clipRule="evenodd" d="M17.27 8.4 13.36 4.5a1 1 0 0 1 0-1.42l1.98-1.98a1 1 0 0 1 1.4 0L24 8.34l7.25-7.25a1 1 0 0 1 1.41 0l1.98 1.98a1 1 0 0 1 0 1.42L30.73 8.4c4.2.02 6.59.18 8.48 1.15a10.6 10.6 0 0 1 4.63 4.63C45 16.46 45 19.42 45 25.36v1.68c0 5.94 0 8.9-1.16 11.17a10.6 10.6 0 0 1-4.63 4.63C36.94 44 33.98 44 28.04 44h-8.08c-5.94 0-8.9 0-11.17-1.16a10.6 10.6 0 0 1-4.63-4.63C3 35.94 3 32.98 3 27.04v-1.68c0-5.94 0-8.9 1.16-11.17a10.6 10.6 0 0 1 4.63-4.63c1.9-.97 4.28-1.13 8.48-1.15Zm5.93 26.4c-.56 0-.84 0-1.05-.1a1 1 0 0 1-.44-.45c-.11-.21-.11-.49-.11-1.05V19.6c0-.56 0-.84.1-1.05a1 1 0 0 1 .45-.44c.21-.11.49-.11 1.05-.11h1.6c.56 0 .84 0 1.05.1a1 1 0 0 1 .44.45c.11.21.11.49.11 1.05v13.6c0 .56 0 .84-.1 1.05a1 1 0 0 1-.45.44c-.21.11-.49.11-1.05.11h-1.6Zm-9.45-.1c.21.1.49.1 1.05.1h1.6c.56 0 .84 0 1.05-.1a1 1 0 0 0 .44-.45c.11-.21.11-.49.11-1.05v-6c0-.56 0-.84-.1-1.05a1 1 0 0 0-.45-.44c-.21-.11-.49-.11-1.05-.11h-1.6c-.56 0-.84 0-1.05.1a1 1 0 0 0-.44.45c-.11.21-.11.49-.11 1.05v6c0 .56 0 .84.1 1.05a1 1 0 0 0 .45.44Zm17.85.1c-.56 0-.84 0-1.05-.1a1 1 0 0 1-.44-.45c-.11-.21-.11-.49-.11-1.05v-9c0-.56 0-.84.1-1.05a1 1 0 0 1 .45-.44c.21-.11.49-.11 1.05-.11h1.6c.56 0 .84 0 1.05.1a1 1 0 0 1 .44.45c.11.21.11.49.11 1.05v9c0 .56 0 .84-.1 1.05a1 1 0 0 1-.45.44c-.21.11-.49.11-1.05.11h-1.6Z"></path>
              {/* <path d="M6.5 17.5714C6.5 14.7292 8.86029 12.5 11.6782 12.5H27.8621C30.6799 12.5 33.0402 14.7292 33.0402 17.5714V18.6843L36.745 15.9435C37.6399 15.2815 38.8324 15.1731 39.8318 15.6537C40.8365 16.1369 41.5 17.1486 41.5 18.2857V29.7143C41.5 30.8514 40.8365 31.8631 39.8318 32.3463C38.8324 32.8269 37.6399 32.7185 36.745 32.0565L33.0402 29.3158V30.4286C33.0402 33.2708 30.6799 35.5 27.8621 35.5H11.6782C8.86029 35.5 6.5 33.2708 6.5 30.4286V17.5714Z"></path><path d="M23.25 23.134C23.9167 23.5189 23.9167 24.4811 23.25 24.866L17.25 28.3301C16.5833 28.715 15.75 28.2339 15.75 27.4641L15.75 20.5359C15.75 19.7661 16.5833 19.285 17.25 19.6699L23.25 23.134Z" fill="white"></path> */}
            </>
          ) : (
            <>
              <path d="M21.9 33.85c0 .57.47 1.04 1.04 1.04h2.12c.57 0 1.04-.47 1.04-1.04V19.26c0-.57-.47-1.04-1.04-1.04h-2.12c-.57 0-1.04.47-1.04 1.04v14.59ZM14.85 34.89c-.58 0-1.05-.47-1.05-1.04V26.7c0-.57.47-1.04 1.05-1.04h2.11c.58 0 1.04.47 1.04 1.04v7.15c0 .57-.46 1.04-1.04 1.04h-2.11ZM30 33.85c0 .57.46 1.04 1.04 1.04h2.11c.58 0 1.05-.47 1.05-1.04V23.73c0-.58-.47-1.04-1.05-1.04h-2.11c-.58 0-1.04.46-1.04 1.04v10.12Z"></path><path d="M40.08 9.95C37.92 8.61 35.1 8.4 30.06 8.37l4.03-4.03c.41-.41.41-1.08 0-1.49L32.6 1.36a1.05 1.05 0 0 0-1.48 0l-7 7h-.26L16.89 1.4a1.05 1.05 0 0 0-1.5 0l-1.48 1.48a1.05 1.05 0 0 0 0 1.5l4 4c-5.02.03-7.84.24-9.99 1.58-1.37.85-2.52 2-3.37 3.37-1.59 2.55-1.59 6.04-1.59 13.02 0 6.98 0 10.47 1.6 13.02.84 1.37 2 2.52 3.36 3.37 2.55 1.59 6.04 1.59 13.02 1.59h6.12c6.98 0 10.47 0 13.02-1.59 1.37-.85 2.52-2 3.37-3.37 1.59-2.55 1.59-6.04 1.59-13.02 0-6.98 0-10.47-1.59-13.02-.85-1.37-2-2.52-3.37-3.37Zm-13.02 2.41c3.57 0 5.96 0 7.79.18 1.76.17 2.58.47 3.12.8a6.52 6.52 0 0 1 2.08 2.1c.34.53.64 1.35.8 3.11.19 1.83.19 4.22.19 7.8 0 3.56 0 5.95-.18 7.78-.17 1.76-.47 2.58-.8 3.12a6.52 6.52 0 0 1-2.1 2.08c-.53.34-1.35.64-3.11.81-1.83.18-4.22.18-7.79.18h-6.12c-3.57 0-5.96 0-7.79-.18-1.76-.17-2.58-.47-3.12-.8a6.52 6.52 0 0 1-2.08-2.1c-.34-.53-.64-1.35-.8-3.11a90.95 90.95 0 0 1-.19-7.79c0-3.57 0-5.96.18-7.79.17-1.76.47-2.58.8-3.12a6.52 6.52 0 0 1 2.1-2.08c.53-.34 1.35-.64 3.11-.8 1.83-.18 4.22-.19 7.8-.19h6.11Z"></path>
            </>
          )}
        </svg>
        <p className={`hidden md:flex_col_center ${pathname.startsWith('/live') ? 'text-skin-red' : 'text-skin-base'} font-bold text-lg`}>LIVE</p>
      </Link>
        
      {user?.sellerProfile && 
      <Link href="/seller" className="flex justify-start items-center gap-2 hover:bg-gray-50 w-full py-2">
        <FaShop className={`${pathname.startsWith('/seller') ? "text-tiktok-black" : "text-tiktok-gray"} w-6 h-6 ml-1 cursor-pointer`} />
        <div className="flex_center gap-1.5">
          <p className={`hidden md:flex_col_center ${pathname.startsWith('/seller') ? 'text-skin-red' : 'text-skin-base'} font-bold text-lg`}>Seller Studio</p>
          {/* <span className="hidden md:inline text-xs px-1.5 py-0.5 bg-tiktok-red text-white font-bold rounded-xl">New</span> */}
        </div>
      </Link>}

      <Link href="/profile" className="flex justify-start items-center gap-2 hover:bg-gray-50 w-full py-2">
        <svg fill={`${pathname.startsWith('/profile') ? "rgba(254, 44, 85, 1)" : "rgba(22, 24, 35, 1)"}`} width="32" height="32" viewBox="0 0 48 48" xmlns="http://www.w3.org/2000/svg">
          {pathname.startsWith('/profile') ? (
            <>
              <path fillRule="evenodd" clipRule="evenodd" d="M24.0003 7C20.1343 7 17.0003 10.134 17.0003 14C17.0003 17.866 20.1343 21 24.0003 21C27.8663 21 31.0003 17.866 31.0003 14C31.0003 10.134 27.8663 7 24.0003 7ZM13.0003 14C13.0003 7.92487 17.9252 3 24.0003 3C30.0755 3 35.0003 7.92487 35.0003 14C35.0003 20.0751 30.0755 25 24.0003 25C17.9252 25 13.0003 20.0751 13.0003 14ZM24.0003 33C18.0615 33 13.0493 36.9841 11.4972 42.4262C11.3457 42.9573 10.8217 43.3088 10.2804 43.1989L8.32038 42.8011C7.77914 42.6912 7.4266 42.1618 7.5683 41.628C9.49821 34.358 16.1215 29 24.0003 29C31.8792 29 38.5025 34.358 40.4324 41.628C40.5741 42.1618 40.2215 42.6912 39.6803 42.8011L37.7203 43.1989C37.179 43.3088 36.6549 42.9573 36.5035 42.4262C34.9514 36.9841 29.9391 33 24.0003 33Z"></path>
            </>
          ) : (
            <>
              <path fillRule="evenodd" clipRule="evenodd" d="M24.0003 7C20.1343 7 17.0003 10.134 17.0003 14C17.0003 17.866 20.1343 21 24.0003 21C27.8663 21 31.0003 17.866 31.0003 14C31.0003 10.134 27.8663 7 24.0003 7ZM13.0003 14C13.0003 7.92487 17.9252 3 24.0003 3C30.0755 3 35.0003 7.92487 35.0003 14C35.0003 20.0751 30.0755 25 24.0003 25C17.9252 25 13.0003 20.0751 13.0003 14ZM24.0003 33C18.0615 33 13.0493 36.9841 11.4972 42.4262C11.3457 42.9573 10.8217 43.3088 10.2804 43.1989L8.32038 42.8011C7.77914 42.6912 7.4266 42.1618 7.5683 41.628C9.49821 34.358 16.1215 29 24.0003 29C31.8792 29 38.5025 34.358 40.4324 41.628C40.5741 42.1618 40.2215 42.6912 39.6803 42.8011L37.7203 43.1989C37.179 43.3088 36.6549 42.9573 36.5035 42.4262C34.9514 36.9841 29.9391 33 24.0003 33Z"></path>
            </>
          )}
        </svg>
        <p className={`hidden md:flex_col_center ${pathname.startsWith('/profile') ? 'text-skin-red' : 'text-skin-base'} font-bold text-lg`}>Profile</p>
      </Link>

      {user && <Link href="/wallet" className="flex justify-start items-center gap-2 hover:bg-gray-50 w-full py-2">
        <FaWallet className={`${pathname.startsWith('/wallet') ? "text-tiktok-red" : "text-tiktok-gray"} w-6 h-6 ml-1 cursor-pointer`} />
        <div className="flex_center gap-1.5">
          <p className={`hidden md:flex_col_center ${pathname.startsWith('/wallet') ? 'text-skin-red' : 'text-skin-base'} font-bold text-lg`}>Wallet</p>
          <span className="hidden md:inline text-xs px-1.5 py-0.5 bg-tiktok-red text-white font-bold rounded-xl">New</span>
        </div>
      </Link>}

      {user && <Link href="/shop" className="flex justify-start items-center gap-2 hover:bg-gray-50 w-full py-2">
        <FaShop className={`${pathname.startsWith('/shop') ? "text-tiktok-red" : "text-tiktok-gray"} w-6 h-6 ml-1 cursor-pointer`} />
        <div className="flex_center gap-1.5">
          <p className={`hidden md:flex_col_center ${pathname.startsWith('/shop') ? 'text-skin-red' : 'text-skin-base'} font-bold text-lg`}>Shop</p>
          <span className="hidden md:inline text-xs px-1.5 py-0.5 bg-tiktok-red text-white font-bold rounded-xl">New</span>
        </div>
      </Link>}
      
      <Separator className="my-1 bg-slate-200"/>

      {user ?
      <div className="flex flex-col justify-center items-start gap-1">
        <p className="font-medium text-slate-600 text-sm mb-2">Following accounts</p>
        {displayAccounts.map((acc)=>{
          return (
            <FriendsAccount friendAccount={acc} key={`sidebar-${acc.username}`}/>
          )
        })}
      </div>
      :
      <div className="hidden md:flex_col_center">
        <p className="text-left text-slate-400 text-sm">Log in to follow creators, like videos, and view comments.</p>
        <Link href="/login" className="px-4 py-2 w-full my-4 hover:bg-red-50 flex_center rounded-md border-[1.5px] border-tiktok-red/80"><p className="text-skin-red">Log In</p></Link>
      </div>

      }

      <Separator className="my-1 bg-slate-200"/>


      <div className="hidden md:flex_col_center relative py-4">
        <Image src="/images/sidebarbannerbg.png" alt="banner" height={280} width={220}/>
          <h4 className="absolute inset-0 flex items-center justify-center text-yellow-200 text-xs pl-[50px] px-4 py-2 font-semibold">Create TikTok effects, get a reward</h4>
      </div>
      
      <div className="flex flex-col justify-center items-start w-full">
        <h2 className="text-sm font-semibold text-gray-600 pb-2">Company</h2>
        <div className="flex flex-wrap gap-2 text-xs">
          {sidebarFooterCompany.map((item, index) => (
            <span key={index} className="text-gray-600 hover:underline cursor-pointer">{item}</span>
            ))}
        </div>
      </div>
      <div className="flex flex-col justify-center items-start w-full">
        <h2 className="text-sm font-semibold text-gray-600 pt-3 pb-2">Program</h2>
        <div className="flex flex-wrap gap-2 text-xs">
          {sidebarFooterPrograms.map((item, index) => (
            <span key={index} className="text-gray-600 hover:underline cursor-pointer">{item}</span>
          ))}
        </div>
      </div>
      <div className="flex flex-col justify-center items-start w-full">
        <h2 className="text-sm font-semibold text-gray-600 pt-3 pb-2">Terms & Policies</h2>
        <div className="flex flex-wrap gap-2 text-xs">
          {sidebarFooterTermsPolicies.map((item, index) => (
            <span key={index} className="text-gray-600 hover:underline cursor-pointer">{item}</span>
          ))}
        </div>
      </div>

      <p className="text-left text-gray-500 font-medium text-sm mt-4">© 2024 TikTok</p>


    </nav>
  )
}

export default SideBar