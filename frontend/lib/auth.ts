// https://www.youtube.com/watch?v=DJvM2lSPn6w
import { cookies } from 'next/headers';
import { NextRequest, NextResponse } from 'next/server';

import {SignJWT, jwtVerify } from 'jose';
const secretKey = "secret";
const key = new TextEncoder().encode(secretKey);

export async function encrypt(payload: any) {
  return await new SignJWT(payload)
    .setProtectedHeader({ alg: "HS256" })
    .setIssuedAt()
    .setExpirationTime("10 sec from now")
    .sign(key);
}

export async function decrypt(input: string): Promise<any> {
  const { payload } = await jwtVerify(input, key, {
    algorithms: ["HS256"],
  });
  return payload;
}

export async function updateSession(request : NextRequest){

  const session = request.cookies.get('session')?.value;

  if (!session) return; // not logged in

  const parsed = await decrypt(session);
  parsed.expires = new Date(Date.now() + 10*1000) // refresh the session

  const res = NextResponse.next();

  res.cookies.set({
    name: 'session',
    value: parsed, // TODO: encrypt this
    httpOnly: true,
    expires: parsed.expires
  })

  return request
}

export async function login(loginDetails : LoginDetails){

  // HARDCODE
  const user : UserDetails = {
    email: loginDetails.email,
    firstName: 'Chuck',
    lastName: 'Lee',
  }

  // Create the session
  const expires = new Date(Date.now() + 10 * 1000);
  const session = await encrypt({ user, expires });

  cookies().set('session', session, { expires, httpOnly: true})


}


export async function logout() {
  // Destroy the session
  cookies().set("session", "", { expires: new Date(0) });
}
