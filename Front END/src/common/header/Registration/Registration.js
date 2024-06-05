import FormControl from "@material-ui/core/FormControl";
import InputLabel from "@material-ui/core/InputLabel";
import Input from "@material-ui/core/Input";
import FormHelperText from "@material-ui/core/FormHelperText";
import Button from "@material-ui/core/Button";
import React, {useState} from "react";
import Typography from "@material-ui/core/Typography";
import {useSelector} from "react-redux";
import useHttp from "../../../hooks/use-http";
import useAuthentication from "../../../hooks/use-authentication";

const Registration = ({closeModalHandler}) => {

    const {loginHandler} = useAuthentication();

    const {error, sendRequest: registerUserHandler} = useHttp();
    const user = useSelector(state => state.userSlice.user);

    const [firstName, setFirstName] = useState(null);
    const [firstNameValidation, setFirstNameValidation] = useState("hide");

    const [lastName, setLastName] = useState(null);
    const [lastNameValidation, setLastNameValidation] = useState("hide");

    const [email, setEmail] = useState(null);
    const [emailValidation, setEmailValidation] = useState("hide");

    const [password, setPassword] = useState(null);
    const [passwordValidation, setPasswordValidation] = useState("hide");

    const [phoneNumber, setPhoneNumber] = useState(null);
    const [phoneNumberValidation, setPhoneNumberValidation] = useState("hide");


    if (error) alert(error);

    const firstNameHandler = (firstName) => {
        setFirstName(firstName);
        setFirstNameValidation("hide");
    }
    const lastNameHandler = (lastName) => {
        setLastName(lastName);
        setLastNameValidation("hide");
    }
    const emailHandler = (email) => {
        setEmail(email);
        setEmailValidation("hide");
    }
    const passwordHandler = (password) => {
        setPassword(password);
        setPasswordValidation("hide");
    }
    const phoneNumberHandler = (phoneNumber) => {
        setPhoneNumber(phoneNumber);
        setPhoneNumberValidation("hide");
    }

    const registerUser = () => {
        if (!firstName)
            setFirstNameValidation("display");
        else if (!lastName)
            setLastNameValidation("display");
        else if (!email)
            setEmailValidation("display");
        else if (!password)
            setPasswordValidation("display");
        else if (!phoneNumber)
            setPhoneNumberValidation("display");
        else {
            const url = `${process.env.REACT_APP_DOMAIN_NAME}/users`;
            const header = {
                'Content-Type': 'application/json'
            }
            const newUser = {
                name: `${firstName} ${lastName}`,
                email,
                password,
                phoneNumber
            }
            const requestConfiguration = {
                url,
                method: 'POST',
                header,
                body: newUser
            }
            const setUserData = user => {
                loginHandler(user);
            }
            registerUserHandler(requestConfiguration, setUserData).then(result => result);
            setTimeout(() => {
                closeModalHandler();
            }, 2000);
        }
    }

    return (
        <Typography component={"div"} style={{padding: 0, textAlign: 'center'}}>
            <FormControl required>
                <InputLabel htmlFor="firstname">First Name</InputLabel>
                <Input id="firstname" type="text" onChange={(event) => firstNameHandler(event.target.value)}/>
                <FormHelperText className={firstNameValidation}>
                    <span className="red">First Name Is Required</span>
                </FormHelperText>
            </FormControl>
            <br/><br/>
            <FormControl required>
                <InputLabel htmlFor="lastname">Last Name</InputLabel>
                <Input id="lastname" type="text" onChange={(event) => lastNameHandler(event.target.value)}/>
                <FormHelperText className={lastNameValidation}>
                    <span className="red">Last Name Is Required</span>
                </FormHelperText>
            </FormControl>
            <br/><br/>
            <FormControl required>
                <InputLabel htmlFor="email">Email</InputLabel>
                <Input id="email" type="text" onChange={(event) => emailHandler(event.target.value)}/>
                <FormHelperText className={emailValidation}>
                    <span className="red">Email Is Required</span>
                </FormHelperText>
            </FormControl>
            <br/><br/>
            <FormControl required>
                <InputLabel htmlFor="registerPassword">Password</InputLabel>
                <Input id="registerPassword" type="password" onChange={(event) => passwordHandler(event.target.value)}/>
                <FormHelperText className={passwordValidation}>
                    <span className="red">Password Is Required</span>
                </FormHelperText>
            </FormControl>
            <br/><br/>
            <FormControl required>
                <InputLabel htmlFor="contact">Phone Number</InputLabel>
                <Input id="contact" type="text" onChange={(event) => phoneNumberHandler(event.target.value)}/>
                <FormHelperText className={phoneNumberValidation}>
                    <span className="red">Phone Number Is Required</span>
                </FormHelperText>
            </FormControl>
            <br/><br/>
            {
                user &&
                <FormControl>
                    <span className="successText">
                        Registration Successful. Please Login!
                    </span>
                </FormControl>
            }
            <br/><br/>
            <Button variant="contained" color="primary" onClick={() => registerUser()}>REGISTER</Button>
        </Typography>
    );
}

export default Registration;
