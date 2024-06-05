import {ImageList, ImageListItem, ImageListItemBar} from "@material-ui/core";
import React from "react";
import {useHistory} from "react-router-dom";
import {useSelector} from "react-redux";

const gridListMain = {
    transform: 'translateZ(0)',
    cursor: 'pointer'
}
const ReleasedMovies = () => {

    const history = useHistory();

    const releasedMovies = useSelector(state => state.movieSlice.filteredMovies);

    const movieClickHandler = (movieId) => {
        history.push(`/movieDetails/${movieId}`);
    }

    return (
      <div className="left">
        <ImageList rowHeight={350} cols={4} style={gridListMain}>
          {releasedMovies.map(movie => (
            <ImageListItem
              className="released-movie-grid-item"
              key={movie._id}
              onClick={() => movieClickHandler(movie._id)}
            >
              <img
                src={`/posters/released/${movie['posterURL']}`}
                className="movie-poster"
                alt={movie['name']}
              />
              <ImageListItemBar title={movie.name} />
            </ImageListItem>
          ))}
        </ImageList>
      </div>
    );
}

export default ReleasedMovies;
