import {useCallback, useEffect} from "react";
import {useDispatch, useSelector} from "react-redux";
import {userActions} from "../store/user-slice";

const calculateExpirationTime = (expirationDate) => {
    const currentTime = new Date().getTime();
    const expirationTime = new Date(expirationDate).getTime();
    return expirationTime - currentTime;
}

const setExpirationDate = () => new Date(new Date().getTime() + 60 * 60 * 1000);

let logoutTimer;

const useAuthentication = () => {
    const dispatch = useDispatch();
    const user = useSelector(state => state.userSlice.user);

    const loginHandler = (user) => {
        user.expirationDate = setExpirationDate().toISOString();
        sessionStorage.setItem('user', JSON.stringify(user));
        dispatch(userActions.login(user));
        const expirationTime = calculateExpirationTime(user.expirationDate);
        logoutTimer = setTimeout(logoutHandler, expirationTime);
    }

    const logoutHandler = useCallback(() => {
        if (logoutTimer) {
            sessionStorage.removeItem('user');
            clearTimeout(logoutTimer);
            logoutTimer = undefined;
            dispatch(userActions.logout());
        }
    }, [dispatch]);

    useEffect(() => {
        if (user) {
            const expirationTime = calculateExpirationTime(user.expirationDate);
            logoutTimer = setTimeout(logoutHandler, expirationTime);
        }
    }, [user, logoutHandler, dispatch]);

    return {loginHandler, logoutHandler};
}

export default useAuthentication;
