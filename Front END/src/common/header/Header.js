import React, {useState} from 'react';
import './Header.css';
import Button from '@material-ui/core/Button';
import logo from '../../assets/logo.svg';
import Modal from 'react-modal';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import {useHistory} from 'react-router-dom';
import Login from "./Login/Login";
import Registration from "./Registration/Registration";
import {useSelector} from "react-redux";
import useAuthentication from "../../hooks/use-authentication";
import useHttp from "../../hooks/use-http";

const ModalStyle = {
    content: {
        top: '50%',
        left: '50%',
        right: 'auto',
        bottom: 'auto',
        marginRight: '-50%',
        transform: 'translate(-50%, -50%)'
    }
};

const Header = () => {

    const history = useHistory();

    const {logoutHandler} = useAuthentication();
    const {error, sendRequest: deactivateSession} = useHttp();

    const movie = useSelector(state => state.selectedMovieSlice.movie);
    const user = useSelector(state => state.userSlice.user);
    const displayBookShowButton = useSelector(state => state.headerSlice.displayButton);

    const [displayModal, setDisplayModal] = useState(false);
    const [tabValue, setTabValue] = useState(0);

    if (error) alert(error);

    const openModalHandler = () => {
        setDisplayModal(true);
    }

    const closeModalHandler = () => {
        setDisplayModal(false);
    }

    const tabChangeHandler = () => {
        tabValue === 0 ? setTabValue(1) : setTabValue(0);
    }

    const bookShowHandler = () => {
        if (user && movie)
            history.push(`/bookShow/${movie._id}`);
        else openModalHandler();
    }

    const logoutUser = () => {
        const url = `${process.env.REACT_APP_DOMAIN_NAME}/users/logout`;
        const header = {
            'Authorization': `Bearer ${user.token}`
        }
        const requestConfigurations = {
            url,
            method: 'POST',
            header
        }
        deactivateSession(requestConfigurations, logoutHandler).then(result => result);
    }

    return (
        <div>
            <header className="app-header">
                <img src={logo} className="app-logo" alt="Movies App Logo"/>
                {
                    !user &&
                    <div className="login-button">
                        <Button variant="contained" color="primary"
                                onClick={() => openModalHandler()}>
                            Login
                        </Button>
                    </div>
                }
                {
                    user &&
                    <div className="login-button">
                        <Button variant="contained" color="secondary"
                                onClick={() => logoutUser()}>
                            Logout
                        </Button>
                    </div>
                }
                {
                    displayBookShowButton &&
                    <div className="bookShowButton">
                        <Button variant="contained" color="default"
                                onClick={() => bookShowHandler()}>
                            Book Show
                        </Button>
                    </div>
                }
            </header>
            <Modal style={ModalStyle} ariaHideApp={false} isOpen={displayModal}
                   onRequestClose={() => closeModalHandler()}>
                <Tabs className="tabs" value={tabValue} onChange={() => tabChangeHandler()}>
                    <Tab label="Login"/>
                    <Tab label="Register"/>
                </Tabs>
                {
                    tabValue === 0 &&
                    <Login closeModalHandler={closeModalHandler}/>
                }

                {
                    tabValue === 1 &&
                    <Registration user={user} closeModalHandler={closeModalHandler}/>
                }
            </Modal>
        </div>
    );
}

export default Header;
