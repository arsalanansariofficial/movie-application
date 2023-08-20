const express = require("express");
const movieRouter = new express.Router();
const {MovieModel} = require("../models/movie");

movieRouter.post('/movies', async (request, response) => {
    const movie = new MovieModel(request.body);
    try {
        const databaseResponse = await movie.save();
        response.status(201).send(databaseResponse);
    } catch (error) {
        response.status(500).send(error);
    }
});

movieRouter.get('/movies', async (request, response) => {
    try {
        const databaseResponse = await MovieModel.find({});
        response.status(200).send(databaseResponse);
    } catch (error) {
        response.status(500).send(error);
    }
});

movieRouter.get('/movies/:id', async (request, response) => {
    try {
        const databaseResponse = await MovieModel.findById(request.params.id);
        response.status(200).send(databaseResponse);
    } catch (error) {
        response.status(500).send(error);
    }
});

module.exports = movieRouter;
