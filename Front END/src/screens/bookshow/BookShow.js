import './BookShow.css';
import DetailsForm from "./DetailsForm/DetailsForm";
import {useEffect} from "react";
import {useDispatch} from "react-redux";
import {headerActions} from "../../store/header-slice";

const BookShow = () => {

    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(headerActions.setDisplayButton(false));
    }, [dispatch]);

    return (
        <div>
            <DetailsForm/>
        </div>
    );
}

export default BookShow;
