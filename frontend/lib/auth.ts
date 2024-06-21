"use server"

// https://www.youtube.com/watch?v=DJvM2lSPn6w
import { cookies } from 'next/headers';
import { NextRequest, NextResponse } from 'next/server';


export async function updateSession(request : NextRequest){

  const session = request.cookies.get('session')?.value;

  if (!session) return; // not logged in

  
  const expiry = new Date(Date.now() + 10*1000) // refresh the session

  // Create a new response object to manipulate before sending it to client
  const res = NextResponse.next();

  res.cookies.set({
    name: 'session',
    value: session,
    httpOnly: true,
    expires: expiry,
  })

  return request
}

export async function signup(userSignUpDetails : UserSignUpDetails){
  try {
    const response = await fetch('http://localhost:8080/api/users/signup', {

      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        // 'Authorization': 'Bearer ' + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI2Njc1MzBmMmQ2MmFmMTZmNThjNjM3ODkiLCJpYXQiOjE3MTg5NzYyMTMsImV4cCI6MTcxOTA2MjYxM30.jMmofrfk1_JUng535BvGsZzHdMlM0yXqhUeRdAzTOIM"
      },
      // body: JSON.stringify(userSignUpDetails),
      body: JSON.stringify({
        "username": "alex",
        "password": "password",
        "email": "alex@gmail.com",
        "firstName": "alex",
        "lastName": "lee",
        "roles": ["ROLE_BUYER", "ROLE_SELLER"],
        "shippingAddress": "Block 123",
        "billingAddress": "Block 123",
        "defaultPaymentMethod": "Card",
        "businessName": "Shop 123",
        "businessDescription": "Tools 123"
    }),

    });

    const data : UserDetails = await response.json();

    // Create the session
    const expires = new Date(Date.now() + 10 * 1000);
    const session = { data, expires };

    // cookies().set('session', session, { expires, httpOnly: true});

    return data;

  } catch (error) {
    console.error('An error occurred:', error);
    throw new Error(`Error in signing up ${error}`);
  }
}

export async function login(userSignInDetails : UserSignInDetails){

  try{

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
      id: "6675af1634e89c2e28bfee86",
      username: "alex",
      email: "alex@gmail.com",
      firstName: "alex",
      lastName: "lee",
      roles: [
          "ROLE_SELLER",
          "ROLE_BUYER"
      ],
      buyerProfile: {
          id: "6675af1634e89c2e28bfee88",
          shippingAddress: "Block 123",
          billingAddress: "Block 123",
          defaultPaymentMethod: "Card"
      },
      sellerProfile: {
          id: "6675af1634e89c2e28bfee89",
          businessName: "Shop 123",
          businessDescription: "Tools 123"
      },
      wallet: {
          id: "6675af1634e89c2e28bfee87",
          cashBalance: 0.0,
          coinBalance: 0.0
      },
  }

    // Create the session
    // const expires = new Date(Date.now() + 10 * 1000);
    // const session = await encrypt({ returnedUserDetails, expires });

    // cookies().set('session', session, { expires, httpOnly: true})

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
