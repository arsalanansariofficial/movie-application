const allowResponse = (request, response, next) => {
    response.header("Access-Control-Allow-Origin", "*");
    response.header("Access-Control-Allow-Headers", "*");
    next();
}

module.exports = {allowResponse};
