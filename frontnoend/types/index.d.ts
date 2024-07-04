type UserRole = "ROLE_BUYER" | "ROLE_SELLER";

interface BuyerProfile {
  id: string,
  shippingAddress: string,
  billingAddress: string,
  defaultPaymentMethod: string,
}

// Cross checked
interface SellerProfile {
  id: string,
  businessName: string,
  businessDescription: string,
}

// Cross checked
interface UserWallet {
  id: string,
  userId: string,
  cashBalance: number,
  walletAddress: string,
  tokTokenBalance: number,
}

// Cross checked
interface UserDetails {
  id: string,
  username: string,
  email: string,
  firstName: string,
  lastName: string,
  roles: UserRole[],
  buyerProfile?: BuyerProfile,
  sellerProfile?: SellerProfile,
  wallet?:UserWallet
}


// Cross checked
type UserSignUpDetails = {
  username: string,
  password: string,
  email: string,
  firstName: string,
  lastName: string,
  roles: UserRole[],
  shippingAddress?: string,
  billingAddress?: string,
  businessName?: string,
  businessDescription?:string,
}

// Cross checked
type UserSignInDetails = {
  username: string,
  password: string,
}
