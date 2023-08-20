const express = require("express");
const {allowResponse} = require("./middleware/response-cors");
const movieRouter = require("./routers/movie-router");
const upcomingMovieRouter = require("./routers/upcoming-movie-router");
const userRouter = require("./routers/user-router");
const ticketRouter = require("./routers/ticket-router");
const connectDatabase = require("./database/mongoose");

const app = express();
app.use(allowResponse);

app.use(express.json({limit: '50mb'}));
app.use(express.urlencoded({limit: '50mb', extended: true}));
app.use(movieRouter);
app.use(upcomingMovieRouter);
app.use(userRouter);
app.use(ticketRouter);
connectDatabase().catch(console.error);

module.exports = {app};
