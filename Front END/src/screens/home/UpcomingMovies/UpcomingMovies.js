import {ImageList, ImageListItem, ImageListItemBar} from "@material-ui/core";
import React, {useEffect, useState} from "react";
import useHttp from "../../../hooks/use-http";

const styles = {
    gridListUpcomingMovies: {
        flexWrap: 'nowrap',
        transform: 'translateZ(0)',
        width: '100%'
    }
}

const UpcomingMovies = () => {

    const {error, sendRequest: fetchUpcomingMoviesHandler} = useHttp();

    const [upcomingMovies, setUpcomingMovies] = useState([]);

    if (error) alert(error);

    useEffect(() => {
        if (!error) {
            const requestConfiguration = {
              url: `${process.env.REACT_APP_DOMAIN_NAME}/upcoming-movies`
            };
            const setUpcomingMoviesData = upcomingMovies => {
                setUpcomingMovies(upcomingMovies);
            }
            fetchUpcomingMoviesHandler(requestConfiguration, setUpcomingMoviesData).then(result => result);
        }
    }, [error, fetchUpcomingMoviesHandler]);

    return (
      <ImageList cols={5} style={styles.gridListUpcomingMovies}>
        {upcomingMovies.map(movie => (
          <ImageListItem key={movie._id}>
            <img
              src={`/posters/upcoming/${movie['posterURL']}`}
              className="movie-poster"
              alt={movie.name}
            />
            <ImageListItemBar title={movie.name} />
          </ImageListItem>
        ))}
      </ImageList>
    );
}

export default UpcomingMovies;
