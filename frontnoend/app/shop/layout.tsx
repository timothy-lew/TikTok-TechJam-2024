
import { Metadata } from 'next'

export const metadata: Metadata = {
  title: 'Shop | TikTok',
}

export default function ShopLayout({
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
