import {Link} from "react-router-dom";
import Typography from "@material-ui/core/Typography";
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import FormControl from "@material-ui/core/FormControl";
import InputLabel from "@material-ui/core/InputLabel";
import Input from "@material-ui/core/Input";
import Button from "@material-ui/core/Button";
import React, {useState} from "react";
import {useSelector} from "react-redux";
import useHttp from "../../../hooks/use-http";

const Invoice = ({id, location, theatre, language, showDate, tickets, unitPrice, totalPrice, setTotalPrice, setDisplayNotifier}) => {

    const ticketObject = JSON.parse(sessionStorage.getItem('ticket'));
    const user = useSelector(state => state.userSlice.user);

    const {error, sendRequest: getTicket} = useHttp();

    if (error) alert(error);

    const [couponText, setCouponText] = useState(null);

    const couponTextHandler = (couponText) => {
        setCouponText(couponText);
    }

    const applyCoupon = () => {
        if (couponText === "DISCOUNT")
            setTotalPrice(totalPrice - 100);
    }

    const confirmBooking = () => {
        const url = `${process.env.REACT_APP_DOMAIN_NAME}/tickets`;
        const header = {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${user.token}`
        }
        const requestConfigurations = {
            url,
            method: 'POST',
            header,
            body: ticketObject
        }
        const setTicketData = () => {
            setDisplayNotifier(true);
        }
        getTicket(requestConfigurations, setTicketData).then(result => result);
    }

    return (
        <div className="confirmation marginTop16">
            <div>
                <Link to={`/bookShow/${id}`}>
                    <Typography className="back">
                        &#60; Back to Book Show
                    </Typography>
                </Link>
                <br/>
                <Card className="cardStyle">
                    <CardContent>
                        <Typography variant="h4" component="h2">
                            SUMMARY
                        </Typography>
                        <br/>
                        <div className="coupon-container">
                            <div className="confirmLeft">
                                <Typography>Location:</Typography>
                            </div>
                            <div>
                                <Typography>{location}</Typography>
                            </div>
                        </div>
                        <br/>
                        <div className="coupon-container">
                            <div className="confirmLeft">
                                <Typography>Theatre:</Typography>
                            </div>
                            <div>
                                <Typography>{theatre}</Typography>
                            </div>
                        </div>
                        <br/>
                        <div className="coupon-container">
                            <div className="confirmLeft">
                                <Typography>Language:</Typography>
                            </div>
                            <div>
                                <Typography>{language}</Typography>
                            </div>
                        </div>
                        <br/>
                        <div className="coupon-container">
                            <div className="confirmLeft">
                                <Typography>Show Date:</Typography>
                            </div>
                            <div>
                                <Typography>{showDate}</Typography>
                            </div>
                        </div>
                        <br/>
                        <div className="coupon-container">
                            <div className="confirmLeft">
                                <Typography>Tickets:</Typography>
                            </div>
                            <div>
                                <Typography>{tickets}</Typography>
                            </div>
                        </div>
                        <br/>
                        <div className="coupon-container">
                            <div className="confirmLeft">
                                <Typography>Unit Price:</Typography>
                            </div>
                            <div>
                                <Typography>{unitPrice}</Typography>
                            </div>
                        </div>
                        <br/>
                        <div className="coupon-container">
                            <div>
                                <FormControl className="formControl">
                                    <InputLabel htmlFor="coupon">
                                        <Typography>Coupon Code</Typography>
                                    </InputLabel>
                                    <Input id="coupon" onChange={(event) => couponTextHandler(event.target.value)}/>
                                </FormControl>
                            </div>
                            <div className="marginApply">
                                <Button
                                    variant="contained"
                                    onClick={() => applyCoupon()}
                                    color="primary">
                                    Apply
                                </Button>
                            </div>
                        </div>
                        <br/><br/>
                        <div className="coupon-container">
                            <div className="confirmLeft">
                                <span className="bold">Total Price:</span>
                            </div>
                            <div>{totalPrice}</div>
                        </div>
                        <br/>
                        <Button
                            variant="contained"
                            onClick={() => confirmBooking()}
                            color="primary">
                            Confirm Booking
                        </Button>
                    </CardContent>
                </Card>
            </div>
        </div>
    );
}

export default Invoice;
