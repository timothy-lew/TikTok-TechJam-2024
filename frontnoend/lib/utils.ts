import { type ClassValue, clsx } from "clsx";
import { twMerge } from "tailwind-merge";

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs));
}

export const getBackendUrl = () => {
  if (typeof window === "undefined") {
    // Server-side rendering
    return process.env.NEXT_PUBLIC_BACKEND_URL || "http://app:8080";
  }
  // Client-side rendering
  return window.location.hostname === "localhost"
    ? "http://localhost:8080"
    : process.env.NEXT_PUBLIC_BACKEND_URL || "http://app:8080";
};
