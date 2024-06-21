"use server"

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

export async function signup(userSignUpDetails : UserSignUpDetails){
  try {
    const response = await fetch('http://localhost:8080/api/users/signup', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(userSignUpDetails),
    });

    const data = response.json();

    // HARDCODE - get from `data`
    const returnedUserDetails : UserDetails = {
      id: "7sdv8w23fn",
      username: "chucky",
      email: "chuck@gmail.com",
      // firstName: "Chuck",
      // lastName: "Lee",
      roles: "ROLE_BUYER",
      name: "Chuck",
      cashBalance: 900.34,
      coinBalance: 98721,
    }

    // Create the session
    const expires = new Date(Date.now() + 10 * 1000);
    const session = await encrypt({ returnedUserDetails, expires });

    cookies().set('session', session, { expires, httpOnly: true})

    return returnedUserDetails;

  } catch (error) {
    console.error('An error occurred:', error);
    throw new Error(`Error in signing up ${error}`);
  }
}

export async function login(userSignInDetails : UserSignInDetails){

  try{
    console.log(`Sending to ${process.env.NEXT_PUBLIC_BACKEND_URL}`);

    // const response = await fetch(`${process.env.NEXT_PUBLIC_BACKEND_URL}/api/login`, {
    //   method: 'POST',
    //   headers: {
    //     'Content-Type': 'application/json',
    //   },
    //   body: JSON.stringify(userSignInDetails),
    // });

    // const data = await response.json();

    // console.log(`Sign In Success: ${data}`);

    // HARDCODE - get from `data`
    const returnedUserDetails : UserDetails = {
      id: "7sdv8w23fn",
      username: "chucky",
      email: "chuck@gmail.com",
      // firstName: "Chuck",
      // lastName: "Lee",
      roles: "ROLE_BUYER",
      name: "Chuck",
      cashBalance: 900.34,
      coinBalance: 98721,
    }

    // Create the session
    const expires = new Date(Date.now() + 10 * 1000);
    const session = await encrypt({ returnedUserDetails, expires });

    cookies().set('session', session, { expires, httpOnly: true})

    return returnedUserDetails;

  }
  catch(error){
    console.log(`Error in sign in: ${error}`);

    throw new Error(`Error in signing in ${error}`);
  }



}


export async function logout() {
  // Destroy the session
  cookies().set("session", "", { expires: new Date(0) });
}
