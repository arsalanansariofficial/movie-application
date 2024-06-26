### Movie API

Project based on **Node.js**, it provides API for users to find latest movie release.

### Pre Requisites

- **Node.js** version **21** or greater.

### Dependencies used by the project

1. **multer**.
2. **mongodb**.
3. **bcryptjs**.
4. **express**.
5. **mongoose**.
6. **validator**.
7. **jsonwebtoken**.

### Dev Dependencies used by the project

1. **jest**.
2. **env-cmd**.
3. **nodemon**.
4. **supertest**.

### Available scripts

1. `npm start` for running the application in production environment.

2. `npm run dev` for running the application in local environment.

3. `npm run test` for running the application tests without `watch mode`.

4. `npm run test-dev` for running the application tests with `watch mode`.

### Required environment variables

1. `PORT` specify the port on which the api should run, required in `./config`.

2. `databaseURL` specify the database URL to connect to database `./config`.

3. `secretKey` specify the secret key for hashing the user passwords `./config`.

### Instructions

1. Run the script, this will start the local development server.

2. See `api-documentation.json` for api request in `./public/api`.

