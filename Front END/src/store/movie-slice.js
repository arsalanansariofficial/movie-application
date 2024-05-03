import {createSlice} from "@reduxjs/toolkit";

const movieReducer = (state, movies) => {
    let genres = [];
    let artists = [];
    movies.forEach(movie => {
        movie['genre'].split(',').forEach(genre => {
            if (!genres.includes(genre))
                genres.push(genre);
        });
        movie.artists.split(',').forEach(artist => {
            if (!artists.includes(artist))
                artists.push(artist);
        });
    });
    state.movies = movies;
    state.genres = genres;
    state.artists = artists;
}

const movieSlice = createSlice({
    name: 'movieSlice',
    initialState: {
        movies: [],
        artists: [],
        genres: [],
        filteredMovies: []
    },
    reducers: {
        setMovies(state, action) {
            movieReducer(state, action.payload);
        },
        setFilteredMovies(state, action) {
            state.filteredMovies = action.payload;
        }
    }
});

export const movieActions = movieSlice.actions;
export default movieSlice.reducer;
