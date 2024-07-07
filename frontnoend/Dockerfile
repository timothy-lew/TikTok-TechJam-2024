#FROM node:20-alpine
#WORKDIR /app
#COPY package*.json ./
#RUN npm ci
#COPY . .
#CMD ["npm", "run", "dev"]

FROM node:20-alpine

WORKDIR /app

COPY package*.json ./
RUN npm ci

COPY . .

# Build-time environment variable
ARG NEXT_PUBLIC_BACKEND_URL
ENV NEXT_PUBLIC_BACKEND_URL=$NEXT_PUBLIC_BACKEND_URL

RUN npm run build

# Runtime environment variable
ENV NEXT_PUBLIC_RUNTIME_BACKEND_URL=$NEXT_PUBLIC_BACKEND_URL

CMD ["sh", "-c", "NEXT_PUBLIC_BACKEND_URL=$NEXT_PUBLIC_RUNTIME_BACKEND_URL npm start"]
