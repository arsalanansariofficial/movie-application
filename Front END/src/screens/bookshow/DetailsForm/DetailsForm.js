import Typography from "@material-ui/core/Typography";
import {Link, useHistory} from "react-router-dom";
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import FormControl from "@material-ui/core/FormControl";
import InputLabel from "@material-ui/core/InputLabel";
import Select from "@material-ui/core/Select";
import MenuItem from "@material-ui/core/MenuItem";
import Button from "@material-ui/core/Button";
import React, {useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {selectedMovieActions} from "../../../store/selected-movie-slice";
import Input from "@material-ui/core/Input";

const DetailsForm = () => {

    const unitPrice = 100;
    const history = useHistory();
    const dispatch = useDispatch();
    const user = useSelector(state => state.userSlice.user);
    const movie = useSelector(state => state.selectedMovieSlice.movie);
    const showDates = useSelector(state => state.selectedMovieSlice.showDates);
    const locations = useSelector(state => state.selectedMovieSlice.cities);
    const location = useSelector(state => state.selectedMovieSlice.city);
    const theatres = useSelector(state => state.selectedMovieSlice.theatres);
    const theatre = useSelector(state => state.selectedMovieSlice.theatre);
    const showsInTheatre = useSelector(state => state.selectedMovieSlice.showsInTheatre);
    const selectedShow = useSelector(state => state.selectedMovieSlice.selectedShow);
    const showTimesInSelectedShow = useSelector(state => state.selectedMovieSlice.showTimesInSelectedShow);
    const selectedShowObject = useSelector(state => state.selectedMovieSlice.selectedShowObject);
    const tickets = useSelector(state => state.selectedMovieSlice.tickets);
    const [showDate, setShowDate] = useState('2021-01-01');
    const [ticketCount, setTicketCount] = useState(1);

    const locationChangeHandler = city => {
        dispatch(selectedMovieActions.setCity(city));
    }

    const theatreChangeHandler = theatre => {
        dispatch(selectedMovieActions.setTheatre(theatre));
    }

    const movieShowChangeHandler = movieShow => {
        dispatch(selectedMovieActions.setSelectedShow(movieShow));
    }

    const movieShowTimeChangeHandler = selectedShowObject => {
        dispatch(selectedMovieActions.setSelectedShowObject(selectedShowObject));
    }

    const LinkToMovieDetails = () => <Typography className="back">
        <Link to={`/movieDetails/${movie._id}`}>&#60; Back to Movie Details</Link>
    </Typography>;

    const Title = () => <Typography variant="h5" component="h2" style={{textAlign: 'center'}}>
        BOOK SHOW
    </Typography>;

    const BookShowButton = () => <Button variant="contained" color="primary" fullWidth={true}
                                         onClick={() => bookShow()}>
        BOOK SHOW
    </Button>;

    const LocationForm = () => {
        return (
            locations &&
            <FormControl className="formControl" fullWidth={true} required>
                <InputLabel htmlFor="location">Choose Location:</InputLabel>
                <Select
                    id='location'
                    value={location}
                    onChange={(event) => locationChangeHandler(event.target.value)}>
                    {
                        locations.map(city => {
                            return (
                                <MenuItem key={city._id} value={city}>
                                    {city['cityName']}
                                </MenuItem>
                            );
                        })
                    }
                </Select>
            </FormControl>
        );
    }

    const TheatreForm = () => {
        return (
            theatre &&
            <FormControl className="formControl" fullWidth={true} required>
                <InputLabel htmlFor="theatre">Choose Theatre:</InputLabel>
                <Select
                    id='theatre'
                    value={theatre}
                    onChange={(event) => theatreChangeHandler(event.target.value)}>
                    {
                        theatres.map(theatre => {
                            return (
                                <MenuItem key={theatre._id} value={theatre}>
                                    {theatre['theatreName']}
                                </MenuItem>
                            );
                        })
                    }
                </Select>
            </FormControl>
        );
    }

    const LanguageForm = () => {
        return (
            showsInTheatre &&
            <FormControl className="formControl" fullWidth={true} required>
                <InputLabel htmlFor="movie-shows">Choose Language:</InputLabel>
                <Select
                    id='movie-shows'
                    value={selectedShow}
                    onChange={(event) => movieShowChangeHandler(event.target.value)}>
                    {
                        showsInTheatre.map(movieShow => {
                            return (
                                <MenuItem key={movieShow} value={movieShow}>
                                    {movieShow}
                                </MenuItem>
                            );
                        })
                    }
                </Select>
            </FormControl>
        );
    }

    const ShowDateForm = () => {
        return (
            showDates &&
            <FormControl className="formControl" fullWidth={true} required>
                <InputLabel htmlFor="date">Choose Date:</InputLabel>
                <Select
                    id='date'
                    value={showDate}
                    onChange={(event) => setShowDate(event.target.value)}>
                    {
                        showDates.map(date => {
                            return (
                                <MenuItem key={date} value={date}>
                                    {date}
                                </MenuItem>
                            );
                        })
                    }
                </Select>
            </FormControl>
        );
    }

    const ShowTimingForm = () => {
        return (
            showTimesInSelectedShow &&
            <FormControl className="formControl" fullWidth={true} required>
                <InputLabel htmlFor="show-time">Choose Show Time:</InputLabel>
                <Select
                    id='show-time'
                    value={selectedShowObject}
                    onChange={(event) => movieShowTimeChangeHandler(event.target.value)}>
                    {
                        showTimesInSelectedShow.map(showTime => {
                            return (
                                <MenuItem key={showTime._id} value={showTime}>
                                    {showTime['timing']}
                                </MenuItem>
                            );
                        })
                    }
                </Select>
            </FormControl>
        );
    }

    const TicketForm = () => {
        if (tickets.length > 0)
            return (
                <FormControl className="formControl" fullWidth={true} required>
                    <InputLabel htmlFor="ticket">Number Of Tickets:</InputLabel>
                    <Select
                        id='ticket'
                        value={ticketCount}
                        onChange={(event) => setTicketCount(event.target.value)}>
                        {
                            tickets.map(ticket => {
                                return (
                                    <MenuItem key={ticket} value={ticket}>
                                        {ticket}
                                    </MenuItem>
                                );
                            })
                        }
                    </Select>
                </FormControl>
            );
        return (
            <FormControl className="formControl" fullWidth={true}>
                <InputLabel htmlFor="ticket">Number Of Tickets:</InputLabel>
                <Input type="text" value="No tickets available" disabled={true}></Input>
            </FormControl>
        );
    }

    const bookShow = () => {
        const ticketObject = {
            userId: user.user._id,
            movieId: movie._id,
            cityId: location._id,
            theatreId: theatre._id,
            movieShowLanguage: selectedShow,
            movieShowId: selectedShowObject._id,
            numberOfTickets: ticketCount,
            ticketPrice: unitPrice,
            showDate: showDate,
            location: location['cityName'],
            theatre: theatre['theatreName'],
            totalPrice: ticketCount * unitPrice
        }
        sessionStorage.setItem('ticket', JSON.stringify(ticketObject));
        history.push(`/confirm/${movie._id}`);
    }

    return (
        <div className="bookShow">
            <LinkToMovieDetails/>
            <Card className="cardStyle">
                <CardContent>
                    <Title/>
                    <br/>
                    <LocationForm/>
                    <br/><br/>
                    <TheatreForm/>
                    <br/><br/>
                    <LanguageForm/>
                    <br/><br/>
                    <ShowDateForm/>
                    <br/><br/>
                    <ShowTimingForm/>
                    <br/><br/>
                    <TicketForm/>
                    <br/><br/>
                    <Typography style={{textAlign: 'center'}}>
                        Unit Price: Rs. {unitPrice}
                    </Typography>
                    <br/>
                    <Typography style={{textAlign: 'center'}}>
                        Total Price: Rs. {unitPrice * ticketCount}
                    </Typography>
                    <br/><br/>
                    <BookShowButton/>
                </CardContent>
            </Card>
        </div>
    );
}

export default DetailsForm;
