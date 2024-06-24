// middleware.ts
import { NextRequest, NextResponse } from 'next/server';

const PUBLIC_PATHS = [
  '/api/login',
  '/api/users/signup',
  '/testing',
  '/wallet',
  '/',
  '/signup',
  '/signup/form',
  '/login',
  '/login/form',

];

const IMAGE_EXTENSIONS = ['.png', '.jpg', '.jpeg', '.gif', '.svg'];

async function refreshToken(request: NextRequest) {
  const refreshToken = request.cookies.get('refreshToken')?.value;

  if (!refreshToken) {
    return NextResponse.redirect(new URL('/login', request.nextUrl.origin).toString());
  }

  // TODO:
  const response = await fetch('http://localhost:8080/api/refresh', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ refreshToken }),
  });

  if (!response.ok) {
    return NextResponse.redirect('/login');
  }

  const data = await response.json();
  const newAccessToken = data.accessToken;

  const res = NextResponse.next();
  const accessExpiry = new Date(Date.now() + 15 * 60 * 1000); // 15 minutes expiry

  res.cookies.set('accessToken', newAccessToken, { httpOnly: true, expires: accessExpiry });

  return res;
}

export async function middleware(request: NextRequest) {

  const { pathname } = request.nextUrl;

  // Bypass the middleware for public paths
  if (PUBLIC_PATHS.includes(pathname) || IMAGE_EXTENSIONS.some(ext => pathname.endsWith(ext))) {
    return NextResponse.next();
  }
  
  console.log("Injecting access token");
  const accessToken = request.cookies.get('accessToken')?.value;

  if (!accessToken) {
    return refreshToken(request);
  }

  const headers = new Headers(request.headers);
  headers.set('Authorization', `Bearer ${accessToken}`);

  return NextResponse.rewrite(new URL(request.url, request.nextUrl.origin), { headers });
}
