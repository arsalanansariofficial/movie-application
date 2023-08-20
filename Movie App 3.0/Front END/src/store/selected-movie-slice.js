import {createSlice} from "@reduxjs/toolkit";

const setInitialState = () => {
    const movie = JSON.parse(sessionStorage.getItem('movie'));
    if (movie) {
        const showDates = getShowDates(movie);
        const cities = movie.cities;
        const city = cities[0];
        const theatres = city.theatres;
        const theatre = theatres[0];
        const showsInTheatre = Object.keys(theatre).splice(2, 2);
        const selectedShow = showsInTheatre[0];
        const showTimesInSelectedShow = theatre[selectedShow];
        const selectedShowObject = theatre[selectedShow][0];
        const tickets = getTickets(selectedShowObject);
        return {
            movie,
            showDates,
            cities,
            city,
            theatres,
            theatre,
            showsInTheatre,
            selectedShow,
            showTimesInSelectedShow,
            selectedShowObject,
            tickets,
        };
    }
    return {};
}

const getShowDates = movie => {
    let startDate = new Date(movie['releaseIn']);
    let endDate = new Date(movie['releaseOut']);
    let showDates = [];
    let i = startDate;
    while (i <= endDate) {
        showDates.push(i.toISOString().split('T')[0]);
        let newDate = i.setDate(i.getDate() + 1);
        i = new Date(newDate);
    }
    return showDates;
}

const getTickets = selectedShowObject => {
    let tickets = [];
    for (let i = 1; i <= selectedShowObject['seatsAvailable']; i++) {
        tickets.push(i);
    }
    return tickets;
}

const movieReducer = (state, movie) => {
    const cities = movie['cities'];
    const city = cities[0];
    const showDates = getShowDates(movie);
    state.movie = movie;
    state.showDates = showDates;
    state.cities = cities;
    cityReducer(state, city);
}

const cityReducer = (state, city) => {
    const theatres = city.theatres;
    state.city = city;
    theatresReducer(state, theatres);
}

const theatresReducer = (state, theatres) => {
    const theatre = theatres[0];
    state.theatres = theatres;
    theatreReducer(state, theatre);
}

const theatreReducer = (state, theatre) => {
    const showsInTheatre = Object.keys(theatre).splice(2, 2);
    const selectedShow = showsInTheatre[0];
    state.theatre = theatre;
    state.showsInTheatre = showsInTheatre;
    selectedShowReducer(state, selectedShow);
}

const selectedShowReducer = (state, selectedShow) => {
    const showTimesInSelectedShow = state.theatre[selectedShow];
    const selectedShowObject = state.theatre[selectedShow][0];
    state.selectedShow = selectedShow;
    state.showTimesInSelectedShow = showTimesInSelectedShow;
    selectedShowObjectReducer(state, selectedShowObject);
}

const selectedShowObjectReducer = (state, selectedShowObject) => {
    const tickets = getTickets(selectedShowObject);
    state.selectedShowObject = selectedShowObject;
    state.tickets = tickets;
}

const selectedMovieSlice = createSlice({
    name: 'selectedMovieSlice',
    initialState: setInitialState(),
    reducers: {
        setMovie(state, action) {
            movieReducer(state, action.payload);
        },
        setCity(state, action) {
            cityReducer(state, action.payload);
        },
        setTheatres(state, action) {
            theatresReducer(state, action.payload);
        },
        setTheatre(state, action) {
            theatreReducer(state, action.payload);
        },
        setSelectedShow(state, action) {
            selectedShowReducer(state, action.payload);
        },
        setSelectedShowObject(state, action) {
            selectedShowObjectReducer(state, action.payload);
        }
    }
});

export const selectedMovieActions = selectedMovieSlice.actions;
export default selectedMovieSlice.reducer;
