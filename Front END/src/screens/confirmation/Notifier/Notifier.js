import Snackbar from "@material-ui/core/Snackbar";
import green from "@material-ui/core/colors/green";
import CheckCircleIcon from "@material-ui/icons/CheckCircle";
import CloseIcon from "@material-ui/icons/Close";
import IconButton from "@material-ui/core/IconButton";
import {useHistory} from "react-router-dom";

const styles = theme => ({
    close: {
        width: theme.spacing(4),
        height: theme.spacing(4),
    },
    success: {
        color: green[600],
    }
});

const notifierAnchor = {
    vertical: 'top', horizontal: 'center',
}

const notifierMessage = () => {
    return (
        <span id="client-snackbar" style={styles.success}>
            <div className="confirm">
                <div>
                    <CheckCircleIcon/>
                </div>
                <div className="message">Booking Confirmed!</div>
            </div>
        </span>
    );
}

const notifierAction = (closeNotifier) => {
    return ([
        <IconButton key="close" aria-label="Close" color="inherit" style={styles.close} onClick={() => closeNotifier()}>
            <CloseIcon/>
        </IconButton>
    ]);
}

const Notifier = ({displayNotifier, setDisplayNotifier}) => {

    const history = useHistory();
    const closeNotifier = () => {
        setDisplayNotifier(false);
        setTimeout(() => {
            history.push('/');
        }, 2000);
    }

    return (
        <Snackbar
            anchorOrigin={notifierAnchor}
            className="snackbar"
            open={displayNotifier}
            onClose={() => closeNotifier()}
            message={notifierMessage()}
            action={notifierAction(closeNotifier)}
        />
    );
}

export default Notifier;
