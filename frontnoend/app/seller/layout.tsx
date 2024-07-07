"use client"

import Link from 'next/link';
import { usePathname } from 'next/navigation';
import React from 'react';
import Image from 'next/image';

export default function SellerLayout({
    children,
}: Readonly<{
    children: React.ReactNode;
}>) {
    const pathname = usePathname();

    return (
        <>
            <h2 className="text-tiktok-red text-center w-full text-xl sm:text-2xl md:text-3xl font-bold mb-2">
                Your <Image src="/tiktokIconNoBg.svg" alt="icon" width={42} height={42} className="inline rotate-6"/> Shop
            </h2>
            {/* <nav className="w-[60px] md:w-[260px] flex items-start justify-start gap-4 px-4 pb-8"> */}
            <nav className="w-full flex items-start justify-center gap-4 px-4 pb-4">
                <Link href="/seller" className="flex justify-center items-center gap-4"
                    style={{ color: pathname === "/seller" ? "rgba(254, 44, 85, 1)" : "rgba(22, 24, 35, 1)" }}>
                    <p className={`hidden md:flex_col_center font-bold text-lg`}>Dashboard</p>
                </Link>
                <Link href="/seller/listing" className="flex justify-center items-center gap-4"
                    style={{ color: pathname === "/seller/listing" ? "rgba(254, 44, 85, 1)" : "rgba(22, 24, 35, 1)" }}>
                    <p className={`hidden md:flex_col_center 'text-skin-base' font-bold text-lg`}>Listings</p>
                </Link>
                <Link href="/seller/details" className="flex justify-center items-center gap-4"
                    style={{ color: pathname === "/seller/details" ? "rgba(254, 44, 85, 1)" : "rgba(22, 24, 35, 1)" }}>
                    <p className={`hidden md:flex_col_center 'text-skin-base' font-bold text-lg`}>Details</p>
                </Link>
            </nav>
            {children}
        </>
    )
}