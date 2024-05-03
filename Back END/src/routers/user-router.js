const express = require("express");
const userRouter = new express.Router();
const {UserModel} = require("../models/user");
const {authentication} = require("../middleware/authentication");

userRouter.post('/users', async (request, response) => {
    const user = new UserModel(request.body);
    try {
        const token = await user['generateAuthenticationToken']();
        response.status(201).send({user, token});
    } catch (error) {
        response.status(500).send(error);
    }
});

userRouter.post('/users/login', async (request, response) => {
    try {
        const user = await UserModel['findByCredentials'](request.body.email, request.body.password);
        const token = await user['generateAuthenticationToken']();
        response.status(200).send({user, token});
    } catch (error) {
        if (error.message === 'Failed to login') {
            const errorResponse = {
                code: 400, message: error.message
            }
            response.status(400).send(errorResponse);
        } else response.status(500).send(error);
    }
});

userRouter.post('/users/logout', authentication, async (request, response) => {
    try {
        request.user.tokens = request.user.tokens.filter(({token}) => {
            return token !== request.token;
        });
        await request.user.save();
        response.status(200).send({code: 200, message: 'Session deactivated'});
    } catch (error) {
        response.status(500).send(error);
    }
});

userRouter.post('/users/logoutAll', authentication, async (request, response) => {
    try {
        request.user.tokens = [];
        await request.user.save();
        response.status(200).send({code: 200, message: 'Sessions deactivated'});
    } catch (error) {
        response.status(500).send(error);
    }
});

module.exports = userRouter;
