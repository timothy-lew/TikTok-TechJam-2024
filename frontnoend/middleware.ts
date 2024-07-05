import { NextResponse } from 'next/server'
import type { NextRequest } from 'next/server'


// Navigates user away from unconstructed routes that are for display only
export function middleware(request: NextRequest) {
  // console.log("middleware ran");

  // const unconstructedRoutes = ['/explore', '/following', '/friends', '/live', '/profile'];

  const unconstructedRoutes : string[] = [];

  if (unconstructedRoutes.includes(request.nextUrl.pathname)) {
    return NextResponse.redirect(new URL('/unavailable', request.url));
  }
}

export const config = {
  matcher: [
    /*
     * Match all request paths except for the ones starting with:
     * - api (API routes)
     * - _next/static (static files)
     * - _next/image (image optimization files)
     * - favicon.ico (favicon file)
     */
    '/((?!api|_next/static|_next/image|favicon.ico).*)',
  ],
}