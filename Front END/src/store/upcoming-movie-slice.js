import {createSlice} from "@reduxjs/toolkit";

const upcomingMovieSlice = createSlice({
    name: 'upcomingMovieSlice',
    initialState: {
        upcomingMovies: []
    },
    reducers: {
        setUpcomingMovies(state, action) {
            state.upcomingMovies = action.payload;
        }
    }
});

export const upcomingMoviesActions = upcomingMovieSlice.actions;
export default upcomingMovieSlice.reducer;
