# TikTok-TechJam-2024

## Table of Contents

  * [Contributors](#contributors)
  * [Project Overview](#project-overview)
  * [Pre-requisites](#pre-requisites)
  * [Getting Started](#getting-started)
  * [Deployment](#deployment)

## Contributors
- @Alexlaaa (Alex) Backend and MongoDB Development
- @Greg-Lim (Greg) Frontend Development
- @LimJiaEarn (Jia Earn) Frontend Development
- @jpl12345 (John) Frontend Development
- @timothy-lew (Timothy) Smart Contract Development

## Project Overview

This repository hosts the source code for our project entry, **Tok Coin: Empowering Financial Inclusion, in the 2024 TikTok TechJam**.

Introducing Tok Coin, a cryptocurrency intended to be accessible to all, offering a decentralized and secure gateway for the ‘unbanked’ to the world of digital payments. 

Our innovative solution provides a means for users to exchange fiat money to cryptocurrency through physical gift cards purchasable with cash at local convenience stores. These gift cards are then activated seamlessly on the TikTok platform.  Subsequently, they can use the balance in their accounts to buy Tok Coin. This approach leverages TikTok's platform and an easy-to-understand method of acquiring digital currency, making it accessible to a broader audience.

Tok Coin offers significant advantages over traditional cash, including facilitating international payments without conversion fees, providing transparent and secure transactions, and offering greater stability to reduce currency risk. This is particularly beneficial for regions with a large proportion of ‘unbanked’ individuals, such as Africa and the Middle East, where wild currency fluctuations are common.

We have developed a proof-of-concept that demonstrates the entire process of purchasing a gift card, converting it to Tok Coins, and exchanging it back to cash. Additionally, our demonstration includes how Tok Coin can be used for transactions in an online store, showcasing the experience from both the buyer's and seller's perspectives.


This project integrates multiple technologies for a comprehensive blockchain-enabled application:

- **Frontend**: React (Next.js)
- **Backend**: Spring Boot (Java)
- **Smart Contract**: Solidity (Ethereum)
- **Database**: MongoDB
- **Server Configuration**: Nginx
- **Containerization**: Docker

All components are containerized using Docker for easy local setup.

## Pre-requisites

### 1. Docker Desktop
- Download and install Docker Desktop from [Docker's official website](https://www.docker.com/products/docker-desktop/)

### 2. MetaMask Setup

#### 2.1 Install MetaMask Extension
- Download and install MetaMask Extension on Google Chrome from [MetaMask's official website](https://metamask.io/download/)

#### 2.2 Configure Local Hardhat Network
1. Follow the instructions on [MetaMask Docs](https://docs.metamask.io/wallet/how-to/run-devnet/) to import the local Hardhat network with the following configuration:
   - **Network Name:** localhost-hardhat
   - **RPC URL:** http://127.0.0.1:8545/
   - **Chain ID:** 31337
   - **Currency Symbol:** ETH

#### 2.3 Import Wallets
1. **TikTok's Wallet:**
   - Click on "Add Account" -> "Import Account"
   - Enter private key: `0xac0974bec39a17e36ba4a6b4d238ff944bacb478cbed5efcae784d7bf4f2ff80`

2. **User 1's Wallet (buyer):**
   - Repeat the import process with private key: `0x59c6995e998f97a5a0044966f0945389dc9e86dae88c7a8412f4603b6b78690d`

3. **User 2's Wallet (seller):**
   - Repeat the import process with private key: `0x5de4111afa1a4b94908f83103eb1f1706367c2e68ca870fc3fb9a804cdab365a`

#### 2.4 Import TOKToken
- Import `TOKToken` to MetaMask using contract address: `0x5FbDB2315678afecb367f032d93F642f64180aa3`

#### 2.5 Troubleshooting
- If you encounter a nonce error in MetaMask:
  1. Reset the nonce settings for all wallets ([Reset Nonce Guide](https://docs.metamask.io/wallet/how-to/run-devnet/#reset-your-local-nonce-calculation))
     
     Perform the following for each imported wallet:
     
     MetaMask Plugin -> More options icon -> Settings -> Advanced -> Clear activity tab data
  3. Restart Google Chrome to refresh MetaMask


## Getting Started

1. **Navigate to the Backend directory in the repository**
     ```
     cd /path/to/repository/backend/backend
     ```

2. **Build the Backend**
   
     For Mac:
     ```
     ./mvnw clean package -DskipTests
     ```

     Alternatively, for Windows:
    ```
     mvnw clean package -DskipTests
     ```

2. **Navigate to the root directory of the repository**
     ```
     cd ../..
     ```

3. **Build the Docker Containers**
     ```
     docker compose up --build
     ```

4. **[Optional] Connect MongoDB Compass to Local MongoDB**

   Use the following connection string in MongoDB Compass to connect to your locally running MongoDB instance
     ```
     mongodb://root:secret@localhost:27017/?retryWrites=true&w=majority&appName=Cluster0
     ```

5. **Log in information for our web-app**

   **Buyer account:**

   username: buyer, password: password

   **Seller account:**

   username: seller, password: password

   **Buyer & Seller account:**

   username: user, password: password
     

## Deployment

https://tiktok-techjam-pearl.vercel.app/

#### Instead of configuring local hardhat network (in 2.2), we will use the Sepolia Testnet.
#### Sepolia Testnet

- Follow the instructions on [Metamask Docs](https://docs.metamask.io/wallet/how-to/get-started-building/run-devnet/) to import the Sepolia testnet network with the following configuration:
  - **Network Name:** Sepolia test network
  - **RPC URL:** https://sepolia.infura.io/v3/
  - **Chain ID:** 11155111
  - **Currency Symbol:** SepoliaETH
