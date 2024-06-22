import type { Metadata } from "next";



export const metadata: Metadata = {
  title: "Log In | Tiktok",
};


export default function LoginLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {

  return (
    <div className="w-full flex_col_center gap-4 md:gap-6">

        <h1 className="font-bold text-xl md:text-2xl lg:text-3xl">Log in TikTok</h1>

        <p className="text-slate-400 text-center max-w-[400px]">Manage your wallet, check notifications, comment on videos, and more!</p>
            {children}
        <p className="text-slate-700 text-center text-xs max-w-[400px]">
            By continuing with an account located in <span className="font-semibold">Singapore</span>, you agree to our <span className="font-semibold">Terms of Service</span> and acknowledge that you have read our <span className="font-semibold">Privacy Policy</span>.
        </p>
    </div>
  );
}
