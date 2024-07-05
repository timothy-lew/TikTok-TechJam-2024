import { Product, BuyerInfo } from "@/types/ShopTypes";
import { Gift } from "lucide-react";
import { MdOutlineShoppingCart } from "react-icons/md";
import { Alert, AlertTitle, AlertDescription } from "../ui/alert";

type ConfirmPurchaseModalProps = {
  product: Product;
  quantity: number;
  buyer: BuyerInfo;
  setQuantity: (quantity: number) => void;
  onClose: () => void;
  onConfirmTiktokCoin: () => void;
  onConfirmCash: () => void;
  shippingAddress: string;
};

export const ConfirmPurchaseModal: React.FC<ConfirmPurchaseModalProps> = ({
  product,
  quantity,
  buyer,
  setQuantity,
  onClose,
  onConfirmTiktokCoin,
  onConfirmCash,
  shippingAddress,
}) => {
  const handleQuantityChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setQuantity(parseInt(event.target.value, 10));
  };

  const increaseQuantity = () => {
    if (product) {
      const newQuantity = Math.min(quantity + 1, product.quantity);
      setQuantity(newQuantity);
    }
  };

  const decreaseQuantity = () => {
    const newQuantity = Math.max(quantity - 1, 1);
    setQuantity(newQuantity);
  };

  return (
    <div className="fixed inset-0 z-10 overflow-y-auto flex items-center justify-center bg-gray-500 bg-opacity-75 p-4">
      <div className="relative bg-white rounded-lg shadow-xl w-full max-w-lg mx-auto p-6 max-h-full overflow-y-auto">
        <div className="flex items-center justify-center mb-4">
          <div className="flex-shrink-0 flex items-center justify-center h-24 w-24 rounded-full bg-blue-100">
            <MdOutlineShoppingCart className="h-12 w-12 text-black-600" />
          </div>
        </div>
        <h3 className="text-2xl leading-6 font-medium text-gray-900 text-center mb-4">
          Confirm Purchase
        </h3>
        {product.discountedTokTokenPrice ? (
          <Alert className="bg-yellow-100 border-l-4 border-yellow-500 text-yellow-700 p-4 mb-4">
            <div className="flex items-center">
              <Gift className="h-5 w-5 mr-2" />
              <div className="text-m">
                <AlertTitle className="font-bold">
                  Enjoy {product.discountRate} Off!
                </AlertTitle>
                <AlertDescription className="text-m">
                  Checkout using Tok Coin as your payment method to enjoy{" "}
                  {product.discountRate} savings!
                </AlertDescription>
              </div>
            </div>
          </Alert>
        ) : (
          ""
        )}

        <div className="border border-gray-300 rounded-lg p-4">
          <div className="text-sm text-gray-900 space-y-2">
            <p className="font-bold">Please review your purchase details:</p>
            <p className="p-1">Seller: {product.businessName}</p>
            <p className="p-1">Seller ID: {product.sellerProfileId}</p>
            <p className="p-1">Product: {product.name}</p>
            <p className="p-1">
              Recipient: {buyer.firstName + " " + buyer.lastName}
            </p>
            <p className="p-1">Shipping Address: {shippingAddress}</p>
            <p className="p-1">
              Price:{" "}
              {product.discountedTokTokenPrice ? (
                <strong>
                  ${(product.price * quantity).toFixed(2)} or{" "}
                  {Math.floor(product.discountedTokTokenPrice) * quantity} Tok
                  Coins
                </strong>
              ) : (
                <strong>
                  ${(product.price * quantity).toFixed(2)} or{" "}
                  {product.tokTokenPrice * quantity} Tok Coins
                </strong>
              )}
            </p>
            <div className="p-1 flex items-center space-x-2">
              <span>Quantity:</span>
              <button
                onClick={decreaseQuantity}
                className="px-2 py-1 border rounded"
              >
                -
              </button>
              <input
                type="number"
                min="1"
                max={product.quantity}
                value={quantity}
                onChange={handleQuantityChange}
                className="w-12 text-center border rounded"
              />
              <button
                onClick={increaseQuantity}
                className="px-2 py-1 border rounded"
              >
                +
              </button>
            </div>
          </div>
        </div>
        <div className="px-3 py-3 sm:px-6 sm:flex sm:space-x-4 mt-4">
          <button
            type="button"
            className="w-full sm:w-auto flex-grow inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-red-500 text-base font-medium text-white hover:bg-red-600 sm:text-sm"
            onClick={onConfirmTiktokCoin}
          >
            Buy with Tok Coin
          </button>
          <button
            type="button"
            className="w-full sm:w-auto flex-grow inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-red-500 text-base font-medium text-white hover:bg-red-600 mt-2 sm:mt-0 sm:text-sm"
            onClick={onConfirmCash}
          >
            Buy with Cash
          </button>
          <button
            type="button"
            className="w-full sm:w-auto flex-grow inline-flex justify-center rounded-md border border-gray-300 shadow-sm px-4 py-2 bg-white text-base font-medium text-gray-700 hover:bg-gray-50 mt-2 sm:mt-0 sm:text-sm"
            onClick={onClose}
          >
            Cancel
          </button>
        </div>
      </div>
    </div>
  );
};
