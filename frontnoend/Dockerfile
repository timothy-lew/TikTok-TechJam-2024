FROM node:20-alpine
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
CMD ["npm", "run", "dev"]

#FROM node:20-alpine
#
#WORKDIR /app
#
#COPY package*.json ./
#RUN npm ci
#
#COPY . .
#RUN npm run build
#
#CMD ["npm", "start"]
