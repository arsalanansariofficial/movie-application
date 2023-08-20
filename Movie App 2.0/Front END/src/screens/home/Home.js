import React, {useEffect} from 'react';
import './Home.css';
import ReleasedMovies from "./ReleasedMovies/ReleasedMovies";
import MovieFilter from "./MovieFilter/MovieFilter";
import UpcomingMovies from "./UpcomingMovies/UpcomingMovies";
import useHttp from "../../hooks/use-http";
import {movieActions} from "../../store/movie-slice";
import {useDispatch} from "react-redux";
import {headerActions} from "../../store/header-slice";
import {domainName} from "../../config/dev";

const styles = {
    upcomingMoviesHeading: {
        textAlign: 'center',
        background: '#ff9999',
        padding: '8px',
        fontSize: '1rem'
    }
}

const Home = () => {

    const dispatch = useDispatch();
    const {error, sendRequest: fetchMoviesHandler} = useHttp();

    useEffect(() => {
        dispatch(headerActions.setDisplayButton(false));
        if (!error) {
            const requestConfiguration = {
                url: `${domainName}/movies`
            }
            const setMoviesData = movies => {
                dispatch(movieActions.setMovies(movies));
                dispatch(movieActions.setFilteredMovies(movies));
            };
            fetchMoviesHandler(requestConfiguration, setMoviesData).then(result => result);
        }
        else alert(error);
    }, [dispatch, error, fetchMoviesHandler]);

    return (
        <div>
            <div style={styles.upcomingMoviesHeading}>
                <span>Upcoming Movies</span>
            </div>
            <UpcomingMovies/>
            <div className="flex-container">
                <ReleasedMovies/>
                <MovieFilter/>
            </div>
        </div>
    );
}

export default Home;
