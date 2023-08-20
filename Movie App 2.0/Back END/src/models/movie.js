const mongoose = require("mongoose");

const movieSchema = new mongoose.Schema({
    name: {
        type: String,
        trim: true,
        required: true
    },
    genre: {
        type: String,
        trim: true,
        required: true,
        lowercase: true
    },
    artists: {
        type: String,
        trim: true,
        required: true
    },
    releaseIn: {
        type: String,
        trim: true,
        required: true
    },
    releaseOut: {
        type: String,
        trim: true,
        required: true
    },
    duration: {
        type: String,
        trim: true,
        required: true
    },
    criticsRating: {
        type: String,
        trim: true,
        required: true
    },
    storyline: {
        type: String,
        trim: true,
        required: true
    },
    artistURL: {
        type: String,
        trim: true,
        required: true
    },
    wikipediaURL: {
        type: String,
        trim: true,
        required: true
    },
    trailerURL: {
        type: String,
        trim: true,
        required: true
    },
    posterURL: {
        type: String,
        trim: true,
        required: true
    },
    cities: [{
        cityName: {
            type: String,
            trim: true,
            required: true
        },
        theatres: [{
            theatreName: {
                type: String,
                trim: true,
                required: true
            },
            hindiShow: [{
                timing: {
                    type: String,
                    trim: true,
                    required: true
                },
                seatsAvailable: {
                    type: Number,
                    trim: true,
                    required: true
                }
            }],
            englishShow: [{
                timing: {
                    type: String,
                    trim: true,
                    required: true
                },
                seatsAvailable: {
                    type: Number,
                    trim: true,
                    required: true
                }
            }]
        }]
    }]
});

const MovieModel = mongoose.model('Movie', movieSchema);

module.exports = {MovieModel};
