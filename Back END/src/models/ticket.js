const mongoose = require("mongoose");

const ticketSchema = new mongoose.Schema({
    userId: {
        type: String,
        trim: true,
        required: true
    },
    movieId: {
        type: String,
        trim: true,
        required: true
    },
    cityId: {
        type: String,
        trim: true,
        required: true
    },
    theatreId: {
        type: String,
        trim: true,
        required: true
    },
    movieShowId: {
        type: String,
        trim: true,
        required: true
    },
    movieShowLanguage: {
        type: String,
        trim: true,
        required: true
    },
    numberOfTickets: {
        type: Number,
        trim: true,
        required: true
    },
    ticketPrice: {
        type: Number,
        trim: true,
        required: true
    },
    totalAmount: {
        type: Number,
        trim: true,
        required: true
    }
}, {
    timestamps: true
});

const TicketModel = mongoose.model('Ticket', ticketSchema);

module.exports = {TicketModel};
