import React, {useState} from 'react';
import './Confirmation.css';
import Notifier from "./Notifier/Notifier";
import Invoice from "./Invoice/Invoice";

const Confirmation = () => {

    const ticket = JSON.parse(sessionStorage.getItem('ticket')) || {};

    const [totalPrice, setTotalPrice] = useState(ticket.totalPrice);
    const [displayNotifier, setDisplayNotifier] = useState(false);

    return (
        <div className="Details">
            <Invoice id={ticket.movieId} location={ticket.location} theatre={ticket.theatre} language={ticket.movieShowLanguage}
                     showDate={ticket.showDate} tickets={ticket.numberOfTickets} unitPrice={ticket.ticketPrice}
                     totalPrice={totalPrice} setTotalPrice={setTotalPrice} setDisplayNotifier={setDisplayNotifier}/>
            <Notifier displayNotifier={displayNotifier} setDisplayNotifier={setDisplayNotifier}/>
        </div>
    );

}

export default Confirmation;
