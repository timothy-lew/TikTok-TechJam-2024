'use client'
import { useState } from "react";
import { Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle } from "../ui/card";
import { Button } from "../ui/button";

type Product = {
    id: string;
    sellerProfileId: string;
    name: string;
    description: string;
    price: number;
    quantity: number;
    imageUrl: string;
  };

type ModalProps = {
  product: Product;
  onClose: () => void;
  onConfirm: () => void;
};

const ProductCardDetails = ({ product, sellerUsername }: { product: Product; sellerUsername: string | null }) => {
  const [isModalOpen, setIsModalOpen] = useState(false);

  const openModal = () => setIsModalOpen(true);
  const closeModal = () => setIsModalOpen(false);
  const confirmPurchase = () => {
    // Handle purchase logic here
    closeModal();
  };

  const { id, name, description, price, quantity, imageUrl } = product;

  return (
    <div className="w-full sm:w-1/2 mx-auto">
      <Card className="flex overflow-hidden flex-col">
        <div className="relative w-full h-auto aspect-video">
          {imageUrl && (
            <Image src={`data:image/png;base64,${imageUrl}`} fill alt={name} className="object-contain w-full h-full" />
          )}
        </div>
        <CardHeader>
          <CardTitle>{name}</CardTitle>
          <CardDescription>${price}</CardDescription>
          <CardDescription>Sold by {sellerUsername}</CardDescription>
        </CardHeader>
        <CardContent className="flex-grow">
          <h3 className="line-clamp-4">{description}</h3>
          <p>Quantity available: {quantity}</p>
        </CardContent>
        <CardFooter>
          <Button onClick={openModal} size="lg" className="w-full">
            Buy Now
          </Button>
        </CardFooter>
      </Card>
      {isModalOpen && <Modal product={product} onClose={closeModal} onConfirm={confirmPurchase} />}
    </div>
  );
};

const Modal = ({ product, onClose, onConfirm }: ModalProps) => {
  return (
    <div className="fixed inset-0 z-10 overflow-y-auto">
      <div className="fixed inset-0 bg-gray-500 bg-opacity-75 transition-opacity"></div>
      <div className="flex items-center justify-center min-h-screen px-4 text-center sm:block sm:p-0">
        <div className="relative inline-block transform bg-white rounded-lg text-left shadow-xl transition-all sm:my-8 sm:align-middle sm:max-w-lg w-full">
          <div className="bg-white px-4 pt-5 pb-4 sm:p-6 sm:pb-4">
            <div className="sm:flex sm:items-start">
              <div className="mx-auto flex-shrink-0 flex items-center justify-center h-12 w-12 rounded-full bg-red-100 sm:mx-0 sm:h-10 sm:w-10">
                <svg className="h-6 w-6 text-red-600" fill="none" viewBox="0 0 24 24" stroke="currentColor" aria-hidden="true">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="1.5" d="M12 9v3.75m-9.303 3.376c-.866 1.5.217 3.374 1.948 3.374h14.71c1.73 0 2.813-1.874 1.948-3.374L13.949 3.378c-.866-1.5-3.032-1.5-3.898 0L2.697 16.126zM12 15.75h.007v.008H12v-.008z" />
                </svg>
              </div>
              <div className="mt-3 text-center sm:mt-0 sm:ml-4 sm:text-left">
                <h3 className="text-lg leading-6 font-medium text-gray-900" id="modal-title">
                  Confirm Purchase
                </h3>
                <div className="mt-2">
                  <p className="text-sm text-gray-500">Review your purchase details:</p>
                  <p className="text-sm text-gray-500">Product: {product.name}</p>
                  <p className="text-sm text-gray-500">Price: ${product.price}</p>
                  <p className="text-sm text-gray-500">Quantity: {product.quantity}</p>
                </div>
              </div>
            </div>
          </div>
          <div className="bg-gray-50 px-4 py-3 sm:px-6 sm:flex sm:flex-row-reverse">
            <button type="button" className="w-full inline-flex justify-center rounded-md border border-transparent shadow-sm px-4 py-2 bg-blue-600 text-base font-medium text-white hover:bg-blue-700 sm:ml-3 sm:w-auto sm:text-sm" onClick={onConfirm}>
              Confirm
            </button>
            <button type="button" className="mt-3 w-full inline-flex justify-center rounded-md border border-gray-300 shadow-sm px-4 py-2 bg-white text-base font-medium text-gray-700 hover:bg-gray-50 sm:mt-0 sm:w-auto sm:text-sm" onClick={onClose}>
              Cancel
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProductCardDetails;
