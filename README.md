# TikTok-TechJam-2024

## Project Overview

This project integrates multiple technologies for a comprehensive blockchain-enabled application:

- **Frontend**: React (Next.js)
- **Backend**: Spring Boot (Java)
- **Smart Contract**: Solidity (Ethereum)
- **Database**: MongoDB
- **Containerization**: Docker

All components are containerized using Docker for easy local setup.

## Prerequisites

### 1. Docker Desktop
- Download and install Docker Desktop from [Docker's official website](https://www.docker.com/products/docker-desktop/)

### 2. MetaMask Setup

#### 2.1 Install MetaMask Extension
- Download and install MetaMask Extension on Google Chrome from [MetaMask's official website](https://metamask.io/download/)

#### 2.2 Configure Local Hardhat Network
1. Follow the instructions on [MetaMask Docs](https://docs.metamask.io/wallet/how-to/get-started-building/run-devnet/) to import the local Hardhat network with the following configuration:
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
  2. Restart Google Chrome to refresh MetaMask


## Getting Started

1. **Navigate to the Backend directory in the repository**
     ```
     cd /path/to/repository/backend/backend
     ```

2. **Build the Backend**
     ```
     ./mvnw clean package -DskipTests
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
