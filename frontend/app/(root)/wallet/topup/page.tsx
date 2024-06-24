'use client'
import LoginPopup from '@/components/shared/LoginPopup';
import LoginRequired from '@/components/shared/LoginRequired';
import { useAuth } from '@/hooks/auth-provider';
import React, { useState } from 'react';

const WalletTopUpPage: React.FC = () => {
  const auth = useAuth();

  const user = auth?.user || null;

  // TODO: Fix this if not auth display pop
  const [isLoginPopupOpen, setLoginPopupOpen] = useState(user===null);

  const handleClosePopup = () => setLoginPopupOpen(false);
  if (!user) {
    return (
      <div className="flex_center min-h-screen bg-background text-foreground">
        {isLoginPopupOpen ? (
          <LoginPopup isOpen={isLoginPopupOpen} onClose={handleClosePopup} />
        ) : (
          // Display that login is required
          <LoginRequired></LoginRequired>
        )}
      </div>
    );
  }

  
  const [selectedMethod, setSelectedMethod] = useState<'creditCard' | 'uniqueCode' | null>(null);
  const [creditCardNumber, setCreditCardNumber] = useState('');
  const [nameOnCreditCard, setNameOnCreditCard] = useState('');
  const [cvv, setCvv] = useState('');
  const [expirationDate, setExpirationDate] = useState('');
  const [uniqueCode, setUniqueCode] = useState('');

  const handleMethodSelect = (method: 'creditCard' | 'uniqueCode') => {
    setSelectedMethod(method);
  };

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    if (name === 'creditCardNumber') {
      setCreditCardNumber(value);
    } else if (name === 'nameOnCreditCard') {
      setNameOnCreditCard(value);
    } else if (name === 'cvv') {
      setCvv(value);
    } else if (name === 'expirationDate') {
      setExpirationDate(value);
    } else if (name === 'uniqueCode') {
      setUniqueCode(value);
    }
  };

  return (
    <div className="min-h-screen bg-gray-100 flex flex-col justify-center items-center py-12">
      <div className="max-w-md w-full bg-white p-8 shadow-lg rounded-lg">
        <h2 className="text-xl font-semibold mb-4">Choose Top-Up Method</h2>
        
        <div className="flex justify-between mb-4">
          <button
            className={`w-1/2 px-3 py-3 rounded-lg ${
              selectedMethod === 'creditCard' ? 'bg-red-500 text-white' : 'bg-gray-200 text-gray-600'
            } hover:bg-red-200`}
            onClick={() => handleMethodSelect('creditCard')}
          >
            Credit Card
          </button>
          <button
            className={`w-1/2 px-3 py-3 rounded-lg ${
              selectedMethod === 'uniqueCode' ? 'bg-red-500 text-white' : 'bg-gray-200 text-gray-600'
            } hover:bg-red-200`}
            onClick={() => handleMethodSelect('uniqueCode')}
          >
            Unique Code
          </button>
        </div>

        {selectedMethod === 'creditCard' && (
          <>
            <input
              type="text"
              className="w-full border border-gray-300 rounded-lg py-2 px-3 mb-4 focus:outline-none focus:border-blue-500"
              placeholder="Enter Credit Card Number"
              name="creditCardNumber"
              value={creditCardNumber}
              onChange={handleInputChange}
            />
            <input
              type="text"
              className="w-full border border-gray-300 rounded-lg py-2 px-3 mb-4 focus:outline-none focus:border-blue-500"
              placeholder="Enter Name On Card"
              name="nameOnCreditCard"
              value={nameOnCreditCard}
              onChange={handleInputChange}
            />
            <div className="flex mb-4">
              <input
                type="text"
                className="w-1/2 mr-2 border border-gray-300 rounded-lg py-2 px-3 focus:outline-none focus:border-blue-500"
                placeholder="CVV"
                name="cvv"
                value={cvv}
                onChange={handleInputChange}
              />
              <input
                type="text"
                className="w-1/2 ml-2 border border-gray-300 rounded-lg py-2 px-3 focus:outline-none focus:border-blue-500"
                placeholder="Expiration Date (MM/YYYY)"
                name="expirationDate"
                value={expirationDate}
                onChange={handleInputChange}
              />
            </div>
          </>
        )}

        {selectedMethod === 'uniqueCode' && (
          <input
            type="text"
            className="w-full border border-gray-300 rounded-lg py-2 px-3 mb-4 focus:outline-none focus:border-green-500"
            placeholder="Enter Unique Code"
            name="uniqueCode"
            value={uniqueCode}
            onChange={handleInputChange}
          />
        )}

        <button className="w-full bg-blue-500 text-white py-2 px-4 rounded-lg shadow-md hover:bg-blue-600">
          Confirm Top-Up
        </button>
      </div>
    </div>
  );
};

export default WalletTopUpPage;
