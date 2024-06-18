import { Inter } from "next/font/google";
import type { Metadata } from "next";
import "./globals.css";

import Header from '@/components/shared/Header';
import SideBar from '@/components/shared/SideBar'

import { AuthProvider } from "@/hooks/auth-provider";

const inter = Inter({ subsets: ["latin"] });

export const metadata: Metadata = {
  title: "Tiktok",
};


export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {

  return (
    <html lang="en">
      <body className={inter.className}>
        <AuthProvider>
            <section className="top-0 h-16 bg-black w-full absolute">
              <Header />
            </section>
            
            <section className="flex justify-start items-center pt-20">
              <nav className="w-40 min-h-screen">
                <SideBar />
              </nav>
              <div className="w-full min-h-screen">
                {children}
              </div>
            </section>
        </AuthProvider>
      </body>
    </html>
  );
}
