FROM node:20-alpine

# Install Python and build dependencies
RUN apk add --no-cache python3 make g++

WORKDIR /app

COPY package.json package-lock.json ./
RUN yarn install --frozen-lockfile --legacy-peer-deps

COPY . .

CMD ["yarn", "hardhat", "node"]