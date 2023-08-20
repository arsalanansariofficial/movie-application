import Typography from "@material-ui/core/Typography";
import YouTube from "react-youtube";
import React, {useEffect} from "react";
import {useDispatch, useSelector} from "react-redux";
import {headerActions} from "../../../store/header-slice";

const options = {
    height: '300',
    width: '700',
    playerVars: {
        autoplay: 0
    }
}

const MovieDetails = () => {

    const dispatch = useDispatch();
    const movie = useSelector(state => state.selectedMovieSlice.movie);

    let videoId;
    if (movie)
        if (movie['trailerURL']) videoId = movie['trailerURL'].split('?v=')[1];

    useEffect(() => {
        dispatch(headerActions.setDisplayButton(true));
    }, [dispatch]);

    return (
        movie &&
        <div className="middleDetails">
            <div>
                <Typography variant="h1" component="h2">{movie.name}</Typography>
            </div>
            <br/>
            <div>
                <Typography>
                    <span className="bold">Genres:</span>
                    {movie['genre']}
                </Typography>
            </div>
            <div>
                <Typography>
                    <span className="bold">Duration:</span>
                    {movie['duration']}
                </Typography>
            </div>
            <div>
                <Typography>
                    <span className="bold">Release Date:</span>
                    {movie['releaseIn']}
                </Typography>
            </div>
            <div>
                <Typography>
                    <span className="bold">
                        Rating:
                    </span>
                    {movie['criticsRating']}
                </Typography>
            </div>
            <div className="marginTop16">
                <Typography>
                    <span className="bold">Plot:</span>
                    <a href={movie['wikipediaURL']}>
                        (Wiki Link)
                    </a>
                    {movie['storyline']}
                </Typography>
            </div>
            <div className="trailerContainer">
                <Typography>
                    <span className="bold">Trailer:</span>
                </Typography>
                <YouTube videoId={videoId} opts={options}/>
            </div>
        </div>
    );
}

export default MovieDetails;
