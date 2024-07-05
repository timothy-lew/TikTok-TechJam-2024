"use server";
import { getBackendUrl } from "@/lib/utils";

// https://www.youtube.com/watch?v=DJvM2lSPn6w
import { cookies } from "next/headers";
import { NextRequest, NextResponse } from "next/server";

export async function getAccessToken(): Promise<string | undefined> {
  const accessToken = cookies().get("accessToken");

  if (!accessToken) {
    console.log("No access token, use refresh token to get new accessToken!");
    const refreshToken = await getRefreshToken();

    if (!refreshToken) {
      // TODO
      throw new Error("No tokens stored!");
    }

    try {
      const response = await fetch(`${getBackendUrl()}/api/refresh-token`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ refreshToken: refreshToken }),
      });

      const data = await response.json();

      const accessTokenNew = data.accessToken;
      const refreshTokenNew = data.refreshToken;

      const accessExpiryNew = new Date(Date.now() + 24 * 60 * 60 * 1000); // 1 day expiry
      const refreshExpiryNew = new Date(Date.now() + 7 * 24 * 60 * 60 * 1000); // 7 days expiry

      cookies().set("accessToken", accessTokenNew, {
        httpOnly: true,
        expires: accessExpiryNew,
        path: "/",
      });
      cookies().set("refreshToken", refreshTokenNew, {
        httpOnly: true,
        expires: refreshExpiryNew,
        path: "/",
      });

      return accessTokenNew;
    } catch (error) {
      throw new Error("Error in getting new access token");
    }
  }

  return accessToken?.value;
}

export async function getRefreshToken(): Promise<string | undefined> {
  const refreshToken = cookies().get("refreshToken");

  return refreshToken?.value;
}

export async function signup(userSignUpDetails: UserSignUpDetails) {
  try {
    const usernameCurr: string = userSignUpDetails.username;
    const passwordCurr: string = userSignUpDetails.password;

    console.log("Inside signup fetching with userSignUpDetails:");
    console.log(userSignUpDetails);

    const responseSignUp = await fetch(`${getBackendUrl()}/api/users/signup`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(userSignUpDetails),
    });

    if (!responseSignUp.ok) throw new Error("Error in Sign-up Request");

    const userDetails: UserDetails = await responseSignUp.json();

    console.log("userDetails:");
    console.log(userDetails);

    const responseSignIn = await fetch(`${getBackendUrl()}/api/login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        username: usernameCurr,
        password: passwordCurr,
      }),
    });

    if (!responseSignIn) throw new Error("Error in Sign-In Request");

    const data = await responseSignIn.json();

    console.log(data);

    const accessToken = data.accessToken;
    const refreshToken = data.refreshToken;

    const nextResponse = NextResponse.next();
    const accessExpiry = new Date(Date.now() + 24 * 60 * 60 * 1000); // 1 day expiry
    const refreshExpiry = new Date(Date.now() + 7 * 24 * 60 * 60 * 1000); // 7 days expiry

    console.log("Setting cookies:");
    console.log(`Access Token Expiry: ${accessExpiry.toUTCString()}`);
    console.log(`Refresh Token Expiry: ${refreshExpiry.toUTCString()}`);

    cookies().set("accessToken", accessToken, {
      httpOnly: true,
      expires: accessExpiry,
      path: "/",
    });
    cookies().set("refreshToken", refreshToken, {
      httpOnly: true,
      expires: refreshExpiry,
      path: "/",
    });

    nextResponse.cookies.set("accessToken", accessToken, {
      httpOnly: true,
      expires: accessExpiry,
    });
    nextResponse.cookies.set("refreshToken", refreshToken, {
      httpOnly: true,
      expires: refreshExpiry,
    });

    console.log("Returning from auth.ts");

    return userDetails;
  } catch (error) {
    console.error("An error occurred:", error);
    throw new Error(`Error in signing up ${error}`);
  }
}

export async function login(userSignInDetails: UserSignInDetails) {
  try {
    console.log("ENV:" + getBackendUrl());
    const responseSignIn = await fetch(`${getBackendUrl()}/api/login`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(userSignInDetails),
    });

    const data = await responseSignIn.json();

    console.log("Inside login in auth.ts - data:");
    console.log(data);

    const userDetails = data.user;

    const accessToken = data.accessToken;
    const refreshToken = data.refreshToken;

    const nextResponse = NextResponse.next();

    const accessExpiry = new Date(Date.now() + 24 * 60 * 60 * 1000); // 1 day expiry

    const refreshExpiry = new Date(Date.now() + 7 * 24 * 60 * 60 * 1000); // 7 days expiry

    cookies().set("accessToken", accessToken, {
      httpOnly: true,
      expires: accessExpiry,
      path: "/",
    });
    cookies().set("refreshToken", refreshToken, {
      httpOnly: true,
      expires: refreshExpiry,
      path: "/",
    });

    nextResponse.cookies.set("accessToken", accessToken, {
      httpOnly: true,
      expires: accessExpiry,
    });
    nextResponse.cookies.set("refreshToken", refreshToken, {
      httpOnly: true,
      expires: refreshExpiry,
    });

    return userDetails;
  } catch (error) {
    console.log(`Error in sign in: ${error}`);

    throw new Error(`Error in signing in ${error}`);
  }
}

export async function logout() {
  // Destroy the session
  cookies().delete("accessToken");
  cookies().delete("refreshToken");
}
