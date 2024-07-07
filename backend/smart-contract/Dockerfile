FROM node:20-alpine

# Install Python and build dependencies
RUN apk add --no-cache python3 make g++

WORKDIR /app

# Copy package files
COPY package.json package-lock.json ./

# Install dependencies
RUN npm ci --legacy-peer-deps

# Copy the rest of your code
COPY . .

# Compile Hardhat contracts
RUN npx hardhat compile

# Run Hardhat node
CMD ["npx", "hardhat", "node"]