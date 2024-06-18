# TikTok-TechJam-2024

## Dependencies

Make sure that the following dependencies are installed on your system before following the steps below:

- NodeJS - [Installation Link](https://nodejs.org/en/download)
- Java JDK 17 & above - [Installation Link](https://www.oracle.com/sg/java/technologies/downloads/)
- Maven - [Installation Link](https://maven.apache.org/download.cgi)

## Getting Started

Start the application in the following order.

### Backend - Solidity

- TODO -

### Backend - Spring Boot

## Database

1. **Install Docker Desktop:**

   - Download and install Docker Desktop from [Docker's official website](https://www.docker.com/products/docker-desktop/).

2. **Navigate to Your Project Directory:**

   - Open your terminal and change directory to your project folder:
     ```
     cd /backend/backend
     ```

3. **Run Docker Compose:**

   - Execute the following command in your terminal to start the Docker containers defined in your `compose.yaml` file:
     ```
     docker compose -f ./compose.yaml up -d
     ```

4. **Install MongoDB GUI (MongoDB Compass):**

   - Download and install MongoDB Compass from [MongoDB's official website](https://www.mongodb.com/products/compass).
   - MongoDB Compass is a GUI tool to connect to and manage MongoDB databases.

5. **Connect MongoDB Compass to Local MongoDB:**

   - Use the following connection string in MongoDB Compass to connect to your locally running MongoDB instance:
     ```
     mongodb://root:secret@localhost:27017/?retryWrites=true&w=majority&appName=Cluster0
     ```

6. Navigate to the `Backend` directory in the repository

```
cd path/to/repository/TikTok-TechJam-2024/backend/backend
```

2. Compile the project with Maven

```
mvn clean package
```

3. Run the Java Springboot application:

```
java -jar target/backend-0.0.1-SNAPSHOT.jar
```

### Frontend

To start the frontend:

1. Navigate to the `frontend` directory

```
$ cd path/to/repository/TikTok-TechJam-2024/frontend
```

2. Install frontend dependencies

```
$ npm i
```

3. Create the build

```
$ npm run build
```

4. Run the React application

```
$ npm start
```
