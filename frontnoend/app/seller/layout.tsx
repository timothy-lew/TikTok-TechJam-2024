"use client"

import Link from 'next/link';
import { usePathname } from 'next/navigation';
import React from 'react';

export default function SellerLayout({
    children,
}: Readonly<{
    children: React.ReactNode;
}>) {
    const pathname = usePathname();

    return (
        <>
            <nav className="w-[60px] md:w-[260px] flex items-start justify-start gap-4 px-4 pb-8">
                <Link href="/seller" className="flex justify-center items-center gap-2"
                    style={{ color: pathname === "/seller" ? "rgba(254, 44, 85, 1)" : "rgba(22, 24, 35, 1)" }}>
                    <p className={`hidden md:flex_col_center font-bold text-lg`}>Dashboard</p>
                </Link>
                <Link href="/seller/listing" className="flex justify-center items-center gap-2"
                    style={{ color: pathname === "/seller/listing" ? "rgba(254, 44, 85, 1)" : "rgba(22, 24, 35, 1)" }}>
                    <p className={`hidden md:flex_col_center 'text-skin-base' font-bold text-lg`}>Listing</p>
                </Link>
            </nav>
            {children}
        </>
    )
}