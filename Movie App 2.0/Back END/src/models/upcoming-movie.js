const mongoose = require("mongoose");

const upcomingMovieSchema = new mongoose.Schema({
    name: {
        type: String,
        trim: true,
        required: true
    },
    posterURL: {
        type: String,
        trim: true,
        required: true
    }
});

const UpcomingMovieModel = mongoose.model('UpcomingMovie', upcomingMovieSchema);

module.exports = {UpcomingMovieModel};
