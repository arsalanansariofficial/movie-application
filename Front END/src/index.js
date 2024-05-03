import React from 'react';
import {createRoot} from 'react-dom/client';
import './index.css';
import Controller from './screens/Controller';
import {Provider} from "react-redux";
import store from "./store/store";
import {BrowserRouter} from "react-router-dom";

const App = () => {
    return (
        <Provider store={store}>
            <BrowserRouter>
                <Controller/>
            </BrowserRouter>
        </Provider>
    );
}

const root = createRoot(document.getElementById('root'));
root.render(<App/>);
