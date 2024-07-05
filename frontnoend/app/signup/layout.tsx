import type { Metadata } from "next";
import Link from "next/link";
import "@/app/globals.css";

export const metadata: Metadata = {
  title: "Sign Up | Tiktok",
};


export default function SignUpLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {

  return (
    <div className="w-full flex_col_center gap-4 md:gap-6 min-h-screen">

        <h1 className="font-bold text-xl md:text-2xl lg:text-3xl">Sign up for TikTok</h1>

        <p className="text-slate-400 text-center max-w-[400px]">Create a profile, enjoy a world class shopping experience, and more!</p>
            {children}
        <p className="text-slate-700 text-center text-xs max-w-[400px]">
            By continuing with an account located in <span className="font-semibold">Singapore</span>, you agree to our <span className="font-semibold">Terms of Service</span> and acknowledge that you have read our <span className="font-semibold">Privacy Policy</span>.
        </p>

        
        <p className="text-center text-sm bottom-0 border-t border-slate-600 w-full py-4 mt-auto">
          Already have an account? <Link href="/login" className="text-tiktok-red font-semibold hover:underline">Log in</Link>
        </p>
    </div>
  );
}
