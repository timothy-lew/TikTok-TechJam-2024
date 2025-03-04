
import { Metadata } from 'next'

export const metadata: Metadata = {
  title: 'Wallet | TikTok',
}

export default function WalletLayout({
  children,
}: {
  children: React.ReactNode
}) {



  return (

    <main className="min-h-screen">
        {children}
    </main>

  );
}
