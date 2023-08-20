import {configureStore} from "@reduxjs/toolkit";
import userReducer from "./user-slice";
import movieReducer from "./movie-slice";
import selectedMovieReducer from "./selected-movie-slice";
import upcomingMovieReducer from "./upcoming-movie-slice";
import headerSliceReducer from "./header-slice";

const store = configureStore({
    reducer: {
        userSlice: userReducer,
        movieSlice: movieReducer,
        selectedMovieSlice: selectedMovieReducer,
        upcomingMovieSlice: upcomingMovieReducer,
        headerSlice: headerSliceReducer
    }
});

export default store;
