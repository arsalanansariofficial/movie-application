import FormControl from "@material-ui/core/FormControl";
import InputLabel from "@material-ui/core/InputLabel";
import Input from "@material-ui/core/Input";
import FormHelperText from "@material-ui/core/FormHelperText";
import Button from "@material-ui/core/Button";
import React, {useState} from "react";
import Typography from "@material-ui/core/Typography";
import {useSelector} from "react-redux";
import useAuthentication from "../../../hooks/use-authentication";
import useHttp from "../../../hooks/use-http";
import {domainName} from "../../../config/dev";

const Login = ({closeModalHandler}) => {

    const {loginHandler} = useAuthentication();
    const {error, sendRequest: verifyUser} = useHttp();

    const user = useSelector(state => state.userSlice.user);

    const [userName, setUserName] = useState(null);
    const [userNameValidation, setUserNameValidation] = useState("hide");
    const [password, setPassword] = useState(null);
    const [passwordValidation, setPasswordValidation] = useState("hide");

    const userNameHandler = (userName) => {
        setUserName(userName);
        setUserNameValidation("hide");
    }

    const passwordHandler = (password) => {
        setPassword(password);
        setPasswordValidation("hide");
    }

    if (error) alert(error);

    const loginUserHandler = () => {
        if (!userName)
            setUserNameValidation("display");
        else if (!password)
            setPasswordValidation("display");
        else {
            const url = `${domainName}/users/login`;
            const user = {
                email: userName,
                password
            }
            const header = {
                'Content-Type': 'application/json'
            }
            const requestConfigurations = {
                url,
                method: 'POST',
                header,
                body: user
            }
            verifyUser(requestConfigurations, loginHandler).then(result => result);
            setTimeout(() => {
                closeModalHandler();
            }, 2000);
        }
    }

    return (
        <Typography component={"div"} style={{padding: 0, textAlign: 'center'}}>
            <FormControl required>
                <InputLabel htmlFor="username">Username</InputLabel>
                <Input id="username" type="text" onChange={(event) => userNameHandler(event.target.value)}/>
                <FormHelperText className={userNameValidation}>
                    <span className="red">Username Is Required</span>
                </FormHelperText>
            </FormControl>
            <br/><br/>
            <FormControl required>
                <InputLabel htmlFor="loginPassword">Password</InputLabel>
                <Input id="loginPassword" type="password" onChange={(event) => passwordHandler(event.target.value)}/>
                <FormHelperText className={passwordValidation}>
                    <span className="red">Password Is Required</span>
                </FormHelperText>
            </FormControl>
            <br/><br/>
            {
                user &&
                <FormControl>
                    <span className="successText">
                        Login Successful!
                    </span>
                </FormControl>
            }
            <br/><br/>
            <Button variant="contained" color="primary" onClick={() => loginUserHandler()}>LOGIN</Button>
        </Typography>
    );
}

export default Login;
