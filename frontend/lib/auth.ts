"use server"

// https://www.youtube.com/watch?v=DJvM2lSPn6w
import { cookies } from 'next/headers';
import { NextRequest, NextResponse } from 'next/server';

export async function signup(userSignUpDetails : UserSignUpDetails){

  try {
    const usernameCurr : string = userSignUpDetails.username;
    const passwordCurr : string = userSignUpDetails.password;

    console.log("userSignUpDetails:");
    console.log(userSignUpDetails);

    const responseSignUp = await fetch('http://localhost:8080/api/users/signup', {

      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(userSignUpDetails),

    });

    if (!responseSignUp.ok) throw new Error('Error in Sign-up Request');

    const userDetails : UserDetails = await responseSignUp.json();

    console.log("userDetails:");
    console.log(userDetails);

    const responseSignIn = await fetch('http://localhost:8080/api/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        username: "alex2",
        password: "password2",
      }),
    });

    if (!responseSignIn) throw new Error('Error in Sign-In Request');

    const data = await responseSignIn.json();

    console.log(data);


    const accessToken = data.accessToken;
    const refreshToken = data.refreshToken;

    const nextResponse = NextResponse.next();
    const accessExpiry = new Date(Date.now() + (15*60*1000)); // 15 minutes expiry
    const refreshExpiry = new Date(Date.now() + (7 * 24 * 60 * 60 * 1000)) // 7 days expiry
    
    console.log("Setting cookies:");
    console.log(`Access Token Expiry: ${accessExpiry.toUTCString()}`);
    console.log(`Refresh Token Expiry: ${refreshExpiry.toUTCString()}`);

    cookies().set('accessToken', accessToken, { httpOnly: true, expires: accessExpiry, path: '/' });
    cookies().set('refreshToken', refreshToken, { httpOnly: true, expires: refreshExpiry, path: '/' });

    nextResponse.cookies.set('accessToken', accessToken, {httpOnly: true, expires:accessExpiry});
    nextResponse.cookies.set('refreshToken', refreshToken, {httpOnly: true, expires: refreshExpiry});
    
    console.log("Returning from auth.ts");

    return userDetails


  } catch (error) {
    console.error('An error occurred:', error);
    throw new Error(`Error in signing up ${error}`);
  }
}

export async function login(userSignInDetails : UserSignInDetails){

  try{

    const responseSignIn = await fetch('http://localhost:8080/api/login', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(userSignInDetails),
    });

    const data = await responseSignIn.json();

    const userDetails = data.user;

    const accessToken = data.accessToken;
    const refreshToken = data.refreshToken;

    const nextResponse = NextResponse.next();

    const accessExpiry = new Date(Date.now() + 15 * 60 * 1000); // 15 minutes expiry
    const refreshExpiry = new Date(Date.now() + 7 * 24 * 60 * 60 * 1000); // 7 days expiry

    cookies().set('accessToken', accessToken, { httpOnly: true, expires: accessExpiry, path: '/' });
    cookies().set('refreshToken', refreshToken, { httpOnly: true, expires: refreshExpiry, path: '/' });

    nextResponse.cookies.set('accessToken', accessToken, {httpOnly: true, expires:accessExpiry});
    nextResponse.cookies.set('refreshToken', refreshToken, {httpOnly: true, expires: refreshExpiry});

    return userDetails

  }
  catch(error){
    console.log(`Error in sign in: ${error}`);

    throw new Error(`Error in signing in ${error}`);
  }



}


export async function logout() {
  // Destroy the session
  cookies().delete('accessToken');
  cookies().delete('refreshToken');

}
