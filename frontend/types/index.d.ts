type LoginDetails = {
  email: string,
  password: string,
}

interface UserDetails {
  id: string,
  username: string,
  email: string,
  // firstName: string,
  // lastName: string,
  name: string,
  roles: "ROLE_BUYER" | "ROLE_SELLER",
  profilePic?: string,
  cashBalance: number,
  coinBalance: number,
}

type UserSignInDetails = {
  username: string,
  password: string,
}

type UserSignUpDetails = {
  username: string,
  name: string,
  email: string,
  password: string,
}