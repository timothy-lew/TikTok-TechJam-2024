"use client"

import { WalletProvider } from "@/hooks/wallet-provider";
import { useAuth } from "@/hooks/auth-provider";

export default function RootLayout({
  children,
}: {
  children: React.ReactNode
}) {

  const auth = useAuth();
  const userId = auth?.user?.id || null;


  return (

    <WalletProvider userId={userId || ""}>
      <main className="min-h-screen">
        <div className="container mx-auto px-4 py-8">
          {children}
        </div>
      </main>
    </WalletProvider>

  );
}
