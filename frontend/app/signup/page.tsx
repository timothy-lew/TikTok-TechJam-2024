"use client"
// components/Signup.tsx

import React, { useState } from 'react';
import { useRouter } from 'next/navigation';
import { useAuth } from '@/hooks/auth-provider';

const Signup: React.FC = () => {

  const auth = useAuth();


  const [formData, setFormData] = useState({
    username: '',
    password: '',
    email: '',
    name: '',
    roles: ['ROLE_BUYER'],
  });
  const [isModalOpen, setIsModalOpen] = useState(false);
  const router = useRouter();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value } = e.target;

    if (name === 'roles') {
      setFormData({
        ...formData,
        [name]: value === 'ROLE_BUYER' ? ['ROLE_BUYER'] : ['ROLE_SELLER'],
      });
    } else {
      setFormData({
        ...formData,
        [name]: value,
      });
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try{
      auth?.signUp(formData);
    }
    catch(error){
      alert('Error in signing up!');
    }
  
    // I shifted these code to @lib/auth.ts which is called through our auth provider
    // try {
    //   const response = await fetch('http://localhost:8080/api/users/signup', {
    //     method: 'POST',
    //     headers: {
    //       'Content-Type': 'application/json',
    //     },
    //     body: JSON.stringify(formData),
    //   });

    //   if (response.status === 201) {
    //     setIsModalOpen(true);
    //   } else {
    //     console.error('Signup failed');
    //   }
    // } catch (error) {
    //   console.error('An error occurred:', error);
    // }
  };

  const closeModalAndRedirect = () => {
    setIsModalOpen(false);
    router.push('/');
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100">
      <div className="bg-white p-8 rounded shadow-md w-full max-w-md">
        <h2 className="text-2xl font-bold mb-6 text-center">Sign Up</h2>
        <form onSubmit={handleSubmit}>
          <div className="mb-4">
            <label className="block text-gray-700">Username</label>
            <input
              type="text"
              name="username"
              value={formData.username}
              onChange={handleChange}
              className="mt-1 px-3 py-2 border rounded w-full"
              required
            />
          </div>
          <div className="mb-4">
            <label className="block text-gray-700">Password</label>
            <input
              type="password"
              name="password"
              value={formData.password}
              onChange={handleChange}
              className="mt-1 px-3 py-2 border rounded w-full"
              required
            />
          </div>
          <div className="mb-4">
            <label className="block text-gray-700">Email</label>
            <input
              type="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              className="mt-1 px-3 py-2 border rounded w-full"
              required
            />
          </div>
          <div className="mb-4">
            <label className="block text-gray-700">Name</label>
            <input
              type="text"
              name="name"
              value={formData.name}
              onChange={handleChange}
              className="mt-1 px-3 py-2 border rounded w-full"
              required
            />
          </div>
          <div className="mb-4">
            <label className="block text-gray-700">Role</label>
            <select
              name="roles"
              value={formData.roles[0]}
              onChange={handleChange}
              className="mt-1 px-3 py-2 border rounded w-full"
              required
            >
              <option value="ROLE_BUYER">Buyer</option>
              <option value="ROLE_SELLER">Seller</option>
            </select>
          </div>
          <button
            type="submit"
            className="w-full bg-blue-500 text-white py-2 rounded hover:bg-blue-600 transition duration-200"
          >
            Sign Up
          </button>
        </form>
      </div>

      {isModalOpen && (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
          <div className="bg-white p-6 rounded shadow-md text-center">
            <h3 className="text-lg font-semibold mb-4">Success</h3>
            <p className="mb-4">You have signed up successfully! Please use your credentials to sign in.</p>
            <button
              className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600 transition duration-200"
              onClick={closeModalAndRedirect}
            >
              OK
            </button>
          </div>
        </div>
      )}
    </div>
  );
};

export default Signup;

