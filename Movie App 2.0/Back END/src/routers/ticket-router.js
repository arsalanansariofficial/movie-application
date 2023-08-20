const express = require("express");
const {TicketModel} = require("../models/ticket");
const {authentication} = require("../middleware/authentication");
const {MovieModel} = require("../models/movie");
const ticketRouter = new express.Router();

ticketRouter.post('/tickets', authentication, async (request, response) => {
    const ticketObject = {
        ...request.body,
        totalAmount: request.body.ticketPrice * request.body.numberOfTickets
    }
    const ticket = new TicketModel(ticketObject);
    try {
        // Get movie form the movieId
        const movie = await MovieModel.findById(ticketObject.movieId);

        // Get city from the cityId
        const cityIndex = movie.cities.findIndex(city => city._id.toString() === ticketObject.cityId.toString());
        const city = movie.cities[cityIndex];

        // Get theatre from the theatreId
        const theatreIndex = city.theatres.findIndex(theatre => theatre._id.toString() === ticketObject.theatreId.toString());
        const theatre = city.theatres[theatreIndex];

        // Get movieShow from the movieShowId
        const movieShowIndex = theatre[ticketObject.movieShowLanguage].findIndex(show => show._id.toString() === ticketObject.movieShowId.toString());
        const movieShow = theatre[ticketObject.movieShowLanguage][movieShowIndex];

        if (movieShow.seatsAvailable < ticketObject.numberOfTickets) {
            const errorResponse = {
                code: 400,
                message: `Number of seats available - ${movieShow.seatsAvailable}`
            }
            return response.status(400).send(errorResponse);
        }

        // Update the available seats in the movieShow
        movieShow.seatsAvailable = movieShow.seatsAvailable - ticketObject.numberOfTickets;
        await movie.save();

        const databaseResponse = await ticket.save();
        return response.status(201).send(databaseResponse);
    } catch (error) {
        response.status(500).send(error);
    }
});

ticketRouter.get('/tickets/:id', authentication, async (request, response) => {
    try {
        const databaseResponse = await TicketModel.findById(request.params.id);
        response.status(200).send(databaseResponse);
    } catch (error) {
        response.status(500).send(error);
    }
});

module.exports = ticketRouter;
