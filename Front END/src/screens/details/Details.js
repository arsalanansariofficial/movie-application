import React, {useEffect} from 'react';
import Typography from '@material-ui/core/Typography';
import './Details.css';
import {Link, useParams} from 'react-router-dom';
import ArtistDetails from "./ArtistDetails/ArtistDetails";
import MovieDetails from "./MovieDetails/MovieDetails";
import {useDispatch, useSelector} from "react-redux";
import useHttp from "../../hooks/use-http";
import {selectedMovieActions} from "../../store/selected-movie-slice";

const Details = () => {

    const {id} = useParams();
    const {error, sendRequest: fetchMovieHandler} = useHttp();
    const dispatch = useDispatch();
    const movie = useSelector(state => state.selectedMovieSlice.movie);

    useEffect(() => {
        if (!error) {
            const requestConfigurations = {
              url: `${process.env.REACT_APP_DOMAIN_NAME}/movies/${id}`
            };
            const setMovieData = movie => {
                dispatch(selectedMovieActions.setMovie(movie));
                sessionStorage.setItem('movie', JSON.stringify(movie));
            }
            fetchMovieHandler(requestConfigurations, setMovieData).then(result => result);
        }
    }, [dispatch, error, fetchMovieHandler, id]);

    return (
        <div className="details">
            <div className="back">
                <Typography>
                    <Link to="/">&#60; Back to Home</Link>
                </Typography>
            </div>
            <div className="flex-containerDetails">
                {
                    movie &&
                    <div className="leftDetails">
                        <img src={`/posters/released/${movie['posterURL']}`} alt={movie["name"]} style={{width: '200px', height: '200px'}}/>
                    </div>
                }
                <MovieDetails/>
                <ArtistDetails/>
            </div>
        </div>
    );
}

export default Details;
