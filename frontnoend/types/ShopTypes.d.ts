export type Product = {
  id: string;
  sellerProfileId: string;
  name: string;
  description: string;
  price: number;
  quantity: number;
  imageUrl: string;
  businessName: string;
  sellerWalletAddress: string;
  tokTokenPrice: number;
  discountRate: number;
  discountAppliesTo: string[];
  discountedPrice: number;
  discountedTokTokenPrice: number;
};

export type BuyerInfo = {
  id: string;
  username: string;
  email: string;
  firstName: string;
  lastName: string;
  roles: string[];
  buyerProfile: BuyerProfile;
  sellerProfile: SellerProfile;
  wallet: UserWallet | null;
};

export type BuyerProfile = {
  id: string;
  shippingAddress: string;
  billingAddress: string;
  defaultPaymentMethod: string;
};

export type SellerProfile = {
  id: string;
  businessName: string;
  businessDescription: string;
};

export type Wallet = {
  id: string;
  cashBalance: number;
  coinBalance: number;
};
