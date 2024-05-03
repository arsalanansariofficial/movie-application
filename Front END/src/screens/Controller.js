import React from 'react';
import Home from '../screens/home/Home';
import Details from '../screens/details/Details';
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom';
import BookShow from '../screens/bookshow/BookShow';
import Confirmation from '../screens/confirmation/Confirmation';
import Header from "../common/header/Header";
import {useSelector} from "react-redux";

const Controller = () => {

    const user = useSelector(state => state.userSlice.user);
    const movie = useSelector(state => state.selectedMovieSlice.movie);

    return (
        <Router>
            <Header/>
            <div className="main-container">
                <Route exact path='/' component={Home}/>
                <Route path='/movieDetails/:id' component={Details}/>
                <Route path='/bookShow/:id'>
                    {user && movie && <BookShow/>}
                    {user && !movie && <Redirect to='/'/>}
                    {!user && <Redirect to='/'/>}
                </Route>
                <Route path='/confirm/:id'>
                    {user && movie && <Confirmation/>}
                    {user && !movie && <Redirect to='/'/>}
                    {!user && <Redirect to='/'/>}
                </Route>
            </div>
        </Router>
    );
}

export default Controller;
