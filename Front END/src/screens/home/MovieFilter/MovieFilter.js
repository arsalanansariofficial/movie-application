import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import FormControl from "@material-ui/core/FormControl";
import Typography from "@material-ui/core/Typography";
import InputLabel from "@material-ui/core/InputLabel";
import Input from "@material-ui/core/Input";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import React, {useEffect, useState} from "react";
import Select from "@material-ui/core/Select";
import {MenuItem} from "@material-ui/core";
import {useDispatch, useSelector} from "react-redux";
import {movieActions} from "../../../store/movie-slice";

const styles = theme => ({
    formControl: {
        margin: theme.spacing(1),
        minWidth: 240,
        maxWidth: 240
    }
});

const MovieFilter = () => {

    const dispatch = useDispatch();
    const releasedMovies = useSelector(state => state.movieSlice.movies);

    const genres = useSelector(state => state.movieSlice.genres);
    const artists = useSelector(state => state.movieSlice.artists);

    const [movieName, setMovieName] = useState("");
    const [genre, setGenre] = useState("");
    const [artist, setArtist] = useState("");
    const [startDate, setStartDate] = useState("");
    const [endDate, setEndDate] = useState("");
    const [filterStartDate, setFilterStartDate] = useState("");
    const [filterEndDate, setFilterEndDate] = useState("");

    useEffect(() => {
        const movies = releasedMovies.filter(movie => {
            let filter = true;
            if (movieName && !movie.name.toLowerCase().includes(movieName.toLowerCase()))
                filter = false;
            if (filter && genre && !movie["genre"].toLowerCase().includes(genre.toLowerCase()))
                filter = false;
            if (filter && artist && !movie["artists"].toLowerCase().includes(artist.toLowerCase()))
                filter = false;
            if (filter && filterStartDate && filterEndDate) {
                const dateOne = new Date(filterStartDate);
                const dateTwo = new Date(filterEndDate);
                const movieDate = new Date(movie["releaseIn"]);
                const dateFilter = movieDate >= dateOne && movieDate <= dateTwo;
                if (!dateFilter) filter = false;
            }
            return filter;
        });
        dispatch(movieActions.setFilteredMovies(movies));
    }, [movieName, genre, artist, filterStartDate, filterEndDate, releasedMovies, dispatch]);

    const HeadingForm = () => {
        return (
            <FormControl style={styles.formControl} fullWidth={true}>
                <Typography style={styles.title} color="textSecondary">
                    FIND MOVIES BY:
                </Typography>
            </FormControl>
        );
    }

    const GenreForm = () => {
        return (
            <FormControl style={styles.formControl} fullWidth={true}>
                <InputLabel>Genres</InputLabel>
                <Select value={genre}
                        onChange={(event) => genreSelectHandler(event.target.value)}>
                    {
                        genres.map(genre => {
                            return (
                                <MenuItem key={genre} value={genre}>
                                    {genre}
                                </MenuItem>
                            );
                        })
                    }
                </Select>
            </FormControl>
        );
    }

    const ArtistForm = () => {
        return (
            <FormControl style={styles.formControl} fullWidth={true}>
                <InputLabel>Artists</InputLabel>
                <Select value={artist}
                        onChange={(event) => artistSelectHandler(event.target.value)}>
                    {
                        artists.map(artist => {
                            return (
                                <MenuItem key={artist} value={artist}>
                                    {artist}
                                </MenuItem>
                            );
                        })
                    }
                </Select>
            </FormControl>
        );
    }

    const StartDateForm = () => {
        return (
            <FormControl style={styles.formControl} fullWidth={true}>
                <TextField type="date" value={startDate}
                           onChange={(event) => startDateHandler(event.target.value)}/>
            </FormControl>
        );
    }

    const EndDateForm = () => {
        return (
            <FormControl style={styles.formControl} fullWidth={true}>
                <TextField label="Release Date Start" type="date" value={endDate} InputLabelProps={{shrink: true}}
                           onChange={(event) => EndDateHandler(event.target.value)}/>
            </FormControl>
        );
    }

    const movieNameChangeHandler = (movieName) => {
        setMovieName(movieName);
    }

    const genreSelectHandler = (genre) => {
        setGenre(genre);
    }

    const artistSelectHandler = (artist) => {
        setArtist(artist);
    }

    const startDateHandler = (startDate) => {
        setStartDate(startDate);
    }

    const EndDateHandler = (endDate) => {
        setEndDate(endDate);
    }

    const applyFilter = () => {
        setFilterStartDate(startDate);
        setFilterEndDate(endDate);
    }

    const clearFilter = () => {
        dispatch(movieActions.setFilteredMovies(releasedMovies));
    }


    return (
        <div className="right">
            <Card>
                <CardContent>
                    <HeadingForm/>
                    <FormControl style={styles.formControl} fullWidth={true}>
                        <InputLabel htmlFor="movieName">Movie Name</InputLabel>
                        <Input id="movieName" value={movieName}
                               onChange={(event) => movieNameChangeHandler(event.target.value)}/>
                    </FormControl>
                    <GenreForm/>
                    <ArtistForm/>
                    <br/><br/>
                    <StartDateForm/>
                    <br/><br/>
                    <EndDateForm/>
                    <br/><br/>
                    <FormControl style={styles.formControl} fullWidth={true}>
                        <Button variant="contained" color="primary"
                                onClick={() => applyFilter()}>
                            APPLY
                        </Button>
                    </FormControl>
                    <br/><br/>
                    <FormControl style={styles.formControl} fullWidth={true}>
                        <Button variant="contained" color="default"
                                onClick={() => clearFilter()}>
                            Clear Filter
                        </Button>
                    </FormControl>
                </CardContent>
            </Card>
        </div>
    );
}

export default MovieFilter;
