const express = require("express");
const upcomingMovieRouter = new express.Router();
const {UpcomingMovieModel} = require("../models/upcoming-movie");

upcomingMovieRouter.post('/upcoming-movies', async (request, response) => {
    const upcomingMovie = new UpcomingMovieModel(request.body);
    try {
        const databaseResponse = await upcomingMovie.save();
        response.status(201).send(databaseResponse);
    } catch (error) {
        response.status(500).send(error);
    }
});

upcomingMovieRouter.get('/upcoming-movies', async (request, response) => {
    try {
        const databaseResponse = await UpcomingMovieModel.find({});
        response.status(200).send(databaseResponse);
    } catch (error) {
        response.status(500).send(error);
    }
});

upcomingMovieRouter.get('/upcoming-movies/:id', async (request, response) => {
    try {
        const databaseResponse = await UpcomingMovieModel.findById(request.params.id);
        response.status(200).send(databaseResponse);
    } catch (error) {
        response.status(500).send(error);
    }
});

module.exports = upcomingMovieRouter;
